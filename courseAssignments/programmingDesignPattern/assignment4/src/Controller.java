/**
 * This interface represents the controls or actions that a user can perform on images.
 */
public interface Controller {
  /**
   * Allows user to enter commands to perform operations on images.
   *
   * @param command string command entered by the user to do different operations on images.
   */
  void action(String command);
}
