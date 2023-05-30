package ch.ubs.m295.m295andreas.services;

import ch.ubs.m295.generated.v1.dto.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserSetExtractor implements ResultSetExtractor<Optional<User>> {

      @Override
      public Optional<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  User s = new User();
                  s.setId(rs.getInt("id"));
                  s.setUsername(rs.getString("username"));
                  s.setEmail(rs.getString("email"));
                  s.setPassword(rs.getString("password"));
                  return Optional.of(s);
            }else{
                  return Optional.empty();
            }
      }
}