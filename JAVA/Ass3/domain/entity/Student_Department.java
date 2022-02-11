package RESTdemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="student_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student_Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_id")
    private Department department;

    @Override
    public String toString() {
        return "student_department{" +
                "id=" + id +
                ", stu_name=" + stu.getName() +
                ", department_name=" + department.getName() +
                '}';
    }
}
