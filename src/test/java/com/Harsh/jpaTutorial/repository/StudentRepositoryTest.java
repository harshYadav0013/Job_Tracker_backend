package com.Harsh.jpaTutorial.repository;
import com.Harsh.jpaTutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class StudentRepositoryTest {


    @Autowired
private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
          System.out.println("test executed");
          Student student = Student.builder()
                  .email("harshyadav72060@gmail.com")
                  .firstName("Harsh")
                  .lastName("Yadav")
                  .guardianName("Neelu")
                  .guardianEmail("Neelu@1234")
                  .guardianMobile(728097865)
                  .build();

          studentRepository.save(student);
          studentRepository.flush();   // forces insert immediately

          System.out.println("Student saved");
    }
     @Test
    public  void printAllStudents(){
        List<Student> studentList =studentRepository.findAll();

         System.out.println("student list" + studentList);
    }

    @Test
    public void printAll(){
        List<Student> l = studentRepository.findAll();
        System.out.println("student list" + l);
    }
}