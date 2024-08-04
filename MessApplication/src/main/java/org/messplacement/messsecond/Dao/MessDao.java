package org.messplacement.messsecond.Dao;

import org.messplacement.messsecond.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessDao extends JpaRepository<Student, String> {


    @Query("""
            SELECT SUM(s.total) FROM Student s WHERE s.Reg = :studentId""")
    int getStudentTotal(@Param("studentId") String studentId);
}
