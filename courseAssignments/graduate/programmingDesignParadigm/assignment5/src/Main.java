import java.util.Scanner;

/**
 * This class allows users to pass in commands as input to the console.
 */
public class Main {
  /**
   * The main method.
   */
  public static void main(String[] args) {
    Controller controller = new ImageController();
    if (args.length >= 1) {
      if (args[0].equals("-file")) {
        controller.action("run " + args[1]);
      } else {
        System.out.println("Something went wrong, please check your command.");
      }
    } else {
      Scanner scan = new Scanner(System.in);
      while (true) {
        try {
          String command = scan.nextLine();
          if (command.equals("exit")) {
            break;
          } else if (command.startsWith("#")) {
            continue;
          } else {
            controller.action(command);
          }
        } catch (Exception e) {
          System.out.println("Something went wrong, please check your arguments again.");
        }
      }
      scan.close();
    }
  }
}
