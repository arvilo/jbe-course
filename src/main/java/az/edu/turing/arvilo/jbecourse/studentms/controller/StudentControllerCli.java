package az.edu.turing.arvilo.jbecourse.studentms.controller;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;

import java.util.List;

public class StudentControllerCli {

    private final StudentService service;

    public StudentControllerCli(StudentService service) {
        this.service = service;
    }

    public StudentResponse create(StudentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }

        return service
                .create(request);
    }

    public StudentResponse getById(Long id) {

        return service
                .getById(id);
    }

    public List<StudentResponse> getAll() {

        return service.getAll();
    }

    public StudentResponse update(Long id, StudentRequest request) {

        return service
                .update(id, request);
    }

    public StudentResponse delete(Long id) {

        return service
                .delete(id);
    }
}
