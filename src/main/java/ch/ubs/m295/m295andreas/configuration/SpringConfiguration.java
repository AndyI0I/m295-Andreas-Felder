package ch.ubs.m295.m295andreas.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@EnableConfigurationProperties(Properties.class)
@Configuration
public class SpringConfiguration {


      @Bean
      public Logger getLogger(){
            return LoggerFactory.getLogger(SpringConfiguration.class);
      }

      @Bean
      public NamedParameterJdbcTemplate npjTemplate (DataSource dataSource){
            return new NamedParameterJdbcTemplate(dataSource);
      }

      @Bean
      public


}

