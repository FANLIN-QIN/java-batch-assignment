package RESTdemo.repository;

import org.springframework.stereotype.Repository;
import RESTdemo.domain.entity.Department;

import java.util.List;

@Repository
public interface DepartmentRepository {
    void createDepartment(String name);
    void updateDepartment(Integer id, String newName);
    Department findDepartmentById(Integer id);
    List<Department> findAllDepartment(int pageNumber, int pageSize);
}
