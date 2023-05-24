package ch.ubs.m295.demo.controllers;

import ch.ubs.m295.demo.dao.StudentDao;
import ch.ubs.m295.generated.v1.controller.StudentsApi;
import ch.ubs.m295.generated.v1.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController extends AbstractRestController implements StudentsApi {

      private final StudentDao studentDao;

      @Autowired
      public StudentsController(StudentDao studentDao) {
            this.studentDao = studentDao;
      }

      @Override
      public ResponseEntity<List<Student>> studentsGet() {
            return getResponseFromThis(studentDao.GetAll());
      }

}
