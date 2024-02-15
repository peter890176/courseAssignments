package model;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Model class containing new methods implemented, namely the preview functionality.
 */
public class Model2 extends Model implements ModelInterface2 {
  @Override
  public BufferedImage getPreviewImage(BufferedImage originalImage, BufferedImage operatedImage,
                                       int percent) {
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    BufferedImage previewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int operationWidth = (int) ((percent / 100.0) * width);
    Graphics2D g = previewImage.createGraphics();
    g.drawImage(operatedImage, 0, 0, operationWidth, height, 0, 0,
            operationWidth, height, null);
    g.drawImage(originalImage, operationWidth, 0, width, height,
            operationWidth, 0, width, height, null);
    g.dispose();
    return previewImage;
  }
}
