package RESTdemo.repository.impl;

import RESTdemo.domain.entity.Department;
import RESTdemo.domain.entity.Student;
import RESTdemo.exception.StudentException;
import RESTdemo.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void createStudent(String name) {
        Student s = new Student();
        s.setName(name);
        em.persist(s);
    }

    @Transactional
    @Override
    public void updateStudent(Integer id, String newName) {
        Student s = null;
        try{
            s = findStudentById(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(s == null){
            s = new Student();
        }
        s.setName(newName);
        em.persist(s);
    }

    @Override
    public Student findStudentById(Integer id) {
        Query query = em.createQuery("SELECT e from Student e where e.id = " + id);
        if(query.getResultList().isEmpty()){
            throw new StudentException("NOT_FOUND");
        }
        Student s = (Student) query.getSingleResult();
        return s;
    }

    @Override
    public List<Student> findAllStudents(int pageNum, int pageSize) {
        Query query = em.createQuery("SELECT 2 from Student 2").setMaxResults(pageSize).setFirstResult(pageSize*(pageNum-1));
        List<Student> list = query.getResultList();
        if(list.isEmpty()){
            throw new StudentException("NOT_FOUND");
        }
        return list;
    }
}
