package az.edu.turing.arvilo.jbecourse.studentms.repository.impl;

import az.edu.turing.arvilo.jbecourse.studentms.exception.DbException;
import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryFile
        implements StudentRepository {

    private static final String FILEPATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "resources"
            + File.separator
            + "az"
            + File.separator
            + "edu"
            + File.separator
            + "turing"
            + File.separator
            + "arvilo"
            + File.separator
            + "jbecourse"
            + File.separator
            + "studentms"
            + File.separator
            + "data.json";
    private ObjectMapper objectMapper;

    public StudentRepositoryFile() {
        connect();
    }

    private void connect() {
        if (!fileExist()) {
            resetNextId();
            createDbFile();
        } else if (!isContentValid()) {
            throw new RuntimeException(
                    new DbException(FILEPATH + " file has become corrupt.")
            );
        }
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private synchronized boolean isContentValid() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileInputStream fos = new FileInputStream(new File(FILEPATH))) {
            fos.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(
                    new DbException("Could not read " + FILEPATH + "file.")
            );
        }
        try {
            objectMapper.readValue(new File(FILEPATH), new TypeReference<List<StudentEntity>>() {
            });

            return objectMapper
                    .readValue(
                            new File(FILEPATH), new TypeReference<List<StudentEntity>>() {
                            }
                    ) != null;
        } catch (IOException e) {

            return false;
        }
    }

    private boolean fileExist() {

        return new File(FILEPATH).exists();
    }

    private synchronized void createDbFile() {
        File file = new File(FILEPATH);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(
                    new DbException("Could not create " + FILEPATH + " file.")
            );
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("[]".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(
                    new DbException("Could not write " + FILEPATH + " file.")
            );
        }
    }

    @Override
    public Optional<StudentEntity> save(StudentEntity student) {
        if (student.getId() == null || student.getId() == 0L) {

            return create(student);
        }
        update(student);

        return Optional.of(student);
    }

    @Override
    public synchronized Optional<StudentEntity> create(StudentEntity student) {
        List<StudentEntity> students = list();
        student.setId(generateId());
        students.add(student);
        writeList(students);

        return Optional.of(student);
    }

    @Override
    public synchronized void update(StudentEntity student) {
        writeList(
                list()
                        .stream()
                        .map(entity ->
                                entity.getId().equals(student.getId()) ?
                                        student :
                                        entity
                        )
                        .toList()
        );

    }

    @Override
    public List<StudentEntity> list() {
        try {

            return objectMapper
                    .readValue(
                            new File(FILEPATH),
                            new TypeReference<List<StudentEntity>>() {
                            }
                    );
        } catch (IOException e) {
            throw new RuntimeException(
                    new DbException("Could not read from file.")
            );
        }
    }

    @Override
    public Optional<StudentEntity> getById(Long id) {

        return list()
                .stream()
                .filter(student -> student.getId().equals(id))
                .findAny();
    }

    @Override
    public synchronized void deleteById(Long id) {
        List<StudentEntity> students = list();
        students.remove(
                students
                        .stream()
                        .filter(student -> student.getId().equals(id))
                        .findAny()
                        .orElseThrow()
        );
        writeList(students);
    }

    private synchronized void writeList(List<StudentEntity> entities) {
        if (entities != null) {
            try {
                objectMapper.writeValue(new File(FILEPATH), entities);
            } catch (IOException e) {
                throw new RuntimeException(
                        new DbException("Could not write file.")
                );
            }
        }
    }

    private synchronized Long generateId() {
        String path = String.join(
                File.separator,
                List.of("src", "main", "resources",
                        "az", "edu", "turing", "arvilo",
                        "jbecourse", "studentms", "nextID.bin")
        );
        Long id = -1L;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
            id = dis.readLong();
        } catch (IOException k) {
            throw new RuntimeException(
                    new DbException("Could not generate id.")
            );
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path))) {
            dos.writeLong(id + 1);
        } catch (IOException e) {
            throw new RuntimeException(
                    new DbException("Could not generate id.")
            );
        }
        return id;
    }

    private void resetNextId() {
        String path = String.join(
                File.separator,
                List.of("src", "main", "resources",
                        "az", "edu", "turing", "arvilo",
                        "jbecourse", "studentms", "nextID.bin")
        );
        File file = new File(path);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!fileExist()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeLong(1L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
