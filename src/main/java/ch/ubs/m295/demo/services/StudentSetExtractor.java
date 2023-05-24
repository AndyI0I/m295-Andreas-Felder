package ch.ubs.m295.demo.services;

import ch.ubs.m295.generated.v1.dto.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentSetExtractor implements ResultSetExtractor<Optional<Student>> {
      @Override
      public Optional<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  Student s = new Student();
                  int studentid = rs.getInt("studentid");
                  String studentname = rs.getString("studentname");
                  int age = rs.getInt("age");
                  String grade = rs.getString("grade");
                  String module = rs.getString("module");
                  return Optional.of(s);
            }else{
                  return Optional.empty();
            }
      }
}
