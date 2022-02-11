package RESTdemo.controller;

import RESTdemo.domain.dto.DepartmentResponseDTO;
import RESTdemo.domain.dto.StudentResponseDTO;
import RESTdemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService ds;

    @Autowired
    public DepartmentController(DepartmentService ds) {
        this.ds = ds;
    }

    @PostMapping("/department")
    public void createDepartment(@RequestBody String name){
        ds.createDepartment(name);
    }

    @PutMapping("department/{id}")
    public void updateDepartment(@PathVariable Integer id, @RequestBody String newName){
        try {
            ds.updateDepartment(id,newName);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentResponseDTO> findDepartmentById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(ds.findDepartmentById(id), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment(@RequestParam(defaultValue = "1") int pageNum,
                                                                  @RequestParam(defaultValue = "10") int pageSize){
        try{
            return new ResponseEntity<>(ds.findAllDepartment(pageNum,pageSize), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
