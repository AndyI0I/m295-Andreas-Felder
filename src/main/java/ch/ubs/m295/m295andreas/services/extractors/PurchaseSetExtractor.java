package ch.ubs.m295.m295andreas.services.extractors;

import ch.ubs.m295.m295andreas.dto.PurchaseTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PurchaseSetExtractor implements ResultSetExtractor<Optional<PurchaseTable>> {
      @Override
      public Optional<PurchaseTable> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  PurchaseTable s = new PurchaseTable();
                  s.setId(rs.getInt("id"));
                  s.setIsPending(rs.getBoolean("isPending"));
                  s.setUserId(rs.getInt("userId"));
                  return Optional.of(s);
            }
            else{
                  return Optional.empty();
            }
      }
}
