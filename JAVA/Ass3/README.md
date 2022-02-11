# REST API 
## DATABASE
### Table：
   1. Student(id(pk), name)
   2.Department(id(pk),name)
   3.Student_Department(id(pk), s_id(fk),d_id(fk))
## REST API Functions
### Student Controller:
    1. Create new Student: POST /student
        RequestBody{name}
    2. Update Student: PUT /student/{id}  
    3. Get student by id: GET /student/{id}
    4. Get Student : GET /student?pageNum=1&pageSize=10
### Department Controllor
    1. Create new department: POST /department
        RequestBody{name}
    2. Update department: PUT /department/{id}
    3. Get department by id:GET /department/{id}
    4. Get department : GET /department?pageNum=1&pageSize=10
### Student_Department Controller
    1. Register student and department: POST  /student_department
         RequestBody{student_ID,department_ID}
    2. Get all student and department: GET /student_department
