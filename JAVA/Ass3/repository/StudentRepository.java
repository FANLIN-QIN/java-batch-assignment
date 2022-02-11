package RESTdemo.repository;

import RESTdemo.domain.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    void createStudent(String name);
    void updateStudent(Integer id, String newName);

    Student findStudentById(Integer id);
    List<Student> findAllStudents(int pageNum, int pageSize);
}
