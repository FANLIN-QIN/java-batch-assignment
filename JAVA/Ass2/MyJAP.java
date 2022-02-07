package hw2;

import org.hibernate.jpa.HibernatePersistenceProvider;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class MyJAP {
    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("demo");
        dataSource.setPassword("1234");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan( "hw2");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        MyJAP jpa = new MyJAP();
        DataSource dataSource = jpa.getDataSource();
        Properties properties = jpa.getProperties();
        EntityManagerFactory entityManagerFactory = jpa.entityManagerFactory(dataSource,properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

        selectData(em, "Student");
        selectById(em,"Student", 2);
        joinData(em);
        removeData(em);
    }

    // 4. write jpql to select data / select data by id / join data
    //   5. write jpql to remove data

    public static void selectData(EntityManager em, String tableName){
        Query query = em.createQuery("SELECT e From "+ tableName +" e");
        List<Object> list = (List<Object>) query.getResultList();
        System.out.println(list);
    }

    public static void selectById(EntityManager em, String tableName, Integer id){
        Query query = em.createQuery("SELECT e From "+ tableName +" e" + " WHERE " + "e.id = "+ id);
        List<Object> list =  (List<Object>) query.getResultList();
        System.out.println(list);
    }

    public static void joinData(EntityManager em){
        //merge join
        Query query = em.createQuery("SELECT sd.stu_name, sd.department_name from Department d join Student_Department sd on d.id = sd.department_id ");
        List<Student_Department> list = (List<Student_Department>)query.getResultList();
        System.out.println(list);
    }

    private static void removeData(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select e from Student e join e.Student_Department sd where e.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        Iterator<Student_Department> itr = s.getStudent_Department().iterator();
        while(itr.hasNext()) {
            Student_Department sd = itr.next();
            if(sd.getStu().getId().equals("9")) {
                itr.remove();
            }
        }
        tx.commit();
    }






}
