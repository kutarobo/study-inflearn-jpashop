# study-inflearn-jpashop
[인프런 jpa 강의 학습용](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/)

### 요구사항
- 회원기능
	- 회원등록
	- 회원조회
- 상품기능
	- 등록
	- 수정
	- 조회
- 주문기능
	- 상품 주문
	- 내역 조회
	- 주문 취소
- 기타요구 사항
	- 상품은 재고관리가 필요하다.
	- 상품의 종류는 도서, 음반, 영화가 있다.
	- 상품주문시 배송 정보를 입력할 수 있다.

### 애플리케이션 아키텍처
**계층형 구조사용**
- controller, web: 웹계층
- service: 비즈니스 로직, 트랜잭션 처리
- repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용
- domain: 엔티티가 모여있는 계층, 모든 계층에서 사용

**패키지 구조**
- jpabook.jpashop
	- domain
	- exception
	- repository
	- service
	- web
	 
**개발 순서: 서비스, 리포지토리 계층을 개발하고, 테스트 케이스를 작성해서 검증, 마지막에 웹계층 적용**
	  

