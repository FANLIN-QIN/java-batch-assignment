package RESTdemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Student_Department> student_department= new ArrayList<>();

    public List<Student_Department> getStudent_Department() {
        return student_department;
    }

    public void setStudent_Department(List<Student_Department> student_department) {
        this.student_department = student_department;
    }

    public void add_Student_Department(Student_Department sd) {
        this.student_department.add(sd);
    }

    @Override
    public String toString() {
        return "department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
}
