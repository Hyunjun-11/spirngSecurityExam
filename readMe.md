# [SpringSecurity Study]
# Authentication

* Authentication - 사용자 인증 정보를 저장하는 토큰 개념의 객체로 활용되며 인증이후 SecurityContext 에저장되어 전역적으로 참조가 가능하다.
  * getPrincipal - 인증주체 
    * 인증요청인경우 사용자의 이름
    * 인증 후에는 UserDetails 타입의 객체가 된다
  * getCredentials -인증 주체가 올바른 것을 증명하는 자격으로 비밀번호를 의미한다
  * getAuthorities - 인증 주체에게 부여된 권한 (ex ADMIN, USER)
  * getDetails - 인증요청에 대한 추가적인 세부사항 IP 주소, 인증서 일련번호 등
  * isAuthenticated - 인증상태반환
  * setAuthenticated(boolean) - 인증상태 설정

## 인증절차흐름
1. 클라이언트가 로그인시도
2. AuthenticationFilter(formLogin 같은경우 UsernamePasswordAuthenticationFilter) 동작
3. Authentication 객체를 생성한다
   - principal : 사용자이름(ID)
   - credentials : 비밀번호
4. 인증처리수행을 위해 AuthenticationManager 전달
5. AuthenticationManager 가 시스템에서 가저온정보와 일치하다면 새로운Authentication 객체를 생성한다.
    - principal : UserDetails 객체
    - credentials : 주로 비밀번호이지만 인증된후에 지워져 노출되지 않도록 한다.
    - authorities : 권한으로 GrantedAuthority 타입의 컬렉션
6. AuthenticationManager 에게 전달
7. AuthenticationFilter 에게 전달
8. SecurityContextHolder 를 통해서 새로운 SecurityContext 에 인증된 Authentication 정보를 저장한다.

```java
public abstract class AbstractAuthenticationProcessingFilter {

    private void doFilter() {
        Authentication authenticationResult = this.attemptAuthentication(request, response);
    }
}
public class UsernamePasswordAuthenticationFilter{
    
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = this.obtainUsername(request);
            username = username != null ? username.trim() : "";
            String password = this.obtainPassword(request);
            password = password != null ? password : "";
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}

```
# SecurityContext
* 인증된 사용자의 Authentication 객체 저장
* ThreadLocal 저장소 사용
  * SecurityContextHolder 를 통해 접근 각 스레드가 자신만의 보안컨텍스트를 유지한다.
  * 현재 사용자의 인증사태나 권한을 확인하는 데 사용된다.
* 참조 및 삭제
  * 참조 - SecurityContextHolder.getContextHolderStrategy().getContext
  * 삭제 - SecurityContextHolder.getContextHolderStrategy().clearContext

# SecurityContextHolder
* SecurityContext 객체를 저장한다.
* 전략패턴을 사용한다
 ```java
public class SecurityContextHolder {
    public static final String MODE_THREADLOCAL = "MODE_THREADLOCAL";
    public static final String MODE_INHERITABLETHREADLOCAL = "MODE_INHERITABLETHREADLOCAL";
    public static final String MODE_GLOBAL = "MODE_GLOBAL";
}
```
  * SecurityContextHolderStrategy 인터페이스 사용
  * MODE_THREAD LOCAL
  * 전략모드 직접지정 ( SecurityContextHolder.setStrategyName(String) )
* 스레드 풀에서 사용되는경우 새로운 요청이더라고 기존의 ThreadLocal 이 재사용될 수 있기때문에 클라이언트로 응답직전에 SecurityContext 를 삭제 해 주고있다

### SecurityContextHolder 저장모드
* MODE_THREAD LOCAL - 대부분의 서버환경에 적합(동시성의 문제 해결)
* MODE_INHERITABLETHREADLOCAL - 부모 스레드로부터 자식 스레드로 보안컨텍스트가 상속. 스레드간 분산실행하는 경우 유용
* MODE_GLOBAL - 전역적으로 단일 보안컨텍스트를 사용하며 서버환경에서는 부적합. 간단한 애플리케이션에 적합


# AuthenticationManager
* AuthenticationManager 는 Authentication 객체를 만든다.
* AuthenticationManager 는 여러 AuthenticationProvider 들을 관리하며 목록을 순차적으로 순회하며 인증요청 처리를 한다.
* AuthenticationProvider 목록중 인증처리 요건에 맞는 적절한 Provider 를 찾아 위임한다.
### AuthenticationManagerBuilder 
 - AuthenticationManagerBuilder 를 이용하며 Provider 를 추가할수있다
 - HttpSecurity.getSharedObject(AuthenticationManagerBuilder.class) 를 통해 객체를 참조할수 있다.
 - 




AuthenticationProvider\
UserDetailsService\
UserDetails\