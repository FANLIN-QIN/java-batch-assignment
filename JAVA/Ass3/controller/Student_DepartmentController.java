package RESTdemo.controller;

import RESTdemo.domain.dto.Student_Department_DTO;
import RESTdemo.service.Student_Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Student_DepartmentController {
    private final Student_Department sd;

    @Autowired
    public Student_DepartmentController(Student_Department sd) {
        this.sd= sd;
    }

    @PostMapping("/student_department")
    public void belong(@RequestBody String str) {
        String[] arr = str.split(",");
        if(arr.length!=2){
            throw new RuntimeException("WRONG_REQUEST");
        }
        int s_id = Integer.parseInt(arr[0]);
        int d_id = Integer.parseInt(arr[1]);
        sd.register(s_id,d_id);
    }


    @GetMapping("/student_department")
    public ResponseEntity<List<Student_Department_DTO>> getAllStudentDepartment(){
        try {
            return new ResponseEntity<>(sd.getAllStudentDepartment(), HttpStatus.OK);
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
