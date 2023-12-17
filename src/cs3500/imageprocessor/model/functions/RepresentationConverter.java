package cs3500.imageprocessor.model.functions;

/**
 * This class contains utility methods to convert an RGB representation
 * to HSL and back and print those results.
 * Feel free to change these methods as required.
 */
public class RepresentationConverter {

  /**
   * Converts an RGB representation in the range 0-1 into an HSL
   * representation. The HSL representation is returned as an array of:
   * H, S, L where:
   * H is the hue in degrees (0-360)
   * S is the saturation (0-1)
   * L is the lightness (0-1)
   *
   * @param r red value of the RGB between 0 and 1
   * @param g green value of the RGB between 0 and 1
   * @param b blue value of the RGB between 0 and 1
   */
  public static Double[] convertRGBtoHSL(double r, double g, double b) {
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }
    return new Double[]{hue, saturation, lightness};
  }


  /**
   * Converts an HSL representation. The HSL representation is returned as an array of:
   * H, S, L where:
   * H is the hue in degrees (0-360)
   * S is the saturation (0-1)
   * L is the lightness (0-1)
   * and it is turned into an RGB representation where each component is in the range 0-1
   *
   * @param hue        hue of the HSL representation
   * @param saturation saturation of the HSL representation
   * @param lightness  lightness of the HSL representation
   */

  public static Double[] convertHSLtoRGB(double hue, double saturation, double lightness) {
    double r = Math.round(convertFn(hue, saturation, lightness, 0) * 255);
    double g = Math.round(convertFn(hue, saturation, lightness, 8) * 255);
    double b = Math.round(convertFn(hue, saturation, lightness, 4) * 255);
    return new Double[]{r, g, b};
  }

  /**
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model.
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }

  /**
   * Main method to test the conversion methods.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    RepresentationConverter.convertRGBtoHSL(0.0, 0.0, 0.0);
    RepresentationConverter.convertRGBtoHSL(1.0, 1.0, 1.0);
    RepresentationConverter.convertRGBtoHSL(1.0, 0.0, 0.0);

    RepresentationConverter.convertHSLtoRGB(14.0, 0.813, 0.624);
    RepresentationConverter.convertHSLtoRGB(0.0, 1.0, 0.5);
    RepresentationConverter.convertHSLtoRGB(0.0, 0.0, 1.0);
  }

}
