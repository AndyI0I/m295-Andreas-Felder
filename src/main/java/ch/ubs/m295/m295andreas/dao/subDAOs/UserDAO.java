package ch.ubs.m295.m295andreas.dao.subDAOs;


import ch.ubs.m295.m295andreas.dto.UserTable;
import ch.ubs.m295.m295andreas.services.extractors.UserSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

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

      @Autowired
      private UserSetExtractor userSetExtractor;
      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;

      //add user
      public void addUser(UserTable user) {
            String sql = "INSERT INTO users (username, email, password) VALUES (:username, :email, :password)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("username", user.getUsername())
                  .addValue("email", user.getEmail())
                  .addValue("password", user.getPassword());
            jdbcTemplate.update(sql, paramSource);
      }

      //update user
      public void updateUser(UserTable user) {
            String sql = "UPDATE users SET username = :username, email = :email, password = :password WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", user.getId())
                  .addValue("username", user.getUsername())
                  .addValue("email", user.getEmail())
                  .addValue("password", user.getPassword());
            jdbcTemplate.update(sql, paramSource);
      }

      //get user
      public Optional<UserTable> getUser(int id) {
            String sql = "SELECT * FROM users WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.query(sql, paramSource, userSetExtractor);
      }

      //get all users
      public List<UserTable> getAllUsers() {
            String sql = "SELECT * FROM users";
            SqlParameterSource paramSource = new MapSqlParameterSource();
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserTable.class));
      }

      
}
