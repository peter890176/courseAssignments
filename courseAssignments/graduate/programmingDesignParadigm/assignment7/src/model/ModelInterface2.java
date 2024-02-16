package model;

import java.awt.image.BufferedImage;

/**
 * Interface to represent the model of the image and the preview functionality in
 * addition to all the functions representing the different operations that can
 * be performed on the image.
 */
public interface ModelInterface2 extends ModelInterface {

  /**
   * Function that generates the preview image for the preview functionality
   * with the operation performed on the image.
   *
   * @param originalImage Original buffered image.
   * @param operatedImage Operated buffered image with operation performed on it.
   * @param percent       percentage for the slider to indicate preview percentage.
   * @return buffered Image.
   */
  BufferedImage getPreviewImage(BufferedImage originalImage, BufferedImage operatedImage,
                                int percent);
}
