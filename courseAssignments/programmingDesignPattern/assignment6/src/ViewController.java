import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * This class is a controller class, and is specifically built for the GUI.
 */
public class ViewController extends ImageController implements Controller, ControllerFeatures {
  private IView view;
  private ImageModel m;
  private ConverterInterface converter;
  private ImageIcon icon;
  private ImageIcon imageIcon;
  private ImageIcon histogramIcon;
  private ArrayList<ArrayList<ArrayList<Integer>>> currentImage;
  private ArrayList<ArrayList<ArrayList<Integer>>> prevImage;
  private ArrayList<ArrayList<ArrayList<Integer>>> splitImage;
  private File f;
  private String functionName;
  private String tempFunctionName;
  private int tempB;
  private int tempM;
  private int tempW;


  /**
   * This is the constructor of this class.
   * It sets the model class, and creates the new instance of Converter.
   *
   * @param m the model class which is typically transferred from main method.
   */

  public ViewController(ImageModel m) {
    super(m);
    this.m = m;
    converter = new Converter();
  }

  @Override
  public void setView(IView v) {
    view = v;
    view.addFeatures(this);
  }

  private void loadImage() {
    imageIcon = converter.arrayListImageToImageIcon(prevImage);
    view.displayImage(imageIcon);
    ArrayList<ArrayList<ArrayList<Integer>>> histogram = m.histogramImage("prevImage");
    histogramIcon = converter.arrayListImageToImageIcon(histogram);
    view.displayHistogram(histogramIcon);
    view.setCurrentPath(f.getAbsolutePath());
    view.setMessage("Load success!");
    view.setUnsaved("Unsaved");
    view.setSaveButtonEnable();
    view.setFunctionEnabled();
    view.setGoButtonEnable();
    view.setImageFormatComboboxEnable();
    view.setInstructions("No argument is needed, Click 'Go' to run.");
    view.setSplit(false);
    view.setArgSplit(false);
    view.setSplitPreview(true);
  }

  @Override
  public void chooseLoadFile() {
    try {
      final JFileChooser chooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, JPEG, PPM & PNG Images", "jpg", "jpeg", "ppm", "png");
      chooser.setFileFilter(filter);
      chooser.showOpenDialog(null);
      f = chooser.getSelectedFile();
      String path = f.getAbsolutePath();
      openFile(path);
    } catch (NullPointerException e) {
      view.setMessage("Load was cancelled.");
    }

  }

