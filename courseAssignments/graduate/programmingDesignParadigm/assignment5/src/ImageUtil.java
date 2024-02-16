import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

/**
 * Class contains methods to read/write & manipulate image files (ppm, jpg, png).
 */
public class ImageUtil implements ImageModel {

  private Map<String, ArrayList<ArrayList<ArrayList<Integer>>>> imageNameMapImage = new HashMap<>();

  protected void addImage(String imageName, ArrayList<ArrayList<ArrayList<Integer>>> image) {
    imageNameMapImage.put(imageName, image);
  }

  private ArrayList<ArrayList<ArrayList<Integer>>> getImage(String imageName) {
    return imageNameMapImage.get(imageName);
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
    int height = picture.getHeight();
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
  public ArrayList<ArrayList<ArrayList<Integer>>> valueSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
        } else {
          int maxi = Math.max(Math.max(originalImage.get(i).get(j).get(0),
                  originalImage.get(i).get(j).get(1)), originalImage.get(i).get(j).get(2));
          rgb.add(maxi);
          rgb.add(maxi);
          rgb.add(maxi);
        }
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
  public ArrayList<ArrayList<ArrayList<Integer>>> intensitySplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
        } else {
          int avg = Math.round((float) (
                  (originalImage.get(i).get(j).get(0) + originalImage.get(i).get(j).get(1)
                          + originalImage.get(i).get(j).get(2)) / 3)
          );
          rgb.add(avg);
          rgb.add(avg);
          rgb.add(avg);
        }
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
  public ArrayList<ArrayList<ArrayList<Integer>>> lumaSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
        } else {
          float weightedSum = originalImage.get(i).get(j).get(0) * 0.2126f
                  + originalImage.get(i).get(j).get(1) * 0.7152f
                  + originalImage.get(i).get(j).get(2) * 0.0722f;
          rgb.add(Math.round(weightedSum));
          rgb.add(Math.round(weightedSum));
          rgb.add(Math.round(weightedSum));
        }
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
  public ArrayList<ArrayList<ArrayList<Integer>>> blurSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

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
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
          row.add(rgb);
        } else {
          row.add(blur[j][i]);
        }
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
  public ArrayList<ArrayList<ArrayList<Integer>>> sharpenSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

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
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
          row.add(rgb);
        } else {
          row.add(sharp[j][i]);
        }
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
  public ArrayList<ArrayList<ArrayList<Integer>>> sepiaSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

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
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
          row.add(rgb);
        } else {
          row.add(sepia[j][i]);
        }
      }
      newImage.add(row);
    }
    return newImage;
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
  public ArrayList<ArrayList<ArrayList<Integer>>> histogramImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
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
    ArrayList<ArrayList<ArrayList<Integer>>> newImage = jpgOrPNGToArray(histogram, 256, 256);
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> correctImage(String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
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
    int maxFreqRed = 0;
    int maxFreqLocRed = 10;
    int maxFreqGreen = 0;
    int maxFreqLocGreen = 10;
    int maxFreqBlue = 0;
    int maxFreqLocBlue = 10;

    for (int i = 10; i < 244; i++) {
      if (redLine[i] > maxFreqRed) {
        maxFreqRed = redLine[i];
        maxFreqLocRed = i;
      }
      if (greenLine[i] > maxFreqGreen) {
        maxFreqGreen = greenLine[i];
        maxFreqLocGreen = i;
      }
      if (blueLine[i] > maxFreqBlue) {
        maxFreqBlue = blueLine[i];
        maxFreqLocBlue = i;
      }
    }
    int averagePeak = Math.round((maxFreqLocRed + maxFreqLocGreen + maxFreqLocBlue) / 3);
    int shiftRed = averagePeak - maxFreqLocRed;
    int shiftGreen = averagePeak - maxFreqLocGreen;
    int shiftBlue = averagePeak - maxFreqLocBlue;

    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int r = originalImage.get(i).get(j).get(0) + shiftRed;
        int g = originalImage.get(i).get(j).get(1) + shiftGreen;
        int b = originalImage.get(i).get(j).get(2) + shiftBlue;
        if (r < 0) {
          rgb.add(0);
        } else if (r > 255) {
          rgb.add(255);
        } else {
          rgb.add(r);
        }
        if (g < 0) {
          rgb.add(0);
        } else if (g > 255) {
          rgb.add(255);
        } else {
          rgb.add(g);
        }
        if (b < 0) {
          rgb.add(0);
        } else if (b > 255) {
          rgb.add(255);
        } else {
          rgb.add(b);
        }
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> correctSplitImage(String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);
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
    int maxFreqRed = 0;
    int maxFreqLocRed = 10;
    int maxFreqGreen = 0;
    int maxFreqLocGreen = 10;
    int maxFreqBlue = 0;
    int maxFreqLocBlue = 10;

    for (int i = 10; i < 244; i++) {
      if (redLine[i] > maxFreqRed) {
        maxFreqRed = redLine[i];
        maxFreqLocRed = i;
      }
      if (greenLine[i] > maxFreqGreen) {
        maxFreqGreen = greenLine[i];
        maxFreqLocGreen = i;
      }
      if (blueLine[i] > maxFreqBlue) {
        maxFreqBlue = blueLine[i];
        maxFreqLocBlue = i;
      }
    }
    int averagePeak = Math.round((maxFreqLocRed + maxFreqLocGreen + maxFreqLocBlue) / 3);
    int shiftRed = averagePeak - maxFreqLocRed;
    int shiftGreen = averagePeak - maxFreqLocGreen;
    int shiftBlue = averagePeak - maxFreqLocBlue;

    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        if (j >= cutLine) {
          rgb.add(originalImage.get(i).get(j).get(0));
          rgb.add(originalImage.get(i).get(j).get(1));
          rgb.add(originalImage.get(i).get(j).get(2));
        } else {
          int r = originalImage.get(i).get(j).get(0) + shiftRed;
          int g = originalImage.get(i).get(j).get(1) + shiftGreen;
          int b = originalImage.get(i).get(j).get(2) + shiftBlue;
          if (r < 0) {
            rgb.add(0);
          } else if (r > 255) {
            rgb.add(255);
          } else {
            rgb.add(r);
          }
          if (g < 0) {
            rgb.add(0);
          } else if (g > 255) {
            rgb.add(255);
          } else {
            rgb.add(g);
          }
          if (b < 0) {
            rgb.add(0);
          } else if (b > 255) {
            rgb.add(255);
          } else {
            rgb.add(b);
          }
        }
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> levelImage(int b, int m, int w,
                                                             String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    double b2 = Math.pow(b, 2);
    double m2 = Math.pow(m, 2);
    double w2 = Math.pow(w, 2);
    double bigA = b2 * (m - w) - b * (m2 - w2) + w * m2 - m * w2;
    double bigAa = (-b) * (128 - 255) + 128 * w - 255 * m;
    double bigAb = b2 * (128 - 255) + 255 * m2 - 128 * w2;
    double bigAc = b2 * (255 * m - 128 * w) - b * (255 * m2 - 128 * w2);
    int aCoefficient = (int) Math.round(bigAa / bigA);
    int bCoefficient = (int) Math.round(bigAb / bigA);
    int cCoefficient = (int) Math.round(bigAc / bigA);

    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int rColor = originalImage.get(i).get(j).get(0);
        int gColor = originalImage.get(i).get(j).get(1);
        int bColor = originalImage.get(i).get(j).get(2);
        int newR;
        int newG;
        int newB;
        if (rColor <= b) {
          newR = 0;
        } else if (rColor >= w) {
          newR = 255;
        } else {
          newR = aCoefficient * ((int) Math.pow(rColor, 2))
                  + bCoefficient * rColor + cCoefficient;
        }
        if (gColor <= b) {
          newG = 0;
        } else if (gColor >= w) {
          newG = 255;
        } else {
          newG = aCoefficient * ((int) Math.pow(gColor, 2))
                  + bCoefficient * gColor + cCoefficient;
        }

        if (bColor <= b) {
          newB = 0;
        } else if (bColor >= w) {
          newB = 255;
        } else {
          newB = aCoefficient * ((int) Math.pow(bColor, 2))
                  + bCoefficient * bColor + cCoefficient;
        }

        if (newR < 0) {
          rgb.add(0);
        } else if (newR > 255) {
          rgb.add(255);
        } else {
          rgb.add(newR);
        }
        if (newG < 0) {
          rgb.add(0);
        } else if (newG > 255) {
          rgb.add(255);
        } else {
          rgb.add(newG);
        }
        if (newB < 0) {
          rgb.add(0);
        } else if (newB > 255) {
          rgb.add(255);
        } else {
          rgb.add(newB);
        }
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> levelSplitImage(int b, int m, int w,
                                                                  String imageName, int p) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    int height = originalImage.size();
    int width = originalImage.get(0).size();
    int cutLine = Math.round(width * p / 100);

    double b2 = Math.pow(b, 2);
    double m2 = Math.pow(m, 2);
    double w2 = Math.pow(w, 2);
    double bigA = b2 * (m - w) - b * (m2 - w2) + w * m2 - m * w2;
    double bigAa = (-b) * (128 - 255) + 128 * w - 255 * m;
    double bigAb = b2 * (128 - 255) + 255 * m2 - 128 * w2;
    double bigAc = b2 * (255 * m - 128 * w) - b * (255 * m2 - 128 * w2);
    int aCoefficient = (int) Math.round(bigAa / bigA);
    int bCoefficient = (int) Math.round(bigAb / bigA);
    int cCoefficient = (int) Math.round(bigAc / bigA);

    ArrayList<ArrayList<ArrayList<Integer>>> newImage = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Integer> rgb = new ArrayList<>();
        int rColor = originalImage.get(i).get(j).get(0);
        int gColor = originalImage.get(i).get(j).get(1);
        int bColor = originalImage.get(i).get(j).get(2);
        if (j >= cutLine) {
          rgb.add(rColor);
          rgb.add(gColor);
          rgb.add(bColor);
        } else {
          int newR;
          int newG;
          int newB;
          if (rColor <= b) {
            newR = 0;
          } else if (rColor >= w) {
            newR = 255;
          } else {
            newR = aCoefficient * ((int) Math.pow(rColor, 2))
                    + bCoefficient * rColor + cCoefficient;
          }
          if (gColor <= b) {
            newG = 0;
          } else if (gColor >= w) {
            newG = 255;
          } else {
            newG = aCoefficient * ((int) Math.pow(gColor, 2))
                    + bCoefficient * gColor + cCoefficient;
          }

          if (bColor <= b) {
            newB = 0;
          } else if (bColor >= w) {
            newB = 255;
          } else {
            newB = aCoefficient * ((int) Math.pow(bColor, 2))
                    + bCoefficient * bColor + cCoefficient;
          }

          if (newR < 0) {
            rgb.add(0);
          } else if (newR > 255) {
            rgb.add(255);
          } else {
            rgb.add(newR);
          }
          if (newG < 0) {
            rgb.add(0);
          } else if (newG > 255) {
            rgb.add(255);
          } else {
            rgb.add(newG);
          }
          if (newB < 0) {
            rgb.add(0);
          } else if (newB > 255) {
            rgb.add(255);
          } else {
            rgb.add(newB);
          }
        }
        row.add(rgb);
      }
      newImage.add(row);
    }
    return newImage;
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Integer>>> compressImage(int t, String imageName) {
    ArrayList<ArrayList<ArrayList<Integer>>> originalImage = getImage(imageName);
    int height = originalImage.size();
    int width = originalImage.get(0).size();

    ArrayList<ArrayList<Integer>> redImage = new ArrayList<>();
    ArrayList<ArrayList<Integer>> greenImage = new ArrayList<>();
    ArrayList<ArrayList<Integer>> blueImage = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<Integer> r = new ArrayList<>();
      ArrayList<Integer> g = new ArrayList<>();
      ArrayList<Integer> b = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        r.add(originalImage.get(i).get(j).get(0));
        g.add(originalImage.get(i).get(j).get(1));
        b.add(originalImage.get(i).get(j).get(2));
      }
      redImage.add(r);
      greenImage.add(g);
      blueImage.add(b);
    }


    ArrayList<ArrayList<Integer>> paddedRed = pad(redImage);
    ArrayList<ArrayList<Integer>> paddedGreen = pad(greenImage);
    ArrayList<ArrayList<Integer>> paddedBlue = pad(blueImage);


    ArrayList<ArrayList<Double>> haarRed = haar(paddedRed);
    ArrayList<ArrayList<Double>> haarGreen = haar(paddedGreen);
    ArrayList<ArrayList<Double>> haarBlue = haar(paddedBlue);


    ArrayList<ArrayList<ArrayList<Double>>> thresholded = threshold(haarRed,
            haarGreen, haarBlue, t);

    ArrayList<ArrayList<Double>> threshRed = new ArrayList<>();
    ArrayList<ArrayList<Double>> threshGreen = new ArrayList<>();
    ArrayList<ArrayList<Double>> threshBlue = new ArrayList<>();

    for (int i = 0; i < thresholded.size(); i++) {
      ArrayList<Double> x = new ArrayList<>();
      ArrayList<Double> y = new ArrayList<>();
      ArrayList<Double> z = new ArrayList<>();

      for (int j = 0; j < thresholded.get(0).size(); j++) {
        x.add(thresholded.get(i).get(j).get(0));
        y.add(thresholded.get(i).get(j).get(1));
        z.add(thresholded.get(i).get(j).get(2));
      }
      threshRed.add(x);
      threshGreen.add(y);
      threshBlue.add(z);
    }

    ArrayList<ArrayList<Double>> invRed = invhaar(threshRed);
    ArrayList<ArrayList<Double>> invGreen = invhaar(threshGreen);
    ArrayList<ArrayList<Double>> invBlue = invhaar(threshBlue);

    ArrayList<ArrayList<Integer>> redPart = new ArrayList<>();
    ArrayList<ArrayList<Integer>> greenPart = new ArrayList<>();
    ArrayList<ArrayList<Integer>> bluePart = new ArrayList<>();


    for (int i = 0; i < invRed.size(); i++) {
      ArrayList<Integer> x = new ArrayList<>();
      ArrayList<Integer> y = new ArrayList<>();
      ArrayList<Integer> z = new ArrayList<>();
      for (int j = 0; j < invRed.get(0).size(); j++) {
        x.add((int) Math.round(invRed.get(i).get(j)));
        y.add((int) Math.round(invGreen.get(i).get(j)));
        z.add((int) Math.round(invBlue.get(i).get(j)));
      }
      redPart.add(x);
      greenPart.add(y);
      bluePart.add(z);
    }

    redPart = clamp(redPart);
    greenPart = clamp(greenPart);
    bluePart = clamp(bluePart);

    int originalHeight = originalImage.size();
    int originalWidth = originalImage.get(0).size();

    ArrayList<ArrayList<Integer>> redFinal = unpad(redPart, originalHeight, originalWidth);
    ArrayList<ArrayList<Integer>> greenFinal = unpad(greenPart, originalHeight, originalWidth);
    ArrayList<ArrayList<Integer>> blueFinal = unpad(bluePart, originalHeight, originalWidth);


    ArrayList<ArrayList<ArrayList<Integer>>> merged = merge(redFinal, greenFinal, blueFinal);
    return merged;


  }


  private ArrayList<ArrayList<ArrayList<Double>>> threshold(ArrayList<ArrayList<Double>> r,
                                                            ArrayList<ArrayList<Double>> g,
                                                            ArrayList<ArrayList<Double>> b,
                                                            int t) {
    if (t == 0) {
      return mergeDouble(r, g, b);
    }
    int height = r.size();
    int width = r.get(0).size();

    ArrayList<Double> elements = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (r.get(i).get(j) > 0) {
          elements.add(r.get(i).get(j));
        } else if (r.get(i).get(j) < 0) {
          elements.add(-1 * r.get(i).get(j));
        }
        if (g.get(i).get(j) > 0) {
          elements.add(g.get(i).get(j));
        } else if (g.get(i).get(j) < 0) {
          elements.add(-1 * g.get(i).get(j));
        }
        if (b.get(i).get(j) > 0) {
          elements.add(b.get(i).get(j));
        } else if (b.get(i).get(j) < 0) {
          elements.add(-1 * b.get(i).get(j));
        }

      }
    }

    HashSet<Double> hset = new HashSet<Double>(elements);

    ArrayList<Double> elementsUnique = new ArrayList<>(hset);

    Collections.sort(elementsUnique);


    int total_elements = elementsUnique.size();
    float num_zero_float = ((float) (t)) / ((float) 100) * ((float) total_elements);
    int num_zero = Math.round(num_zero_float);

    double val = elementsUnique.get(num_zero - 1);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double curr = r.get(i).get(j);
        if (curr > 0) {
          if (curr <= val) {
            r.get(i).set(j, 0.0);
          }
        } else if (curr < 0) {
          if (-1 * curr <= val) {
            r.get(i).set(j, 0.0);

          }
        }

      }
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double curr = g.get(i).get(j);
        if (curr > 0) {
          if (curr <= val) {
            g.get(i).set(j, 0.0);
          }
        } else if (curr < 0) {
          if (-1 * curr <= val) {
            g.get(i).set(j, 0.0);

          }
        }

      }
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double curr = b.get(i).get(j);
        if (curr > 0) {
          if (curr <= val) {
            b.get(i).set(j, 0.0);
          }
        } else if (curr < 0) {
          if (-1 * curr <= val) {
            b.get(i).set(j, 0.0);

          }
        }

      }
    }

    return mergeDouble(r, g, b);
  }


  private ArrayList<ArrayList<Integer>> pad(ArrayList<ArrayList<Integer>> pic) {
    ArrayList<ArrayList<Integer>> padded = new ArrayList<>();
    int height = pic.size();
    int width = pic.get(0).size();


    int dimension = Math.max(width, height);
    int twoPower = 1;
    while (twoPower < dimension) {
      twoPower = 2 * twoPower;
    }

    if (twoPower == height && twoPower == width) {
      return pic;
    }

    int length = twoPower;


    for (int i = 0; i < length; i++) {
      ArrayList<Integer> c = new ArrayList<>();
      for (int j = 0; j < length; j++) {
        if (i < height && j < width) {
          c.add(pic.get(i).get(j));
        } else {
          c.add(0);
        }
      }
      padded.add(c);
    }

    return padded;
  }

  private ArrayList<ArrayList<Integer>> clamp(ArrayList<ArrayList<Integer>> pic) {
    for (int i = 0; i < pic.size(); i++) {
      for (int j = 0; j < pic.get(0).size(); j++) {
        if (pic.get(i).get(j) < 0) {
          pic.get(i).set(j, 0);
        } else if (pic.get(i).get(j) > 255) {
          pic.get(i).set(j, 255);
        }
      }
    }
    return pic;
  }

  private ArrayList<ArrayList<Integer>> unpad(ArrayList<ArrayList<Integer>> pic, int h, int w) {
    ArrayList<ArrayList<Integer>> send = new ArrayList<>();
    for (int i = 0; i < h; i++) {
      ArrayList<Integer> row = new ArrayList<>();
      for (int j = 0; j < w; j++) {
        row.add(pic.get(i).get(j));
      }
      send.add(row);
    }
    return send;
  }

  private ArrayList<ArrayList<ArrayList<Integer>>> merge(ArrayList<ArrayList<Integer>> red,
                                                         ArrayList<ArrayList<Integer>> green,
                                                         ArrayList<ArrayList<Integer>> blue) {
    ArrayList<ArrayList<ArrayList<Integer>>> send = new ArrayList<>();

    for (int i = 0; i < red.size(); i++) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < red.get(0).size(); j++) {
        ArrayList<Integer> pixels = new ArrayList<>();
        pixels.add(red.get(i).get(j));
        pixels.add(green.get(i).get(j));
        pixels.add(blue.get(i).get(j));
        row.add(pixels);
      }
      send.add(row);
    }

    return send;
  }


  private ArrayList<ArrayList<ArrayList<Double>>> mergeDouble(ArrayList<ArrayList<Double>> red,
                                                              ArrayList<ArrayList<Double>> green,
                                                              ArrayList<ArrayList<Double>> blue) {
    ArrayList<ArrayList<ArrayList<Double>>> send = new ArrayList<>();

    for (int i = 0; i < red.size(); i++) {
      ArrayList<ArrayList<Double>> row = new ArrayList<>();
      for (int j = 0; j < red.get(0).size(); j++) {
        ArrayList<Double> pixels = new ArrayList<>();
        pixels.add(red.get(i).get(j));
        pixels.add(green.get(i).get(j));
        pixels.add(blue.get(i).get(j));
        row.add(pixels);
      }
      send.add(row);
    }

    return send;
  }


  private ArrayList<Double> transform(ArrayList<Double> s) {
    ArrayList<Double> avg = new ArrayList<>();
    ArrayList<Double> diff = new ArrayList<>();
    for (int i = 0; i < s.size() - 1; i += 2) {
      double av = (s.get(i) + s.get(i + 1)) / Math.sqrt(2);
      double di = (s.get(i) - s.get(i + 1)) / Math.sqrt(2);
      avg.add(av);
      diff.add(di);
    }
    ArrayList<Double> res = new ArrayList<>();
    res.addAll(avg);
    res.addAll(diff);
    return res;

  }


  private ArrayList<Double> inverse(ArrayList<Double> s) {
    ArrayList<Double> avg = new ArrayList<>();
    ArrayList<Double> diff = new ArrayList<>();
    int i = 0;
    int j = s.size() / 2;

    while (i < s.size() / 2) {
      double a = s.get(i);
      double b = s.get(j);
      double av = (a + b) / Math.sqrt(2);
      double di = (a - b) / Math.sqrt(2);
      avg.add(av);
      diff.add(di);

      i += 1;
      j += 1;
    }
    ArrayList<Double> res = new ArrayList<>();
    for (int z = 0; z < avg.size(); z++) {
      res.add(avg.get(z));
      res.add(diff.get(z));
    }
    return res;
  }


  private ArrayList<ArrayList<Double>> haar(ArrayList<ArrayList<Integer>> x) {
    ArrayList<ArrayList<Double>> doubleX = new ArrayList<>();


    for (int i = 0; i < x.size(); i++) {
      ArrayList<Double> row = new ArrayList<>();
      for (int j = 0; j < x.size(); j++) {
        row.add((double) x.get(i).get(j));
      }
      doubleX.add(row);
    }


    int c = doubleX.get(0).size();

    while (c > 1) {
      for (int i = 0; i < c; i++) {
        ArrayList<Double> temp = new ArrayList<>();

        for (int j = 0; j < c; j++) {
          temp.add(doubleX.get(i).get(j));
        }
        ArrayList<Double> temp2 = transform(temp);
        for (int a = 0; a < temp2.size(); a++) {
          doubleX.get(i).set(a, temp2.get(a));
        }
      }

      for (int i = 0; i < c; i++) {
        ArrayList<Double> temp3 = new ArrayList<>();
        for (int j2 = 0; j2 < c; j2++) {
          temp3.add(doubleX.get(j2).get(i));
        }
        ArrayList<Double> temp4 = transform(temp3);
        for (int a = 0; a < temp4.size(); a++) {
          doubleX.get(a).set(i, temp4.get(a));
        }

      }
      c = c / 2;
    }

    return doubleX;

  }

  private ArrayList<ArrayList<Double>> invhaar(ArrayList<ArrayList<Double>> x) {
    int c = 2;
    while (c <= x.get(0).size()) {

      for (int i = 0; i < c; i++) {
        ArrayList<Double> temp3 = new ArrayList<>();
        for (int j2 = 0; j2 < c; j2++) {
          temp3.add(x.get(j2).get(i));
        }

        ArrayList<Double> temp4 = inverse(temp3);
        for (int a = 0; a < c; a++) {
          x.get(a).set(i, temp4.get(a));
        }

      }

      for (int i = 0; i < c; i++) {

        ArrayList<Double> temp = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          temp.add(x.get(i).get(j));
        }

        ArrayList<Double> temp2 = inverse(temp);
        for (int a = 0; a < temp2.size(); a++) {
          x.get(i).set(a, temp2.get(a));
        }

      }
      c = c * 2;

    }
    return x;
  }


}