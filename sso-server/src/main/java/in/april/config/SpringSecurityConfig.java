package in.april.config;

import in.april.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                // http security 要拦截的url，这里这拦截，oauth2相关和登录登录相关的url，其他的交给资源服务处理
                requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll();

        // 表单登录
        http.formLogin()
                // 登录页面
                .loginPage("/login")
                // 登录处理url
                .loginProcessingUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}

