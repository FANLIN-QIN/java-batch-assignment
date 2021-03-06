package RESTdemo.service;

import RESTdemo.domain.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    createDepartment(String name);
    updateDepartment(Integer id, String newName);
    DepartmentResponseDTO findDepartmentById(Integer id);
    List<DepartmentResponseDTO> findAllDepartment(int pageNumber,int pageSize);
}
