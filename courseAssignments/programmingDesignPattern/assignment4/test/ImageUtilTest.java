import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests performed on the methods of the ImageUtil class.
 */
public class ImageUtilTest {


  @Test
  public void redComponentPPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_P3.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.redComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array1.get(i).get(j).get(0) - array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void redComponentPNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_image.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.redComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array1.get(i).get(j).get(0) - array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void redComponentJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_image.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.redComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array1.get(i).get(j).get(0) - array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void redComponentJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_image.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.redComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array1.get(i).get(j).get(0) - array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void greenComponentPPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_P3.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.greenComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, array1.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void greenComponentPNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_image.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.greenComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, array1.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void greenComponentJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_image.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.greenComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, array1.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void greenComponentJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_image.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.greenComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, array1.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, (long) array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void blueComponentPPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_P3.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blueComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, array1.get(i).get(j).get(2) - array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void blueComponentPNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_image.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blueComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, array1.get(i).get(j).get(2) - array2.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void blueComponentJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_image.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blueComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, array1.get(i).get(j).get(2) - array2.get(i).get(j).get(2));


      }
    }

  }

  @Test
  public void blueComponentJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_image.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blueComponent("p1");
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, (long) array2.get(i).get(j).get(0));
        assertEquals(0, (long) array2.get(i).get(j).get(1));
        assertEquals(0, array1.get(i).get(j).get(2) - array2.get(i).get(j).get(2));


      }
    }

  }

  @Test
  public void flipVerticalPPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipVertical("p1");

    assertEquals(4, (long) array2.get(0).get(0).get(0));
    assertEquals(5, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(15, (long) array2.get(0).get(1).get(1));
    assertEquals(6, (long) array2.get(0).get(1).get(2));
    assertEquals(1, (long) array2.get(1).get(0).get(0));
    assertEquals(2, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(3, (long) array2.get(1).get(1).get(1));
    assertEquals(3, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void flipVerticalPNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipVertical("p1");

    assertEquals(4, (long) array2.get(0).get(0).get(0));
    assertEquals(5, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(15, (long) array2.get(0).get(1).get(1));
    assertEquals(6, (long) array2.get(0).get(1).get(2));
    assertEquals(1, (long) array2.get(1).get(0).get(0));
    assertEquals(2, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(3, (long) array2.get(1).get(1).get(1));
    assertEquals(3, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void flipVerticalJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipVertical("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(7, (long) array2.get(0).get(0).get(1));
    assertEquals(4, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(9, (long) array2.get(0).get(1).get(1));
    assertEquals(6, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void flipVerticalJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipVertical("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(7, (long) array2.get(0).get(0).get(1));
    assertEquals(4, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(9, (long) array2.get(0).get(1).get(1));
    assertEquals(6, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));

  }


  @Test
  public void flipHorizontalPPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipHorizontal("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(1, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(15, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(4, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(0, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void flipHorizontalPNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipHorizontal("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(1, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(15, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(4, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(0, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void flipHorizontalJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipHorizontal("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(5, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(9, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(4, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void flipHorizontalJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.flipHorizontal("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(5, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(9, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(4, (long) array2.get(1).get(1).get(2));
  }


  @Test
  public void brightenImagePositivePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", 245);

    assertEquals(246, (long) array2.get(0).get(0).get(0));
    assertEquals(247, (long) array2.get(0).get(0).get(1));
    assertEquals(245, (long) array2.get(0).get(0).get(2));
    assertEquals(245, (long) array2.get(0).get(1).get(0));
    assertEquals(248, (long) array2.get(0).get(1).get(1));
    assertEquals(248, (long) array2.get(0).get(1).get(2));
    assertEquals(249, (long) array2.get(1).get(0).get(0));
    assertEquals(250, (long) array2.get(1).get(0).get(1));
    assertEquals(245, (long) array2.get(1).get(0).get(2));
    assertEquals(245, (long) array2.get(1).get(1).get(0));
    assertEquals(255, (long) array2.get(1).get(1).get(1));
    assertEquals(251, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImagePositivePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", 245);

    assertEquals(246, (long) array2.get(0).get(0).get(0));
    assertEquals(247, (long) array2.get(0).get(0).get(1));
    assertEquals(245, (long) array2.get(0).get(0).get(2));
    assertEquals(245, (long) array2.get(0).get(1).get(0));
    assertEquals(248, (long) array2.get(0).get(1).get(1));
    assertEquals(248, (long) array2.get(0).get(1).get(2));
    assertEquals(249, (long) array2.get(1).get(0).get(0));
    assertEquals(250, (long) array2.get(1).get(0).get(1));
    assertEquals(245, (long) array2.get(1).get(0).get(2));
    assertEquals(245, (long) array2.get(1).get(1).get(0));
    assertEquals(255, (long) array2.get(1).get(1).get(1));
    assertEquals(251, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImagePositiveJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", 250);

    assertEquals(250, (long) array2.get(0).get(0).get(0));
    assertEquals(253, (long) array2.get(0).get(0).get(1));
    assertEquals(250, (long) array2.get(0).get(0).get(2));
    assertEquals(250, (long) array2.get(0).get(1).get(0));
    assertEquals(255, (long) array2.get(0).get(1).get(1));
    assertEquals(252, (long) array2.get(0).get(1).get(2));
    assertEquals(250, (long) array2.get(1).get(0).get(0));
    assertEquals(255, (long) array2.get(1).get(0).get(1));
    assertEquals(254, (long) array2.get(1).get(0).get(2));
    assertEquals(250, (long) array2.get(1).get(1).get(0));
    assertEquals(255, (long) array2.get(1).get(1).get(1));
    assertEquals(255, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImagePositiveJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", 250);

    assertEquals(250, (long) array2.get(0).get(0).get(0));
    assertEquals(253, (long) array2.get(0).get(0).get(1));
    assertEquals(250, (long) array2.get(0).get(0).get(2));
    assertEquals(250, (long) array2.get(0).get(1).get(0));
    assertEquals(255, (long) array2.get(0).get(1).get(1));
    assertEquals(252, (long) array2.get(0).get(1).get(2));
    assertEquals(250, (long) array2.get(1).get(0).get(0));
    assertEquals(255, (long) array2.get(1).get(0).get(1));
    assertEquals(254, (long) array2.get(1).get(0).get(2));
    assertEquals(250, (long) array2.get(1).get(1).get(0));
    assertEquals(255, (long) array2.get(1).get(1).get(1));
    assertEquals(255, (long) array2.get(1).get(1).get(2));

  }


  @Test
  public void brightenImageNegativePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", -2);

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(0, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(1, (long) array2.get(0).get(1).get(1));
    assertEquals(1, (long) array2.get(0).get(1).get(2));
    assertEquals(2, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(13, (long) array2.get(1).get(1).get(1));
    assertEquals(4, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImageNegativePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", -2);

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(0, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(1, (long) array2.get(0).get(1).get(1));
    assertEquals(1, (long) array2.get(0).get(1).get(2));
    assertEquals(2, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(13, (long) array2.get(1).get(1).get(1));
    assertEquals(4, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImageNegativeJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", -4);

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(0, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(1, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void brightenImageNegativeJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.brightenImage("p1", -4);

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(0, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(1, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void valueImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.valueImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(3, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(5, (long) array2.get(1).get(0).get(2));
    assertEquals(15, (long) array2.get(1).get(1).get(0));
    assertEquals(15, (long) array2.get(1).get(1).get(1));
    assertEquals(15, (long) array2.get(1).get(1).get(2));

  }


  @Test
  public void valueImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.valueImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(3, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(5, (long) array2.get(1).get(0).get(2));
    assertEquals(15, (long) array2.get(1).get(1).get(0));
    assertEquals(15, (long) array2.get(1).get(1).get(1));
    assertEquals(15, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void valueImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.valueImage("p1");

    assertEquals(3, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(5, (long) array2.get(0).get(1).get(0));
    assertEquals(5, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(7, (long) array2.get(1).get(0).get(0));
    assertEquals(7, (long) array2.get(1).get(0).get(1));
    assertEquals(7, (long) array2.get(1).get(0).get(2));
    assertEquals(9, (long) array2.get(1).get(1).get(0));
    assertEquals(9, (long) array2.get(1).get(1).get(1));
    assertEquals(9, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void valueImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.valueImage("p1");

    assertEquals(3, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(5, (long) array2.get(0).get(1).get(0));
    assertEquals(5, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(7, (long) array2.get(1).get(0).get(0));
    assertEquals(7, (long) array2.get(1).get(0).get(1));
    assertEquals(7, (long) array2.get(1).get(0).get(2));
    assertEquals(9, (long) array2.get(1).get(1).get(0));
    assertEquals(9, (long) array2.get(1).get(1).get(1));
    assertEquals(9, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void intensityImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.intensityImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(1, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(3, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(3, (long) array2.get(1).get(0).get(2));
    assertEquals(7, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void intensityImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.intensityImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(1, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(3, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(3, (long) array2.get(1).get(0).get(2));
    assertEquals(7, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void intensityImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.intensityImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(1, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(3, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(3, (long) array2.get(1).get(0).get(2));
    assertEquals(5, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(5, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void intensityImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.intensityImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(1, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(3, (long) array2.get(1).get(0).get(0));
    assertEquals(3, (long) array2.get(1).get(0).get(1));
    assertEquals(3, (long) array2.get(1).get(0).get(2));
    assertEquals(5, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(5, (long) array2.get(1).get(1).get(2));

  }

  @Test
  public void lumaImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.lumaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(11, (long) array2.get(1).get(1).get(0));
    assertEquals(11, (long) array2.get(1).get(1).get(1));
    assertEquals(11, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void lumaImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.lumaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(2, (long) array2.get(0).get(1).get(0));
    assertEquals(2, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(11, (long) array2.get(1).get(1).get(0));
    assertEquals(11, (long) array2.get(1).get(1).get(1));
    assertEquals(11, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void lumaImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.lumaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(4, (long) array2.get(0).get(1).get(0));
    assertEquals(4, (long) array2.get(0).get(1).get(1));
    assertEquals(4, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(5, (long) array2.get(1).get(0).get(2));
    assertEquals(7, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void lumaImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.lumaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(4, (long) array2.get(0).get(1).get(0));
    assertEquals(4, (long) array2.get(0).get(1).get(1));
    assertEquals(4, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(5, (long) array2.get(1).get(0).get(2));
    assertEquals(7, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void blurImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blurImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(1, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(1, (long) array2.get(1).get(0).get(2));
    assertEquals(1, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void blurImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blurImage("p1");

    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(1, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(1, (long) array2.get(1).get(0).get(2));
    assertEquals(1, (long) array2.get(1).get(1).get(0));
    assertEquals(5, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void blurImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blurImage("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(2, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(4, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void blurImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.blurImage("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(3, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(4, (long) array2.get(1).get(0).get(1));
    assertEquals(2, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(4, (long) array2.get(1).get(1).get(1));
    assertEquals(2, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sharpenImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sharpenImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(8, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(1, (long) array2.get(0).get(1).get(0));
    assertEquals(9, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(10, (long) array2.get(1).get(0).get(1));
    assertEquals(2, (long) array2.get(1).get(0).get(2));
    assertEquals(1, (long) array2.get(1).get(1).get(0));
    assertEquals(18, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sharpenImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sharpenImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(8, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(1, (long) array2.get(0).get(1).get(0));
    assertEquals(9, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(10, (long) array2.get(1).get(0).get(1));
    assertEquals(2, (long) array2.get(1).get(0).get(2));
    assertEquals(1, (long) array2.get(1).get(1).get(0));
    assertEquals(18, (long) array2.get(1).get(1).get(1));
    assertEquals(7, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sharpenImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sharpenImage("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(8, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(10, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(11, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(13, (long) array2.get(1).get(1).get(1));
    assertEquals(8, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sharpenImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sharpenImage("p1");

    assertEquals(0, (long) array2.get(0).get(0).get(0));
    assertEquals(8, (long) array2.get(0).get(0).get(1));
    assertEquals(3, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(10, (long) array2.get(0).get(1).get(1));
    assertEquals(5, (long) array2.get(0).get(1).get(2));
    assertEquals(0, (long) array2.get(1).get(0).get(0));
    assertEquals(11, (long) array2.get(1).get(0).get(1));
    assertEquals(6, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(13, (long) array2.get(1).get(1).get(1));
    assertEquals(8, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sepiaImagePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_small.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sepiaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(3, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(13, (long) array2.get(1).get(1).get(0));
    assertEquals(11, (long) array2.get(1).get(1).get(1));
    assertEquals(9, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sepiaImagePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/PNG_small.png", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sepiaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(1, (long) array2.get(0).get(0).get(2));
    assertEquals(3, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(2, (long) array2.get(0).get(1).get(2));
    assertEquals(5, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(13, (long) array2.get(1).get(1).get(0));
    assertEquals(11, (long) array2.get(1).get(1).get(1));
    assertEquals(9, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sepiaImageJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1
            = image.readJPGOrPNG("res2/JPG_small.jpg", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sepiaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(4, (long) array2.get(0).get(1).get(0));
    assertEquals(4, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(6, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(8, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(6, (long) array2.get(1).get(1).get(2));


  }

  @Test
  public void sepiaImageJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_small.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.sepiaImage("p1");

    assertEquals(2, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(2, (long) array2.get(0).get(0).get(2));
    assertEquals(4, (long) array2.get(0).get(1).get(0));
    assertEquals(4, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(6, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(4, (long) array2.get(1).get(0).get(2));
    assertEquals(8, (long) array2.get(1).get(1).get(0));
    assertEquals(7, (long) array2.get(1).get(1).get(1));
    assertEquals(6, (long) array2.get(1).get(1).get(2));


  }


  @Test
  public void combinePPM() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_red.ppm", "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readPPM("res2/PPM_green.ppm", "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readPPM("res2/PPM_blue.ppm", "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.combinedImage("p1", "p2", "p3");

    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array4.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array4.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, array4.get(i).get(j).get(2) - array3.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void combinePNG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/PNG_image_red.png",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readJPGOrPNG("res2/PNG_image_green.png",
            "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readJPGOrPNG("res2/PNG_image_blue.png",
            "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.combinedImage("p1", "p2", "p3");

    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array4.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array4.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, array4.get(i).get(j).get(2) - array3.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void combineJPG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPG_image_red.jpg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readJPGOrPNG("res2/JPG_image_green.jpg",
            "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readJPGOrPNG("res2/JPG_image_blue.jpg",
            "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.combinedImage("p1", "p2", "p3");

    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array4.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array4.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, array4.get(i).get(j).get(2) - array3.get(i).get(j).get(2));

      }
    }

  }

  @Test
  public void combineJPEG() {
    ImageUtil image = new ImageUtil();

    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_image_red.jpeg",
            "p1");
    ArrayList<ArrayList<ArrayList<Integer>>> array2
            = image.readJPGOrPNG("res2/JPEG_image_green.jpeg", "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3
            = image.readJPGOrPNG("res2/JPEG_image_blue.jpeg", "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.combinedImage("p1", "p2", "p3");

    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array4.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array4.get(i).get(j).get(1) - array2.get(i).get(j).get(1));
        assertEquals(0, array4.get(i).get(j).get(2) - array3.get(i).get(j).get(2));

      }
    }

  }


  @Test
  public void splitPPM() {
    ImageUtil image = new ImageUtil();

    //Original
    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readPPM("res2/PPM_P3.ppm", "p1");

    //Split
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readPPM("res2/PPM_red.ppm", "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readPPM("res2/PPM_green.ppm", "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.readPPM("res2/PPM_blue.ppm", "p4");

    //Combine back
    ArrayList<ArrayList<ArrayList<Integer>>> array5 = image.combinedImage("p2", "p3", "p4");

    // Check if the combined image array is same as the original image array before splitting
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array5.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array5.get(i).get(j).get(1) - array1.get(i).get(j).get(1));
        assertEquals(0, array5.get(i).get(j).get(2) - array1.get(i).get(j).get(2));

      }
    }
  }

  /*
  @Test
  public void splitPNG() {
    ImageUtil image = new ImageUtil();

    //Original
    ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/PNG_image.png",
     "p1");

    //Split
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readPPM("res2/PPM_image_red.ppm",
            "p2");
    ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readPPM("res2/PPM_image_green.ppm",
            "p3");
    ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.readPPM("res2/PPM_image_blue.ppm",
            "p4");

    //Combine back
    ArrayList<ArrayList<ArrayList<Integer>>> array5 = image.combinedImage("p2", "p3", "p4");

    // Check if the combined image array is same as the original image array before splitting
    for (int i = 0; i < array1.size(); i++) {
      for (int j = 0; j < array1.get(0).size(); j++) {
        assertEquals(0, array5.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
        assertEquals(0, array5.get(i).get(j).get(1) - array1.get(i).get(j).get(1));
        assertEquals(0, array5.get(i).get(j).get(2) - array1.get(i).get(j).get(2));
      }
    }
  }
  */
  /*
    @Test
    public void splitJPG() {
      ImageUtil image = new ImageUtil();

      //Original
      ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPG_image.jpg"
      , "p1");

      //Split
      ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readPPM("res2/PPM_image_red.ppm",
              "p2");
      ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readPPM("res2/PPM_image_green.ppm",
              "p3");
      ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.readPPM("res2/PPM_image_blue.ppm",
              "p4");

      //Combine back
      ArrayList<ArrayList<ArrayList<Integer>>> array5 = image.combinedImage("p2", "p3", "p4");

      // Check if the combined image array is same as the original image array before splitting
      for(int i = 0 ; i < array1.size(); i++) {
        for (int j=0 ; j < array1.get(0).size() ; j++) {
          assertEquals(0, array5.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
          assertEquals(0, array5.get(i).get(j).get(1) - array1.get(i).get(j).get(1));
          assertEquals(0, array5.get(i).get(j).get(2) - array1.get(i).get(j).get(2));

        }
      }

    }


    @Test
    public void splitJPEG() {
      ImageUtil image = new ImageUtil();

      //Original
      ArrayList<ArrayList<ArrayList<Integer>>> array1 = image.readJPGOrPNG("res2/JPEG_image.jpeg"
      , "p1");

      //Split
      ArrayList<ArrayList<ArrayList<Integer>>> array2 = image.readPPM("res2/PPM_image_red.ppm",
              "p2");
      ArrayList<ArrayList<ArrayList<Integer>>> array3 = image.readPPM("res2/PPM_image_green.ppm",
              "p3");
      ArrayList<ArrayList<ArrayList<Integer>>> array4 = image.readPPM("res2/PPM_image_blue.ppm",
              "p4");

      //Combine back
      ArrayList<ArrayList<ArrayList<Integer>>> array5 = image.combinedImage("p2", "p3", "p4");

      // Check if the combined image array is same as the original image array before splitting
      for(int i = 0 ; i < array1.size(); i++) {
        for (int j=0 ; j < array1.get(0).size() ; j++) {
          assertEquals(0, array5.get(i).get(j).get(0) - array1.get(i).get(j).get(0));
          assertEquals(0, array5.get(i).get(j).get(1) - array1.get(i).get(j).get(1));
          assertEquals(0, array5.get(i).get(j).get(2) - array1.get(i).get(j).get(2));

        }
      }
    }
*/

  /**
   * To test whether the action function with correct command works successfully.
   */
  @Test
  public void actionCorrect() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("load res2/JPG_Example_Original.jpg j0");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the action function with wrong format works successfully.
   */
  @Test
  public void actionWrongFormat() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("load res2/JPG_Example_Original.jpgggggggg j0");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the action function with wrong number(more) of arguments works successfully.
   */
  @Test
  public void actionWrongNumberMoreArguments() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("load res2/JPG_Example_Original.jpg j0 j1 j2");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the action function with wrong number(less) of arguments works successfully.
   */
  @Test
  public void actionWrongNumberLessArguments() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("load res2/JPG_Example_Original.jpg");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the action function with # sign works successfully.
   */
  @Test
  public void actionWithPonderSign() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("#load res2/JPG_Example_Original.jpg");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the run file function in action with correct run file works successfully.
   */
  @Test
  public void actionRunFile() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("run res2/runFile.txt");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

  /**
   * To test whether the run file function in action with incorrect run file works successfully.
   */
  @Test
  public void actionRunErrorFile() {
    ImageUtil image = new ImageUtil();
    try {
      image.action("run res2/runFileError.txt");
    } catch (Exception e) {
      fail("Should have no exception");
    }
  }

}