package az.edu.turing.arvilo.jbecourse.studentms.model.dto.request;

public class StudentCreateRequest {

    private String name;
    private String surname;

    public StudentCreateRequest() {
    }

    public StudentCreateRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
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
        return "StudentCreateRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
