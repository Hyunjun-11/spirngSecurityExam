# [SpringSecurity 초기화과정]



### SecurityConfig
* SecurityConfig 를 개발자가 직접 입력하지않을시 default 값을가진 보안시스템이 작동한다
* 2가지 조건이따르는데 SpringBootWebSecurityConfiguration 메서드에
* @ConditionalOnDefaultWebSecurity 조건중
* @ConditionalOnMissingBean({SecurityFilterChain.class}) 개발자가 SecurityFilterChain이라는 이름으로 빈을 생성하지않았을때
* @ConditionalOnClass({SecurityFilterChain.class, HttpSecurity.class}) 애플리케이션 클래스패스에 SecurityFilterChain, HttpSecurity 
* 존재할때에만 생성되도록 되어있다.

### DelegatingFilterProxyRegistrationBean
* DelegatingFilterProxyRegistrationBean 이 생성이되며 파라미터로 SecurityProperties 을 받는다.
* springSecurityFilterChain 이라는 이름을가진 Bean 으로 생성된다.
* servlet 과 springSecurity 의 관계를 이어주는 역할을한다.

### SecurityProperties
* default 값으로 User 객체가 생성된다.
* private String name = "user"
* private String password = UUID.randomUUID().toString();
* getName, getPassword 를통해서 초기화된다.
* UserDetailsServiceAutoConfiguration 클래스의 inMemoryUserDetailsManager 메서드가 호출되고 생성된 username 과 pw를 저장한다.

### HttpSecurityConfiguration
* HttpSecurity 초기화
* @Scope("prototype") 으로생성
* 기본설정이 되어서 HttpSecurity Bean 이 생성된다.

### SpringBootWebSecurityConfiguration
* 위에서 생성된 HttpSecurity 를 주입받아 
* SecurityFilterChain 의 파라미터로 들어오게된다.
* defaultSecurityFilterChain 메서드중 formLogin 방식과 httpBasic 의 설정도 추가되어 
* http.build() 를 통해 초기화

### build > AbstractConfiguredSecurityBuilder
* doBuild 과정은
* INITIALIZING > CONFIGURING > BUILDING > BUILT 순으로 이루어지는데
* BUILDING 과정중에  O result = this.performBuild() 에서 그동안 설정했던과정들이 보안필터로 등록이된다.
* 

## SecurityBuilder / SecurityConfigurer
1. SecurityBuilder 에 의해서 SecurityConfigurer 초기화된다
2. SecurityConfigurer 는 init메서드와 configure메서드를 가지고있다
3. 매개변수로는 B builder 를 받는다
