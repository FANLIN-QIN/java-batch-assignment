package RESTdemo.repository.impl;

import RESTdemo.domain.entity.Department;
import RESTdemo.exception.DepartmentException;
import RESTdemo.repository.DepartmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void createDepartment(String name) {
        Department d = new Department();
        d.setName(name);
        em.persist(d);
    }

    @Override
    public void updateDepartment(Integer id, String newName) {
        Department d = null;
        try{
            d = findDepartmentById(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(d == null){
            d = new Department();
        }
        d.setName(newName);
        em.persist(d);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Query query = em.createQuery("SELECT d from Department d where d.id = " + id);
        if(query.getResultList().isEmpty()){
            throw new DepartmentException("NOT_FOUND");
        }
        Department d = (Department) query.getSingleResult();
        return d;
    }

    @Override
    public List<Department> findAllDepartment(int pageNum, int pageSize) {
        Query query = em.createQuery("SELECT 2 from Department 2").setMaxResults(pageSize).setFirstResult(pageSize*(pageNum-1));
        List<Department> list = query.getResultList();
        if(list.isEmpty()){
            throw new DepartmentException("NOT_FOUND");
        }
        return list;
    }
}
