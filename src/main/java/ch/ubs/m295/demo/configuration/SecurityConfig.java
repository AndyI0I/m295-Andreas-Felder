package ch.ubs.m295.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
      public UserDetailsService userDetailsService() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build());
                return manager;
      }

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            //http
            //      .authorizeHttpRequests()
            //      .requestMatchers("/students")
            //      .hasRole("USER")
            //      .anyRequest()
            //      .permitAll()
            //      .and()
            //      .httpBasic();
            //
            http.authorizeHttpRequests().requestMatchers("/students/**").anonymous().anyRequest().permitAll();

            return http.build();
      }
}
