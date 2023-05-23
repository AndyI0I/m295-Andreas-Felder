package ch.ubs.m295.demo.dto;

public class Student {
      private int studentid;
      private String studentname;
      private Grade grade;
      private int age;
      private String module;

      public Student() {
      }

      public Student(int studentid, String studentname ,Grade grade, int age, String module) {
            this.studentid = studentid;
            this.studentname = studentname;
            this.age = age;
            this.grade = grade;
            this.module = module;
      }

      public int getStudentid() {
            return studentid;
      }

      public String getStudentname() {
            return studentname;
      }

      public Grade getGrade() {
            return grade;
      }

      public int getAge() {
            return age;
      }

      public String getModule() {
            return module;
      }

      public void setStudentid(int studentid) {
            this.studentid = studentid;
      }

      public void setStudentname(String studentname) {
            this.studentname = studentname;
      }

      public void setGrade(Grade grade) {
            this.grade = grade;
      }

      public void setAge(int age) {
            this.age = age;
      }

      public void setModule(String module) {
            this.module = module;
      }


}
