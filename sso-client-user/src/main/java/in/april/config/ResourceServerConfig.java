package in.april.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(subDomainFilter, ChannelProcessingFilter.class); //子域名解析过滤器

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .exceptionHandling()
                .and().authorizeRequests().antMatchers("/medium").permitAll()
                .and().authorizeRequests().antMatchers("/view/**").permitAll()
                .antMatchers("/**").access("#oauth2.isOAuth()");// and hasRole('ROLE_USER')
    }

}

