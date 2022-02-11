package RESTdemo.domain.dto;

public class Student_Department_DTO {
    String StudentName, DepartmentName;

    public Student_Department_DTO(String StudentName, String DepartmentName) {
        this.StudentName = StudentName;
        this.DepartmentName = DepartmentName;
    }

    public String getDepartmentName() {

        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {

        this.DepartmentName = DepartmentName;
    }

    public String getStudentName() {

        return StudentName;
    }

    public void setStudentName(String StudentName) {

        this.StudentName = StudentName;
    }
}
