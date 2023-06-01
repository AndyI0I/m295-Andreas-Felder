package ch.ubs.m295.m295andreas.dao.subDAOs;

import ch.ubs.m295.generated.v1.dto.SaleHistory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;


public class SaleHistoryDAO {

      private final NamedParameterJdbcTemplate jdbcTemplate;

      public SaleHistoryDAO(NamedParameterJdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
      }

      //add saleHistory
      public int addSaleHistory(SaleHistory saleHistory) {
            String sql = "INSERT INTO saleHistory (userId, salesProductId, isPending) VALUES (:userId, :salesProductId, :isPending)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  //.addValue("userId", saleHistory.getUserId())
                  .addValue("salesProductId", saleHistory.getProducts())
                  .addValue("isPending", saleHistory.getIsPending());
            return jdbcTemplate.update(sql, paramSource);
      }

      //get saleHistory
      public Optional<SaleHistory> getSaleHistory(int id) {
            String sql = "SELECT * FROM saleHistory WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return null; //jdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper<>(SaleHistory.class));
      }

      //get all saleHistorys
      public List<SaleHistory> getAllSaleHistorys() {
            String sql = "SELECT * FROM saleHistory";
            SqlParameterSource paramSource = new MapSqlParameterSource();
            return jdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper<>(SaleHistory.class));
      }

      //update saleHistory
      public int updateSaleHistory(SaleHistory saleHistory) {
            String sql = "UPDATE saleHistory SET userId = :userId, salesProductId = :salesProductId, isPending = :isPending WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", saleHistory.getId())
                  //.addValue("userId", saleHistory.getUserId())
                  .addValue("salesProductId", saleHistory.getProducts())
                  .addValue("isPending", saleHistory.getIsPending());
            return jdbcTemplate.update(sql, paramSource);
      }
}
