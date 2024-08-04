package org.messplacement.messsecond;

import org.messplacement.messsecond.Entities.Student;
import org.messplacement.messsecond.Service.MessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controller {

    @Autowired
    MessService messService;

    @GetMapping("/home")
    public String getHomePage(){
        return messService.getHomePage();
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        return messService.getStudents();
    }

    @GetMapping("/students/{Reg}")
    public Student getStudent(@PathVariable String  Reg){
        return messService.getStudent(Reg);
    }

    @GetMapping("studentTotal/{id}")
    public int getStudentTotal(@PathVariable String id){
        int total =  messService.getStudentTotal(id);
        return total;
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody List<Student> student){
        messService.addStudent(student);
        return "Student added successfully";
    }

    @PutMapping("/students")
    public String updateStudent(@RequestBody Student student){
        messService.updateStudent(student);
        return "Student updated";
    }

    @DeleteMapping("/students")
    public String deleteStudent(@RequestBody Student student){
        messService.deleteStudent(student);
        return "Student deleted";
    }
}