  @Override
  public void openFile(String path) {
    if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
      prevImage = readJPGOrPNG(path, "prevImage");
      loadImage();
      m.addImage("currentImage", prevImage);
    } else if (path.endsWith(".ppm")) {
      prevImage = readPPM(path, "prevImage");
      loadImage();
      m.addImage("currentImage", prevImage);
    } else {
      view.setMessage("File format wrong.");
    }
    currentImage = prevImage;
    m.addImage("currentImage", currentImage);
  }

  @Override
  public void chooseSaveFile() {
    try {
      final JFileChooser chooser = new JFileChooser(".");
      chooser.showSaveDialog(null);
      File f = chooser.getSelectedFile();
      String path = f.getAbsolutePath();
      saveFile(path);
    } catch (NullPointerException e) {
      view.setMessage("Save was cancelled.");
    } catch (InvalidPathException e) {
      view.setMessage("File name error.");
    }
  }


  @Override
  public void saveFile(String path) {
    try {
      if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")
              || path.endsWith(".ppm")) {
        //Do nothing, since the path is correct.
      } else {
        path = path + view.getSaveFormat();
      }
      if (path.contains(" ")) {
        view.setMessage("File name error.");
      } else {
        if (path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".png")) {
          writeJpgOrPng(path, "currentImage");
          view.setCurrentPath(path);
          view.setMessage("Save success!");
          view.setUnsaved("Saved!");

        } else if (path.endsWith(".ppm")) {
          writePPM(path, "currentImage");
          view.setCurrentPath(path);
          view.setMessage("Save success!");
          view.setUnsaved("Saved!");
        } else {
          view.setMessage("File format wrong.");
        }
      }
    } catch (NullPointerException e) {
      view.setMessage("Save was cancelled.");
    } catch (InvalidPathException e) {
      view.setMessage("File name error.");
    }
  }

  private void setArg() {
    view.setArg0(false);
    view.setArg1(false);
    view.setArg2(false);
    view.setArg0Text("arg0");
    view.setArg1Text("arg1");
    view.setArg2Text("arg2");
    view.setArg0Label("arg0:");
    view.setArg1Label("arg1:");
    view.setArg2Label("arg2:");
    view.setArg0LabelEnable(false);
    view.setArg1LabelEnable(false);
    view.setArg2LabelEnable(false);

  }

  @Override
  public void functionChoose() {
    String function = view.getSelectedFunction();
    switch (function) {
      case "Compress":
        setArg();
        view.setArg0(true);
        view.setArg0Text("integer");
        view.setArg0Label("percentage:");
        view.setArg0LabelEnable(true);
        view.setInstructions("Please enter a number(0-100), "
                + "before you click 'Go' to run.");

        break;
      case "Levels-adjust":
        view.setArg0(true);
        view.setArg1(true);
        view.setArg2(true);
        view.setArg0Label("b:");
        view.setArg1Label("m:");
        view.setArg2Label("w:");
        view.setArg0Text("integer");
        view.setArg1Text("integer");
        view.setArg2Text("integer");
        view.setArg0LabelEnable(true);
        view.setArg1LabelEnable(true);
        view.setArg2LabelEnable(true);
        view.setInstructions("Please enter integers 'b', 'm' and 'w',"
                + " before you click 'Go' to run.");
        break;
      default:
        setArg();
        view.setInstructions("No argument is needed, Click 'Go' to run.");
    }
  }

  private void imageDisplay() {
    view.displayImage(imageIcon);
    view.displayHistogram(histogramIcon);
    view.setMessage("Image process successfully!");
    view.setUnsaved("Unsaved");
    view.setCurrentPath("");
    view.setSplitPreview(true);
  }


  @Override
  public void goClick(String func) {
    try {
      prevImage = currentImage;
      m.addImage("prevImage", currentImage);
      String function = view.getSelectedFunction();
      switch (function) {
        case "Red-component":
          currentImage = m.redComponent("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Red-component";
          view.setSplit(false);
          view.setArgSplit(false);

          break;
        case "Green-component":
          currentImage = m.greenComponent("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Green-component";
          view.setSplit(false);
          view.setArgSplit(false);

          break;
        case "Blue-component":
          currentImage = m.blueComponent("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Blue-component";
          view.setSplit(false);
          view.setArgSplit(false);

          break;
        case "Horizontal-flip":
          currentImage = m.flipHorizontal("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Horizontal-flip";
          view.setSplit(false);
          view.setArgSplit(false);

          break;
        case "Vertical-flip":
          currentImage = m.flipVertical("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Vertical-flip";
          view.setSplit(false);
          view.setArgSplit(false);

          break;
        case "Blur":
          currentImage = m.blurImage("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Blur";
          view.setSplit(true);
          view.setArgSplit(true);
          break;
        case "Sharpen":
          currentImage = m.sharpenImage("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Sharpen";
          view.setSplit(true);
          view.setArgSplit(true);
          break;
        case "Luma-component":
          currentImage = m.lumaImage("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Luma-component";
          view.setSplit(true);
          view.setArgSplit(true);
          break;
        case "Sepia":
          currentImage = m.sepiaImage("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Sepia";
          view.setSplit(true);
          view.setArgSplit(true);
          break;
        case "Compress":
          try {
            int percentage = Integer.parseInt(view.getArg0());
            if (percentage >= 0 && percentage <= 100) {
              currentImage = m.compressImage(percentage, "currentImage");
              imageIcon = converter.arrayListImageToImageIcon(currentImage);
              histogramIcon = converter.arrayListImageToImageIcon(
                      arrayListToHistogram(currentImage));
              imageDisplay();
              m.addImage("currentImage", currentImage);
              functionName = "Compress";
              view.setSplit(false);
              view.setArgSplit(false);

            } else {
              view.setMessage("Compress percentage wrong, percentage must be within 0 to 100");
            }
          } catch (NumberFormatException e) {
            view.setMessage("Compress percentage wrong, percentage must be within 0 to 100");
          }
          break;
        case "Color-correct":
          currentImage = m.correctImage("currentImage");
          imageIcon = converter.arrayListImageToImageIcon(currentImage);
          histogramIcon = converter.arrayListImageToImageIcon(
                  arrayListToHistogram(currentImage));
          imageDisplay();
          m.addImage("currentImage", currentImage);
          functionName = "Color-correct";
          view.setSplit(true);
          view.setArgSplit(true);
          break;
        case "Levels-adjust":
          try {
            int b = Integer.parseInt(view.getArg0());
            int mid = Integer.parseInt(view.getArg1());
            int w = Integer.parseInt(view.getArg2());
            tempB = b;
            tempM = mid;
            tempW = w;
            if (0 <= b && w <= 255 && b < mid && mid < w) {
              currentImage = m.levelImage(b, mid, w, "currentImage");
              imageIcon = converter.arrayListImageToImageIcon(currentImage);
              histogramIcon = converter.arrayListImageToImageIcon(
                      arrayListToHistogram(currentImage));
              imageDisplay();
              m.addImage("currentImage", currentImage);
              functionName = "Levels-adjust";
              view.setSplit(true);
              view.setArgSplit(true);
            } else {
              view.setMessage("Wrong b, m or w, must be within 0 to 255 and b < m < w");
            }
          } catch (NumberFormatException e) {
            view.setMessage("Wrong b, m or w, must be within 0 to 255 and b < m < w");
          }
          break;
        default:
          System.out.println("Somethings went wrong.");
      }
    } catch (NullPointerException e) {
      view.setMessage("Error happens in go function.");
    } catch (InvalidPathException e) {
      view.setMessage("File name error.");
    }
  }


  @Override
  public void split() {
    ImageIcon splitImageIcon;
    int percentage = 0;
    try {
      percentage = Integer.parseInt(view.getArgSplit());
      if (percentage >= 0 && percentage <= 100) {
        try {
          switch (functionName) {
            case "Blur":
              splitImage = m.blurSplitImage("prevImage", percentage);
              splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
              view.displayImage(splitImageIcon);
              view.setSplitPreview(false);
              tempFunctionName = functionName;
              functionName = "Resume";
              break;
            case "Sharpen":
              splitImage = m.sharpenSplitImage("prevImage", percentage);
              splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
              view.displayImage(splitImageIcon);
              view.setSplitPreview(false);
              tempFunctionName = functionName;
              functionName = "Resume";
              break;
            case "Luma-component":
              splitImage = m.lumaSplitImage("prevImage", percentage);
              splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
              view.displayImage(splitImageIcon);
              view.setSplitPreview(false);
              tempFunctionName = functionName;
              functionName = "Resume";
              break;
            case "Sepia":
              splitImage = m.sepiaSplitImage("prevImage", percentage);
              splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
              view.displayImage(splitImageIcon);
              view.setSplitPreview(false);
              tempFunctionName = functionName;
              functionName = "Resume";
              break;
            case "Color-correct":
              splitImage = m.correctSplitImage("prevImage", percentage);
              splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
              view.displayImage(splitImageIcon);
              view.setSplitPreview(false);
              tempFunctionName = functionName;
              functionName = "Resume";
              break;
            case "Levels-adjust":
              try {
                if (0 <= tempB && tempW <= 255 && tempB < tempM && tempM < tempW) {
                  splitImage = m.levelSplitImage(tempB, tempM, tempW, "prevImage", percentage);
                  splitImageIcon = converter.arrayListImageToImageIcon(splitImage);
                  view.displayImage(splitImageIcon);
                  view.setSplitPreview(false);
                  tempFunctionName = functionName;
                  functionName = "Resume";
                } else {
                  view.setMessage("Wrong b, m or w, must be within 0 to 255 and b < m < w");
                }
              } catch (NumberFormatException e) {
                view.setMessage("Wrong b, m or w, must be within 0 to 255 and b < m < w");
              }
              break;
            case "Resume":
              view.displayImage(imageIcon);
              functionName = tempFunctionName;
              view.setSplitPreview(true);
              break;
            default:
              System.out.println("Somethings went wrong.");
          }
        } catch (NullPointerException e) {
          view.setMessage("Save was cancelled.");
        } catch (InvalidPathException e) {
          view.setMessage("File name error.");
        }

      } else {
        view.setMessage("Split percentage wrong, percentage must be within 0 to 100");
      }
    } catch (NumberFormatException e) {
      view.setMessage("Split percentage wrong, percentage must be within 0 to 100");
    }
  }
}
