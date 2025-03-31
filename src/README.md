# What for This?

# Schedule API
| 기능       | HTTP Method |       URL       | Path Variable |                                                       request Param                                                        | request                                                                               | response                                                                                                                                                                             |                상태                |
|----------|:-----------:|:---------------:|:--------------:|:--------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------:|
| 추가       |    POST     |   /shcedules    |                |                                                                                                                            | {<br>&nbsp; "userPw": "비밀번호",&nbsp;<br> "name" : "이름",&nbsp;<br> "todo" : "할 일" <br>} | {<br> &nbsp;"id" : 1,<br>&nbsp;"todo" : "내용",&nbsp;<br> "created_at" :null,&nbsp;<br> &nbsp;"updated_at" : null<br>} <br>                                                           |   성공:OK<br>실패:Bad_request<br>    |
| 전체 조회    |     GET     |   /shcedules    |                | [둘다 참]<br>&nbsp;?day=조건&name=이름,<br>&nbsp; [날짜 만]<br>&nbsp;?day=조건<br>&nbsp;[이름 만]<br>&nbsp;?name=이름, [전체 조회]<br>&nbsp;공백 |                                                                                       | {<br>&nbsp;&nbsp;[<br>&nbsp;&nbsp;"id" : 1,<br>&nbsp;&nbsp;"todo" : "내용",<br>&nbsp;&nbsp;"created_at" : 2025-03-24<br>&nbsp;&nbsp;"updated_at": null<br>&nbsp;<br>&nbsp;]<br>}<br> |   성공:OK<br>실패:Bad_request<br>    |
| 일정 내용 수정 |     PUT     |   /shcedules/{id}    |   id (Long)   |                                                                                                                            | {<br>&nbsp;"name" : "이름",<br>&nbsp;"todo" : "할 일"<br>}                                | {<br>&nbsp; "id": 2,<br>&nbsp; "name": "paragon",<br>&nbsp; "todo": "내용",<br>&nbsp; "created_at": "2025-03-26 00:25:25",<br>&nbsp;"updated_at": "2025-03-26 00:25:44"<br>&nbsp;}     |   성공:OK<br>실패:NotFound_request<br>    |
| 작성자 수정   |    PATCH    |   /shcedules/{id}    |   id (Long)    |                                                                                                                            | {<br>&nbsp;"name": "Enermy",<br>&nbsp;"todo": "내용"<br>&nbsp;}                         | {<br>&nbsp;"id": 2,<br>&nbsp;"name": "Enermy",<br>&nbsp;"todo": "내용",<br>&nbsp;"created_at": "2025-03-26 00:25:25",<br>&nbsp; "updated_at": "2025-03-26 10:28:41"<br>&nbsp;}       |   성공:OK<br>실패:NotFound_request<br>    |
| 단 건 삭제   |   DELETE    | /shcedules/{id} |   id (Long)    |                                                                                                                            | {<br>&nbsp;"userPw":"test1234"<br>&nbsp;}                                             |                                                                                                                                                                                      | 성공:OK<br>실패:NotFound_request<br> |

# ERD

# DB
  * ScheduleDB

| 칼럼         | 명칭     | 입력 Type   | Null 유무 |
|------------|--------|-----------|------|
| user_id    | 작성자 id | VARCHAR    | NOT NULL |
| user_pw    | 작성자 PW | VARCHAR    | NOT NULL |
| name       | 작성자 이름 | VARCHAR    | NOT NULL |
| id         | 글 ID   | BIGINT    | NOT NULL |
| todo       | 내용     | VARCHAR   | NULL |
| created_at | 작성일    | TIMESTEMP | NULL |
| updated_at | 수정일    | TIMESTEMP | NULL |


  * UserDB

| 칼럼          | 명칭         | 입력 Type     | Null 유무 |
|-------------|------------|-------------|------|
| member_id   | 작성자 number | VARCHAR     | NOT NULL |
| user_id     | 작성자 id     | VARCHAR     | NOT NULL |
| user_pw     | 작성자 pw     | VARCHAR     | NOT NULL |
| name        | 작성자 이름     | VARCHAR     | NOT NULL |
| joindate_at | 작성일        | TIMESTEMP   | NOT NULL |

# How Work? 