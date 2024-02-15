

import java.util.Scanner;

/**
 * This class provides the main method,
 * and does some preliminary classification of the input arguments.
 */
public class MockMain {
  /**
   * Three ways to run this program include direct GUI execution,
   * file-based execution, and interactive console interaction.
   */
  public static void main(String[] args) {
    MockImageModel imageUtil = new MockImageUtil();
    MockController mockController = new MockImageController(imageUtil);
    MockControllerFeatures viewController = new MockViewController(imageUtil);

    if (args.length > 1) {
      if (args[0].equals("-file")) {
        mockController.action("run " + args[1]);
      } else {
        System.out.println("Something went wrong, please check your command.");
      }
    } else if (args.length == 1) {
      if (args[0].equals("-text")) {
        System.out.println("Please enter command:");
        Scanner scan = new Scanner(System.in);
        while (true) {
          try {
            String command = scan.nextLine();
            if (command.equals("exit")) {
              break;
            } else if (command.startsWith("#")) {
              continue;
            } else {
              mockController.action(command);
            }
          } catch (Exception e) {
            System.out.println("Something went wrong, please check your arguments again.");
          }
        }
        scan.close();
      } else {
        System.out.println("Something went wrong, please check your command.");
      }
    } else {
      MockIView view = new MockImageView("Assignment6_View");
      viewController.setView(view);
    }
  }
}