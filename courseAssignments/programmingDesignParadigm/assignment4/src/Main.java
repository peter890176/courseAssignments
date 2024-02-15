import java.util.Scanner;

/**
 * This class allows users to pass in commands as input to the console.
 */
public class Main {
  /**
   * The main method.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ImageUtil util = new ImageUtil();
    while (true) {
      String command = scan.nextLine();
      if (command.equals("exit")) {
        break;
      } else if (command.startsWith("#")) {
        continue;
      } else {
        util.action(command);
      }
    }
    scan.close();
  }
}
