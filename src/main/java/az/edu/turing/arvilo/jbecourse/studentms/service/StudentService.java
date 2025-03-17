package az.edu.turing.arvilo.jbecourse.studentms.service;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest request);

    List<StudentResponse> getAll();

    StudentResponse getById(Long id);

    StudentResponse update(Long id, StudentRequest request);

    StudentResponse delete(Long id);
}
