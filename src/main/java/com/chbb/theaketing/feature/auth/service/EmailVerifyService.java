package com.chbb.theaketing.feature.auth.service;

import java.util.concurrent.TimeUnit;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.chbb.theaketing.core.util.AuthCodeGenerator;
import com.chbb.theaketing.feature.auth.dto.AuthDto;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.redis.RedisRepository;
import com.chbb.theaketing.feature.mail.dto.MailDto;
import com.chbb.theaketing.feature.mail.service.MailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerifyService {

    private final MailService mailService;

    private final RedisRepository redisRepository;

    private static final long EXPIRE_DURATION = 5; // 만료 시간: 5분

    public void send(String email) throws Exception {
        String code = AuthCodeGenerator.generate(6);

        sendAuthCode(email, code);
        saveAuthCode(email, code);
        log.info("인증코드: " + getAuthCode(email));
    }

    public void sendAuthCode(String email, String code) throws Exception {
        MailDto mail = MailDto.builder()
                .address(email)
                .title("티케터 인증코드")
                .content("티케터 인증코드는 " + code + "입니다.")
                .build();
        try {
            mailService.sendMail(mail);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e.getCause());
            throw new TheaketingException(ErrorCode.EMAIL_SEND_ERROR);
        } catch (MailException e) {
            log.error(e.getMessage(), e.getCause());
            throw new TheaketingException(ErrorCode.EMAIL_SEND_ERROR);
        }
    }

    public void check(AuthDto.EmailAuthCheckReq req) throws Exception {
        String code = getAuthCode(req.getEmail());
        if (code == null) {
            throw new TheaketingException(ErrorCode.CODE_EXPIRED);
        }
        if (!req.getAuthNumber().equals(code)) {
            throw new TheaketingException(ErrorCode.WRONG_CODE);
        }
        saveAuthStatus(req.getEmail());
    }

    // 이메일과 인증번호를 Redis에 저장
    private void saveAuthCode(String email, String code) {
        redisRepository.setValue(email, code, EXPIRE_DURATION, TimeUnit.MINUTES);
    }

    // 이메일과 인증상태를 Redis에 저장
    private void saveAuthStatus(String email) {
        redisRepository.setValue(email, "true", 1, TimeUnit.HOURS);
    }

    // Redis에서 인증번호 조회
    private String getAuthCode(String email) {
        return redisRepository.getValue(email);
    }

}
