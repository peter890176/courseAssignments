import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class implements the specific details of the conversion methods to avoid code duplications.
 *
 */
public class MockConverter implements MockConverterInterface {

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
}
