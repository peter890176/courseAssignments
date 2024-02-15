
/**
 * This interface represents the view features of controllers.
 * Specifically, the latest version view controller should support these functions.
 */
public interface MockControllerFeatures {

  /**
   * This method is for opening a file.
   *
   */
  void openFile(String path);

  /**
   * This method is for choosing the opening file path.
   *
   */
  void chooseLoadFile();

  /**
   * This method is for choosing the saving file path.
   *
   */
  void chooseSaveFile();

  /**
   * This method is for saving a file.
   *
   */
  void saveFile(String path);

  /**
   * This method is for choosing the function that user want.
   *
   */
  void functionChoose();

  /**
   * This method is for starting the image processing.
   *
   */
  void goClick(String func);

  /**
   * This method is for split previewing of the image.
   *
   */
  void split();

  /**
   * This method is to set the view class into current class.
   *
   */
  void setView(MockIView view);
}
