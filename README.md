# VYBZ Like Service

VYBZ 플랫폼의 좋아요 기능을 담당하는 마이크로서비스입니다.

## 📋 목차

-   [개요](#개요)
-   [기술 스택](#기술-스택)
-   [주요 기능](#주요-기능)
-   [프로젝트 구조](#프로젝트-구조)
-   [API 문서](#api-문서)
-   [설치 및 실행](#설치-및-실행)
-   [환경 설정](#환경-설정)
-   [좋아요 시스템](#좋아요-시스템)
-   [이벤트 처리](#이벤트-처리)

## 🎯 개요

VYBZ Like Service는 다음과 같은 기능을 제공합니다:

-   **피드 좋아요**: 피드(릴스)에 대한 좋아요 토글 기능
-   **댓글 좋아요**: 댓글에 대한 좋아요 토글 기능
-   **라이브 좋아요**: 라이브 스트림에 대한 좋아요 기능
-   **이벤트 발행**: Kafka를 통한 좋아요 수 변경 이벤트 발행
-   **데이터 저장**: MongoDB를 통한 좋아요 데이터 저장
-   **서비스 디스커버리**: Eureka Client를 통한 서비스 등록

## 🛠 기술 스택

### Backend

![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

### Infra

![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### 협업

![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

### Database & Cache

-   **MongoDB**: 좋아요 데이터 저장
-   **Redis**: 캐싱 및 세션 관리

### Message Queue

-   **Apache Kafka**: 비동기 이벤트 발행

### Documentation

-   **Swagger/OpenAPI 3.0**: API 문서화

### Build & Deploy

-   **Gradle**: 빌드 도구
-   **Docker**: 컨테이너화

## 🚀 주요 기능

### 1. 좋아요 시스템

-   **피드 좋아요**: 피드(릴스)에 대한 좋아요 토글 기능
-   **댓글 좋아요**: 댓글에 대한 좋아요 토글 기능
-   **라이브 좋아요**: 라이브 스트림에 대한 좋아요 기능
-   **중복 방지**: 사용자별 좋아요 중복 방지
-   **실시간 처리**: 트랜잭션 기반 실시간 좋아요 처리

### 2. 좋아요 타입 지원

-   **피드 타입**: 릴스, 일반 피드 등 다양한 피드 타입 지원
-   **작성자 타입**: 버스커, 일반 사용자 등 작성자 타입 구분
-   **좋아요 상태**: 좋아요 추가/제거 상태 관리

### 3. 이벤트 처리

-   **좋아요 수 변경 이벤트**: 좋아요 추가/제거 시 이벤트 발행
-   **델타 기반 업데이트**: 좋아요 수 증감을 델타로 처리

## 📁 프로젝트 구조

```
src/main/java/back/vybz/like_service/
├── common/                    # 공통 모듈
│   ├── config/               # 설정 클래스들
│   │   ├── MongoConfig.java
│   │   ├── ObjectMapperConfig.java
│   │   └── SwaggerConfig.java
│   ├── entity/               # 공통 엔티티
│   │   ├── BaseResponseEntity.java
│   │   └── BaseResponseStatus.java
│   ├── exception/            # 예외 처리
│   │   ├── AsyncExceptionHandler.java
│   │   ├── BaseException.java
│   │   ├── BaseExceptionHandler.java
│   │   ├── BaseExceptionHandlerFilter.java
│   │   └── BaseResponseStatus.java
│   └── util/                 # 유틸리티
│       └── CursorPage.java
├── kafka/                    # Kafka 이벤트 처리
│   ├── config/               # Kafka 설정
│   │   ├── CommentLikeDeltaEventKafkaConfig.java
│   │   ├── CommonKafkaProducerConfig.java
│   │   ├── FeedLikeDeltaEventConfig.java
│   │   └── LiveLikeDeltaEventKafkaConfig.java
│   ├── event/                # 이벤트 모델
│   │   ├── CommentLikeDeltaEvent.java
│   │   ├── FeedLikeDeltaEvent.java
│   │   └── LiveLikeDeltaEvent.java
│   └── producer/             # 이벤트 프로듀서
│       ├── CommentLikeDeltaEventKafkaProducer.java
│       ├── FeedLikeDeltaEventProducer.java
│       └── LiveLikeDeltaEventProducer.java
├── like/                     # 좋아요 도메인
│   ├── application/          # 좋아요 서비스 로직
│   │   └── service/
│   │       ├── CommentLikeService.java
│   │       ├── CommentLikeServiceImpl.java
│   │       ├── FeedLikeService.java
│   │       ├── FeedLikeServiceImpl.java
│   │       ├── LiveLikeService.java
│   │       └── LiveLikeServiceImpl.java
│   ├── domain/               # 좋아요 도메인 모델
│   │   └── mongodb/
│   │       ├── CommentLike.java
│   │       ├── FeedLike.java
│   │       ├── FeedType.java
│   │       ├── LiveLike.java
│   │       └── WriterType.java
│   ├── dto/                  # 좋아요 DTO
│   │   ├── request/
│   │   │   ├── RequestCommentLikeDto.java
│   │   │   ├── RequestFeedLikeDto.java
│   │   │   └── RequestLiveLikeDto.java
│   │   └── response/
│   │       ├── ResponseCommentLikeDto.java
│   │       └── ResponseFeedLikeDto.java
│   ├── infrastructure/       # 좋아요 리포지토리
│   │   ├── CommentLikeRepository.java
│   │   ├── FeedLikeRepository.java
│   │   └── LiveLikeRepository.java
│   ├── presentation/         # 좋아요 컨트롤러
│   │   └── LikeController.java
│   └── vo/                   # 좋아요 VO
│       ├── request/
│       │   ├── RequestCommentLikeVo.java
│       │   ├── RequestFeedLikeVo.java
│       │   └── RequestLiveLikeVo.java
│       └── response/
│           ├── ResponseCommentLikeVo.java
│           └── ResponseFeedLikeVo.java
└── LikeServiceApplication.java
```

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

-   **URL**: `http://localhost:8000/like-service/swagger-ui/index.html`
-   **API 그룹**: LIKE-SERVICE

### 주요 API 엔드포인트

#### 좋아요 API

-   `POST /api/v1/like/feed` - 피드 좋아요 토글
-   `POST /api/v1/like/comment` - 댓글 좋아요 토글
-   `POST /api/v1/like/live` - 라이브 스트림 좋아요

### API 요청/응답 예시

#### 피드 좋아요 토글 요청

```json
POST /api/v1/like/feed
{
    "feedId": "feed-uuid-123",
    "feedType": "REELS",
    "likerUuid": "user-uuid-456"
}
```

#### 피드 좋아요 토글 응답

```json
{
    "status": "SUCCESS",
    "message": "좋아요 토글 성공",
    "data": {
        "feedId": "feed-uuid-123",
        "likerUuid": "user-uuid-456",
        "liked": true,
        "createdAt": "2024-01-01T12:00:00Z"
    }
}
```

#### 댓글 좋아요 토글 요청

```json
POST /api/v1/like/comment
{
    "commentId": "comment-uuid-789",
    "likerUuid": "user-uuid-456"
}
```

#### 라이브 스트림 좋아요 요청

```json
POST /api/v1/like/live
{
    "liveId": "live-uuid-101",
    "likerUuid": "user-uuid-456"
}
```

## 🚀 설치 및 실행

### 1. 사전 요구사항

-   Java 17
-   Gradle 8.4+
-   Docker (선택사항)
-   MongoDB 6.0+
-   Redis 7.0+
-   Kafka 3.0+

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-like

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-like .

# Docker 컨테이너 실행
docker run -p 8000:8000 vybz-like
```

## ⚙️ 환경 설정

### 주요 설정 파일

-   `application.yml`: 기본 설정

### 환경 변수

```yaml
# MongoDB 설정
spring:
  data:
    mongodb:
      uri: mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOST}:${MONGO_PORT}/${MONGO_DATABASE}?authSource=admin&replicaSet=myReplicaSet
      auto-index-creation: true

  # Redis 설정
    redis:
      host: ${REDIS_HOST}
      port: ${REDIS_PORT}
      password: ${REDIS_PASSWORD}

  # Kafka 설정
  kafka:
    bootstrap-servers: <탄력적 IP>:10000,<탄력적 IP>:10001,<탄력적 IP>:10002
```

## 💖 좋아요 시스템

### 1. 좋아요 토글 로직

```java
@Transactional
public ResponseFeedLikeDto toggleFeedLike(RequestFeedLikeDto requestFeedLikeDto) {
    String feedId = requestFeedLikeDto.getFeedId();
    String likerUuid = requestFeedLikeDto.getLikerUuid();

    Optional<FeedLike> existingLike = feedLikeRepository.findByFeedIdAndLikerUuid(feedId, likerUuid);

    boolean liked;

    if (existingLike.isPresent()) {
        // 좋아요 제거
        feedLikeRepository.deleteById(existingLike.get().getId());
        liked = false;
        
        // 좋아요 수 감소 이벤트 발행
        feedLikeDeltaEventProducer.send(
            FeedLikeDeltaEvent.builder()
                .feedId(feedId)
                .feedType(requestFeedLikeDto.getFeedType())
                .delta(-1)
                .build()
        );
    } else {
        // 좋아요 추가
        FeedLike newLike = FeedLike.builder()
            .feedId(feedId)
            .feedType(requestFeedLikeDto.getFeedType())
            .likerUuid(likerUuid)
            .build();
        
        feedLikeRepository.save(newLike);
        liked = true;
        
        // 좋아요 수 증가 이벤트 발행
        feedLikeDeltaEventProducer.send(
            FeedLikeDeltaEvent.builder()
                .feedId(feedId)
                .feedType(requestFeedLikeDto.getFeedType())
                .delta(1)
                .build()
        );
    }
    
    return ResponseFeedLikeDto.builder()
        .feedId(feedId)
        .likerUuid(likerUuid)
        .liked(liked)
        .createdAt(Instant.now())
        .build();
}
```

## 📡 이벤트 처리

### Kafka 이벤트

#### 발행 이벤트

-   **FeedLikeDeltaEvent**: 피드 좋아요 수 변경 이벤트
    -   `feedId`: 피드 ID
    -   `feedType`: 피드 타입
    -   `delta`: 좋아요 수 증감값 (+1 또는 -1)

-   **CommentLikeDeltaEvent**: 댓글 좋아요 수 변경 이벤트
    -   `commentId`: 댓글 ID
    -   `delta`: 좋아요 수 증감값

-   **LiveLikeDeltaEvent**: 라이브 좋아요 수 변경 이벤트
    -   `liveId`: 라이브 ID
    -   `delta`: 좋아요 수 증감값

### 이벤트 프로듀서

-   `FeedLikeDeltaEventProducer`: 피드 좋아요 이벤트 발행
-   `CommentLikeDeltaEventKafkaProducer`: 댓글 좋아요 이벤트 발행
-   `LiveLikeDeltaEventProducer`: 라이브 좋아요 이벤트 발행

### Kafka 토픽

-   `feed-delta-count`: 피드 좋아요 수 변경 이벤트 토픽
-   `comment-delta-count`: 댓글 좋아요 수 변경 이벤트 토픽
-   `live-delta-count`: 라이브 좋아요 수 변경 이벤트 토픽

### 이벤트 처리 시점

-   **좋아요 추가**: 사용자가 좋아요를 누를 때 +1 이벤트 발행
-   **좋아요 제거**: 사용자가 좋아요를 취소할 때 -1 이벤트 발행

## 🏗 아키텍처

### 도메인 주도 설계 (DDD)

-   **Domain Layer**: 좋아요 도메인 모델과 비즈니스 로직
-   **Application Layer**: 좋아요 서비스 로직과 유스케이스
-   **Infrastructure Layer**: MongoDB 접근과 외부 시스템 연동
-   **Presentation Layer**: REST API 엔드포인트

### 마이크로서비스 패턴

-   **Service Discovery**: Eureka Client를 통한 서비스 등록
-   **Event-Driven**: Kafka를 통한 비동기 이벤트 발행
-   **Stateless**: 상태 없는 서비스 설계

### 데이터베이스 설계

-   **MongoDB**: 좋아요 데이터 저장
-   **복합 인덱스**: 피드ID + 사용자ID 조합으로 중복 방지
-   **Redis**: 캐싱 및 세션 관리

### 이벤트 기반 아키텍처

-   **이벤트 발행**: 좋아요 상태 변경 시 이벤트 발행
-   **비동기 처리**: Kafka를 통한 비동기 이벤트 처리
-   **데이터 일관성**: 이벤트를 통한 다른 서비스와의 데이터 동기화

## 🔧 개발 가이드

### 코드 컨벤션

-   **패키지 구조**: 도메인별 계층 분리
-   **네이밍**: 명확하고 일관된 네이밍 규칙
-   **예외 처리**: BaseException을 통한 통일된 예외 처리
-   **로깅**: Slf4j를 통한 구조화된 로깅

### 테스트

```bash
# 단위 테스트 실행
./gradlew test

# 통합 테스트 실행
./gradlew integrationTest
```

### 예외 처리

```java
// 좋아요 관련 예외
public enum BaseResponseStatus {
    LIKE_FAIL("좋아요 처리에 실패했습니다."),
    DUPLICATE_LIKE("이미 좋아요를 누른 게시물입니다."),
    INVALID_FEED_ID("유효하지 않은 피드 ID입니다."),
    INVALID_USER_ID("유효하지 않은 사용자 ID입니다.");
}
```

### 성능 최적화

#### MongoDB 최적화

-   **복합 인덱스**: 피드ID + 사용자ID 조합으로 빠른 조회
-   **트랜잭션**: 좋아요 토글 시 데이터 일관성 보장
-   **중복 방지**: 유니크 인덱스로 중복 좋아요 방지

#### Kafka 최적화

-   **배치 처리**: 메시지 배치 처리로 성능 향상
-   **비동기 발행**: CompletableFuture를 통한 비동기 이벤트 발행
-   **에러 처리**: 이벤트 발행 실패 시 로깅 및 재시도

## 📊 모니터링

### 로깅

-   **애플리케이션 로그**: Spring Boot 로깅
-   **좋아요 로그**: 좋아요 작업 로깅
-   **Kafka 로그**: 이벤트 발행 로깅
-   **MongoDB 로그**: 데이터베이스 작업 로깅

### 메트릭

-   **좋아요 처리량**: 초당 좋아요 처리 수
-   **응답 시간**: API 응답 시간
-   **에러율**: 에러 발생률
-   **Kafka 메시지**: 이벤트 발행 성공률

### 알림

-   **좋아요 실패**: 좋아요 처리 실패 알림
-   **MongoDB 오류**: 데이터베이스 연결 오류 알림
-   **Kafka 오류**: 메시지 발행 실패 알림

## 🚨 트러블슈팅

### 일반적인 문제

#### MongoDB 연결 실패

```bash
# MongoDB 연결 확인
mongo --host <탄력적 IP> --port 27020 -u vybz -p <비밀번호> --authenticationDatabase admin

# 컬렉션 상태 확인
db.feed_like.getIndexes()
```

#### Kafka 연결 실패

```bash
# Kafka 브로커 상태 확인
kafka-topics.sh --bootstrap-server <탄력적 IP>:10000 --list

# 토픽 상세 정보 확인
kafka-topics.sh --bootstrap-server <탄력적 IP>:10000 --describe --topic feed-delta-count
```

#### Redis 연결 실패

```bash
# Redis 연결 확인
redis-cli -h <탄력적 IP>  -p 63379 -a <비밀번호>

# Redis 상태 확인
redis-cli -h <탄력적 IP>  -p 63379 -a <비밀번호> ping
```

#### Eureka 연결 실패

```bash
# Eureka 서버 상태 확인
curl http://eureka:8761/eureka/apps/like-service

# 서비스 등록 확인
curl http://eureka:8761/eureka/apps
```

### 로그 확인

```bash
# 애플리케이션 로그 확인
tail -f logs/application.log

# 에러 로그 확인
grep "ERROR" logs/application.log

# Kafka 로그 확인
grep "Kafka" logs/application.log
```

### 성능 문제 해결

#### 좋아요 처리 지연

1. **MongoDB 인덱스 확인**: 복합 인덱스가 제대로 생성되었는지 확인
2. **Kafka 프로듀서 설정**: 배치 크기와 지연 시간 조정
3. **Redis 캐싱**: 자주 조회되는 데이터 캐싱

#### 메모리 사용량 증가

1. **MongoDB 연결 풀**: 연결 풀 크기 조정
2. **Redis 메모리**: Redis 메모리 사용량 모니터링
3. **JVM 힙 크기**: JVM 힙 크기 조정

## 📝 라이선스

이 프로젝트는 VYBZ 팀의 내부 프로젝트입니다.

## 👥 팀

-   **개발팀**: VYBZ Backend Team

---

**VYBZ Like Service** - MongoDB 기반 좋아요 서비스
