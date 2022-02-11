package RESTdemo.service.impl;

import RESTdemo.domain.dto.Student_Department_DTO;
import RESTdemo.domain.entity.Department;
import RESTdemo.domain.entity.Student;
import RESTdemo.repository.Student_Department_Repository;
import RESTdemo.service.Student_Department;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Student_Department_ServiceImpl implements Student_Department{
    private final Student_Department_Repository rp;

    @Autowired
    public Student_Department_ServiceImpl(Student_Department_Repository rp) {
        this.rp = rp;
    }

    @Override
    public void belong(int stu_id, int d_id) {
        rp.belong(stu_id,d_id);
    }

    @Override
    public List<Student_Department_DTO> getAllStudentCourse() {
        return rp.getAllStudent_Department().stream().
                map(s -> new Student_Department_DTO(s.getStu().getName(),s.getDepartment().getName())).
                collect(Collectors.toList());
    }
}
