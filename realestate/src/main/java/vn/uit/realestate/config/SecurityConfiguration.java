package vn.uit.realestate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import jakarta.servlet.DispatcherType;
import vn.uit.realestate.service.CustomUserDetailsService;
import vn.uit.realestate.service.UserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public AuthenticationSuccessHandler CustomSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        // authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // v6. lamda
        http
                .authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                        DispatcherType.INCLUDE)
                .permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/", "/login", "/register", "/client/**", "/css/**", "/js/**", "/images/**", "/register").permitAll()
                .requestMatchers("/agency/**").hasRole("AGENCY")
                .anyRequest().authenticated())
                .sessionManagement((sessionManagement) -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/logout?expired")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false))
                .logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))
                .rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeServices()))
                .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .failureUrl("/login?error")
                .successHandler(CustomSuccessHandler())
                .permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));

        return http.build();
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices
                = new SpringSessionRememberMeServices();
// optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }
}
