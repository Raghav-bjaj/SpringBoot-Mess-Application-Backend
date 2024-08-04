package org.messplacement.messsecond.Service;

import org.messplacement.messsecond.Entities.Student;

import java.util.List;

public interface MessService {
     String getHomePage();

     List<Student> getStudents();

     Student getStudent(String Reg);

     int getStudentTotal(String studentId);

     String  addStudent(List<Student>  student);

     String updateStudent(Student student);

     String deleteStudent(Student student);
}

