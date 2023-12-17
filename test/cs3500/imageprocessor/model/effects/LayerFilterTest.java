package cs3500.imageprocessor.model.effects;import org.junit.Test;import cs3500.imageprocessor.model.effects.blendfilter.BlendingFilter;import cs3500.imageprocessor.model.effects.singlelayer.BlueComponent;import cs3500.imageprocessor.model.effects.singlelayer.BrightenIntensity;import cs3500.imageprocessor.model.effects.singlelayer.BrightenLuma;import cs3500.imageprocessor.model.effects.singlelayer.BrightenValue;import cs3500.imageprocessor.model.effects.singlelayer.DarkenIntensity;import cs3500.imageprocessor.model.effects.singlelayer.DarkenLuma;import cs3500.imageprocessor.model.effects.singlelayer.DarkenValue;import cs3500.imageprocessor.model.effects.singlelayer.GreenComponent;import cs3500.imageprocessor.model.effects.singlelayer.Normal;import cs3500.imageprocessor.model.effects.singlelayer.RedComponent;import cs3500.imageprocessor.model.effects.singlelayer.SingleLayerFilter;import cs3500.imageprocessor.model.image.Image;import cs3500.imageprocessor.model.image.Layer;import cs3500.imageprocessor.model.image.Pixel;import cs3500.imageprocessor.model.processor.ImageProcessorModel;import cs3500.imageprocessor.model.processor.ImageProcessorModelImpl;import static org.junit.Assert.assertEquals;/** * Tests the Effects class. */public class LayerFilterTest {  BlueComponent blueComponent = new BlueComponent();  GreenComponent greenComponent = new GreenComponent();  RedComponent redComponent = new RedComponent();  BrightenIntensity brightenIntensity = new BrightenIntensity();  BrightenLuma brightenLuma = new BrightenLuma();  BrightenValue brightenValue = new BrightenValue();  DarkenIntensity darkenIntensity = new DarkenIntensity();  DarkenLuma darkenLuma = new DarkenLuma();  DarkenValue darkenValue = new DarkenValue();  Normal normal = new Normal();  ImageProcessorModel<Layer, Image, Pixel, SingleLayerFilter, BlendingFilter> imageProcessor =          new ImageProcessorModelImpl();  /**   * Tests the applyFunction method.   */  @Test  public void testApplyFunction() {    imageProcessor.newProject(3, 6);    for (int i = 0; i < 3; i++) {      for (int j = 0; j < 6; j++) {        assertEquals(255, imageProcessor.getLayer("Background").getPixel(i, j).getRed());        assertEquals(255, imageProcessor.getLayer("Background").getPixel(i, j).getGreen());        assertEquals(255, imageProcessor.getLayer("Background").getPixel(i, j).getBlue());      }    }    imageProcessor.applyFunction("blue-component", "Background");    for (int i = 0; i < 3; i++) {      for (int j = 0; j < 6; j++) {        assertEquals(0, imageProcessor.getLayer("Background").getPixel(i, j).getRed());        assertEquals(0, imageProcessor.getLayer("Background").getPixel(i, j).getGreen());        assertEquals(255, imageProcessor.getLayer("Background").getPixel(i, j).getBlue());      }    }  }  /**   * Tests the getName method.   */  @Test  public void getName() {    assertEquals("Blue Component", blueComponent.getName());    assertEquals("Green Component", greenComponent.getName());    assertEquals("Red Component", redComponent.getName());    assertEquals("Brighten Intensity", brightenIntensity.getName());    assertEquals("Brighten Luma", brightenLuma.getName());    assertEquals("Brighten Value", brightenValue.getName());    assertEquals("Darken Intensity", darkenIntensity.getName());    assertEquals("Darken Luma", darkenLuma.getName());    assertEquals("Darken Value", darkenValue.getName());    assertEquals("Normal", normal.getName());  }}