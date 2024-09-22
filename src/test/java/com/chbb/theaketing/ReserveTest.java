package com.chbb.theaketing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chbb.theaketing.feature.reservation.dto.ReservationDto;
import com.chbb.theaketing.feature.reservation.service.ReservationService;

@SpringBootTest
public class ReserveTest {
    @Autowired
    private ReservationService reservationService;

    @Test
    public void testConcurrentReserve() throws InterruptedException {
        int numberOfThreads = 100; // 동시에 실행할 스레드 수
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    // ReservationReq 객체 생성
                    ReservationDto.ReservationReq req = new ReservationDto.ReservationReq(1L, 39L, 1L);

                    // 예매 서비스 호출
//                    reservationService.test(req, (finalI+1L));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown(); // 완료 후 카운트 감소
                }
            });
        }

        latch.await(); // 모든 스레드가 완료될 때까지 대기
        executor.shutdown();
        Thread.sleep(1 * 45 * 1000);

    }
}
