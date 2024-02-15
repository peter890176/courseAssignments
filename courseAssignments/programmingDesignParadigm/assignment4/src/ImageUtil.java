import java.awt.Color;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Class contains methods to read/write & manipulate image files (ppm, jpg, png).
 */
public class ImageUtil implements ImageModel, Controller {
  private Map<String, ArrayList<ArrayList<ArrayList<Integer>>>> imageNameMapimage = new HashMap<>();

  private void addImage(String imageName, ArrayList<ArrayList<ArrayList<Integer>>> image) {
    imageNameMapimage.put(imageName, image);
  }

  private ArrayList<ArrayList<ArrayList<Integer>>> getImage(String imageName) {
    return imageNameMapimage.get(imageName);
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> readPPM(String filePath, String filename) {
    Scanner sc;
    //  System.out.println("Working Directory = " + System.getProperty("user.dir"));

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    //   System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //  System.out.println("Height of image: " + height);
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    int maxValue = sc.nextInt();
    //   System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    image = ppmToArray(sc, width, height);
    addImage(filename, image);
    return image;
  }

  private ArrayList<ArrayList<ArrayList<Integer>>> ppmToArray(Scanner sc, int width, int height) {
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
    //System.out.println("Width of image: "+width);
    int height = picture.getHeight();
    //System.out.println("Height of image: "+height);
    ArrayList<ArrayList<ArrayList<Integer>>> image = new ArrayList<>();
    image = jpgOrPNGToArray(picture, width, height);
    addImage(filename, image);
    return image;
  }


  private ArrayList<ArrayList<ArrayList<Integer>>> jpgOrPNGToArray(BufferedImage picture,
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
  public ArrayList<ArrayList<ArrayList<Integer>>> redComponent(String imageName) {

    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(originalImage.get(i).get(j).get(0));
        rgb.add(0);
        rgb.add(0);
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;

  }


  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> greenComponent(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(0);
        rgb.add(originalImage.get(i).get(j).get(1));
        rgb.add(0);
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> blueComponent(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(0);
        rgb.add(0);
        rgb.add(originalImage.get(i).get(j).get(2));
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> flipVertical(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(originalImage.get(height - i - 1).get(j).get(0));
        rgb.add(originalImage.get(height - i - 1).get(j).get(1));
        rgb.add(originalImage.get(height - i - 1).get(j).get(2));
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> flipHorizontal(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(originalImage.get(i).get(width - j - 1).get(0));
        rgb.add(originalImage.get(i).get(width - j - 1).get(1));
        rgb.add(originalImage.get(i).get(width - j - 1).get(2));
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> brightenImage(String imageName, int increaseBy) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int x;
        int y;
        int z;
        if (increaseBy >= 0) {
          x = Math.min((originalImage.get(i).get(j).get(0) + increaseBy), 255);
          y = Math.min((originalImage.get(i).get(j).get(1) + increaseBy), 255);
          z = Math.min((originalImage.get(i).get(j).get(2) + increaseBy), 255);
        } else {
          x = Math.max((originalImage.get(i).get(j).get(0) + increaseBy), 0);
          y = Math.max((originalImage.get(i).get(j).get(1) + increaseBy), 0);
          z = Math.max((originalImage.get(i).get(j).get(2) + increaseBy), 0);
        }
        rgb.add(x);
        rgb.add(y);
        rgb.add(z);
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }


  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> valueImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int maxi = Math.max(Math.max(originalImage.get(i).get(j).get(0),
                originalImage.get(i).get(j).get(1)), originalImage.get(i).get(j).get(2));
        rgb.add(maxi);
        rgb.add(maxi);
        rgb.add(maxi);
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> intensityImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int avg = Math.round((float) (
                (originalImage.get(i).get(j).get(0) + originalImage.get(i).get(j).get(1)
                        + originalImage.get(i).get(j).get(2)) / 3)
        );
        rgb.add(avg);
        rgb.add(avg);
        rgb.add(avg);
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;

  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> lumaImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();

    for (int i = 0; i < height; i++) {

      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();

        float weightedSum = originalImage.get(i).get(j).get(0) * 0.2126f
                + originalImage.get(i).get(j).get(1) * 0.7152f
                + originalImage.get(i).get(j).get(2) * 0.0722f;
        rgb.add(Math.round(weightedSum));
        rgb.add(Math.round(weightedSum));
        rgb.add(Math.round(weightedSum));
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> blurImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    ArrayList[][] image = new ArrayList[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[j][i] = originalImage.get(i).get(j);
      }
    }

    float[][] filter = {{(float) 1 / 16, (float) 1 / 8, (float) 1 / 16},
        {(float) 1 / 8, (float) 1 / 4, (float) 1 / 8},
        {(float) 1 / 16, (float) 1 / 8, (float) 1 / 16}};

    ArrayList[][] blur = new ArrayList[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blur[j][i] = new ArrayList();
        for (int x = 0; x < 3; x++) {
          float result = 0.0f;

          for (int c = -1; c <= 1; c++) {
            for (int d = -1; d <= 1; d++) {
              if (j + c >= 0 && j + c < width && i + d >= 0 && i + d < height) {
                result += (float) ((Integer) image[j + c][i + d].get(x)) * filter[1 + d][1 + c];
              } else {
                result += 0.0f;
              }
            }
          }


          int finalResult;
          if (Math.round(result) >= 255) {
            finalResult = 255;
          } else if (Math.round(result) <= 0) {
            finalResult = 0;
          } else {
            finalResult = Math.round(result);
          }
          blur[j][i].add(finalResult);
        }
      }
    }
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(blur[j][i]);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> sharpenImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    ArrayList[][] image = new ArrayList[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[j][i] = originalImage.get(i).get(j);
      }
    }

    ArrayList[][] sharp = new ArrayList[width][height];
    float[][] filter = {{(float) -1 / 8, (float) -1 / 8, (float) -1 / 8,
        (float) -1 / 8, (float) -1 / 8},
        {(float) -1 / 8, (float) 1 / 4, (float) 1 / 4, (float) 1 / 4, (float) -1 / 8},
        {(float) -1 / 8, (float) 1 / 4, (float) 1, (float) 1 / 4, (float) -1 / 8},
        {(float) -1 / 8, (float) 1 / 4, (float) 1 / 4, (float) 1 / 4, (float) -1 / 8},
        {(float) -1 / 8, (float) -1 / 8, (float) -1 / 8, (float) -1 / 8, (float) -1 / 8}};

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sharp[j][i] = new ArrayList();
        for (int x = 0; x < 3; x++) {
          float result = 0.0f;
          for (int c = -2; c <= 2; c++) {
            for (int d = -2; d <= 2; d++) {
              if (j + c >= 0 && j + c < width && i + d >= 0 && i + d < height) {
                result += (float) ((Integer) image[j + c][i + d].get(x)) * filter[2 + d][2 + c];
              } else {
                result += 0.0f;
              }

            }
          }
          int finalResult;
          if (Math.round(result) >= 255) {
            finalResult = 255;
          } else if (Math.round(result) <= 0) {
            finalResult = 0;
          } else {
            finalResult = Math.round(result);
          }
          sharp[j][i].add(finalResult);
        }
      }
    }
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(sharp[j][i]);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> sepiaImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    ArrayList[][] image = new ArrayList[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[j][i] = originalImage.get(i).get(j);
      }
    }

