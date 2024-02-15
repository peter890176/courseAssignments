package model.operations;

import model.ImageInterface;

/**
 * Class containing all the operations involving applying a filter to an image.
 */
public class FilterOperations extends AbstractClass {

  /**
   * Method to apply sepia filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] sepiaImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    double[][] mat = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return getOutputMatrix(width, height, mat);
  }


  /**
   * Method to apply luma filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] lumaImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    double[][] mat = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
                      {0.2126, 0.7152, 0.0722}};
    return getOutputMatrix(width, height, mat);
  }

  private int[][][] getOutputMatrix(int width, int height, double[][] mat) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double[] pixel = {rgbMatrix[x][y][0], rgbMatrix[x][y][1], rgbMatrix[x][y][2]};
        double[] result = matrixMultiply(mat, pixel);
        for (int i = 0; i < 3; i++) {
          result[i] = clampValue(result[i]);
          newRGBMatrix[x][y][i] = (int) result[i];
        }
      }
    }
    return newRGBMatrix;
  }

  private double[] matrixMultiply(double[][] matrix, double[] vector) {
    double[] result = new double[matrix.length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < vector.length; j++) {
        result[i] += matrix[i][j] * vector[j];
      }
    }
    return result;
  }

  /**
   * Method to apply intensity filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] intensityImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;

    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double temp = (double) (rgbMatrix[x][y][0] + rgbMatrix[x][y][1] +
                rgbMatrix[x][y][2]) / 3;
        temp = clampValue(temp);
        newRGBMatrix[x][y][0] = (int) temp;
        newRGBMatrix[x][y][1] = (int) temp;
        newRGBMatrix[x][y][2] = (int) temp;
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to apply value filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] valueImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double temp = Math.max(rgbMatrix[x][y][0], Math.max(rgbMatrix[x][y][1],
                rgbMatrix[x][y][2]));
        temp = clampValue(temp);
        newRGBMatrix[x][y][0] = (int) temp;
        newRGBMatrix[x][y][1] = (int) temp;
        newRGBMatrix[x][y][2] = (int) temp;
      }
    }
    return newRGBMatrix;
  }
}