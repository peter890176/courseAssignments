import org.junit.Test;

import controller.Features;
import controller.InteractiveController;
import model.MockModel;
import view.MockView;
import view.MockView10;
import view.MockView11;
import view.MockView12;
import view.MockView13;
import view.MockView14;
import view.MockView15;
import view.MockView16;
import view.MockView17;
import view.MockView2;
import view.MockView3;
import view.MockView4;
import view.MockView5;
import view.MockView6;
import view.MockView7;
import view.MockView8;
import view.MockView9;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the interactive controller and its methods.
 */
public class TestInteractiveController {
  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.loadButton();
    assertEquals("Open JFileChooser Load Image Histogram Image " +
            "Save Image Save Image Display Image Display Histogram Msg Popup ", log.toString());
  }



  @Test
  public void testApplyBlur() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Blur Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyLuma() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView2(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Luma Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyValue() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView15(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Value Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyIntensity() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView14(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Intensity Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplySepia() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView3(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Sepia Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyDither() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView17(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Dither Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";
    assertEquals(expectedLog, log.toString());
  }


  @Test
  public void testApplyCompress() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView4(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save Image " +
            "Display Image Display Histogram Msg Popup Get Selected Item Send BImage Get " +
            "Secondary Input Load Image Compress Image Save Image Histogram Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyCompressInvalid() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView13(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save Image " +
            "Display Image Display Histogram Msg Popup Get Selected Item Send BImage " +
            "Get Secondary Input Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyLevelsAdjustInvalid() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView5(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save " +
            "Image Display Image Display Histogram Msg Popup Get Selected " +
            "Item Send BImage Get Secondary Input Get Secondary Input Get Secondary " +
            "Input Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyLevelsAdjustInvalid2() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView12(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save " +
            "Image Display Image Display Histogram Msg Popup Get Selected " +
            "Item Send BImage Get Secondary Input Get Secondary Input Get Secondary " +
            "Input Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyLevelsAdjustValid() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView11(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save " +
            "Image Display Image Display Histogram Msg Popup Get Selected Item Send " +
            "BImage Get Secondary Input Get Secondary Input Get Secondary Input Load " +
            "Image LevelAdjust Image Save Image Histogram Image Histogram Image Save " +
            "Image Save Image Slider Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyVerticalFlip() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView6(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save " +
            "Image Display Image Display Histogram Msg Popup Get Selected Item Send BImage " +
            "Load Image Flip Vertical Save Image Histogram Image Histogram Image Save Image " +
            "Save Image Display Image Display Histogram Msg Popup ";


    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyRed() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView7(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save Image " +
            "Display Image Display Histogram Msg Popup Get Selected Item Send BImage " +
            "Load Image Red Image Save Image Histogram Image Histogram Image Save Image Save" +
            " Image Display Image Display Histogram Msg Popup ";


    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyBrighten() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView8(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save Image" +
            " Display Image Display Histogram Msg Popup Get Selected Item Send BImage" +
            " Load Image Get Secondary Input Brighten Image Save Image Histogram Image " +
            "Histogram Image Save Image Save Image Display Image Display Histogram Msg Popup ";


    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplySharpen() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView9(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save Image" +
            " Display Image Display Histogram Msg Popup Get Selected Item Send BImage" +
            " Load Image Sharpen Image Save Image Histogram Image Histogram Image Save " +
            "Image Save Image Slider Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testApplyColorCorrect() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView10(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram Image Save Image Save " +
            "Image Display Image Display Histogram Msg Popup Get Selected Item " +
            "Send BImage Load Image ColorCorrect Image Save Image Histogram Image " +
            "Histogram Image Save Image Save Image Slider Select Display Image Display " +
            "Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testCancel() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.cancelButton();
    assertEquals("Disable Slider UnTick Checkbox Display Image" +
            " Display Histogram Msg Popup ", log.toString());
  }

  @Test
  public void testConfirm() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.confirmButton();
    assertEquals("Disable Slider UnTick Checkbox Display Image" +
            " Display Histogram Msg Popup ", log.toString());
  }

  @Test
  public void testSlider() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.applyButton();
    features.sliderButton();
    assertEquals("Get Selected Item Send BImage Load Image Blur Image Save Image " +
            "Histogram Image Histogram Image Save Image Save Image Slider Select Display" +
            " Image Display Histogram Msg Popup Get Slider Value Slider Select PreviewLoad " +
            "Image Histogram Image Save Image Display Image Display Histogram ", log.toString());
  }

  @Test
  public void testCheckbox() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.checkboxButton();
    assertEquals("Tick ValueEnable Slider ", log.toString());
  }

  @Test
  public void testExecute() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.execute();
    assertEquals("Display Set Button Features Add Add Slider Checkbox " +
            "Disable Slider ", log.toString());
  }

  @Test
  public void testInvalidImage() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView16(log));
    features.applyButton();
    assertEquals("Get Selected Item Send BImage Msg Popup ", log.toString());
  }


  @Test
  public void testMultiple2() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView2(log));
    features.loadButton();
    features.applyButton();
    features.loadButton();
    assertEquals("Open JFileChooser Load Image Histogram Image Save Image Save Image " +
            "Display Image Display Histogram Msg Popup Get Selected Item Send BImage Load Image" +
            " Luma Image Save Image Histogram Image Histogram Image Save Image Save Image" +
            " Slider Select Display Image Display Histogram Msg Popup Popup BoxOpen " +
            "JFileChooser Load Image Histogram Image Save Image Save Image Display " +
            "Image Display Histogram Msg Popup ", log.toString());
  }

}
