

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



