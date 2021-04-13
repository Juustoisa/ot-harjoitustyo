package TeaStorehouse.utils;
import java.util.Scanner;

public class IO {
    private Scanner scanner = new Scanner(System.in);
    
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public double readNumber(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public void printMainMenu() {
        System.out.println("\n"
                + "Choose a command with corresponding number \n"
                + "1. List/edit your Teas \n"
                + "2. List/edit your Notes \n"
                + "3. Add a new Tea \n"
                + "4. Add a new Note \n"
                + "\n0. Close\n");
    }
}
