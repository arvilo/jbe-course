package az.edu.turing.arvilo.jbecourse.studentms.repository;

import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Optional<StudentEntity> save(StudentEntity student);

    Optional<StudentEntity> create(StudentEntity student);

    StudentEntity update(StudentEntity student);

    List<StudentEntity> list();

    Optional<StudentEntity> getById(Long id);

    void deleteById(Long id);
}
