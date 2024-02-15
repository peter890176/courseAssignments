import java.util.ArrayList;

/**
 * This interface represents the controls or actions that a user can perform on images.
 */
public interface MockController {
  /**
   * Allows user to enter commands to perform operations on images.
   *
   * @param command string command entered by the user to do different operations on images.
   */
  void action(String command);

  /**
   * Read an image file in the PPM format.
   *
   * @param filePath the path of the file.
   * @param filename the name of the file.
   * @return a nested ArrayList of integers representing pixels that make up the image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> readPPM(String filePath, String filename);

  /**
   * Read an image file in the PNG or JPG/JPEG format.
   *
   * @param filePath the path of the file.
   * @param filename the name of the file.
   * @return a nested ArrayList of integers representing pixels that make up the image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> readJPGOrPNG(String filePath, String filename);

  /**
   * Write an image file in the PPM format.
   *
   * @param filePath  the path of the file.
   * @param imageName the name of the image.
   */
  void writePPM(String filePath, String imageName);

  /**
   * Write an image file in the PNG or JPG/JPEG format.
   *
   * @param filePath  the path of the file.
   * @param imageName the name of the image.
   */
  void writeJpgOrPng(String filePath, String imageName);
}
