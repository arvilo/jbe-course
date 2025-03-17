package az.edu.turing.arvilo.jbecourse.studentms.service;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentCreateRequest request);

    List<StudentResponse> getAll();

    StudentResponse getById(Long id);

    StudentResponse update(Long id, StudentCreateRequest request);

    StudentResponse delete(Long id);
}
