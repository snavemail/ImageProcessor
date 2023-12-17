package cs3500.imageprocessor.model.effects.singlelayer;

import java.util.function.Function;

import cs3500.imageprocessor.model.image.PixelImpl;
import cs3500.imageprocessor.model.functions.FunctionUtils;
import cs3500.imageprocessor.model.image.Pixel;

/**
 * This class contains methods to brighten a pixel based on the max value of either of the
 * Red, Green, or Blue values in this pixel.
 */
public class BrightenValue implements Function<Pixel, Pixel>, SingleLayerFilter {

  private final String name = "Brighten-Value";

  /**
   * Returns a new pixel with an updated value based to brighten it.
   * @param pixel the function argument
   * @return a new pixel with an updated value.
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int red = pixel.getRed();
    int green = pixel.getGreen();
    int blue = pixel.getBlue();

    int brightenValue = Math.max(Math.max(red, green), blue);

    int newRed = FunctionUtils.clampValue(red + brightenValue);
    int newGreen = FunctionUtils.clampValue(green + brightenValue);
    int newBlue = FunctionUtils.clampValue(blue + brightenValue);

    return new PixelImpl(newRed, newGreen, newBlue);
  }

  @Override
  public String getName() {
    return this.name;
  }
}
