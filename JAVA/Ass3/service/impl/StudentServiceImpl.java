package RESTdemo.service.impl;

import RESTdemo.domain.dto.StudentResponseDTO;
import RESTdemo.domain.entity.Student;
import RESTdemo.repository.StudentRepository;
import RESTdemo.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStudent(String name) {
          repository.createStudent(name);
    }

    @Override
    public void updateStudent(Integer id, String newName) {
           repository.updateStudent(id,newName);
    }

    @Override
    public StudentResponseDTO findStudentById(Integer id) {
        Student s = repository.findStudentById(id);
        return new StudentResponseDTO(s.getName());
    }

    @Override
    public List<StudentResponseDTO> getAllStudent(int pageNum, int pageSize) {
        return repository.findAllStudents(pageNum,pageSize)
                .stream()
                .map(s -> new StudentResponseDTO(s.getName()))
                .collect(Collectors.toList());
    }
}
