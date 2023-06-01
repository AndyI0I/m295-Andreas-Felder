package ch.ubs.m295.m295andreas.dao.subDAOs;

import ch.ubs.m295.m295andreas.dto.ProductTable;
import ch.ubs.m295.m295andreas.services.extractors.ProductSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

public class ProductDAO {

      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;
      @Autowired
      private ProductSetExtractor productSetExtractor;

      public int add(ProductTable product) {
            String sql = "INSERT INTO product (productname, seller, price) VALUES (:productname, :seller, :price)";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("productname", product.getProductname())
                  .addValue("seller", product.getSeller())
                  .addValue("price", product.getPrice());
            return jdbcTemplate.update(sql, namedParameters);
      }

      public int update(ProductTable product) {
            String sql = "UPDATE product SET productname = :productname,  seller = :seller WHERE id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", product.getId())
                  .addValue("productname", product.getProductname())
                  .addValue("seller", product.getSeller())
                  .addValue("price", product.getPrice());
            return jdbcTemplate.update(sql, namedParameters);
      }

      public int delete(int id) {
            String sql =
                   "DELETE p, m FROM Products p " +
                  "LEFT JOIN PurchaseToProductMapping m ON p.id = m.productId " +
                  "WHERE p.id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.update(sql, namedParameters);
      }

      public Optional<ProductTable> get(int id) {
            String sql = "SELECT * FROM product WHERE id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.query(sql, namedParameters, productSetExtractor);
      }

      public List<ProductTable> getall() {
            String sql = "SELECT * FROM product";
            SqlParameterSource namedParameters = new MapSqlParameterSource();
            return jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<>(ProductTable.class));
      }
}
