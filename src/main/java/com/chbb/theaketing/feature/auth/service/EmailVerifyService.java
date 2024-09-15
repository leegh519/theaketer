package com.chbb.theaketing.feature.auth.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.chbb.theaketing.core.util.AuthCodeGenerator;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
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

    private final RedisTemplate<String, String> redisTemplate;

    private static final long EXPIRE_DURATION = 5; // 만료 시간: 5분

    public void send(String email) throws TheaketingException {
        String code = AuthCodeGenerator.generate(6);

        sendAuthCode(email, code);
        saveAuthCode(email, code);
        log.info(getAuthCode(email));
    }

    public void sendAuthCode(String email, String code) throws TheaketingException {
        MailDto mail = MailDto.builder()
                .address(email)
                .title("티케터 인증")
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

    // 이메일과 인증번호를 Redis에 저장
    private void saveAuthCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, EXPIRE_DURATION, TimeUnit.MINUTES);
    }

    // Redis에서 인증번호 조회
    private String getAuthCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    // 인증번호 삭제
    private void deleteAuthCode(String email) {
        redisTemplate.delete(email);
    }
}
