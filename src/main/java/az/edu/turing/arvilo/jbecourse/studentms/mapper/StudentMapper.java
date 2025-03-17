package az.edu.turing.arvilo.jbecourse.studentms.mapper;

import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentEntity mapRequestToEntity(StudentRequest request) {

        return request == null ?
                null :
                new StudentEntity(
                        0L,
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
}
