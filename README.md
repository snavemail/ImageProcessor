# PROJECT DESCRIPTION:

This is an image processor application with the ability to create projects with multiple image
layers and apply various filters. You can use this program either by inputting commands or by interacting with a custom graphic user interface. Users have the ability to create a new project of custom size, add unlimited layers and images (with user-specified coordinates), apply filters, and save images and projects in various formats. Our processor currently has thirteen filters available:
* Green component: turns image green
* Blue component: turns image blue
* Red component: turns image red
* Brighten intensity: brightens image based on intensity
* Brighten value: brightens image based on value
* Brighten luma: brightens image based on luma
* Brighten blend: brightens image based on previous image
* Darken intensity: darkens image based on intensity
* Darken value: darkens image based on value
* Darken luma: darkens image based on luma
* Darken blend: darkens image based on previous image
* Inversion: inverts colors
* Normal: no change

All required features are available!


## REQUIREMENTS:
* Java 11 or higher JRE
* JUnit 4 for running the tests

* To run the processor, download the zip file from Handin and run the ImageProcessor class located in the src directory. This will launch the GUI, though you can type into the CLI if you prefer.


## USEME LOCATION:
The USEME file is a set of instructions on how to use our processor, with an example. The USEME file is located in the root submission folder. 


## HOW IT WORKS:

Our model creates pixels that have an alpha (optional value), red, green, and blue value. It creates
a 2D array of those pixels to create image. Our model supports our filter methods, which applies
specific equations on the pixel's RBG components to create the filter's desired effect. Our updated model has additional blending filters. From there, our controller allows for user input to be taken in the form of specific commands, which communicates to our model which methods should be run. Our view allows for projects to be saved, created, and loaded. In our updated model, we now have a Graphic User Interface in place of our command line that can be launched by running the cs3500.imageprocessor.ImageProcessor class.


## CLASSES:

### Controller Package:

##### TextController

* ImageController:
    * Interface for the controller, responsible for user input and running program.
    * A user can select from a list of commands to run the program.
* ImageControllerImpl:
    * Stores all commands that a user can run and takes input, starts the program.

##### GUIController

* ImageGUIController:
    * Represents a controller for the GUI.
    * This controller is responsible for running the image processor.
    * It is designed to be user-friendly.
* ImageGUIControllerImpl:
    * Implements methods in interface that run the image processor.

### View Package:

##### TextView

* ImageViewInterface:
    * Interface for the view, responsible for displaying images and eventual GUI.
    * Represents a view to display data about our images.
    * Handles displaying information about the image, saving the image, and saving the project.
* ImageViewImpl:
    * Represents a view to display data about our images.

##### GUIView

* ImageView:
    * Interface for the GUI view, responsible for displaying images via the GUI.
    * It contains methods to refresh the view, add features to the view, set the layer list,
      add to the panel, and get the left panel.
* ImageViewImpl:
    * Represents a view to display our GUI and the images on them based on the user input
      given from the controller.
* ImagePanel:
    * Simple panel to display a single image, displays the image using a given
      BufferedImage, during its refresh.

### Model Package:

##### Processor Package

* ImageProcessorModel-
    * The ImageProcessorModel interface defines the methods that an image processor
      model should implement.
    * It provides functionality to apply filters to the image
* ImageProcessorModelImpl-
    * This class represents a model for an image processor.
    * It contains a map of layers.
    * It also contains the width and height of the project.
    * It can add layers, remove layers, and get the layers.
    * It can also get the width and height of the project.
    * It can also save the project as a text file.
    * It can also load a project from a text file.

##### Effects Package

* Contains classes that are each responsible for creating and applying a unique filter effect.
* Added BrightenBlend, DarkenBlend, and Inversion.

##### Functions Package

* FunctionUtils:
    * contains utility methods for rgb values and xy coords.
* RepresentationConverter:
    * contains methods to convert rgb values to decimal and decimal to rgb values.

##### Image Package

* Image:
    * Interface for image classes.
    * This interface represents an image. An image has a list of pixels, a width, a height, and a
    * maximum RGB value. An image can be applied a function to it. An image can also be moved to a
    * different location.
* ImageImpl:
    * Creates an image object using a 2D array of Pixels
    * This class contains methods to create images. It implements the Image interface.
    * It is used to create images for the model. It is also used to create images for the view.
* ImageUtil2:
    * contains utility methods to read a PPM image from file and write a PPM image to file.
* Pixel:
    * Interface for pixel classes.
    * A pixel has a red, green, and blue value.
    * You can get the RGB value of this pixel, or you can get the red, green, or blue value of this
      pixel.
* PixelImpl:
    * Creates a pixel object with calculated rgb and alpha values.
    * This class contains methods to create pixels that will then be used to create images.
    * A pixel has a red, green, and blue value.
* Layer:
    * This interface represents a layer of images.
    * A layer can have a filter applied to it.
    * The filter will be applied to all images in the layer.
    * A layer can also have multiple images added to it.
* LayerImpl:
    * Creates a list of images that make up a layer.
    * A layer can have a filter applied to it.
    * The filter will be applied to all images in the layer.
    * A layer can also have multiple images added to it.

### Other:

* cs3500.imageprocessor.ImageProcessor:
    * Represents the main class for the image processor, responsible for running the
      image processor, creates the model, view, and controller and then runs the controller.


## CITATIONS:

### Image:

* Image of cups is an art project by Kelsey Cao, a student at Northeastern University.
* Image of tako is given by Professor Lucia Nunez
* Image of class-diagram-a6 is from Liam Evans and Sophia Campione's assignment 6
