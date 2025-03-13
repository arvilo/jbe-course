package az.edu.turing.arvilo.jbecourse.studentms.model.entity;

public class StudentEntity {

    private Long id;
    private String name;
    private String surname;
    private boolean deleted;

    public StudentEntity() {
    }

    public StudentEntity(long id, String name, String surname, boolean deleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
