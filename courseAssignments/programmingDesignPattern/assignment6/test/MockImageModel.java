import java.util.ArrayList;

/**
 * This interface represents the image processing features that can be done on images.
 */
public interface MockImageModel {


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
   * Value representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the value representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> valueSplitImage(String imageName, int p);

  /**
   * Intensity representation of image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels of the intensity representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> intensityImage(String imageName);

  /**
   * Intensity representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the intensity representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> intensitySplitImage(String imageName, int p);

  /**
   * Luma representation of image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels of the luma representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> lumaImage(String imageName);

  /**
   * Luma representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the luma representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> lumaSplitImage(String imageName, int p);

  /**
   * Blur the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the blurred image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> blurImage(String imageName);

  /**
   * Blur representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the blur representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> blurSplitImage(String imageName, int p);

  /**
   * Sharpen the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the sharpened image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sharpenImage(String imageName);

  /**
   * Sharpen representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the sharpen representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sharpenSplitImage(String imageName, int p);

  /**
   * Sepia version of the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the sepia image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sepiaImage(String imageName);

  /**
   * Sepia representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the sepia representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> sepiaSplitImage(String imageName, int p);

  /**
   * Combined version of the images.
   *
   * @param r the first image to combine.
   * @param g the second image to combine.
   * @param b the third image to combine.
   * @return a nested ArrayList of integers representing pixels that make up the combined image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> combinedImage(String r, String g, String b);

  /**
   * Histogram of the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the histogram image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> histogramImage(String imageName);

  /**
   * Color-corrected version of the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the corrected image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> correctImage(String imageName);

  /**
   * Color-corrected representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param p         the splitting proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the corrected representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> correctSplitImage(String imageName, int p);

  /**
   * Level-adjusted version of the image.
   *
   * @param imageName the name of the image.
   * @return a nested ArrayList of integers representing pixels that make up the adjusted image.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> levelImage(int b, int m, int w, String imageName);

  /**
   * Level-adjusted representation of image with splitting function.
   *
   * @param imageName the name of the image.
   * @param b         the black part of the image.
   * @param m         the middle part of the image.
   * @param w         the white part of the image.
   * @return a nested ArrayList of integers representing pixels of the adjusted representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> levelSplitImage(int b, int m, int w,
                                                           String imageName, int p);

  /**
   * Compression representation of image.
   *
   * @param imageName  the name of the image.
   * @param percentage the compression proportion of the image.
   * @return a nested ArrayList of integers representing pixels of the compressed representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> compressImage(int percentage, String imageName);

  /**
   * To add specific imageName and image into the HashMap.
   *
   * @param imageName the name of the image.
   * @param image     a nested ArrayList of integers
   *                  representing pixels of the compressed representation.
   */
  void addImage(String imageName, ArrayList<ArrayList<ArrayList<Integer>>> image);

  /**
   * To get the specific image from the HashMap.
   *
   * @param imageName the name of the image.
   * @return     a nested ArrayList of integers representing pixels of compressed representation.
   */
  ArrayList<ArrayList<ArrayList<Integer>>> getImage(String imageName);
}
