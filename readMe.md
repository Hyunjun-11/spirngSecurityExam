## HttpBasic

* 클라이언트에서 요청이 들어온후
* 인증이되지 않은 사용자는
* 헤더에 WWW-Authenticate 추가해서보내주고 사용자의 인증을 요청한다
* 클라이언트는 인증요청을 WWW-Authenticate 에 담아서보낸다
* 정보는base64로 인코딩이 되기때문에 디코딩이가능하다
  1. HTTPS 나 TLS 에서 사용해야 그나마 안전하다.

## BasicAuthenticationFilter
1. 클라이언트 요청이 들어오면 BasicAuthenticationFilter 가 실행된다.
2. UsernamePasswordAuthenticationToken 에 username 과 password 를 담아서 토큰화를 한후에
3. AuthenticationManager 으로 보낸후 인증처리를 수행한다.

* ### 인증성공
  * UsernamePasswordAuthenticationToken 에 UserDetails + Authorities 를 담는다
  * SecurityContextHolder 에 Authentication 에 설정한다
  * 요청 컨텍스트에 SecurityContext 가 저장된다
    * formLogin > 세션범위내에서 인증이 유지된다.
    * basic > 요청범위내에서만 인증된다 
      * securityContextRepository 에 저장
  * 그후 chain.doFilter > 다음로직을 수행한다.

* ### 인증실패
  * SecurityContextHolder 삭체
  * WWW-Authenticate 를 클라이언트에게 보내도록 호출된다. (AuthenticationEntryPoint)

```
public class BasicAuthenticationFilter extends OncePerRequestFilter {
doFilterInternal  > converter에서 유효성인증
}
```


