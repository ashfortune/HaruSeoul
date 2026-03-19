<div align="center">
  <img src="https://github.com/user-attachments/assets/28920dfe-03a0-4ffe-b74f-41cc5ae70e16" alt="HaruSeoul 대표이미지" width="100%" />

  # 🏮 HaruSeoul (하루서울)
  **"서울의 하루를 특별하게 만드는 로컬 경험, 하루서울과 함께하세요."**

  [![Stack](https://img.shields.io/badge/Stack-Nuxt3--Spring--Boot-orange?style=flat-square)](#)
  [![Platform](https://img.shields.io/badge/Platform-Web-blue?style=flat-square)](#)
  [![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](#)
</div>

---

## 📌 프로젝트 소개 (About the Project)
**HaruSeoul**은 개인 호스트가 직접 기획한 창의적인 서울 여행 프로그램을 탐색하고 예약할 수 있는 **호스트 기반 로컬 여행 예약 플랫폼**입니다. 기존 대형 플랫폼의 획일화된 패키지 투어를 넘어, 현지인이 공유하는 살아있는 경험과 소규모 맞춤형 투어를 지향합니다.

- **목표**: 누구나 서울의 호스트가 되어 가치 있는 경험을 공유하고, 게스트는 특별한 서울의 하루를 발견하도록 돕습니다.
- **차별점**: 가이드 자격 요건 없이 현지 실정에 밝은 서울 시민이면 누구나 호스트로 참여 가능 (낮은 진입 장벽, 고유한 로컬리티 확보).

---

## 🛠 기술 스택 (Tech Stack)

### **Frontend**
- **Framework**: <img src="https://img.shields.io/badge/Nuxt_3-00DC82?style=flat-square&logo=nuxtdotjs&logoColor=white" /> (Vue 3, Composition API)
- **styling**: SCSS, Flexbox, Responsive Design
- **State/Auth**: JWT (JSON Web Token), Axios, Composable API
- **User Experience**: Multi-modal UX, SSE Real-time Notification

### **Backend**
- **Framework**: <img src="https://img.shields.io/badge/Spring_Boot_3.3-6DB33F?style=flat-square&logo=springboot&logoColor=white" /> (Java 21)
- **Data Access**: Spring Data JPA + MyBatis (Hybrid Approach)
- **Security**: Spring Security + JWT
- **Communication**: SSE (Server-Sent Events) for Real-time alerts
- **Database**: <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white" />

---

## 🚀 주요 기능 (Key Features)

### 🍱 게스트 시스템 (Guest Portal)
- **탐색**: 카테고리, 날짜, 프로그램 상태별 필터를 통한 직관적인 프로그램 탐색
- **예약**: 실시간 가입/로그인 기반 예약 신청 및 취소 프로세스
- **관리**: 마이페이지를 통한 예약 현황 모니터링 및 이력 관리

### 🏠 호스트 시스템 (Host Portal)
- **프로그램 운영**: 프로그램 신규 등록, 상세 정보(경로, 교통수단, 인원) 수정 및 삭제
- **예약 거버넌스**: 게스트 예약 신청에 대한 실시간 승인/거절 처리
- **상태 관리**: 프로그램 발행 여부(Publish/Unpublish) 즉각 제어

### 🔔 공통 및 시스템 기능
- **실시간 알림**: SSE 기술을 활용하여 예약 상태 변화 시 호스트와 게스트에게 즉각적인 피드백 제공
- **인증 보안**: 최신 JWT 인증 방식을 통해 안전한 계정 정보 및 권한 관리 수행

---

## 🔍 트러블슈팅 (Troubleshooting)

### 1️⃣ 실시간 알림(SSE) 연결 관리 및 타임아웃
- **문제(Problem)**: 브라우저 기본 타임아웃으로 인해 서버와의 연결이 끊기거나, 비정상 종료된 클라이언트 리소스가 잔류하는 이슈 발생.
- **해결(Solution)**: 
  - `SseEmitter` 생성 시 타임아웃을 `0L`(무제한)로 설정하여 안정적인 연결을 확보했습니다.
  - 사용자별 세션을 안전하게 관리하기 위해 `ConcurrentHashMap` 기반의 `SseClientRegistry`를 구축하고, `onCompletion`, `onTimeout` 등 생명주기 콜백을 통해 리소스 누수를 원천 차단했습니다.

### 2️⃣ 프론트엔드-백엔드 간 CORS 정책 대응
- **문제(Problem)**: 개발 환경에서 프론트엔드(`3003`)와 백엔드(`8083`)의 포트 불일치로 인한 API 요청 차단 현상.
- **해결(Solution)**: Spring Security(`WebSecurityConfig`)에서 `CorsConfigurationSource`를 정의하고 프론트엔드 도메인을 명시적으로 허용하여 협업 및 개발 편의성을 높였습니다.

### 3️⃣ 멀티파트 업로드 정적 이미지 접근 에러
- **문제(Problem)**: 호스트가 업로드한 이미지가 실제 로컬 저장소에는 존재하지만, 웹 상에서 엑박(404)으로 뜨는 문제.
- **해결(Solution)**: `WebMvcConfigurer`를 구현하여 `/api/v1/uploads/**` 가상 경로를 실제 파일 저장소(`/uploads/`)와 매핑하는 `addResourceHandlers`를 설정하여 해결했습니다.

---

## 📂 프로젝트 구조 (Project Structure)

```text
HaruSeoul /
├── backend /               # Spring Boot Application
│   ├── src/main/java/      # Java Source (com.m2j2.HaruSeoul)
│   │   ├── auth /          # Security & JWT logic
│   │   ├── host /          # Host-side controllers & services
│   │   ├── guest /         # Guest-side logic
│   │   ├── notification /  # SSE & Real-time alert logic
│   │   └── entity /        # JPA Entities
│   └── pom.xml             # Maven Dependencies
├── frontend /              # Nuxt 3 Project
│   └── haruseoul-nuxt3 /
│       ├── pages /         # File-system based Routing
│       ├── layouts /       # Shared Layouts
│       ├── components /    # Reusable Vue components
│       ├── composables /   # Shared logic hooks
│       └── nuxt.config.ts  # Nuxt Configuration
└── README.md
```

---

## 🚦 시작하기 (Getting Started)

### Prerequisites
- Java 21+
- Node.js 18.x+
- MariaDB

### 로컬 실행 방법

#### 1. Backend (Spring Boot)
```bash
cd backend
./mvnw spring-boot:run
```
- API Endpoint: `http://localhost:8080` (기본값)

#### 2. Frontend (Nuxt 3)
```bash
cd frontend/haruseoul-nuxt3
npm install
npm run dev
```
- Application Address: `http://localhost:3003`

---

## 👥 팀원 (Team Members)

| 이름 | 역할 | 담당 시스템 |
| :--- | :--- | :--- |
| **김민지** | 팀장 | 호스트 프로그램 관리 시스템 구축 |
| **김민석** | 팀원 | 호스트 예약 관리 시스템 및 승인 로직 |
| **유재복** | 팀원 | 게스트 프로그램 조회 및 필터 고도화 |
| **김준성** | 팀원 | 게스트 예약 신청/취소 및 마이페이지 관리 |

---

<div align="center">
  © 2024 HaruSeoul Team. All Rights Reserved.
</div>
