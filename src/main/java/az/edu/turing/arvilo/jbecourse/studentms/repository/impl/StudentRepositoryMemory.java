package az.edu.turing.arvilo.jbecourse.studentms.repository.impl;

import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryMemory
        implements StudentRepository {

    private static final List<StudentEntity> students = new ArrayList<>();
    private static long nextId = 1;

    @Override
    public Optional<StudentEntity> save(StudentEntity student) {
        if (student.getId() == null || student.getId() == 0L) {

            return create(student);
        }
        update(student);

        return Optional.of(student);
    }

    @Override
    public Optional<StudentEntity> create(StudentEntity student) {
        student.setId(generateId());
        students.add(student);

        return Optional.of(student);
    }

    @Override
    public void update(StudentEntity student) {

        map(
                student,
                students
                        .stream()
                        .filter(
                                student1 ->
                                        student1.getId().equals(student.getId())
                        )
                        .findAny()
                        .orElseThrow()
        );
    }

    @Override
    public List<StudentEntity> list() {

        return students;
    }

    @Override
    public Optional<StudentEntity> getById(Long id) {

        return students
                .stream()
                .filter(student -> student.getId().equals(id))
                .findAny();
    }

    @Override
    public void deleteById(Long id) {
        students.remove(
                students
                        .stream()
                        .filter(student -> student.getId().equals(id))
                        .findAny()
                        .orElseThrow()
        );
    }

    private static long generateId() {

        return nextId++;
    }

    private void map(StudentEntity source, StudentEntity target) {
        Arrays.stream(StudentEntity.class.getDeclaredFields())
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(target, field.get(source));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}