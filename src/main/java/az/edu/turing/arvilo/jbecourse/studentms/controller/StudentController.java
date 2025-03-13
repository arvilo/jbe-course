package az.edu.turing.arvilo.jbecourse.studentms.controller;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;

import java.util.List;

public interface StudentController {

    StudentResponse create(StudentCreateRequest request);

    StudentResponse getById(Long id);

    List<StudentResponse> getAll();

    StudentResponse update(StudentUpdateRequest request);

    StudentResponse delete(Long id);
}
