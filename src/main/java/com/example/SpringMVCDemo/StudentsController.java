package com.example.SpringMVCDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StudentsController {
    public static ArrayList<Student> students = new ArrayList<>();
    private String errorMessage = "";

    public StudentsController() {
        students.add(new Student(1, "test1", "9898397451"));
        students.add(new Student(2, "test2", "2394934491"));
        students.add(new Student(3, "test3", "5234892381"));
    }

    @GetMapping("/students")
    public ResponseEntity<ArrayList<Student>> readAll() {
        return new ResponseEntity<ArrayList<Student>>(students, HttpStatus.OK);
    }

    //    @GetMapping("/students/{id}")
//    public ResponseEntity<Student> read(@PathVariable("id") int studentId) {
//        var student = students.stream().filter((animal) -> animal.getId() == studentId).findFirst().get();
//        return new ResponseEntity<Student>(student, HttpStatus.OK);
//    }
    @GetMapping("/student")
    public ResponseEntity<Student> readById(@RequestParam(name = "id") int studentId) {
        var student = students.stream().filter((animal) -> animal.getId() == studentId).findFirst().orElse(new Student(0, "null", "null"));
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student) {
        if (isValidated(student)) {
            students.add(student);
            return new ResponseEntity<Student>(student, HttpStatus.CREATED);
        } else {
            var validationModel = new ValidationModel("create(student)", "isValidated(student)", errorMessage);
            return new ResponseEntity<ValidationModel>(validationModel, HttpStatus.BAD_REQUEST);
        }

    }

    //    @PutMapping("/students/{id}")
//    public ResponseEntity<?> update(@RequestBody Student student,
//                                    @PathVariable("id") int studentId) {
//        var oldStudent = students.stream().filter((animal) -> animal.getId() == studentId).findFirst().get();
//        oldStudent.setName(student.getName());
//        oldStudent.setPhone(student.getPhone());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable("id") int studentId) {
//        try {
            //will replace by Database
//            throw new TimeoutException("Can not connect My SQL Server");

            var oldStudent = students.stream().filter((animal) -> animal.getId() == studentId).findFirst()
                    .orElse(new Student(-1, "null", "null"));
            oldStudent.setName(student.getName());
            oldStudent.setPhone(student.getPhone());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        catch (TimeoutException timeoutException)
//        {
//            var errorModel = new ErrorModel("update(Student student, int studentId)", Arrays.stream(timeoutException.getStackTrace()).findFirst().toString(), timeoutException.getMessage(), timeoutException.getStackTrace().toString());
//            return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.REQUEST_TIMEOUT);
//        }
//        catch (Exception exception) {
//            var errorModel = new ErrorModel("update(Student student, int studentId)",
//                    Arrays.stream(exception.getStackTrace()).findFirst().toString(),
//                    exception.getMessage(),
//                    exception.getStackTrace().toString());
//
//            return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        finally {
//            //Base on discuss with Customer/PM/PO
//            // rollback
        //  retry
//
//        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int studentId) {
        var student = students.stream().filter((animal) -> animal.getId() == studentId).findFirst().orElse(new Student(0, "null", "null"));
        students.remove(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isValidated(Student student) {
        if (student.getName().length() > 50) {
            errorMessage = "StudentName must less than equal 50";
            return false;
        }
        String pattern = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";
        if (student.getPhone().length() != 10) {
            errorMessage = "StudentPhone is not valid";
            return false;
        }
        return true;
    }
}
