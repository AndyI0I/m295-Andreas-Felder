package ch.ubs.m295.m295andreas.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@EnableConfigurationProperties(Properties.class)
@Configuration
public class SpringConfiguration {


      @Bean
      public Logger getLogger(){
            return LoggerFactory.getLogger(SpringConfiguration.class);
      }

      @Bean
      public  dao(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
            return new StudentDao(namedParameterJdbcTemplate, new StudentSetExtractor());
      }
}
