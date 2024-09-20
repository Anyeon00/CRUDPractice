### 1차 요구사항

유저 기능  <br>
- 기본적인 CRUD (조회는 단건 조회, 전체 조회)  <br>
- 로그인/로그아웃 (스프링 시큐리티 X, 단순 DB 조회해서 값 일치만 확인 후 세션 저장)  <br>

게시글 / 댓글 기능  <br>
- 기본적인 CRUD(조회는 단건 조회, 전체 조회)  <br>

일단 복잡한 기능은 전부 빼고 기본만 만들어봅니다.  <br>
DTO 고려 X, 스프링 validation X  <br>
스프링 웹, data jpa, mysql 이렇게만 가지고 만들어보고 롬복은 선택사항  <br>
연관관계는 사진 참조  <br>
기능은 쉬우니까 데이터를 처리하는 위치에 대한 고민과 연관관계 처리에 대한 고민 위주로 진행  <br>
좋은 구조 고민에 시간을 오래 쓰기보단 일단 동작하게 만들고 계속 리팩토링 하는 방향으로 진행  <br>

![스크린샷 2024-09-09 오후 6 33 49](https://github.com/user-attachments/assets/37694cfc-84dd-4941-abf7-45f5b5e6c0a4)

***

### 1차 코드리뷰

- ResponseEntity<?> 에서 와일드카드 대신 DTO사용

- 인가처리서비스로직을 서비스보단 컨트롤러 레벨에서 처리하기
Request나 Session같은건 웹이랑 밀접한 기술이므로 Service보다 컨트롤러단에서 처리 or 필터나인터셉터 유틸 이런거만들어서처리

- 컨트롤러에서 findOne으로부터 Optional이 아닌 객체를 리턴받도록 처리
그걸 처리해주는게 서비스이므로

- n+1 문제 고려하기 **중요**

- 변수명 tmp보다 더 명확한 의미의 변수명 사용 ex)findUser

- 컨트롤러 URL 네이밍
단건조회는 @GetMapping post/{id}
전체조회는 @GetMapping post

