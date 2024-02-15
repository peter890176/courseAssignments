

import java.util.Scanner;

/**
 * This class provides the main method,
 * and does some preliminary classification of the input arguments.
 */
public class Main {
  /**
   * Three ways to run this program include direct GUI execution,
   * file-based execution, and interactive console interaction.
   */
  public static void main(String[] args) {
    ImageModel imageUtil = new ImageUtil();
    Controller controller = new ImageController(imageUtil);
    ControllerFeatures viewController = new ViewController(imageUtil);

    if (args.length > 1) {
      if (args[0].equals("-file")) {
        controller.action("run " + args[1]);
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
              controller.action(command);
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
      IView view = new ImageView("Assignment6_View");
      viewController.setView(view);
    }
  }
}