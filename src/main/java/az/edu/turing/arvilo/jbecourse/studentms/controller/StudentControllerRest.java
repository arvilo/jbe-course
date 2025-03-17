package az.edu.turing.arvilo.jbecourse.studentms.controller;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentControllerRest {

    private final StudentService service;

    public StudentControllerRest(StudentService service) {
        this.service = service;
    }

    @PostMapping("/students/create")
    public StudentResponse create(@RequestBody StudentCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }

        return service
                .create(request);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getById(@PathVariable Long id) {

        return service
                .getById(id);
    }

    @GetMapping("/students")
    public List<StudentResponse> getAll() {

        return service.getAll();
    }

    @PutMapping("/students/update/{id}")
    public StudentResponse update(@PathVariable Long id,
                                  @RequestBody StudentCreateRequest request) {

        return service
                .update(id, request);
    }

    @DeleteMapping("/students/{id}")
    public StudentResponse delete(@PathVariable Long id) {

        return service
                .delete(id);
    }
}
