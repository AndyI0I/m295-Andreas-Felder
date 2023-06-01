package ch.ubs.m295.m295andreas.configuration;

import ch.ubs.m295.m295andreas.dao.subDAOs.UserDAO;
import ch.ubs.m295.m295andreas.services.extractors.ProductSetExtractor;
import ch.ubs.m295.m295andreas.services.extractors.PurchaseToProductMappingSetExtractor;
import ch.ubs.m295.m295andreas.services.extractors.UserSetExtractor;
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
      public UserSetExtractor userSetExtractor(){
            return new UserSetExtractor();
      }

      @Bean
      public PurchaseToProductMappingSetExtractor pToPSetExtractor(){
            return new PurchaseToProductMappingSetExtractor();
      }

      @Bean
      public ProductSetExtractor productSetExtractor (){
             return new ProductSetExtractor();
      }

      @Bean
      public UserDAO userDAO(){
            return new UserDAO();
      }



}

