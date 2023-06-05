package ch.ubs.m295.m295andreas.dao.subDAOs;

import ch.ubs.m295.m295andreas.dto.PurchaseToProductMappingTable;
import ch.ubs.m295.m295andreas.services.extractors.PurchaseToProductMappingSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

public class PurchaseToProductMappingDAO {

      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;
      @Autowired
      private PurchaseToProductMappingSetExtractor pToPSetExtractor;

      public int add(PurchaseToProductMappingTable purchase) {
            String sql = "INSERT INTO purchase_to_product_mapping (purchaseId, productId, quantity) VALUES (:purchaseId, :productId, :quantity)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("purchaseId", purchase.getPurchaseId())
                  .addValue("productId", purchase.getProductId())
                  .addValue("quantity", purchase.getQuantity());
            return jdbcTemplate.update(sql, paramSource);
      }

      public int update(PurchaseToProductMappingTable oldPurchase, PurchaseToProductMappingTable newPurchase) {
            String sql = "UPDATE PurchaseToProductMapping SET productId = :newProductId, quantity = :newQuantity WHERE purchaseId = :purchaseId AND productId = :productId;";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("purchaseId", oldPurchase.getPurchaseId())
                  .addValue("productId", oldPurchase.getProductId())
                  .addValue("newProductId", newPurchase.getProductId())
                  .addValue("newQuantity", newPurchase.getQuantity());
            return jdbcTemplate.update(sql, paramSource);
      }

      public int deleteByPurchaseId(int purchaseId) {
            String sql = "DELETE FROM purchase_to_product_mapping WHERE purchaseId = :purchaseId";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("purchaseId", purchaseId);
            return jdbcTemplate.update(sql, paramSource);
      }

      public int deleteByProductId(int productId) {
            String sql = "DELETE FROM purchase_to_product_mapping WHERE productId = :productId";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("productId", productId);
            return jdbcTemplate.update(sql, paramSource);
      }

      public Optional<PurchaseToProductMappingTable> get(int id) {
            String sql = "SELECT * FROM purchase_to_product_mapping WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.query(sql, paramSource, pToPSetExtractor);
      }

      public List<PurchaseToProductMappingTable> getByPurchaseId(int purchaseId) {
            String sql = "SELECT * FROM purchase_to_product_mapping WHERE purchaseId = :purchaseId";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("purchaseId", purchaseId);
            return jdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper<>(PurchaseToProductMappingTable.class));
      }

      public List<PurchaseToProductMappingTable> getByProductId(int productId) {
            String sql = "SELECT * FROM purchase_to_product_mapping WHERE productId = :productId";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("productId", productId);
            return jdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper<>(PurchaseToProductMappingTable.class));
      }

      public List<PurchaseToProductMappingTable> getAll() {
            String sql = "SELECT * FROM purchase_to_product_mapping";
            SqlParameterSource paramSource = new MapSqlParameterSource();
            return jdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper<>(PurchaseToProductMappingTable.class));
      }

}
