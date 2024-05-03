# [SpringSecurity Study]

# UserDetails
- 사용자의 기본정보를 저장하는 인터페이스로 springSecurity 에서 사용하는 사용자 타입이다.
- 저장된 사용자 정보는 추후에 인증절차에서 사용되기위해 Authentication 객체에 포함되며 구현체로서 User 클래스가 제공된다.
```java
public interface UserDetails extends Serializable {
    //사용자의 비밀번호가 유효기간이 지났는지 확인하며 기간이 지난 비밀번호는 인증할 수 없다.
    boolean isCredentialsNonExpired();
    //사용자 계정의 유효기간이 지났는지를 나타내며 기간이 만료된 계정은 인증 할 수 없다.
    boolean isAccountNonExpired();
    //사용자가 잠겨 있는지 아닌지 잠긴 사용자는 인증 할 수 없다.
    boolean isAccountNonLocked();
    //사용자가 활성화 되었는지 비활성화 되었는지를 나타내며 비활성화된 사용자는 인증 할 수 없다.
    boolean isEnabled();
    // username , password, Authorities 를 반환하며 pw를 제외하고 null 을 반환 할 수 없다.
    String getUsername();
    String getPassword();
    Collection<? extends GrantedAuthority> getAuthorities();
}
```

