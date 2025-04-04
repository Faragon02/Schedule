# What for This?

wwd

### 📘 API 명세서
# Schedule API
#### 🧾 스케줄 API

| 기능             | 메서드 | URL                          | 요청 데이터                       | 응답 형식          | 상태 코드                        |
|------------------|--------|------------------------------|------------------------------|----------------|----------------------------------|
| 스케줄 등록      | POST   | `/schedules`             | (세션 쿠키), `title`, `content`, | 등록 완료 메시지      | `201 Created`, `400`             |
| 스케줄 단건 조회 | GET    | `/schedules/{id}`        | (세션 쿠키)                      | GetResponseDto | `200 OK`, `404`                  |
| 스케줄 수정      | PUT    | `/schedules/{id}`        | (세션 쿠키), `title`, `content`  | 수정 완료 메시지      | `200 OK`, `400`, `404`           |
| 스케줄 삭제      | DELETE | `/schedules/{id}`        | (세션 쿠키)                      | 삭제 완료 메시지      | `200 OK`, `404`                  |
# User API

#### 🧾 회원 API

| 기능             | 메서드 | URL            | 요청 데이터                                | 응답 형식     | 상태 코드                            |
|------------------|--------|----------------|---------------------------------------|-----------|---------------------------------------|
| 회원 가입        | POST   | `users/signup` | `userName`, `password`, `email`       | 가입 완료 메시지 | `201 Created`, `400`, `409`          |
| 로그인           | POST   | `users/login`  | `userName`, `password`                | 로그인 완료 메세지 | `200 OK`, `401`, `404`               |
| 마이페이지 조회  | GET    | `/users`       | (세션 쿠키)                               |           | `200 OK`, `401`                      |
| 비밀번호 변경    | PATCH  | `/users`       | (세션 쿠키), `oldPassword`, `newPassword` | 변경 성공 메시지 | `200 OK`, `400`, `403`               |
| 회원 탈퇴        | DELETE |`/users`       | (세션 쿠키)                               | 탈퇴 완료 메시지 | `200 OK`, `401`                      |

#### 🧾 Exception Type 정리
| Code | Exception | 내용             |
|------|-----------|----------------|
| 400  | POST      | `users/signup` |
| 401  | POST      | `users/login`  |
| 403  | GET       | `/users`       | 
| 404  | PATCH     | `/users`       | 
| 409  | DELETE    | `/users`       | 


#### 🧾 정상 처리 정리
| Code | 내용        |
|------|-----------|
| 201  | Created   |
| 200 | OK        |

# ERD





# DB
  * ScheduleDB

| 칼럼         | 명칭         | 입력 Type   | Null 유무  |
|------------|------------|-----------|----------|
| writer     | 작성자        | VARCHAR   | NOT NULL |
| id         | 글 ID       | BIGINT    | NOT NULL |
| title      | 제목         | VARCHAR   | NOT NULL |
| contents   | 내용         | VARCHAR   | NULL     |
| created_at | 작성일        | DATATIME  | NULL     |
| updated_at | 수정일        | DATATIME | NULL     |


  * UserDB

| 칼럼         | 명칭         | 입력 Type     | Null 유무 |
|------------|------------|-------------|------|
| id         | 작성자 number | VARCHAR     | NOT NULL |
| user_id    | 작성자 id     | VARCHAR     | NOT NULL |
| user_pw    | 작성자 pw     | VARCHAR     | NOT NULL |
| email      | 메일주소       | VARCHAR     | NOT NULL |
| created_at | 가입일        | DATATIME  | NULL     |
| updated_at | 수정일        | DATATIME | NULL     |

# How Work? 
