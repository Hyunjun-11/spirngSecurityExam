# [SpringSecurity Study]

# CustomAuthenticationProvider

 ```java
 public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
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
            //이곳으로 들어가보면
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
 }

```

```java
public class ProviderManager {
    public Authentication authenticate(Authentication authentication) {
        //인증과정중
        //providers 리스트에 커스텀한 Provider 가 들어가있는것을 확인해볼수있다.
    }
}
```
- securityConfig > bean 으로 1 개만 추가할경우
```java
@Bean
public AuthenticationProvider authenticationProvider() {
    return new CustomAuthenticationProvider();
}

```
- provides 에 추가되는게아닌 parent.provides 에 커스텀이 추가되어있다