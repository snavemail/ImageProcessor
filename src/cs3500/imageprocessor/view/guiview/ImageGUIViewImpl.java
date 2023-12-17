package cs3500.imageprocessor.view.guiview;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import cs3500.imageprocessor.controller.guicontroller.ImageGUIController;


/**
 * This class represents the GUI view for the image processor. It contains a panel for the image,
 * a panel for the list of layers, and a panel for the list of commands. It implements the
 * ImageGUIView interface.
 */
public class ImageGUIViewImpl extends JFrame implements ImageGUIView {
  private ImageGUIController controller;
  private final JPanel leftPanel;
  private final ImagePanel imagePanel;

  /**
   * Constructs an ImageGUIViewImpl object. It initializes the controller, the left panel, and the
   * image panel. Adds a main panel to the frame, and adds the left panel and the image panel to
   * the main panel. Also adds a top panel to the frame, which contains the list of commands. Adds a
   * right panel to the frame, which contains the list of filters.
   */
  public ImageGUIViewImpl() {
    super();
    int width = 1100;
    int height = 800;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(width, height));

    // Main Panel - Everything
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    //Top Panel - List of Commands
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
    topPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
    topPanel.setPreferredSize(new Dimension(width, 50));
    String[] listOfCommands = {"New Project", "Load", "Save Image", "Save Project",
        "Add Image to Layer", "Add Layer"};

    for (String command : listOfCommands) {
      JButton button = new JButton(command);
      button.setActionCommand(command);
      button.addActionListener(this);
      topPanel.add(button);
    }
    add(topPanel, BorderLayout.NORTH);


    // Right Panel - List of Filters
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setPreferredSize(new Dimension(200, height - 50));
    String[] listOfFilters = {"Blue Component", "Green Component", "Red Component",
        "Brighten Intensity", "Brighten Luma", "Brighten Value", "Brighten Blend",
        "Darken Intensity", "Darken Luma", "Darken Value", "Darken Blend",
        "Inversion", "Normal"};
    for (String command : listOfFilters) {
      JButton button = new JButton(command);
      button.setPreferredSize(new Dimension(180, 50));
      button.setActionCommand(command);
      button.addActionListener(this);
      rightPanel.add(button);
    }
    rightPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
    add(rightPanel, BorderLayout.EAST);

    // Left Panel - List of Layers
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setPreferredSize(new Dimension(200, height - 50));
    leftPanel.setBorder(BorderFactory.createTitledBorder("Layers"));
    JLabel layerLabel = new JLabel("Layer Name");
    leftPanel.add(layerLabel);
    add(leftPanel, BorderLayout.WEST);

    // Image Panel
    imagePanel = new ImagePanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setPreferredSize(new Dimension(width - 400, height - 50));
    this.add(imagePanel, BorderLayout.CENTER);

    //Scroll Panel
    JScrollPane mainScrollPane = new JScrollPane((Component) imagePanel);
    add(mainScrollPane);

    this.pack();
  }

  /**
   * Determines what to do when a button is pressed.
   *
   * @param arg the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg) {
    switch (arg.getActionCommand()) {
      case "New Project":
        try {
          int width = Integer.parseInt(JOptionPane.showInputDialog("Enter a width"));
          int height = Integer.parseInt(JOptionPane.showInputDialog("Enter a height"));
          this.controller.newProject(width, height);
          this.controller.setLayerList();
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Enter a valid number");
        }
        break;
      case "Load": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File file = fchooser.getSelectedFile();
          try {
            this.controller.load(file.getPath());
            this.controller.setLayerList();
          } catch (Exception f) {
            JOptionPane.showMessageDialog(this, "Bad file name");
          }
        }
      }
      break;
      case "Save Image": {
        final JFileChooser fchooser1 = new JFileChooser(".");
        int retvalue = fchooser1.showSaveDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File file = fchooser1.getSelectedFile();
          try {
            this.controller.saveImage(file.getPath());
          } catch (Exception f) {
            JOptionPane.showMessageDialog(this, "Bad file name");
          }
        }
      }
      break;
      case "Save Project": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File file = fchooser.getSelectedFile();
          try {
            this.controller.saveProject(file.getPath());
          } catch (Exception f) {
            JOptionPane.showMessageDialog(this, "Bad file name");
          }
        }
      }
      break;
      case "Add Image to Layer": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showOpenDialog(this);
        try {
          int xLoc = Integer.parseInt(JOptionPane.showInputDialog("Enter an x-coord"));
          int yLoc = Integer.parseInt(JOptionPane.showInputDialog("Enter a y-coord"));
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File file = fchooser.getSelectedFile();
            try {
              this.controller.addImageToLayer(file.getPath(), xLoc, yLoc);
            } catch (Exception f) {
              JOptionPane.showMessageDialog(this, "Bad file name");
            }
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Enter a valid number");
        }
      }
      break;
      case "Add Layer": {
        String layerName = JOptionPane.showInputDialog("Enter a name for the layer");
        try {
          this.controller.addLayer(layerName);
          this.controller.setLayerList();
        } catch (IllegalAccessException e) {
          JOptionPane.showMessageDialog(this, "Bad layer name");
        } catch (IndexOutOfBoundsException e) {
          JOptionPane.showMessageDialog(this, "No Layer Typed");
        }
      }
      break;
      case "Blue Component":
        try {
          this.controller.applyFilter("blue-component");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Green Component":
        try {
          this.controller.applyFilter("green-component");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Red Component":
        try {
          this.controller.applyFilter("red-component");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Brighten Intensity":
        try {
          this.controller.applyFilter("brighten-intensity");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Brighten Luma":
        try {
          this.controller.applyFilter("brighten-luma");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Brighten Value":
        try {
          this.controller.applyFilter("brighten-value");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Darken Intensity":
        try {
          this.controller.applyFilter("darken-intensity");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Darken Luma":
        try {
          this.controller.applyFilter("darken-luma");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Darken Value":
        try {
          this.controller.applyFilter("darken-value");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Brighten Blend":
        try {
          this.controller.applyBlendingFilter("brighten-blend");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Darken Blend":
        try {
          this.controller.applyBlendingFilter("darken-blend");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        break;
      case "Inversion": {
        try {
          this.controller.applyBlendingFilter("inversion");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
      break;
      case "Normal": {
        try {
          this.controller.applyFilter("normal");
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
      break;
      default:
        break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    /*
    No need to implement this method. It is required by the ItemListener
     */
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    /*
    No need to implement this method. It is required by the ListSelectionListener
     */
  }

  @Override
  public void refresh() {
    BufferedImage image1 = controller.createImage();
    this.imagePanel.updateImage(image1);
    this.repaint();
    this.revalidate();
  }

  @Override
  public void addFeatures(ImageGUIController controller) {
    this.controller = controller;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public JPanel getLeftPanel() {
    return this.leftPanel;
  }
}
