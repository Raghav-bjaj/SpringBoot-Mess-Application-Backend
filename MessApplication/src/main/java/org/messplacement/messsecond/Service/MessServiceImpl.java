package org.messplacement.messsecond.Service;

import org.messplacement.messsecond.Dao.MessDao;
import org.messplacement.messsecond.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MessServiceImpl implements MessService {

    //Calculate total (list of objects)
    public List<Student> calcTotal(List<Student> student) {
        int total = 0;

        for (Student value : student) {
            if (value.getBreakfast()) {
                total += 75;
            }
            if (value.getLunch()) {
                total += 100;
            }
            if (value.getDinner()) {
                total += 125;
            }
            value.setTotal(total);
            total = 0;
            if(value.getDate() == null){value.setDate(LocalDate.now());}

        }
        return student;
    }
// calculate total for single object
    public Student calcTotal(Student student ){
        int total = 0;
        if (student.getBreakfast()) {total += 75;}
        if (student.getLunch() ) {total += 100;}
        if (student.getDinner()) {total += 125;}
        student.setTotal(total);
        if(student.getDate() == null){student.setDate(LocalDate.now());}
        return student;
    }


    @Autowired
    private MessDao messDao;

//   List<Student> list;
//
//    Student mess1 = new Student(1, new Date(), true, true, false, 100);
//    Student mess2 = new Student(2, new Date(), false, true, true, 150);
      public MessServiceImpl() {
//        list = new ArrayList<Student>();
//        list.add(mess1);
//        list.add(mess2);
    }

    @Override
    public String getHomePage(){
        return "This is the home page, Service Layer.";
    }

    @Override
    public List<Student> getStudents(){
          return messDao.findAll();
    }

    @Override
    public Student getStudent(String Reg) {
        return messDao.getById(Reg);
    }

    @Override
    public int getStudentTotal(String studentId) {
          return messDao.getStudentTotal(studentId);
    }


    @Override
    public String  addStudent(List<Student> student) {
        List<Student> obj = calcTotal(student);
        while (!messDao.findAll().isEmpty()) {
            if (!obj.isEmpty()) {
                messDao.save(obj.get(0));
                obj.remove(0);
            } else {
                break;
            }
        }
        return "Student added successfully";
    }

    @Override
    public String updateStudent(Student student) {
          messDao.deleteById(student.getreg());
          Student obj = calcTotal(student);
          messDao.save(obj);
        return " Updated Successfully";
    }

    @Override
    public String deleteStudent(Student student) {
          messDao.delete(student);
        return " Deleted Successfully";
    }


}
