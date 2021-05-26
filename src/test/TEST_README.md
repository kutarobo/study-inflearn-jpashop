### test 용 db 환경 설정 분리하는 방법
1. test/resources 디렉토리 추가
2. test/resources에 application.yml 복사
3. test application.yml 아래와 같이 변경
```yml
spring:
	datasource:
		url: jdbc:h2:mem:test
# dependecies에 h2 가 있어야한다.
# h2database.com > cheat sheet > in-memory 참고
```
4. 이후 테스트 실행시 위 설정을 타고 db 가 실행된다.

### 하지만 스프링부트에서는!! 
- 별도의 설정이 없으면 메모리모드로 돌아간다
- test용 application 파일이 추가된 상태에서 설정을 모두 지우면 메모리모들 테스트가 동작된다.
- 기본이 create가 아닌 create-drop 이다. (테스트 후 자원정리가됨)


