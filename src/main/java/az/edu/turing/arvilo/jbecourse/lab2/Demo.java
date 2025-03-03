package az.edu.turing.arvilo.jbecourse.lab2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Demo {

    private final static String FILEPATH = "src/main/resources/az/edu/turing/arvilo/jbecourse/lab2/file.txt";

    public String inputString() {
        try (InputStream inputStream = new FileInputStream(FILEPATH)) {
            int data;
            StringBuilder fileContent = new StringBuilder();
            while ((data = inputStream.read()) != -1) {
                fileContent.append((char) data);
            }

            return fileContent.toString();
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
                throw new IllegalArgumentException();
            }
            int num1 = text
                    .chars()
                    .limit(text.length() - 1)
                    .peek(ch -> {
                        if (!Character.isDigit(ch)) {
                            throw new NumberFormatException();
                        }
                    })
                    .map(Character::getNumericValue)
                    .sum();
            int num2 = Integer.parseInt(text.substring(text.length() - 1));

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
