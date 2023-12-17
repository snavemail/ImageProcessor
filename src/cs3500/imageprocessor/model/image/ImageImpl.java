package cs3500.imageprocessor.model.image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class contains methods to create images. It implements the Image interface.
 * It is used to create images for the model. It is also used to create images for the view.
 */
public class ImageImpl implements Image {

  private final List<List<Pixel>> imageGrid;
  private final int width;
  private final int height;
  private int xLoc;
  private int yLoc;
  private final int maxValue;

  /**
   * Constructor to create image. Takes in a list of pixels, x and y location, and max value.
   *
   * @param imageGrid the image grid
   * @param xLoc the x-coord of image
   * @param yLoc the y-coord of image
   * @param maxValue the max value
   *
   * @throws IllegalArgumentException if parameters are invalid
   */
  public ImageImpl(List<List<Pixel>> imageGrid, int xLoc, int yLoc, int maxValue) {

    if (imageGrid == null || maxValue < 0 || xLoc < 0 || yLoc < 0) {
      throw new IllegalArgumentException("invalid parameters");
    }
    this.imageGrid = imageGrid;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.width = imageGrid.get(0).size();
    this.height = imageGrid.size();
    this.maxValue = maxValue;
  }

  /**
   * Constructor to create image with a given image grid, width and height,
   * x and y location, and max value.
   *
   * @param imageGrid the image grid
   * @param xLoc the x-coord of image
   * @param yLoc the y-coord of image
   * @param height the height of image
   * @param width the width of image
   * @param maxValue the max value
   *
   * @throws IllegalArgumentException if parameters are invalid
   */
  public ImageImpl(List<List<Pixel>> imageGrid, int xLoc, int yLoc, int width, int height,
                   int maxValue) {
    if (imageGrid == null || width < 1 || height < 1 || maxValue < 0 || xLoc < 0 || yLoc < 0) {
      throw new IllegalArgumentException("invalid parameters");
    }
    this.imageGrid = imageGrid;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  public List<List<Pixel>> getImageList() {
    return new ArrayList<>(imageGrid);
  }

  public int getWidth() {
    return this.width;
  }

  @Override
  public int getXLoc() {
    return this.xLoc;
  }

  @Override
  public int getYLoc() {
    return this.yLoc;
  }

  @Override
  public void setXLoc(int xLoc) {
    this.xLoc = xLoc;
  }

  @Override
  public void setYLoc(int yLoc) {
    this.yLoc = yLoc;
  }

  public int getHeight() {
    return this.height;
  }

  public int getMaxValue() {
    return this.maxValue;
  }

  public List<Pixel> flattenList() {
    return imageGrid.stream().flatMap(List::stream).collect(Collectors.toList());
  }

  @Override
  public boolean inBounds(int x, int y, int width, int height) {
    return x >= getXLoc() && x < (getXLoc() + width) && y >= getYLoc() && y < (getYLoc() + height);
  }

  @Override
  public Pixel getPixel(int x, int y) {
    return imageGrid.get(y - getYLoc()).get(x - getXLoc());
  }
}
