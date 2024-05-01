### Logout
* formLogin 을 사용하면 login/logout 페이지가 자동생성
* 스프링시큐리티는 기본적으로 DefaultLogoutPageGenerationFilter 를통해 로그아웃페이지를 제공
* "Get / logout" URL 로 접그이 가능하다.
* 기본적으로 "POST / logout" 으로만 가능하나 CSRF 기능을 비활성화 할 경우 , 또는 RequestMatcher 를 사용할 경우 GET, PUT, DELETE 모두 가능하다.
* 로그인페이지가 커스텀하게 생성될 경우 로그아웃도 커스텀하게 구현해야 한다.

### LogoutFilter
* 클라이언트에서 요청을받으면
* RequestMatcher 에서 요청정보가 매칭되는지 확인
* LogoutHandler 에서 여러 작업들을 수행한다.
  * CookieClearingLogoutHandler - 쿠키제거
  * SecurityConfig 
  * CsrfLogoutHandler 
  * SecurityContextLogoutHandler - 세션무효화, Authentication 제거
  * LogoutSuccessEventPublishingLogoutHandler - 이벤트개시
* 위 작업이 끝나고나면 LogoutSuccessHandler 에서 어디로이동을할건지 기본적으로는 login 페이지로 이동시킨다.

```java
public class LogoutFilter extends GenericFilterBean {
  private void doFilter()  {
    if (this.requiresLogout(request, response)) {
      //인증객체를 가저와서
      Authentication auth = this.securityContextHolderStrategy.getContext().getAuthentication();
      if (this.logger.isDebugEnabled()) {
        this.logger.debug(LogMessage.format("Logging out [%s]", auth));
      }

      this.handler.logout(request, response, auth);
      //핸들러에서 초기화를 진행한다.
      this.logoutSuccessHandler.onLogoutSuccess(request, response, auth);
    } else {
      chain.doFilter(request, response);
    }
  }
}

```