package ch.ubs.m295.m295andreas.dao;

import ch.ubs.m295.m295andreas.dto.UserTable;
import ch.ubs.m295.m295andreas.dto.ProductTable;
import ch.ubs.m295.m295andreas.dto.PurchaseTable;
import ch.ubs.m295.m295andreas.dto.PurchaseToProductMapping;
import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.generated.v1.dto.Product;
import ch.ubs.m295.generated.v1.dto.Purchase;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class DBOrchestrator {


      private final NamedParameterJdbcTemplate jdbcTemplate;

      public DBOrchestrator(NamedParameterJdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
      }

      public void insertUser(UserTable userTable) {
            String sql = "INSERT INTO user (username, email, password) VALUES (:username, :email, :password)";

            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("username", userTable.getUsername())
                        .


            jdbcTemplate.update(sql);
      }
}