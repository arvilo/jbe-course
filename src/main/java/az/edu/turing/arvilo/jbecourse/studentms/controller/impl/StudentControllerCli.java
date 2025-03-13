package az.edu.turing.arvilo.jbecourse.studentms.controller.impl;

import az.edu.turing.arvilo.jbecourse.studentms.controller.StudentController;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;

import java.util.List;

public class StudentControllerCli
        implements StudentController {

    private final StudentService service;

    public StudentControllerCli(StudentService service) {
        this.service = service;
    }

    @Override
    public StudentResponse create(StudentCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }

        return service
                .create(request)
                .orElseThrow();
    }

    @Override
    public StudentResponse getById(Long id) {

        return service
                .getById(id)
                .orElse(null);
    }

    @Override
    public List<StudentResponse> getAll() {

        return service.getAll();
    }

    @Override
    public StudentResponse update(StudentUpdateRequest request) {

        return service
                .update(request)
                .orElse(null);
    }

    @Override
    public StudentResponse delete(Long id) {

        return service
                .delete(id)
                .orElse(null);
    }
}
