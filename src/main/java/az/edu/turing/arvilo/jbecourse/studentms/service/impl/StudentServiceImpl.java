package az.edu.turing.arvilo.jbecourse.studentms.service.impl;

import az.edu.turing.arvilo.jbecourse.studentms.mapper.StudentMapper;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.response.StudentResponse;
import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StudentServiceImpl
        implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<StudentResponse> create(StudentCreateRequest request) {

        return repository
                .create(mapper.mapRequestToEntity(request))
                .map(mapper::mapEntityToResponse);
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
    public Optional<StudentResponse> getById(Long id) {

        return repository
                .getById(id)
                .filter(entity -> !entity.isDeleted())
                .map(mapper::mapEntityToResponse);
    }

    @Override
    public Optional<StudentResponse> update(StudentUpdateRequest request) {
        StudentEntity entity = mapper.mapRequestToEntity(request);
        try {
            repository.update(entity);

            return getById(entity.getId());
        } catch (NoSuchElementException e) {

            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentResponse> delete(Long id) {

        return repository
                .getById(id)
                .map(entity -> {
                    entity.setDeleted(true);
                    repository.update(entity);

                    return entity;
                })
                .map(mapper::mapEntityToResponse);
    }
}
