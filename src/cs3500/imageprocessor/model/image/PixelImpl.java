package cs3500.imageprocessor.model.image;

import cs3500.imageprocessor.model.functions.FunctionUtils;

/**
 * This class contains methods to create pixels
 * that will then be used to create images. A pixel has a red, green, and blue value.
 */
public class PixelImpl implements Pixel {

  private final int alpha;
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for pixel that takes in red, green, and blue values.
   */
  public PixelImpl(int red, int green, int blue) {
    this.alpha = 255;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructor for pixel that takes in alpha, red, green, and blue values.
   */
  public PixelImpl(int alpha, int red, int green, int blue) {
    this.alpha = alpha;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructor for pixel that takes in a 24-bit rgb value.
   */
  public PixelImpl(int channelValue) {
    this.alpha = channelValue >> 24 & 0x0ff;
    this.red = channelValue >> 16 & 0x0ff;
    this.green = channelValue >> 8 & 0x0ff;
    this.blue = channelValue & 0x0ff;
  }

  @Override
  public int getRGB() {
    return FunctionUtils.channelValue(this.red, this.green, this.blue);
  }

  @Override
  public int getRGBA() {
    return FunctionUtils.alphaChannelValue(this.alpha, this.red, this.green, this.blue);
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}
