package az.edu.turing.arvilo.jbecourse.studentms;

import az.edu.turing.arvilo.jbecourse.studentms.controller.StudentController;
import az.edu.turing.arvilo.jbecourse.studentms.controller.impl.StudentControllerCli;
import az.edu.turing.arvilo.jbecourse.studentms.mapper.StudentMapper;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentCreateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.model.dto.request.StudentUpdateRequest;
import az.edu.turing.arvilo.jbecourse.studentms.repository.StudentRepository;
import az.edu.turing.arvilo.jbecourse.studentms.repository.impl.StudentRepositoryFile;
import az.edu.turing.arvilo.jbecourse.studentms.service.StudentService;
import az.edu.turing.arvilo.jbecourse.studentms.service.impl.StudentServiceImpl;

import java.util.Scanner;

public class ConsoleApp {

    private final StudentController controller;
    private final Scanner scanner;

    public ConsoleApp(StudentController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void homePage() {
        System.out.print("\n");
        System.out.print("""
                1)Create
                2)Get by id
                3)Get all
                4)Update
                5)Delete
                Enter:\s""");
        switch (scanner.nextLine()) {
            case "1":
                create();
                break;
            case "2":
                getById();
                break;
            case "3":
                getAll();
                break;
            case "4":
                update();
                break;
            case "5":
                delete();
                break;
        }
    }

    public void create() {
        System.out.print("\n");
        StudentCreateRequest request = new StudentCreateRequest();
        System.out.print("Name: ");
        request.setName(scanner.nextLine());
        System.out.print("Surname: ");
        request.setSurname(scanner.nextLine());
        System.out.println("*".repeat(20));
        System.out.println(controller.create(request));
        homePage();
    }

    public void getById() {
        System.out.print("\n");
        System.out.print("Id: ");
        String input = scanner.nextLine().trim();
        try {
            System.out.println(
                    controller.getById(Long.parseLong(input))
            );
            homePage();
        } catch (NumberFormatException e) {
            getById();
        }
    }

    public void getAll() {
        System.out.print("\n");
        controller
                .getAll()
                .forEach(student -> {
                    System.out.println("*".repeat(20) + "\n" + student);
                });
        homePage();
    }

    public void update() {
        System.out.print("\n");
        System.out.print("id: ");
        Long id = 0L;
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            update();
        }
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.println(
                controller.update(new StudentUpdateRequest(id, name, surname))
        );
        homePage();
    }

    public void delete() {
        System.out.print("\n");
        System.out.print("Id: ");
        String input = scanner.nextLine().trim();
        try {
            System.out.println(
                    controller.delete(Long.parseLong(input))
            );
            homePage();
        } catch (NumberFormatException e) {
            delete();
        }
    }

    public void run() {
        homePage();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentMapper mapper = new StudentMapper();
        StudentRepository repository = new StudentRepositoryFile();
        StudentService service = new StudentServiceImpl(repository, mapper);
        StudentController controller = new StudentControllerCli(service);
        ConsoleApp app = new ConsoleApp(controller, scanner);
        app.run();
    }
}
