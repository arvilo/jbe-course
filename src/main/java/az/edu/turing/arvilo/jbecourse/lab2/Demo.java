package az.edu.turing.arvilo.jbecourse.lab2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Demo {

    private final static String FILEPATH = "src/main/resources/az/edu/turing/arvilo/jbecourse/lab2/file.txt";

    public String inputString() {
        try (Scanner scanner = new Scanner(new File(FILEPATH))) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNext()) {
                content.append(scanner.next());
            }

            return content.toString();
        } catch (IOException e) {
            System.out.println("FAIL!");
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter input: ");

                return scanner.nextLine();
            }
        }
    }

    public int computeSequence(String text) {
        try {
            if (text == null || text.length() < 2) {
                throw new IllegalArgumentException("text can't be null or have a length less than 2.");
            }
            int num1 = text
                    .chars()
                    .limit(text.length() - 1)
                    .peek(ch -> {
                        if (!Character.isDigit(ch)) {
                            throw new IllegalArgumentException("Content is not number");
                        }
                    })
                    .map(Character::getNumericValue)
                    .sum();
            if (!Character.isDigit(text.charAt(text.length() - 1))) {
                throw new IllegalArgumentException("Content is not number");
            }
            int num2 = Integer.parseInt(text.substring(text.length() - 1));
            if (num2 == 0) {
                throw new ArithmeticException("Last char can't be '0'.");
            }

            return num1 / num2;
        } catch (ArithmeticException | IllegalArgumentException e) {

            return -1;
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.computeSequence(demo.inputString()));
    }
}
