# SPRING PLUS

## ✅ 필수

#### <a href = "https://dimenshun.tistory.com/151?category=1228321">Lv 1 트러블 슈팅</a>

### 1. 코드 개선 퀴즈 - @Transactional의 이해

![image](https://github.com/user-attachments/assets/49a10604-70bd-45ab-ae30-cf09709d7fbb)

![image](https://github.com/user-attachments/assets/547fd514-665d-449b-917e-c9596acefa78)

- 할 일 저장 기능을 구현한 API(/todos)를 호출할 때, 아래와 같은 에러가 발생하고 있어요.
- 에러가 발생하지 않고 정상적으로 할 일을 저장 할 수 있도록 코드를 수정해주세요.

### 2. 코드 추가 퀴즈 - JWT의 이해

🚨 기획자의 긴급 요청이 왔어요!
아래의 요구사항에 맞춰 기획 요건에 대응할 수 있는 코드를 작성해주세요.

- User의 정보에 nickname이 필요해졌어요.
    - User 테이블에 nickname 컬럼을 추가해주세요.
    - nickname은 중복 가능합니다.
- 프론트엔드 개발자가 JWT에서 유저의 닉네임을 꺼내 화면에 보여주길 원하고 있어요.

### 3. 코드 개선 퀴즈 -  JPA의 이해

![image](https://github.com/user-attachments/assets/ff5046b5-ac57-4418-9083-7ff81b1c8a59)

🚨 기획자의 긴급 요청이 왔어요!
아래의 요구사항에 맞춰 기획 요건에 대응할 수 있는 코드를 작성해주세요.

- 할 일 검색 시 `weather` 조건으로도 검색할 수 있어야해요.
    - `weather` 조건은 있을 수도 있고, 없을 수도 있어요!
- 할 일 검색 시 수정일 기준으로 기간 검색이 가능해야해요.
    - 기간의 시작과 끝 조건은 있을 수도 있고, 없을 수도 있어요!
- JPQL을 사용하고, 쿼리 메소드명은 자유롭게 지정하되 너무 길지 않게 해주세요.

💡 필요할 시, 서비스 단에서 if문을 사용해 여러 개의 쿼리(JPQL)를 사용하셔도 좋습니다

### 4. 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해

![image](https://github.com/user-attachments/assets/6e1ed14f-8c4c-4d30-9d28-40df86f241f2)

- 테스트 패키지 `org.example.expert.domain.todo.controller`의 `todo_단건_조회_시_todo가_존재하지_않아_예외가_발생한다()` 테스트가 실패하고 있어요.

- 테스트가 정상적으로 수행되어 통과할 수 있도록 테스트 코드를 수정해주세요.

### 5. 코드 개선 퀴즈 - AOP의 이해

![image](https://github.com/user-attachments/assets/1fbadba8-85d7-4954-a2c0-4d4831a8799a)

😱 AOP가 잘못 동작하고 있어요!
- `UserAdminController` 클래스의 `changeUserRole()` 메소드가 실행 전 동작해야해요.
- `AdminAccessLoggingAspect` 클래스에 있는 AOP가 개발 의도에 맞도록 코드를 수정해주세요.

#### <a href = "https://dimenshun.tistory.com/157">Lv 2 트러블 슈팅</a>

### 6. JPA Cascade

![image](https://github.com/user-attachments/assets/d29e3a88-c990-4861-abad-c3ef69c822bd)

🤔 앗❗ 실수로 코드를 지웠어요!

- 할 일을 새로 저장할 시, 할 일을 생성한 유저는 담당자로 자동 등록되어야 합니다.
- JPA의 `cascade` 기능을 활용해 할 일을 생성한 유저가 담당자로 등록될 수 있게 해주세요.

### 7. N + 1

![image](https://github.com/user-attachments/assets/0197ada0-1d20-4539-b77d-e7a4a1ea9142)
![image](https://github.com/user-attachments/assets/519b252b-697b-457d-b847-44a8f6c5cb7f)

- `CommentController` 클래스의 `getComments()` API를 호출할 때 N+1 문제가 발생하고 있어요. N+1 문제란, 데이터베이스 쿼리 성능 저하를 일으키는 대표적인 문제 중 하나로, 특히 연관된 엔티티를 조회할 때 발생해요.
- 해당 문제가 발생하지 않도록 코드를 수정해주세요.

### 8. QueryDSL

![image](https://github.com/user-attachments/assets/55305b16-2f54-4f33-96f8-b7227bd976cf)

TodoService.getTodo 메소드

- JPQL로 작성된 `findByIdWithUser` 를 QueryDSL로 변경합니다.
- 7번과 마찬가지로 N+1 문제가 발생하지 않도록 유의해 주세요!

### 9. Spring Security

💡 Spring Security를 도입하기로 결정했어요!

- 기존 `Filter`와 `Argument Resolver`를 사용하던 코드들을 Spring Security로 변경해주세요.
    - 접근 권한 및 유저 권한 기능은 그대로 유지해주세요.
    - 권한은 Spring Security의 기능을 사용해주세요.
- 토큰 기반 인증 방식은 유지할 거예요. JWT는 그대로 사용해주세요.

## 🔥 도전

#### <a href = "https://dimenshun.tistory.com/170">Lv3-1 트러블 슈팅</a>

### 10. QueryDSL 을 사용하여 검색 기능 만들기

👉 일정을 검색하는 기능을 만들고 싶어요!
검색 기능의 성능 및 사용성을 높이기 위해 QueryDSL을 활용한 쿼리 최적화를 해보세요.
❗Projections를 활용해서 필요한 필드만 반환할 수 있도록 해주세요❗

- 새로운 API로 만들어주세요.
- 검색 조건은 다음과 같아요.
    - 검색 키워드로 일정의 제목을 검색할 수 있어요.
        - 제목은 부분적으로 일치해도 검색이 가능해요.
    - 일정의 생성일 범위로 검색할 수 있어요.
        - 일정을 생성일 최신순으로 정렬해주세요.
    - 담당자의 닉네임으로도 검색이 가능해요.
        - 닉네임은 부분적으로 일치해도 검색이 가능해요.
- 다음의 내용을 포함해서 검색 결과를 반환해주세요.
    - 일정에 대한 모든 정보가 아닌, 제목만 넣어주세요.
    - 해당 일정의 담당자 수를 넣어주세요.
    - 해당 일정의 총 댓글 개수를 넣어주세요.
- 검색 결과는 페이징 처리되어 반환되도록 합니다.
