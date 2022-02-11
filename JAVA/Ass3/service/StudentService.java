package RESTdemo.service;

import RESTdemo.domain.dto.StudentResponseDTO;
import RESTdemo.domain.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    void createStudent(String name);
    void updateStudent(Integer id, String newName);

    StudentResponseDTO findStudentById(Integer id);
    List<StudentResponseDTO> getAllStudent(int pageNumber,int pageSize);
}
