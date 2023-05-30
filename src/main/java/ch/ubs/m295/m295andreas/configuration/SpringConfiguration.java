package ch.ubs.m295.m295andreas.configuration;

import ch.ubs.m295.m295andreas.dao.UserDAO;
import ch.ubs.m295.m295andreas.services.UserSetExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Properties;

@EnableConfigurationProperties(Properties.class)
@Configuration
public class SpringConfiguration {


      @Bean
      public Logger getLogger(){
            return LoggerFactory.getLogger(SpringConfiguration.class);
      }

      @Bean
      public UserDAO dao(NamedParameterJdbcTemplate namedParameterJdbcTemplate){

            //create database and tables if not exists
            String sql = "CREATE DATABASE IF NOT EXISTS m295";
            namedParameterJdbcTemplate.getJdbcTemplate().execute(sql);
            sql = "USE m295";
            namedParameterJdbcTemplate.getJdbcTemplate().execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), PRIMARY KEY (id))";
            namedParameterJdbcTemplate.getJdbcTemplate().execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS Products (id INT NOT NULL AUTO_INCREMENT, productname VARCHAR(255), quantity int, seller VARCHAR(255),price DOUBLE, PRIMARY KEY (id))";


            return new UserDAO(namedParameterJdbcTemplate, new UserSetExtractor());
      }
}
