# 🎯 Like Service

> **좋아요 기능을 담당하는 마이크로서비스**

좋아요(Like) 기능을 전담하는 Spring Boot 기반의 마이크로서비스입니다. 피드, 댓글, 라이브 스트림에 대한 좋아요 기능을 제공하며, Kafka를 통한 이벤트 발행과 MongoDB를 통한 데이터 저장을 지원합니다.

---

## 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.4.5 |
| **Database** | MongoDB |
| **Message Queue** | Apache Kafka |
| **Build Tool** | Gradle |

---

## 📋 서비스 목록

| 서비스명 | 설명 | 언어 | 상태 |
|----------|------|------|------|
| **Like Service** | 피드/댓글/라이브 좋아요 기능 | Java | ✅ Active |
| **Aggregation Service** | 좋아요 집계 및 통계 | Java | 🔄 In Progress |
| **Comment Service** | 댓글 관리 서비스 | Java | 🔄 In Progress |
| **Live Service** | 라이브 스트림 관리 | Java | 🔄 In Progress |
| **Feed Service** | 피드 관리 서비스 | Java | 🔄 In Progress |

---

## 📌 Architecture Diagram

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Like Service  │───▶│ Aggregation      │───▶│ Comment Service │
│                 │    │ Service          │    │                 │
│ • Feed Like     │    │ • Like Count     │    │ • Comment CRUD  │
│ • Comment Like  │    │ • Statistics     │    │ • Reply         │
│ • Live Like     │    │ • Analytics      │    │ • Moderation    │
└─────────────────┘    └──────────────────┘    └─────────────────┘
         │                       │                       │
         │                       ▼                       │
         │              ┌─────────────────┐              │
         │              │ Live Service    │              │
         │              │                 │              │
         │              │ • Live Stream   │              │
         │              │ • Broadcasting  │              │
         │              │ • Chat          │              │
         │              └─────────────────┘              │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Feed Service  │    │   Feed Service  │    │   Feed Service  │
│                 │    │                 │    │                 │
│ • Feed CRUD     │    │ • Feed CRUD     │    │ • Feed CRUD     │
│ • Content Mgmt  │    │ • Content Mgmt  │    │ • Content Mgmt  │
│ • Timeline      │    │ • Timeline      │    │ • Timeline      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- MongoDB
- Apache Kafka

### Local Development

```bash
# 1. 프로젝트 클론
git clone <repository-url>
cd like_service

# 2. 의존성 설치
./gradlew build

# 3. 애플리케이션 실행
./gradlew bootRun
```

---

## 📁 Project Structure

```
like_service/
├── 📄 build.gradle                 # Gradle 빌드 설정
├── 📄 README.md                    # 프로젝트 문서
├── 📁 gradle/
│   └── 📁 wrapper/                 # Gradle Wrapper
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/
│   │   │   └── 📁 back/vybz/like_service/
│   │   │       ├── 📁 common/              # 공통 설정 및 유틸리티
│   │   │       │   ├── 📁 config/          # 설정 클래스들
│   │   │       │   ├── 📁 entity/          # 공통 엔티티
│   │   │       │   ├── 📁 exception/       # 예외 처리
│   │   │       │   └── 📁 util/            # 유틸리티 클래스
│   │   │       ├── 📁 kafka/               # Kafka 이벤트 및 프로듀서
│   │   │       │   ├── 📁 config/          # Kafka 설정
│   │   │       │   ├── 📁 event/           # 이벤트 클래스
│   │   │       │   └── 📁 producer/        # 이벤트 프로듀서
│   │   │       ├── 📁 like/                # 좋아요 도메인
│   │   │       │   ├── 📁 application/     # 애플리케이션 서비스
│   │   │       │   ├── 📁 domain/          # 도메인 모델
│   │   │       │   ├── 📁 dto/             # 데이터 전송 객체
│   │   │       │   ├── 📁 infrastructure/  # 인프라스트럭처
│   │   │       │   ├── 📁 presentation/    # 프레젠테이션 계층
│   │   │       │   └── 📁 vo/              # 값 객체
│   │   │       └── 📄 LikeServiceApplication.java
│   │   └── 📁 resources/           # 설정 파일들
│   └── 📁 test/                    # 테스트 코드
└── 📄 gradlew                      # Gradle Wrapper 실행 스크립트
```

---

## 🔧 주요 기능

### ✅ 피드 좋아요
- 피드에 대한 좋아요/좋아요 취소 기능
- 토글 방식으로 동작
- Kafka를 통한 좋아요 이벤트 발행
- 집계서비스로 좋아요 통계 전달

### ✅ 댓글 좋아요
- 댓글에 대한 좋아요/좋아요 취소 기능
- 대댓글 지원
- Kafka를 통한 좋아요 이벤트 발행
- 집계서비스로 좋아요 통계 전달

### ✅ 라이브 스트림 좋아요
- 라이브 스트림에 대한 좋아요 기능
- 실시간 이벤트 발행
- 집계서비스로 좋아요 통계 전달

---

## 🔄 서비스 간 데이터 흐름

### 1. 좋아요 이벤트 발행
```
Like Service → Kafka → Aggregation Service
```

### 2. 집계 데이터 전달
```
Aggregation Service → Kafka → Comment/Live/Feed Services
```

### 3. 서비스 간 통신
- **Like Service**: 좋아요 이벤트 발행
- **Aggregation Service**: 좋아요 수 집계 및 통계 생성
- **Comment Service**: 댓글 좋아요 수 반영
- **Live Service**: 라이브 좋아요 수 반영
- **Feed Service**: 피드 좋아요 수 반영

---

## 🔄 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/like/feed` | 피드 좋아요 토글 |
| `POST` | `/api/v1/like/comment` | 댓글 좋아요 토글 |
| `POST` | `/api/v1/like/live` | 라이브 스트림 좋아요 토글 |

---

## 🚨 주의사항

### Unique Index
- `FeedLike`: `(feedId, likerUuid)` 조합에 unique index 적용
- `CommentLike`: `(commentId, likerUuid)` 조합에 unique index 적용
- 중복 데이터가 있을 경우 서버 기동 실패 가능

### 중복 데이터 정리
```javascript
// MongoDB에서 중복 데이터 정리 (백업 후 실행)
db.feed_like.aggregate([
  { $group: { _id: { feedId: "$feedId", likerUuid: "$likerUuid" }, count: { $sum: 1 }, ids: { $push: "$_id" } } },
  { $match: { count: { $gt: 1 } } }
]).forEach(function(doc) {
  doc.ids.shift();
  db.feed_like.deleteMany({ _id: { $in: doc.ids } });
});
```


<div align="center">

**Made with ❤️ by VYBZ Team**

</div>
