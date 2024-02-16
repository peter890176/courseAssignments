import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 * This interface provides some useful methods for the format conversion to avoid code duplications.
 *
 */
public interface ConverterInterface {

  /**
   * This method is for conversion from JPG, JPEG or PNG to nested arrayList.
   *
   * @param picture the buffered type image
   * @param width the width of the image.
   * @param height the height of the image.
   * @return a nested ArrayList of integers representing pixels that make up red component.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> jpgOrPNGToArray(BufferedImage picture,
                                                           int width, int height);

  /**
   * This method is for conversion from ppm to nested arrayList.
   *
   * @param sc the results of reading the ppm file.
   * @param width the width of the image.
   * @param height the height of the image.
   * @return a nested ArrayList of integers representing pixels that make up red component.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> ppmToArray(Scanner sc, int width, int height);

  /**
   * This method is for conversion from nested arrayList to ImageIcon.
   *
   * @param arrayListImage the nested arrayList image
   * @return the image icon Java Swing component.
   */
  ImageIcon arrayListImageToImageIcon(ArrayList<ArrayList<ArrayList<Integer>>> arrayListImage);

}
