package az.edu.turing.arvilo.jbecourse.studentms.model.dto.request;

public class StudentUpdateRequest {

    private Long id;
    private String name;
    private String surname;

    public StudentUpdateRequest() {
    }

    public StudentUpdateRequest(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "StudentUpdateRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
