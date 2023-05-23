package ch.ubs.m295.demo.services;

import ch.ubs.m295.demo.dto.Grade;
import ch.ubs.m295.demo.dto.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentSetExtractor implements ResultSetExtractor<Optional<Student>> {
      @Override
      public Optional<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  int studentid = rs.getInt("studentid");
                  String studentname = rs.getString("studentname");
                  Grade grade = Grade.valueOf(rs.getString("grade").toString());
                  int age = rs.getInt("age");
                  String module = rs.getString("module");
                  return Optional.of(new Student(studentid, studentname, grade, age, module));
            }else{
                  return Optional.empty();
            }
      }
}
