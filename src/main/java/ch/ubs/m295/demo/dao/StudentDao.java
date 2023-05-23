package ch.ubs.m295.demo.dao;

import ch.ubs.m295.demo.dto.Grade;
import ch.ubs.m295.demo.dto.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class StudentDao {

      private final NamedParameterJdbcTemplate jdbcTemplate;

      public StudentDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.jdbcTemplate = namedParameterJdbcTemplate;
      }

      public int add(Student student) {

            String sql = "insert into student (studentId, studentname, grade, age, module) values (:studentId, :studentname, :grade, :age, :module)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("studentId", student.getStudentid())
                    .addValue("studentname", student.getStudentname())
                    .addValue("grade", student.getGrade().toString())
                    .addValue("age", student.getAge())
                    .addValue("module", student.getModule());

            return this.jdbcTemplate.update(sql, paramSource);
      }
      public int updateById(Student student) {
            String sql = "UPDATE student SET studentname = :studentname, grade = :grade, age = :age, module = :module WHERE studentid = :studentid";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("studentid", student.getStudentid())
                    .addValue("studentname", student.getStudentname())
                    .addValue("grade", student.getGrade().toString())
                    .addValue("age", student.getAge())
                    .addValue("module", student.getModule());
            return this.jdbcTemplate.update(sql, paramSource);
      }

      public int deleteById(int studentId) {
            String sql = "DELETE FROM student WHERE studentid = :studentid";
            SqlParameterSource paramSource = new MapSqlParameterSource().addValue("studentid", studentId);
            return this.jdbcTemplate.update(sql, paramSource);
      }

      public Student GetByID(int studentId) {
            String sql = "SELECT * FROM student WHERE studentid = :studentid";
            SqlParameterSource paramSource = new MapSqlParameterSource().addValue("studentid", studentId);
            return this.jdbcTemplate.queryForObject(sql, paramSource, (rs, rowNum) -> {
                  int studentid = rs.getInt("studentid");
                  String studentname = rs.getString("studentname");
                  Grade grade = Grade.valueOf(rs.getString("grade").toString());
                  int age = rs.getInt("age");
                  String module = rs.getString("module");
                  return new Student(studentid, studentname, grade, age, module);
            });
      }
      public List<Student> GetAll() {
            String sql = "SELECT * FROM Student";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
      }
}
