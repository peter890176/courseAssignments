import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * This class is a test class for how the controller should work in this new MVC design.
 */
public class MockViewControllerTest {


  @Test
  public void testOpenFile() {
    MockImageModel m = new MockImageUtil();
    MockControllerFeatures c = new MockViewController(m);
    MockIView view = new MockImageView("Assignment6_View");
    c.setView(view);
    String s = "C:\\Users\\Admin\\Downloads"
            + "\\assignment6received\\assignment6received\\res2\\PPM_small.ppm";
    c.openFile(s);
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = m.getImage("currentImage");
    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(15, (long) array2.get(1).get(1).get(1));
    assertEquals(6, (long) array2.get(1).get(1).get(2));
  }


  @Test
  public void testFunctionSelectAndGo() {
    MockImageModel m = new MockImageUtil();
    MockControllerFeatures c = new MockViewController(m);
    MockIView view = new MockImageView("Assignment6_View");
    c.setView(view);
    String s = "C:\\Users\\Admin\\Downloads\\assignment6received"
            + "\\assignment6received\\res2\\PPM_small.ppm";
    c.openFile(s);

    c.goClick("Red-component");
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = m.getImage("currentImage");
    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(0, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(0, (long) array2.get(0).get(1).get(1));
    assertEquals(0, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(0, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(0, (long) array2.get(1).get(1).get(1));
    assertEquals(0, (long) array2.get(1).get(1).get(2));
  }

  @Test
  public void testSaveFile() {
    MockImageModel m = new MockImageUtil();
    MockControllerFeatures c = new MockViewController(m);
    MockIView view = new MockImageView("Assignment6_View");
    c.setView(view);
    String s = "C:\\Users\\Admin\\Downloads\\assignment6received"
            + "\\assignment6received\\res2\\PPM_small.ppm";
    c.openFile(s);
    ArrayList<ArrayList<ArrayList<Integer>>> array2 = m.getImage("currentImage");
    assertEquals(1, (long) array2.get(0).get(0).get(0));
    assertEquals(2, (long) array2.get(0).get(0).get(1));
    assertEquals(0, (long) array2.get(0).get(0).get(2));
    assertEquals(0, (long) array2.get(0).get(1).get(0));
    assertEquals(3, (long) array2.get(0).get(1).get(1));
    assertEquals(3, (long) array2.get(0).get(1).get(2));
    assertEquals(4, (long) array2.get(1).get(0).get(0));
    assertEquals(5, (long) array2.get(1).get(0).get(1));
    assertEquals(0, (long) array2.get(1).get(0).get(2));
    assertEquals(0, (long) array2.get(1).get(1).get(0));
    assertEquals(15, (long) array2.get(1).get(1).get(1));
    assertEquals(6, (long) array2.get(1).get(1).get(2));

    c.saveFile("C:\\Users\\Admin\\Downloads\\assignment6received"
            + "\\assignment6received\\res2\\PPM_small2.ppm");

  }






}