import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a controller class receives the user input and then transfer to model for operations.
 */
public class ImageController implements Controller {
  private ImageUtil imageUtil = new ImageUtil();

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
            imageUtil.readPPM(imagePath, imageName);
          } else if (imagePath.contains(".jpg") || imagePath.contains(".jpeg")
                  || imagePath.contains(".png")) {
            imageUtil.readJPGOrPNG(imagePath, imageName);
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
            imageUtil.writePPM(destinationPath, targetImageName);
          } else if (destinationPath.contains(".jpg") || destinationPath.contains(".jpeg")
                  || destinationPath.contains(".png")) {
            imageUtil.writeJpgOrPng(destinationPath, targetImageName);
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
}
