```java
package org.messplacement.messsecond.Service;

import org.messplacement.messsecond.Dao.MessDao;
import org.messplacement.messsecond.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MessServiceImpl implements MessService {

    @Autowired
    private MessDao messDao;

    // Calculate total for a list of students
    public List<Student> calcTotal(List<Student> students) {
        for (Student student : students) {
            int total = 0;
            if (student.getBreakfast()) {
                total += 75;
            }
            if (student.getLunch()) {
                total += 100;
            }
            if (student.getDinner()) {
                total += 125;
            }
            student.setTotal(total);
            if (student.getDate() == null) {
                student.setDate(LocalDate.now());
            }
        }
        return students;
    }

    // Calculate total for a single student
    public Student calcTotal(Student student) {
        int total = 0;
        if (student.getBreakfast()) {
            total += 75;
        }
        if (student.getLunch()) {
            total += 100;
        }
        if (student.getDinner()) {
            total += 125;
        }
        student.setTotal(total);
        if (student.getDate() == null) {
            student.setDate(LocalDate.now());
        }
        return student;
    }

    @Override
    public String getHomePage() {
        return "This is the home page, Service Layer.";
    }

    @Override
    public List<Student> getStudents() {
        return messDao.findAll();
    }

    @Override
    public Student getStudent(String reg) {
        Optional<Student> student = messDao.findById(reg);
        return student.orElseThrow(() -> new RuntimeException("Student not found with registration: " + reg));
    }

    @Override
    public int getStudentTotal(String studentId) {
        return messDao.getStudentTotal(studentId);
    }

    @Override
    public String addStudent(List<Student> students) {
        List<Student> calculatedStudents = calcTotal(students);
        messDao.saveAll(calculatedStudents);
        return "Students added successfully";
    }

    @Override
    public String updateStudent(Student student) {
        Optional<Student> existingStudent = messDao.findById(student.getReg());
        if (existingStudent.isEmpty()) {
            throw new RuntimeException("Student not found with registration: " + student.getReg());
        }
        messDao.deleteById(student.getReg());
        Student updatedStudent = calcTotal(student);
        messDao.save(updatedStudent);
        return "Updated successfully";
    }

    @Override
    public String deleteStudent(Student student) {
        Optional<Student> existingStudent = messDao.findById(student.getReg());
        if (existingStudent.isEmpty()) {
            throw new RuntimeException("Student not found with registration: " + student.getReg());
        }
        messDao.delete(student);
        return "Deleted successfully";
    }
}
```
