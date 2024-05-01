### anonymous

* 인증사용자와 인증받지 못한 사용자를 동일한 기준에서 객체로 구분을하기위해
* SecurityContextHolder 에 저장한다.
* 세션에는 저장하지않는다.
* AnonymousAuthenticationToken 을 만들어서 관리한다.
* HttpServletRequest#getPrincipal 에서 authentication 을 사용하여 파라미터를 해결하는데 요청이 익명일 때 이값은 null 이다.
```java
public String method(Authentication authentication){
    if(authentication instanceof AnonymousAuthenticationToken){
        return "anonymous";
    }else {
        return "not anonymous";
    }
}
```
* 익명요청에서 Authentication 을 얻고싶다면 @CurrentSecurityContext 를 사용하면 된다.
* CurrentSecurityContextArgumentResolver 에서 요청을 가로채어 처리한다.
```java
public String method(@CurrentSecurityContext SecurityContext context){
    return context.getAuthentication().getName();
}
```

### AnonymousAuthenticationFilter
* SecurityContextHolder 에 Authentication 객체가 없을 경우 감지하고 필요한 경우 새로운 Authentication 객체로 채운다.
* 이전필터에서 Authentication 가 비어있지않다면(인증을 받았다면) 다음필터로 로직을 실행시키고
* null 이라면 AnonymousAuthenticationToken 을만들어 SecurityContextHolder 에 주입한다.
