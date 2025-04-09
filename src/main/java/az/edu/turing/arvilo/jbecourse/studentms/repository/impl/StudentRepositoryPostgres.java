package az.edu.turing.arvilo.jbecourse.studentms.repository.impl;

import az.edu.turing.arvilo.jbecourse.studentms.model.entity.StudentEntity;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryPostgres
        implements StudentRepository {

    private Connection connection;

    public StudentRepositoryPostgres() {
        getConnection();
        String createTableSql = """
                create table if not exists students (
                id SERIAL PRIMARY KEY,
                name varchar(30),
                surname varchar(30),
                deleted bool
                )""";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getConnection() {
        String port = "54320";
        String dbName = "studentms";
        String user = "postgres";
        String password = "123456";
        String url = String.format("jdbc:postgresql://localhost:%s/%s", port, dbName);
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<StudentEntity> save(StudentEntity student) {
        if (student.getId() == null || student.getId() == 0L) {

            return create(student);
        }

        return Optional.of(update(student));
    }

    @Override
    public Optional<StudentEntity> create(StudentEntity student) {
        getConnection();
        String sql = """
                insert into 
                students (name, surname, deleted)
                values (?, ?, ?)
                returning id""";
        Optional<StudentEntity> studentEntity;
        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
            pStmt.setString(1, student.getName());
            pStmt.setString(2, student.getSurname());
            pStmt.setBoolean(3, student.isDeleted());
            ResultSet resultSet = pStmt.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                studentEntity = Optional.of(student);
            } else {
                throw new SQLException();
            }
            if (resultSet.next()) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return studentEntity;
    }

    @Override
    public StudentEntity update(StudentEntity student) {
        getConnection();
        String sql = """
                update students 
                set name = ?,
                surname = ?,
                deleted = ?
                where id = ?""";
        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
            pStmt.setString(1, student.getName());
            pStmt.setString(2, student.getSurname());
            pStmt.setBoolean(3, student.isDeleted());
            pStmt.setLong(4, student.getId());
            if (pStmt.executeUpdate() != 1) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return student;
    }

    @Override
    public List<StudentEntity> list() {
        getConnection();
        List<StudentEntity> list;
        String sql = "select * from students";
        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = pStmt.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(
                        new StudentEntity(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getBoolean("deleted")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

    @Override
    public Optional<StudentEntity> getById(Long id) {
        getConnection();
        Optional<StudentEntity> studentEntity;
        String sql = "select * from students where id = ?";
        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
            pStmt.setLong(1, id);
            ResultSet resultSet = pStmt.executeQuery();
            if (resultSet.next()) {
                studentEntity = Optional.of(
                        new StudentEntity(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getBoolean("deleted")
                        )
                );
            } else {
                studentEntity = Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return studentEntity;
    }

    @Override
    public synchronized void deleteById(Long id) {
        getConnection();
        String sql = """
                delete from students 
                where id = ?""";
        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
            pStmt.setLong(4, id);
            if (pStmt.executeUpdate() != 1) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
