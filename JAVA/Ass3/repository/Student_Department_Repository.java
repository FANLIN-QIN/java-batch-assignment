package RESTdemo.repository;

import RESTdemo.domain.entity.Student_Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_Department_Repository {
    void belong(Integer stu_id, Integer d_id);
    List<Student_Department> getAllStudent_Department();

}
