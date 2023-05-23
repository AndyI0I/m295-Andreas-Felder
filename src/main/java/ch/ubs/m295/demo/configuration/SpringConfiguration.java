package ch.ubs.m295.demo.configuration;

import ch.ubs.m295.demo.dao.StudentDao;
import ch.ubs.m295.demo.services.AppLogger;
import ch.ubs.m295.demo.services.StudentSetExtractor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@EnableConfigurationProperties(Properties.class)
@Configuration
public class SpringConfiguration {

      @Bean
      Sample sample(Properties properties) {
            return new Sample(properties);
      }

      @Bean
      public AppLogger getLogger(){
            return new AppLogger();
      }

      @Bean
      public StudentDao dao(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
            return new StudentDao(namedParameterJdbcTemplate, new StudentSetExtractor());
      }
}
