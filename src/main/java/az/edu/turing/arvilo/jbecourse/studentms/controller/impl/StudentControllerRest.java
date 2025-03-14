package az.edu.turing.arvilo.jbecourse.studentms.controller.impl;

import az.edu.turing.arvilo.jbecourse.studentms.controller.StudentController;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentControllerRest
        implements StudentController {

    private final StudentService service;

    public StudentControllerRest(StudentService service) {
        this.service = service;
    }

    @PostMapping("/students/create")
    @Override
    public StudentResponse create(@RequestBody StudentCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }

        return service
                .create(request)
                .orElseThrow();
    }

    @GetMapping("/students/{id}")
    @Override
    public StudentResponse getById(@PathVariable Long id) {

        return service
                .getById(id)
                .orElse(null);
    }

    @GetMapping("/students")
    @Override
    public List<StudentResponse> getAll() {

        return service.getAll();
    }

    @PutMapping("/students/update")
    @Override
    public StudentResponse update(@RequestBody StudentUpdateRequest request) {

        return service
                .update(request)
                .orElse(null);
    }

    @DeleteMapping("/students/{id}")
    @Override
    public StudentResponse delete(@PathVariable Long id) {

        return service
                .delete(id)
                .orElseThrow(
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Student not found"
                            );
                        });
    }
}
