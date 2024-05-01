

## UsernamePasswordAuthenticationFilter

### AbstractAuthenticationProcessingFilter
* UsernamePasswordAuthenticationFilter 는 
* AbstractAuthenticationProcessingFilter 를 확장한 클래스이다.
* attemptAuthentication() 메서드를 제정의해서 사용한다.

### 요청
* 클라이언트에서 요청이들어오면 RequestMatcher 에서 요청정보가 매칭이 되는지확인한다.
* 실패하면 chain.doFilter > 로 다음로직을 수행한다.
* 성공시 UsernamePasswordAuthentication Token 을생성 Token에(username password)를 저장한다.
* AuthenticationManager 클래스로 전달해서 인증처리를 요청한다.
* AuthenticationManager 에서는 DB 에 저장되어있는 ID,PW를 비교해서 일치하는지 검사한다.
  * #### 인증성공시
    * UsernamePasswordAuthenticationToken 을생성 후 (UserDetails_유저정보 + Authorities_권한)이 저장된다
    * SessionAuthenticationStrategy 클래스에서 세션작업들을 수행한다.
    * SecurityContextHolder 에 Authentication(인증객채) 을저장한다.
      * 사용자의 인증상태를 유지하기위해
    * RememberMeServices (Remember-me 가 설정된경우)
    * ApplicationEventPublisher 인증 성공 이벤트를 게시한다
    * AuthenticationSuccessHandler 인증성공 핸들러를 호출한다.
   * #### 인증실패시
     * SecurityContextHolder 가 삭제된다.
     * RememberMeServices(RememberMeServices.loginFail) 호출
     * AuthenticationFailureHandler 인증실패 핸들러를 호출한다.

```java
// AbstractAuthenticationProcessingFilter 클래스의
public abstract class AbstractAuthenticationProcessingFilter extends GenericFilterBean implements ApplicationEventPublisherAware, MessageSourceAware {
    private void doFilter() {
        //requestMatcher 검사
        if (!this.requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
        }
       //attemptAuthentication 메서드 재 정의
       //과정중에 U.N.T 생성 인증처리
       //아래과정중 성공시 authenticationResult 에 인증객체가 담긴다(userDetail_principal)
       Authentication authenticationResult = this.attemptAuthentication(request, response);
       //세션에저장
       this.sessionStrategy.onAuthentication(authenticationResult, request, response);
       this.successfulAuthentication(request, response, chain, authenticationResult);

    }

   protected void successfulAuthentication(){
      SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
      context.setAuthentication(authResult);
      this.securityContextHolderStrategy.setContext(context);
      //세션에저장(setContextInSession)
      this.securityContextRepository.saveContext(context, request, response);
      this.successHandler.onAuthenticationSuccess(request, response, authResult);
}



public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
   // 
   public Authentication attemptAuthentication() {
       //사용자 정보를 가져와 Token 에 저장한다.
      UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
       //getAuthenticationManager 에게 인증처리를 요청
      return this.getAuthenticationManager().authenticate(authRequest);
   }
   
    
}



```


