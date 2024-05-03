# [SpringSecurity Study]

# UserDetailsService
- 사용자와 관련된 상세 데이터를 로드하는 것이며 사용자의 신원, 권한, 자격증명 같은 정보를 포함
- AuthenticationProvider 가 주로 사용한다
- 사용자가 시스템에 존재하는지 여부와 사용자 데이터를 검색하고 인증과정을 수행한다.
```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

