package dwarf.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static dwarf.framework.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll() // white list url
                .antMatchers("/api/*").hasAnyRole(USER.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.USER_DELETE.getPermission()) // urls for permission
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.USER_DELETE.getPermission()) // urls for permission
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.USER_DELETE.getPermission()) // urls for permission
                .antMatchers(HttpMethod.PATCH,"/management/api/**").hasAuthority(ApplicationUserPermission.USER_DELETE.getPermission()) // urls for permission
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), EVENT_ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails eventAdmin = User.builder()
                .username("EventAdmin")
                .password(passwordEncoder.encode("password"))
                //.roles(EVENT_ADMIN.name()) // ROLE_EVENT_ADMIN
                .authorities(EVENT_ADMIN.getGrantedAuthorities())
                .build();

        UserDetails user = User.builder()
                .username("User")
                .password(passwordEncoder.encode("password"))
                //.roles(USER.name()) // ROLE_USER
                .authorities(USER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(admin, eventAdmin, user);
    }

}
