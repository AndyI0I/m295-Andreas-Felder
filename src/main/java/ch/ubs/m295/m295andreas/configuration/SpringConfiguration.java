package ch.ubs.m295.m295andreas.configuration;

import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import ch.ubs.m295.m295andreas.dao.subDAOs.ProductDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseToProductMappingDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.UserDAO;
import ch.ubs.m295.m295andreas.services.extractors.ProductSetExtractor;
import ch.ubs.m295.m295andreas.services.extractors.PurchaseSetExtractor;
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
      public PurchaseSetExtractor purchaseSetExtractor(){
            return new PurchaseSetExtractor();
      }

      @Bean
      public UserDAO userDAO(){
            return new UserDAO();
      }

      @Bean
      public ProductDAO productDAO(){
            return new ProductDAO();
      }

      @Bean
      public PurchaseDAO purchaseDAO(){
            return new PurchaseDAO();
      }

      @Bean
      public PurchaseToProductMappingDAO pToPDAO(){
            return new PurchaseToProductMappingDAO();
      }

      @Bean
      public DBOrchestrator dbOrchestrator(){
            return new DBOrchestrator();
      }
}

