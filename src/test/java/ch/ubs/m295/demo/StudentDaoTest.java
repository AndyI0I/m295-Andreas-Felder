package ch.ubs.m295.demo;


import ch.ubs.m295.demo.dao.StudentDao;
import ch.ubs.m295.demo.dto.Grade;
import ch.ubs.m295.demo.dto.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentDaoTest {


      @Mock
      private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

      private StudentDao studentDao;

      @BeforeEach
      void initalize() {
            this.studentDao = new StudentDao(this.namedParameterJdbcTemplate);
      }

      @Test
      void insert() {
            Student student = new Student(
                    10,
                    "Chuck Norris",
                    Grade.A,
                    98,
                    "M295"
            );
            this.studentDao.add(student);
            ArgumentCaptor<MapSqlParameterSource> argumentCaptor =
                    ArgumentCaptor.forClass(MapSqlParameterSource.class);
            verify(this.namedParameterJdbcTemplate).update(
                    eq("insert into student (studentId, studentname, grade, age, module) values (:studentId, :studentname, :grade, :age, :module)"),
                    argumentCaptor.capture()
            );
            MapSqlParameterSource mapSqlParameterSource = argumentCaptor.getValue();
            assertThat(mapSqlParameterSource.getValue("studentId")).isEqualTo(10);
            assertThat(mapSqlParameterSource.getValue("studentname")).isEqualTo("Chuck Norris");
            assertThat(mapSqlParameterSource.getValue("grade")).isEqualTo("A");
            assertThat(mapSqlParameterSource.getValue("age")).isEqualTo(98);
            assertThat(mapSqlParameterSource.getValue("module")).isEqualTo("M295");
      }
}
