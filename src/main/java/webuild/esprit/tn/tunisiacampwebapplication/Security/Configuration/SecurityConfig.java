

package webuild.esprit.tn.tunisiacampwebapplication.Security.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthFilter authFilter ;
    private final AuthenticationProvider authenticationProvider;

   //private final LogoutHandler logoutHandler;

   private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
                .csrf()
                .disable()
                .authorizeHttpRequests()
<<<<<<< HEAD
                .antMatchers("/**")
                .permitAll()
=======
                .antMatchers("/AUTH/auth/**")

               .permitAll()
>>>>>>> main
                //.hasAnyRole("ADMIN")
                .anyRequest()
               .authenticated()

             //   .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)

               //.logout()
                //.logoutUrl("/api/v1/auth/logout")
                //.addLogoutHandler(logoutHandler)
                //.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())

           .logout()
                .logoutUrl("/api/v1/auth/logout")
               .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())


        ;

        return http.build();
    }
}

