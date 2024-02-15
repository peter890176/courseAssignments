import java.util.ArrayList;

/**
 * This interface represents the image processing features that can be done on images.
 */
public interface ImageModel {

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

  /**
   * Turn image into its red component.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up red component.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> redComponent(String imageName);

  /**
   * Turn image into its green component.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up green component.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> greenComponent(String imageName);

  /**
   * Turn image into its blue component.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up blue component.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> blueComponent(String imageName);

  /**
   * Flip image vertically.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the flipped image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> flipVertical(String imageName);

  /**
   * Flip image horizontally.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the flipped image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> flipHorizontal(String imageName);

  /**
   * Brighten (or darken) image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up brightened image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> brightenImage(String imageName, int increaseBy);

  /**
   * Value representation of image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels of the value representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> valueImage(String imageName);

  /**
   * Intensity representation of image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels of the intensity representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> intensityImage(String imageName);

  /**
   * Luma representation of image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels of the luma representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> lumaImage(String imageName);

  /**
   * Blur the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the blurred image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> blurImage(String imageName);

  /**
   * Sharpen the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the sharpened image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sharpenImage(String imageName);

  /**
   * Sepia version of the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the sepia image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sepiaImage(String imageName);

  /**
   * Combined version of the images.
   *
   * @param r the first image to combine.
   * @param g the second image to combine.
   * @param b the third image to combine.
   * @return a nested ArrayList of integers representing pixels that make up the combined image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> combinedImage(String r, String g, String b);


}
