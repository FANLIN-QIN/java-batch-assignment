package RESTdemo.repository.impl;

import RESTdemo.domain.entity.Department;
import RESTdemo.domain.entity.Student;
import RESTdemo.domain.entity.Student_Department;
import RESTdemo.exception.StudentException;
import RESTdemo.exception.DepartmentException;
import RESTdemo.repository.Student_Department_Repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Student_Department_RepositoryImpl implements Student_Department_Repository{
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void belong(Integer stu_id, Integer d_id) {
        Query query = em.createQuery("SELECT e from Student e where e.id = " + stu_id);
        if(query.getResultList().isEmpty()){
            throw new StudentException("STUDENT_NOT_FOUND");
        }
        Student s = (Student) query.getSingleResult();
        Query query2 = em.createQuery("SELECT e from Department e where e.id = " + d_id);
        if(query2.getResultList().isEmpty()){
            throw new DepartmentException("DEPARTMENT_NOT_FOUND");
        }
        Department d = (Department) query2.getSingleResult();
        Student_Department sd = new Student_Department();
        sd.setDepartment(d);
        sd.setStu(s);
        em.persist(sd);
    }

    @Override
    public List<Student_Department> getAllStudent_Department() {
        Query query = em.createQuery("SELECT e from Student_Department e");
        if(query.getResultList().isEmpty()){
            throw new RuntimeException("NO_RESULT");
        }
        return query.getResultList();
    }
}
