package ch.ubs.m295.generated.v1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Student
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-24T14:59:37.391568900+02:00[Europe/Zurich]")
public class Student   {
  @JsonProperty("studentid")
  private Integer studentid;

  @JsonProperty("studentname")
  private String studentname;

  /**
   * Gets or Sets grade
   */
  public enum GradeEnum {
    A("A"),
    
    B("B"),
    
    C("C"),
    
    D("D"),
    
    E("E"),
    
    F("F");

    private String value;

    GradeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GradeEnum fromValue(String value) {
      for (GradeEnum b : GradeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("grade")
  private GradeEnum grade;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("module")
  private String module;

  public Student studentid(Integer studentid) {
    this.studentid = studentid;
    return this;
  }

  /**
   * Get studentid
   * @return studentid
  */
  @ApiModelProperty(value = "")


  public Integer getStudentid() {
    return studentid;
  }

  public void setStudentid(Integer studentid) {
    this.studentid = studentid;
  }

  public Student studentname(String studentname) {
    this.studentname = studentname;
    return this;
  }

  /**
   * Get studentname
   * @return studentname
  */
  @ApiModelProperty(value = "")


  public String getStudentname() {
    return studentname;
  }

  public void setStudentname(String studentname) {
    this.studentname = studentname;
  }

  public Student grade(GradeEnum grade) {
    this.grade = grade;
    return this;
  }

  /**
   * Get grade
   * @return grade
  */
  @ApiModelProperty(value = "")


  public GradeEnum getGrade() {
    return grade;
  }

  public void setGrade(GradeEnum grade) {
    this.grade = grade;
  }

  public Student age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * @return age
  */
  @ApiModelProperty(value = "")


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Student module(String module) {
    this.module = module;
    return this;
  }

  /**
   * Get module
   * @return module
  */
  @ApiModelProperty(value = "")


  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(this.studentid, student.studentid) &&
        Objects.equals(this.studentname, student.studentname) &&
        Objects.equals(this.grade, student.grade) &&
        Objects.equals(this.age, student.age) &&
        Objects.equals(this.module, student.module);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentid, studentname, grade, age, module);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");
    
    sb.append("    studentid: ").append(toIndentedString(studentid)).append("\n");
    sb.append("    studentname: ").append(toIndentedString(studentname)).append("\n");
    sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    module: ").append(toIndentedString(module)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