    ArrayList[][] sepia = new ArrayList[width][height];
    float[][] filter = {{0.393f, 0.769f, 0.189f},
        {0.349f, 0.686f, 0.168f},
        {0.272f, 0.534f, 0.131f}};

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sepia[j][i] = new ArrayList();
        float result;
        result = (Integer) image[j][i].get(0) * filter[0][0]
                + (Integer) image[j][i].get(1) * filter[0][1]
                + (Integer) image[j][i].get(2) * filter[0][2];
        sepia[j][i].add(outOfRange(Math.round(result)));
        result = (Integer) image[j][i].get(0) * filter[1][0]
                + (Integer) image[j][i].get(1) * filter[1][1]
                + (Integer) image[j][i].get(2) * filter[1][2];
        sepia[j][i].add(outOfRange(Math.round(result)));
        result = (Integer) image[j][i].get(0) * filter[2][0]
                + (Integer) image[j][i].get(1) * filter[2][1]
                + (Integer) image[j][i].get(2) * filter[2][2];
        sepia[j][i].add(outOfRange(Math.round(result)));

      }

    }
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(sepia[j][i]);
      }
      newImage.add(row);
    }
    return newImage;
  }

  private int outOfRange(int number) {
    if (number >= 255) {
      return 255;
    } else if (number <= 0) {
      return 0;
    } else {
      return number;
    }
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> combinedImage(String r, String g, String b) {
    ArrayList<ArrayList<ArrayList<Integer>>> rImage = getImage(r);
    ArrayList<ArrayList<ArrayList<Integer>>> gImage = getImage(g);
    ArrayList<ArrayList<ArrayList<Integer>>> bImage = getImage(b);

    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = rImage.size();
    int width = rImage.get(0).size();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(rImage.get(i).get(j).get(0));
        rgb.add(gImage.get(i).get(j).get(1));
        rgb.add(bImage.get(i).get(j).get(2));
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }


  @Override
  public void writePPM(String filePath, String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> image = getImage(imageName);
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
    ArrayList<ArrayList<ArrayList<Integer>>> image = getImage(imageName);
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
      if (filePath.contains(".jpg")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "jpg", outputfile);
      } else if (filePath.contains(".jpeg")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "jpeg", outputfile);
      } else if (filePath.contains(".png")) {
        File outputfile = new File(filePath);
        ImageIO.write(output, "png", outputfile);
      }
    } catch (IOException e) {
      e.getStackTrace();
    }
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
          addImage(destinationImageName, redComponent(originalImageName));
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
          addImage(destinationImage, greenComponent(originalImage));
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
          addImage(destinationName, blueComponent(originalName));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "value-component":
        try {
          addImage(commandLine[2], valueImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "luma-component":
        try {
          addImage(commandLine[2], lumaImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "intensity-component":
        try {
          addImage(commandLine[2], intensityImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "horizontal-flip":
        try {
          addImage(commandLine[2], flipHorizontal(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "vertical-flip":
        try {
          addImage(commandLine[2], flipVertical(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "brighten":
        try {
          addImage(commandLine[3], brightenImage(commandLine[2], Integer.parseInt(commandLine[1])));
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
          addImage(commandLine[1], combinedImage(commandLine[2], commandLine[3], commandLine[4]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "blur":
        try {
          addImage(commandLine[2], blurImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "sharpen":
        try {
          addImage(commandLine[2], sharpenImage(commandLine[1]));
        } catch (NullPointerException e) {
          System.out.println("Image does not exist or wrong image name.");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Wrong command.");
        }
        break;
      case "sepia":
        try {
          addImage(commandLine[2], sepiaImage(commandLine[1]));
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

