package az.edu.turing.arvilo.jbecourse.studentms.mapper;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;

import java.util.Optional;

public class StudentMapper {

    public StudentEntity mapRequestToEntity(StudentCreateRequest request) {

        return request == null ?
                null :
                new StudentEntity(
                        0,
                        request.getName(),
                        request.getSurname(),
                        false
                );
    }

    public StudentResponse mapEntityToResponse(StudentEntity entity) {

        return entity == null ?
                null :
                new StudentResponse(
                        entity.getId(),
                        entity.getName(),
                        entity.getSurname()
                );
    }

    public StudentEntity mapRequestToEntity(StudentUpdateRequest request) {

        return request == null ?
                null :
                new StudentEntity(
                        request.getId(),
                        request.getName(),
                        request.getSurname(),
                        false
                );
    }
}
