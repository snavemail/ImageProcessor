## DECOUPLING VIEWS:We successfully decoupled the views by making it necessary that we only need to pass the viewinterfaces and implementations, and only the controller and model interfaces.This is what we need to add to send along our view written in package/class format:* controller    * guicontroller        * ImageGUIController    * textcontroller        * ImageController* model    * ImageProcessorModel (model works for both controllers/views)* view    * guiview        * ImageGUIView         * ImageGUIViewImpl        * ImagePanel    * textview        * ImageViewImpl        * ImageViewInterface

Our two controller interfaces are necessary as they are used to take in user input and communicate to the view what it needs to display with every updated user interaction.

Our model interface is necessary as it contains the methods that execute all of our processor's features and maintains the logic and rules of our processor. 

Our view interfaces and implementations are necessary in order to display any interactions made by the user. Our ImagePanel implementation is necessary as it aids our view implementation by creating a panels that can display images. 