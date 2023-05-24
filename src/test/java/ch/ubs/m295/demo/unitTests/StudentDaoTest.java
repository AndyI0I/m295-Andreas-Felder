package ch.ubs.m295.demo.unitTests;

import ch.ubs.m295.demo.dao.StudentDao;
import ch.ubs.m295.demo.services.StudentSetExtractor;
import ch.ubs.m295.generated.v1.dto.Student;
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
      @Mock
      private StudentSetExtractor studentSetExtractor;

      private StudentDao studentDao;

      @BeforeEach
      void initalize() {
            this.studentDao = new StudentDao(this.namedParameterJdbcTemplate, studentSetExtractor);
      }

      @Test
      void insert() {
            Student student = new Student(){{
                  setStudentid(10);
                  setStudentname("Chuck Norris");
                  setGrade(Student.GradeEnum.A);
                  setAge(98);
                  setModule("M295");
            }};
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

      //test if the getById method works
      @Test
      void read(){
            this.studentDao.GetByID(10);
            ArgumentCaptor<MapSqlParameterSource> argumentCaptor = ArgumentCaptor.forClass(MapSqlParameterSource.class);

            verify(this.namedParameterJdbcTemplate).query(
                    eq("SELECT * FROM student WHERE studentid = :studentid"),
                    argumentCaptor.capture(),
                    eq(studentSetExtractor)
            );
            MapSqlParameterSource mapSqlParameterSource = argumentCaptor.getValue();
            assertThat(mapSqlParameterSource.getValue("studentid")).isEqualTo(10);
      }
}
