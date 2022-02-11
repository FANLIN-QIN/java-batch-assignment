package RESTdemo.service.impl;

import RESTdemo.domain.dto.DepartmentResponseDTO;
import RESTdemo.domain.entity.Department;
import RESTdemo.repository.DepartmentRepository;
import RESTdemo.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository dp;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository dp) {
        this.dp = dp;
    }


    @Override
    public void createDepartment(String name) {
          dp.createDepartment(name);
    }

    @Override
    public void updateDepartment(Integer id, String newName) {
           dp.updateDepartment(id,newName);
    }

    @Override
    public DepartmentResponseDTO findDepartmentById(Integer id) {
        Department d = dp.findDepartmentById(id);
        return new DepartmentResponseDTO(d.getName());
    }

    @Override
    public List<DepartmentResponseDTO> findAllDepartment(int pageNum, int pageSize) {
        return dp.findAllDepartment(pageNum,pageSize)
                .stream()
                .map(s -> new DepartmentResponseDTO(s.getName()))
                .collect(Collectors.toList());
    }
}
