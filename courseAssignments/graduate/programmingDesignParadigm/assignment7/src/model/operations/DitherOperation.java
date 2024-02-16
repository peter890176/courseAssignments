package model.operations;

import model.ImageInterface;


/**
 * Class containing all the methods for dithering operation.
 */
public class DitherOperation extends AbstractClass {

  /**
   * Method to apply Dither filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] ditherImage(ImageInterface imageData) {
    FilterOperations fo = new FilterOperations();
    rgbMatrix = fo.intensityImage(imageData);
    int height = rgbMatrix.length;
    int width = rgbMatrix[0].length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int old_color = rgbMatrix[i][j][0];
        int new_color;
        if (255 - old_color < old_color) {
          new_color = 255;
        } else {
          new_color = 0;
        }
        int error = old_color - new_color;
        rgbMatrix[i][j][0] = new_color;
        rgbMatrix[i][j][1] = new_color;
        rgbMatrix[i][j][2] = new_color;
        if (j != width - 1) {
          int newValue = (thresh(Math.round(((float) rgbMatrix[i][j + 1][0] +
                  ((float) (error * 7 / 16))))));
          rgbMatrix[i][j + 1][0] = newValue;
          rgbMatrix[i][j + 1][1] = newValue;
          rgbMatrix[i][j + 1][2] = newValue;
        }
        if (i != height - 1 && j != 0) {
          int newValue = (thresh(Math.round(((float) rgbMatrix[i + 1][j - 1][0] +
                  ((float) (error * 3 / 16))))));
          rgbMatrix[i + 1][j - 1][0] = newValue;
          rgbMatrix[i + 1][j - 1][1] = newValue;
          rgbMatrix[i + 1][j - 1][2] = newValue;
        }
        if (i != height - 1) {
          int newValue = (thresh(Math.round(((float) rgbMatrix[i + 1][j][0] +
                  ((float) (error * 5 / 16))))));
          rgbMatrix[i + 1][j][0] = newValue;
          rgbMatrix[i + 1][j][1] = newValue;
          rgbMatrix[i + 1][j][2] = newValue;
        }
        if (i != height - 1 && j != width - 1) {
          int newValue = (thresh(Math.round(((float) rgbMatrix[i + 1][j + 1][0] +
                  ((float) (error * 1 / 16))))));
          rgbMatrix[i + 1][j + 1][0] = newValue;
          rgbMatrix[i + 1][j + 1][1] = newValue;
          rgbMatrix[i + 1][j + 1][2] = newValue;
        }
      }
    }
    return rgbMatrix;
  }

  private int thresh(int x) {
    if (x > 255) {
      return 255;
    } else if (x < 0) {
      return 0;
    } else {
      return x;
    }
  }
}
