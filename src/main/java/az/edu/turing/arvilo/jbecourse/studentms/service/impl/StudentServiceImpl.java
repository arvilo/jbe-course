package az.edu.turing.arvilo.jbecourse.studentms.service.impl;

import az.edu.turing.arvilo.jbecourse.studentms.mapper.StudentMapper;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl
        implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponse create(StudentRequest request) {

        return repository
                .create(mapper.mapRequestToEntity(request))
                .map(mapper::mapEntityToResponse)
                .orElseThrow();
    }

    @Override
    public List<StudentResponse> getAll() {

        return repository
                .list()
                .stream()
                .filter(student -> !student.isDeleted())
                .map(mapper::mapEntityToResponse)
                .toList();
    }

    @Override
    public StudentResponse getById(Long id) {

        return repository
                .getById(id)
                .filter(entity -> !entity.isDeleted())
                .map(mapper::mapEntityToResponse)
                .orElse(null);
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        StudentEntity entity = mapper.mapRequestToEntity(request);
        entity.setId(id);
        try {

            return mapper.mapEntityToResponse(repository.update(entity));
        } catch (NoSuchElementException e) {

            return null;
        }
    }

    @Override
    public StudentResponse delete(Long id) {

        return repository
                .getById(id)
                .map(entity -> {
                    entity.setDeleted(true);

                    return repository.update(entity);
                })
                .map(mapper::mapEntityToResponse)
                .orElse(null);
    }
}
