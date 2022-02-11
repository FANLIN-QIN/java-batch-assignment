package RESTdemo.service;

import RESTdemo.domain.dto.Student_Department_DTO;
import RESTdemo.domain.entity.Department;
import RESTdemo.domain.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Student_Department {
    belong(int stu_id, int d_id);
    List<Student_Department_DTO> getAllStudentCourse();
}
