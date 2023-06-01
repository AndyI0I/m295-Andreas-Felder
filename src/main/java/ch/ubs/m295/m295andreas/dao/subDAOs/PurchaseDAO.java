package ch.ubs.m295.m295andreas.dao.subDAOs;

import ch.ubs.m295.m295andreas.dto.PurchaseTable;
import ch.ubs.m295.m295andreas.services.extractors.PurchaseSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

public class PurchaseDAO {

      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;
      @Autowired
      private PurchaseSetExtractor purchaseSetExtractor;

      public int addPurchase(PurchaseTable purchase) {
            String sql = "INSERT INTO purchasehistory (userId, isPending) VALUES (:userId, :isPending)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("user_id", purchase.getUserId())
                  .addValue("isPending", purchase.getIsPending());
            return jdbcTemplate.update(sql, paramSource);
      }

      public int updatePurchase(PurchaseTable purchase) {
            String sql = "UPDATE purchasehistory SET userId = :userId, isPending = :isPending WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", purchase.getId())
                  .addValue("userId", purchase.getUserId())
                  .addValue("isPending", purchase.getIsPending());
            return jdbcTemplate.update(sql, paramSource);
      }

      public int deletePurchase(int id) {
            String sql = "DELETE FROM purchasehistory WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.update(sql, paramSource);
      }

      public Optional<PurchaseTable> getPurchase(int id) {
            String sql = "SELECT * FROM purchasehistory WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.query(sql, paramSource, purchaseSetExtractor);
      }

      public List<PurchaseTable> getAllPurchases() {
            String sql = "SELECT * FROM purchasehistory";
            SqlParameterSource paramSource = new MapSqlParameterSource();
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PurchaseTable.class));
      }
}
