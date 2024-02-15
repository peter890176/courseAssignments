import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This is a controller class receives the user input and then transfer to model for operations.
 */
public class ImageController implements Controller {

  private ImageModel imageUtil;
  private ConverterInterface converter;

  /**
   * This is the constructor of this class. Typically, it takes the parameter from the main method.
   */
  public ImageController(ImageModel imageUtil) {
    this.imageUtil = (ImageUtil) imageUtil;
    converter = new Converter();
  }

  @Override
  public void action(String command) {
    String[] commandLine = command.split(" ");
    String event = commandLine[0];
    switch (event) {
      case "load":
        try {
          String imagePath = commandLine[1];
          String imageName = commandLine[2];
          if (imagePath.contains(".ppm")) {
            readPPM(imagePath, imageName);
          } else if (imagePath.contains(".jpg") || imagePath.contains(".jpeg")
                  || imagePath.contains(".png")) {
            readJPGOrPNG(imagePath, imageName);
          } else {
            System.out.println("Image does not exist or wrong image format.");
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "save":
        try {
          String destinationPath = commandLine[1];
          String targetImageName = commandLine[2];
          if (destinationPath.contains(".ppm")) {
            writePPM(destinationPath, targetImageName);
          } else if (destinationPath.contains(".jpg") || destinationPath.contains(".jpeg")
                  || destinationPath.contains(".png")) {
            writeJpgOrPng(destinationPath, targetImageName);
          } else {
            System.out.println("Image does not exist or wrong image format.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }

        break;
      case "red-component":
        try {
          String originalImageName = commandLine[1];
          String destinationImageName = commandLine[2];
          imageUtil.addImage(destinationImageName, imageUtil.redComponent(originalImageName));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "green-component":

        try {
          String originalImage = commandLine[1];
          String destinationImage = commandLine[2];
          imageUtil.addImage(destinationImage, imageUtil.greenComponent(originalImage));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "blue-component":

        try {
          String originalName = commandLine[1];
          String destinationName = commandLine[2];
          imageUtil.addImage(destinationName, imageUtil.blueComponent(originalName));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "value-component":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.valueImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.valueSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "luma-component":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.lumaImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.lumaSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "intensity-component":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.intensityImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.intensitySplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "horizontal-flip":
        try {
          imageUtil.addImage(commandLine[2], imageUtil.flipHorizontal(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "vertical-flip":
        try {
          imageUtil.addImage(commandLine[2], imageUtil.flipVertical(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "brighten":
        try {
          imageUtil.addImage(commandLine[3],
                  imageUtil.brightenImage(commandLine[2], Integer.parseInt(commandLine[1])));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "rgb-split":
        try {
          action("red-component " + commandLine[1] + " " + commandLine[2]);
          action("green-component " + commandLine[1] + " " + commandLine[3]);
          action("blue-component " + commandLine[1] + " " + commandLine[4]);
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "rgb-combine":
        try {
          imageUtil.addImage(commandLine[1], imageUtil.combinedImage(commandLine[2],
                  commandLine[3], commandLine[4]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "blur":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.blurImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.blurSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "sharpen":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.sharpenImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.sharpenSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "sepia":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.sepiaImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.sepiaSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;

      case "histogram":
        try {
          imageUtil.addImage(commandLine[2], imageUtil.histogramImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;

      case "color-correct":
        try {
          if (commandLine.length == 3) {
            imageUtil.addImage(commandLine[2], imageUtil.correctImage(commandLine[1]));
          } else if (commandLine.length == 5) {
            if (commandLine[3].equals("split") && (Integer.parseInt(commandLine[4]) >= 0)
                    && (Integer.parseInt(commandLine[4]) <= 100)) {
              imageUtil.addImage(commandLine[2], imageUtil.correctSplitImage(commandLine[1],
                      Integer.parseInt(commandLine[4])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }
          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;

      case "levels-adjust":
        try {
          if (commandLine.length == 6
                  && 0 <= Integer.parseInt(commandLine[1])
                  && Integer.parseInt(commandLine[3]) <= 255
                  && Integer.parseInt(commandLine[1]) < Integer.parseInt(commandLine[2])
                  && Integer.parseInt(commandLine[2]) < Integer.parseInt(commandLine[3])
          ) {
            imageUtil.addImage(commandLine[5], imageUtil.levelImage(
                    Integer.parseInt(commandLine[1]),
                    Integer.parseInt(commandLine[2]),
                    Integer.parseInt(commandLine[3]),
                    commandLine[4]));
          } else if (commandLine.length == 8
                  && 0 <= Integer.parseInt(commandLine[1])
                  && Integer.parseInt(commandLine[3]) <= 255
                  && Integer.parseInt(commandLine[1]) < Integer.parseInt(commandLine[2])
                  && Integer.parseInt(commandLine[2]) < Integer.parseInt(commandLine[3])
          ) {
            if (commandLine[6].equals("split") && (Integer.parseInt(commandLine[7]) >= 0)
                    && (Integer.parseInt(commandLine[7]) <= 100)) {
              imageUtil.addImage(commandLine[5], imageUtil.levelSplitImage(
                      Integer.parseInt(commandLine[1]),
                      Integer.parseInt(commandLine[2]),
                      Integer.parseInt(commandLine[3]),
                      commandLine[4], Integer.parseInt(commandLine[7])));
            } else {
              System.out.println("Wrong command arguments, or split portion must be 0 to 100.");
            }

          } else {
            System.out.println("Wrong command arguments.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;

      case "compress":
        try {
          if ((Integer.parseInt(commandLine[1]) >= 0)
                  && (Integer.parseInt(commandLine[1]) <= 100)) {
            imageUtil.addImage(commandLine[3],
                    imageUtil.compressImage(Integer.parseInt(commandLine[1]),
                            commandLine[2]));
          } else {
            System.out.println("Wrong command arguments, or compress portion must be 0 to 100.");
          }
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "run":
        try {
          Scanner scan = new Scanner(new FileInputStream(commandLine[1]));
          while (scan.hasNextLine()) {
            String fileCommand = scan.nextLine();
            if (fileCommand.equals("exit")) {
              break;
            } else if (fileCommand.startsWith("#")) {
              continue;
            } else {
              action(fileCommand);
            }
          }
          scan.close();
        } catch (FileNotFoundException e) {
          System.out.println("File " + commandLine[1] + " not found!");
          return;
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      default:
        System.out.println("Something wrong in your command, please double check.");
    }
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> readPPM(String filePath, String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    int maxValue = sc.nextInt();
    image = converter.ppmToArray(sc, width, height);
    imageUtil.addImage(filename, image);
    return image;
  }


  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> readJPGOrPNG(String filePath, String filename) {
    BufferedImage picture;
    try {
      // the line that reads the image file
      picture = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }

    int width = picture.getWidth();
    int height = picture.getHeight();
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    image = converter.jpgOrPNGToArray(picture, width, height);
    imageUtil.addImage(filename, image);
    return image;
  }


  @Override
  public void writePPM(String filePath, String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> image = imageUtil.getImage(imageName);
    int height = image.size();
    int width = image.get(0).size();
    try {
      FileOutputStream output
              = new FileOutputStream(filePath);
      String s = "P3";
      byte[] array = s.getBytes();
      output.write(array);
      output.write("\n".getBytes());
      output.write((width + " " + height).getBytes());
      int maxi = 0;
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          ArrayList<Integer> rgb = image.get(i).get(j);
          int temp = Math.max(
                  Math.max(rgb.get(0), rgb.get(1)), rgb.get(2));
          if (maxi <= temp) {
            maxi = temp;
          }
        }
      }
      output.write("\n".getBytes());
      output.write((String.valueOf(maxi)).getBytes());
      output.write("\n".getBytes());

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          ArrayList<Integer> rgb = image.get(i).get(j);
          output.write((rgb.get(0) + " " + rgb.get(1) + " " + rgb.get(2) + " ").getBytes());
        }
        output.write("\n".getBytes());
      }
      output.close();
    } catch (Exception e) {
      e.getStackTrace();
    }
  }

  @Override
  public void writeJpgOrPng(String filePath, String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> image = imageUtil.getImage(imageName);
    int height = image.size();
    int width = image.get(0).size();
    try {
      BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          ArrayList<Integer> rgb = image.get(i).get(j);
          Color color = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
          int c = color.getRGB();
          output.setRGB(j, i, c);
        }
      }
      if (filePath.endsWith(".jpg")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "jpg", outputfile);
      } else if (filePath.endsWith(".jpeg")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "jpeg", outputfile);
      } else if (filePath.endsWith(".png")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "png", outputfile);
      }
    } catch (IOException e) {
      e.getStackTrace();
    }
  }


  /**
   * This method convert the nested arrayList into Histogram.
   *
   * @param image a nested ArrayList of integers representing pixels that make up red component.
   * @return a nested ArrayList of integers representing pixels that make up red component.
   */
  public ArrayList<ArrayList<ArrayList<Integer>>> arrayListToHistogram(
          ArrayList<ArrayList<ArrayList<Integer>>> image) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = image;
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int[] redLine = new int[256];
    int[] greenLine = new int[256];
    int[] blueLine = new int[256];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redLine[originalImage.get(i).get(j).get(0)]++;
        greenLine[originalImage.get(i).get(j).get(1)]++;
        blueLine[originalImage.get(i).get(j).get(2)]++;
      }
    }

    BufferedImage histogram = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D histogramImage = histogram.createGraphics();
    histogramImage.setColor(Color.WHITE);
    histogramImage.fillRect(0, 0, 256, 256);
    int maxFreq = 0;
    for (int i = 0; i < 256; i++) {
      maxFreq = Math.max(maxFreq, redLine[i]);
      maxFreq = Math.max(maxFreq, greenLine[i]);
      maxFreq = Math.max(maxFreq, blueLine[i]);
    }

    histogramImage.setColor(Color.LIGHT_GRAY);
    for (int i = 13; i < 256; i += 13) {
      histogramImage.drawLine(i, 0, i, 256);
      histogramImage.drawLine(0, i, 256, i);
    }


    histogramImage.setColor(Color.RED);
    int x1;
    int x2;
    int y1;
    int y2;
    for (int i = 1; i < 256; i++) {
      x1 = i - 1;
      x2 = i;
      y1 = 255 - Math.round(redLine[i - 1] * 256 / maxFreq);
      y2 = 255 - Math.round(redLine[i] * 256 / maxFreq);
      histogramImage.drawLine(x1, y1, x2, y2);
    }
    histogramImage.setColor(Color.GREEN);
    for (int i = 1; i < 256; i++) {
      x1 = i - 1;
      x2 = i;
      y1 = 255 - Math.round(greenLine[i - 1] * 256 / maxFreq);
      y2 = 255 - Math.round(greenLine[i] * 256 / maxFreq);
      histogramImage.drawLine(x1, y1, x2, y2);
    }
    histogramImage.setColor(Color.BLUE);
    for (int i = 1; i < 256; i++) {
      x1 = i - 1;
      x2 = i;
      y1 = 255 - Math.round(blueLine[i - 1] * 256 / maxFreq);
      y2 = 255 - Math.round(blueLine[i] * 256 / maxFreq);
      histogramImage.drawLine(x1, y1, x2, y2);
    }
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = converter.jpgOrPNGToArray(
            histogram, 256, 256);
    return newImage;
  }

}
