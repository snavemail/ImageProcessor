package cs3500.imageprocessor.model.image;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return the image that was read.
   */
  public static Image readPPM(String filename, String imageName) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    System.out.println("Name of image: " + imageName);
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    List<List<Pixel>> imageGrid = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        row.add(new PixelImpl(red, green, blue));
      }
      imageGrid.add(row);
    }
    return new ImageImpl(imageGrid, 0, 0, maxValue);
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return the image that was read.
   */
  public static List<Layer> readCollage(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("C1")) {
      System.out.println("Invalid TXT file: plain RAW file should begin with C1");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    List<Layer> layer = new ArrayList<>();

    while (sc.hasNext()) {
      String layerName = sc.next();
      System.out.println("Name of layer: " + layerName);
      String filterName = sc.next();
      System.out.println("Name of filter: " + filterName);

      List<List<Pixel>> imageGrid = new ArrayList<>();
      for (int i = 0; i < height; i++) {
        List<Pixel> row = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          int alpha = sc.nextInt();
          row.add(new PixelImpl(alpha, red, green, blue));
        }
        imageGrid.add(row);
      }
      Image image = new ImageImpl(imageGrid, 0, 0, maxValue);
      layer.add(new LayerImpl(layerName, new ArrayList<>(List.of(image)), filterName,
              width, height));
    }
    return layer;
  }
}

