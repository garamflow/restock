# 베이스 이미지 선택
FROM openjdk:17-jdk

# 애플리케이션 jar 파일을 복사
COPY build/libs/*SNAPSHOT.jar /app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]