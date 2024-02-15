import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 * This class implements the specific details of the conversion methods to avoid code duplications.
 */
public class Converter implements ConverterInterface {

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> jpgOrPNGToArray(BufferedImage picture,
                                                                  int width, int height) {
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        Color x = new Color(picture.getRGB(j, i));
        int r = x.getRed();
        int g = x.getGreen();
        int b = x.getBlue();
        rgb.add(r);
        rgb.add(g);
        rgb.add(b);
        row.add(rgb);
      }
      image.add(row);
    }
    return image;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> ppmToArray(Scanner sc, int width, int height) {
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        rgb.add(r);
        rgb.add(g);
        rgb.add(b);
        row.add(rgb);
      }
      image.add(row);
    }
    return image;
  }

  @Override
  public ImageIcon arrayListImageToImageIcon(
          ArrayList<ArrayList<ArrayList<Integer>>> arrayListImage) {
    int height = arrayListImage.size();
    int width = arrayListImage.get(0).size();
    BufferedImage outputDisplay = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = arrayListImage.get(i).get(j);
        Color color = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
        int c = color.getRGB();
        outputDisplay.setRGB(j, i, c);
      }
    }
    ImageIcon icon = new ImageIcon(outputDisplay);
    return icon;
  }
}
