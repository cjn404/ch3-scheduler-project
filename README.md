# Ch3 일정 관리 앱 프로젝트

## 목표
3 Layer Architecture 에 따라 CRUD 필수 기능은 JPA를 통해 DB(MySQL)과 연동되도록 구현

<br>

## 개발 대상
Lv1부터 Lv4.5까지 단계별 일정 관리 API 구현

<br>

## 개발 프로세스
- **Lv0** : API 명세 및 ERD 작성 
- **Lv1** : 단건 일정 생성
- **Lv2** : 전체 및 선택 일정 조회
- **Lv3** : 선택 일정 수정
- **Lv4** : 선택 일정 삭제
- **Lv4.5** : 비밀번호 Bcrypt 암호화 구현 및 생성 실패 시 오류 코드 400 상정

<br>

## 디렉토리 구조
```
src
├── main
│   ├── java
│   │   └── org
│   │       └── example
│   │           └── ch3schedulerapiproject
│   │               ├── Ch3SchedulerApiProjectApplication.java
│   │               └── scheduler
│   │                   ├── config
│   │                   │   └── PasswordConfig.java
│   │                   ├── controller
│   │                   │   └── SchedulerController.java
│   │                   ├── dto
│   │                   │   ├── DeleteSchedulerRequest.java
│   │                   │   ├── SchedulerRequest.java
│   │                   │   ├── SchedulerResponse.java
│   │                   │   └── UpdateSchedulerRequest.java
│   │                   ├── entity
│   │                   │   ├── BaseEntity.java
│   │                   │   └── Scheduler.java
│   │                   ├── repository
│   │                   │   └── SchedulerRepository.java
│   │                   └── service
│   │                       └── SchedulerService.java
│   └── resources
│       ├── application.yml
│       ├── static
│       └── templates
└── test
    └── java
        └── org
            └── example
                └── ch3schedulerapiproject
                    └── Ch3SchedulerApiProjectApplicationTests.java
```
<br>

## 주요 기능
- **Lv1 단건 일정 생성**
  - ID, 비밀번호, 작성자명, 일정 제목, 일정 내용, 작성일, 수정일을 저장
  - 각 일정의 고유 식별자 ID는 자동 생성 및 관리
  - 최소 생성 시, 수정일과 작성일 동일
  - API 응답 시 비밀번호 제외
 
- **Lv2 전체 및 선택 일정 조회**  
  - 전체 일정 조회
    - 작성자명 기준으로 등록된 일정 전체 조회 가능
    - 수정일 기준 내림차순 정렬
  - 선택 일정 조회 : ID 기준 조회
  - API 응답 시 비밀번호 제외
 
- **Lv3 선택 일정 수정**  
  - 작성자명, 일정 제목만 수정 가능
  - 일정 수정 요청 시 비밀번호 필요
  - 수정 완료 시 수정 시점으로 수정일 변경. 단, 작성일은 고정
  - API 응답 시 비밀번호 제외
    
- **Lv4 선택 일정 삭제**  
  - 일정 삭제 요청 시 비밀번호 필요
 
- **Lv4.5 비밀번호 Bcrypt 암호화 구현 및 생성 실패 시 오류 코드 400 상정**
  - 일정 생성 시 평문으로 기입된 비밀번호를 해시 값으로 암호화
 
<br> 

## 개발 환경
- java gradle corretto 17
- Spring Boot v3.5.4
- MySQL

<br>

## API 명세서
| Method | Endpoint | Description | Parameters | Request Body | Response | Status Code | Error Codes |
| --- | ---| --- | --- | --- | --- | --- | --- |
| POST | /schedulers | 일정 생성 | 없음 | { "id": long } <br> { "password": string } <br> { "name": string } <br> { "title": string } <br> { "content": string } | { "id": long } <br> { "title": string } <br> { "content": string } <br> { "name": string } <br> { "createdAt": string } <br> { "modifiedAt": string } | 200 OK | 400 Bad Request |
| GET | /schedulers | 전체 일정 조회 | name (optional) | 없음 | { "id": long } <br> { "name": string } <br> { "title": string } <br> { "content": string } <br> { "createdAt": string } <br> { "modifiedAt": string } | 200 OK | 404 Not Found |
| GET | /schedulers/{id} | 선택 일정 조회 | id (path) | 없음 | { "id": long } <br> { "name": string } <br> { "title": string } <br> { "content": string } <br> { "createdAt": string } <br> { "modifiedAt": string } | 200 OK | 404 Not Found |
| PATCH | /schedulers/{id} | 선택 일정 작성자명 & 제목만 수정 | id (path) | { "title": string } <br> { "content": string } | { "id": long } <br> { "name": string } <br> { "title": string } <br> { "content": string } <br> { "createdAt": string } <br> { "modifiedAt": string } | 200 OK  | 404 Not Found <br> 401 UNAUTHORIZED |
| DELETE | /schedulers/{id} | 선택 일정 삭제 | id (path)       | { "password": "string" } | 없음 | 204 No Content | 404 Not Found <br> 401 UNAUTHORIZED |

<br>

## ERD
<img width="375" height="420" alt="Image" src="https://github.com/user-attachments/assets/e971cd76-bd52-4ae6-9eaf-be0d182cc0c1" />
