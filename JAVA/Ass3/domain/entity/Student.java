package RESTdemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Student_Department> student_department = new ArrayList<>();

    public List<Student_Department> getStudent_Department() {
        return student_department;
    }

    public void setStudent_Department(List<Student_Department> student_department) {
        this.student_department = student_department;
    }

    public void add_Student_Department(Student_Department sd){
        this.student_department.add(sd);
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

