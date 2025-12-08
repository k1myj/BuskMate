# 1. Java 17이 들어있는 가벼운 이미지 사용
FROM eclipse-temurin:21-jdk-alpine

# 2. 작업 디렉터리
WORKDIR /app

# 3. 빌드된 jar를 이미지 안으로 복사
#   - GitHub Actions에서 먼저 ./gradlew bootJar 돌리고
#   - build/libs 안에 생긴 jar를 복사하는 방식
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. 컨테이너에서 열어줄 포트
EXPOSE 8080

# 5. 앱 실행 명령
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
