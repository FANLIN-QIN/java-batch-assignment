package RESTdemo.controller;

import RESTdemo.domain.dto.StudentResponseDTO;
import RESTdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService ss;

    @Autowired
    public StudentController(StudentService ss) {
        this.ss = ss;
    }

    @PostMapping("/student")
    public void createStudent(@RequestBody String name){
        ss.createStudent(name);
    }

    @PutMapping("student/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody String newName){
        try {
            ss.updateStudent(id,newName);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDTO> findStudentById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(ss.findStudentById(id), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent(@RequestParam(defaultValue = "1") int pageNum,
                                                                   @RequestParam(defaultValue = "10") int pageSize){
        try{
            return new ResponseEntity<>(ss.getAllStudent(pageNum,pageSize), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
