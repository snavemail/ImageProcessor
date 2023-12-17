package cs3500.imageprocessor.model.effects.singlelayer;import cs3500.imageprocessor.model.effects.LayerFilter;import cs3500.imageprocessor.model.image.Pixel;/** * This interface represents a filter for a layer. */public interface SingleLayerFilter extends LayerFilter {  /**   * Applies the filter to the given pixel.   *   * @param pixel the pixel to be filtered.   * @return the filtered pixel.   */  Pixel apply(Pixel pixel);}