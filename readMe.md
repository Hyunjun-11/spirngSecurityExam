## SecurityProperties 유저객체가 생성됨.

1. UserDetailsServiceAutoConfiguration 클래스의
2. inMemoryUserDetailsManager 메서드가 호출되고 생성된 username 과 pw를
3. InMemoryUserDetailsManager 메모리에 저장한다.

## SpringBootWebSecurityConfiguration클래스에 의해서 초기화가된다

1. SecurityFilterChainConfiguration메서드가 실행
2. DefaultWebSecurityCondition의 조건이 참이어야만 기본보안조건이 실행

## SecurityBuilder / SecurityConfigurer
1. SecurityBuilder 에의해서 SecurityConfigurer 초기화된다
2. SecurityConfigurer 는 init메서드와 configure메서드를 가지고있다
3. 매개변수로는 B builder 를 받는다
##  HttpSecurityConfiguration
1. scope 는 싱글턴이아닌 prototype ( httpsecurity를 생성할때마다 생성)
2. csrf - this.getOrApply(new CsrfConfigurer(context))); 생성해서 초기화
3. exceptionHandling - this.getOrApply(new ExceptionHandlingConfigurer()));
4. 등등 여러설정을 초기화하고나서 httpSecurity bean 객채생성
5. 많은 configurers 생성
6. securityFilterChain 에 주입
7. build > built