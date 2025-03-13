package az.edu.turing.arvilo.jbecourse.studentms.service;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<StudentResponse> create(StudentCreateRequest request);

    List<StudentResponse> getAll();

    Optional<StudentResponse> getById(Long id);

    Optional<StudentResponse> update(StudentUpdateRequest request);

    Optional<StudentResponse> delete(Long id);
}
