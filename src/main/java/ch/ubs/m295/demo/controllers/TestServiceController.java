package ch.ubs.m295.demo.controllers;

import ch.ubs.m295.demo.dao.StudentDao;
import ch.ubs.m295.demo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestServiceController {

    private final StudentDao studentDao;

    @Autowired
    public TestServiceController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@RequestBody Student student) {
        try {
            studentDao.add(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error inserting student");
        }
        return ResponseEntity.ok("Student inserted successfully");
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            studentDao.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting student");
        }
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
        try {
            studentDao.updateById(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating student");
        }
        return ResponseEntity.ok("Student updated successfully");
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = studentDao.GetByID(id);
        return student;
    }

    @GetMapping("/students")
    public List<Student> testGetAllStudents() {
        List<Student> students = studentDao.GetAll();
        return students;
    }
}

