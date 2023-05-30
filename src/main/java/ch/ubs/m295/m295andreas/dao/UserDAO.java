package ch.ubs.m295.m295andreas.dao;


import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.m295andreas.services.UserSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;

/*public class User   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;
*/
public class UserDAO {

      private final UserSetExtractor userSetExtractor;
      private NamedParameterJdbcTemplate jdbcTemplate;

      public UserDAO(NamedParameterJdbcTemplate jdbcTemplate, UserSetExtractor userSetExtractor) {
            this.jdbcTemplate = jdbcTemplate;
            this.userSetExtractor = userSetExtractor;
      }

      //add user
      public void addUser(User user) {
            String sql = "INSERT INTO users (username, email, password) VALUES (:username, :email, :password)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("username", user.getUsername())
                  .addValue("email", user.getEmail())
                  .addValue("password", user.getPassword());
            jdbcTemplate.update(sql, paramSource);
      }

      //get user
      public User getUser(int id) {
            String sql = "SELECT * FROM users WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.queryForObject(sql, paramSource, userSetExtractor);
      }

      
}
