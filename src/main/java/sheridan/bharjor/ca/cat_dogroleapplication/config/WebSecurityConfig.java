package sheridan.bharjor.ca.cat_dogroleapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;

    WebSecurityConfig(
            UserDetailsService userDetailsService,
            AuthenticationManagerBuilder auth,
            DataSource dataSource,
            PasswordEncoder passwordEncoder
    )throws Exception {
        this.dataSource = dataSource;
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.headers().frameOptions().sameOrigin();

        security.authorizeRequests()
                // remove "h2-console" from the program in production
                .antMatchers("/css/**", "/js/**", "/index", "/", "/images/**")
                .permitAll();

        // this line is for h2-console, it reduces security
        security.csrf().disable();



        security.authorizeRequests()
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        security.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/Home")
                .failureUrl("/login?error")
                .permitAll();

        security.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
                .deleteCookies("my-remember-me-cookie")
                .permitAll();

        //used to store remember me information when logging in
        security.rememberMe()
                .rememberMeCookieName("my-remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60);
    }

    private PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
