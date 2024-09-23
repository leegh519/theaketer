# 자바버전 설정
FROM openjdk:17-ea-11-jdk-slim

# 컨테이너 내부에서 작업 디렉토리 설정
WORKDIR /app

# WAR 파일을 컨테이너로 복사
COPY target/*.jar /app/theaketing.jar

# 실행 파일 설정
CMD ["java", "-jar", "/app/theaketing.jar"]


#타임존 설정
ENV TZ=Asia/Seoul
