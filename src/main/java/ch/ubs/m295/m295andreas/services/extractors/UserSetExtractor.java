package ch.ubs.m295.m295andreas.services.extractors;

import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.m295andreas.dto.UserTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserSetExtractor implements ResultSetExtractor<Optional<UserTable>> {

      @Override
      public Optional<UserTable> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  UserTable s = new UserTable();
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