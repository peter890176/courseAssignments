import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Component;


/**
 * This class represents the JAVA Swing GUI.
 * It contains the methods to open, save and processing the image.
 */
public class MockImageView extends JFrame implements MockIView {
  private GridBagConstraints gbc;
  private JButton open;
  private JLabel currentPath;
  private JLabel unsaved;
  private JComboBox<String> functionCombobox;
  private JLabel instructions;
  private JLabel arg0Label;
  private JTextField arg0;
  private JLabel arg1Label;
  private JTextField arg1;
  private JLabel arg2Label;
  private JTextField arg2;
  private JTextField argSplit;
  private JButton go;
  private JButton split;
  private ImageIcon icon;
  private JLabel imageLabel;
  private JComboBox<String> imageFormatCombobox;
  private JButton saveButton;
  private JLabel message;
  private JLabel histogramLabel;

  /**
   * The constructor of this class.
   * Bases on the JFrame, it includes several components and also sets the layout.
   *
   * @param caption the title of this JFrame.
   */
  public MockImageView(String caption) {
    super(caption);
    setSize(1200, 800);
    setLocation(300, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    Insets insets = this.getInsets();


    JLabel loadImage = new JLabel(" Load Image:");
    loadImage.setFont(new Font("Arial", Font.PLAIN, 14));
    Dimension size = loadImage.getPreferredSize();
    loadImage.setBounds(5 + insets.left, 10 + insets.top, size.width, size.height);
    this.add(loadImage);


    open = new JButton("Open");
    size = open.getPreferredSize();
    open.setBounds( 95 + insets.left, 5 + insets.top, size.width, size.height);
    this.add(open);

    currentPath = new JLabel("Current Path");
    size = currentPath.getPreferredSize();
    currentPath.setBounds( 170 + insets.left, 10 + insets.top, size.width * 8, size.height);
    this.add(currentPath);

    unsaved = new JLabel("Unsaved/Saved");
    size = unsaved.getPreferredSize();
    unsaved.setBounds( 800 + insets.left, 10 + insets.top, size.width * 2, size.height);
    this.add(unsaved);
    unsaved.setFont(new Font("Arial", Font.PLAIN, 20));


    JLabel saveAs = new JLabel("Save as...");
    size = saveAs.getPreferredSize();
    saveAs.setBounds( 940 + insets.left, 10 + insets.top, size.width * 2, size.height);
    this.add(saveAs);
    saveAs.setFont(new Font("Arial", Font.PLAIN, 14));


    String[] saveFormat = {".jpg", ".jpeg", ".png", ".ppm"};
    imageFormatCombobox = new JComboBox<String>();
    for (int i = 0; i < saveFormat.length; i++) {
      imageFormatCombobox.addItem(saveFormat[i]);
    }
    size = imageFormatCombobox.getPreferredSize();
    imageFormatCombobox.setBounds( 1015 + insets.left, 5 + insets.top, size.width, size.height);
    this.add(imageFormatCombobox);
    imageFormatCombobox.setEnabled(false);

    saveButton = new JButton("Save");
    saveButton.setEnabled(false);
    size = saveButton.getPreferredSize();
    saveButton.setBounds( 1115 + insets.left, 5 + insets.top, size.width, size.height);
    this.add(saveButton);


    JLabel functionalities = new JLabel("Function:");
    size = functionalities.getPreferredSize();
    functionalities.setBounds( 10 + insets.left, 55 + insets.top, size.width + 20, size.height);
    this.add(functionalities);
    functionalities.setFont(new Font("Arial", Font.PLAIN, 14));

    String[] functions = {"Red-component",
        "Green-component",
        "Blue-component",
        "Horizontal-flip",
        "Vertical-flip",
        "Blur",
        "Sharpen",
        "Luma-component",
        "Sepia",
        "Compress",
        "Color-correct",
        "Levels-adjust"};
    functionCombobox = new JComboBox<String>();
    for (int i = 0; i < functions.length; i++) {
      functionCombobox.addItem(functions[i]);
    }
    functionCombobox.setEnabled(false);
    size = functionCombobox.getPreferredSize();
    functionCombobox.setBounds( 75 + insets.left, 50 + insets.top, size.width, size.height);
    this.add(functionCombobox);


    instructions = new JLabel();
    size = instructions.getPreferredSize();
    instructions.setBounds( 225 + insets.left, 45 + insets.top, 700, 30);
    this.add(instructions);
    instructions.setFont(new Font("Arial", Font.PLAIN, 16));


    arg0Label = new JLabel("arg0:");
    size = arg0Label.getPreferredSize();
    arg0Label.setBounds( 695 + insets.left, 50 + insets.top, 115, size.height);
    this.add(arg0Label);
    arg0Label.setEnabled(false);

    arg0 = new JTextField("arg0");
    size = arg0.getPreferredSize();
    arg0.setBounds( 770 + insets.left, 47 + insets.top, size.width + 15, size.height);
    this.add(arg0);
    arg0.setEnabled(false);

    arg1Label = new JLabel("arg1:");
    size = arg1Label.getPreferredSize();
    arg1Label.setBounds( 830 + insets.left, 50 + insets.top, size.width, size.height);
    this.add(arg1Label);
    arg1Label.setEnabled(false);


    arg1 = new JTextField("arg1");
    size = arg1.getPreferredSize();
    arg1.setBounds( 870 + insets.left, 47 + insets.top, size.width + 15, size.height);
    this.add(arg1);
    arg1.setEnabled(false);

    arg2Label = new JLabel("arg2:");
    size = arg2Label.getPreferredSize();
    arg2Label.setBounds( 920 + insets.left, 50 + insets.top, size.width, size.height);
    this.add(arg2Label);
    arg2Label.setEnabled(false);

    arg2 = new JTextField("arg2");
    size = arg2.getPreferredSize();
    arg2.setBounds( 960 + insets.left, 47 + insets.top, size.width + 15, size.height);
    this.add(arg2);
    arg2.setEnabled(false);

    go = new JButton("Go");
    size = go.getPreferredSize();
    go.setBounds( 1015 + insets.left,45 + insets.top, size.width * 2, size.height + 15);
    this.add(go);
    go.setEnabled(false);
    go.setFont(new Font("Arial", Font.PLAIN, 16));

    JLabel splitPercentage = new JLabel("Split Percentage:");
    size = splitPercentage.getPreferredSize();
    splitPercentage.setBounds( 825 + insets.left, 125 + insets.top, size.width + 20, size.height);
    this.add(splitPercentage);
    splitPercentage.setFont(new Font("Arial", Font.PLAIN, 14));


    argSplit = new JTextField("integer");
    size = argSplit.getPreferredSize();
    argSplit.setBounds( 940 + insets.left, 125 + insets.top, size.width, size.height);
    this.add(argSplit);
    argSplit.setEnabled(false);

    split = new JButton("Split");
    size = split.getPreferredSize();
    split.setBounds( 1000 + insets.left, 115 + insets.top, size.width + 25, size.height + 25);
    this.add(split);
    split.setEnabled(false);

    imageLabel = new JLabel(icon);
    JScrollPane imageScroll = new JScrollPane(imageLabel);
    imageScroll.setPreferredSize(new Dimension(800, 600));
    size = imageScroll.getPreferredSize();
    imageScroll.setBounds( 10 + insets.left, 75 + insets.top, size.width, size.height);
    this.add(imageScroll);


    message = new JLabel("Message/Errors");
    this.add(message);
    size = message.getPreferredSize();
    message.setBounds( 10 + insets.left, 700 + insets.top, size.width * 10, size.height * 2);
    message.setFont(new Font("Arial", Font.PLAIN, 18));


    histogramLabel = new JLabel();
    histogramLabel.setPreferredSize(new Dimension(260, 260));
    size = histogramLabel.getPreferredSize();
    histogramLabel.setBounds( 900 + insets.left, 350 + insets.top, size.width, size.height * 2);
    this.add(histogramLabel);

    setResizable(false);
    setVisible(true);

  }

  /**
   * This method is to return the component of itself.
   */
  public Component getComponent() {
    return this;
  }

  @Override
  public void addFeatures(MockControllerFeatures viewController) {
    open.addActionListener(evt -> viewController.chooseLoadFile());
    saveButton.addActionListener(evt -> viewController.chooseSaveFile());
    functionCombobox.addActionListener(evt -> viewController.functionChoose());
    go.addActionListener(evt -> viewController.goClick(getSelectedFunction()));
    split.addActionListener(evt -> viewController.split());

  }

  @Override
  public void setCurrentPath(String s) {
    currentPath.setText(s);
  }

  @Override
  public void setUnsaved(String s) {
    unsaved.setText(s);
    if (s.equals("Unsaved")) {
      unsaved.setForeground(Color.RED);
    } else {
      unsaved.setForeground(new Color(50, 205, 50));
    }
  }

  @Override
  public void setMessage(String s) {
    message.setText(s);
  }

  @Override
  public void setSaveButtonEnable() {
    saveButton.setEnabled(true);
  }

  @Override
  public String getSaveFormat() {
    return (String) imageFormatCombobox.getSelectedItem();
  }

  @Override
  public void setFunctionEnabled() {
    functionCombobox.setEnabled(true);
  }

  @Override
  public void setArg0(boolean b) {
    if (b) {
      arg0.setEnabled(true);
    } else {
      arg0.setEnabled(false);
    }
  }

  @Override
  public void setArg0Text(String s) {
    arg0.setText(s);
  }

  @Override
  public void setArg0Label(String s) {
    arg0Label.setText(s);
  }

  @Override
  public void setArg1(boolean b) {
    if (b) {
      arg1.setEnabled(true);
    } else {
      arg1.setEnabled(false);
    }
  }

  @Override
  public void setArg1Text(String s) {
    arg1.setText(s);
  }

  @Override
  public void setArg1Label(String s) {
    arg1Label.setText(s);
  }

  @Override
  public void setArg2(boolean b) {
    if (b) {
      arg2.setEnabled(true);
    } else {
      arg2.setEnabled(false);
    }
  }

  @Override
  public void setArg2Text(String s) {
    arg2.setText(s);
  }

  @Override
  public void setArg2Label(String s) {
    arg2Label.setText(s);
  }

  @Override
  public void setArgSplit(boolean b) {
    if (b) {
      argSplit.setEnabled(true);
    } else {
      argSplit.setEnabled(false);
    }
  }

  @Override
  public void setSplit(boolean b) {
    if (b) {
      split.setEnabled(true);
    } else {
      split.setEnabled(false);
    }
  }

  @Override
  public void setGoButtonEnable() {
    go.setEnabled(true);
  }

  @Override
  public void setImageFormatComboboxEnable() {
    imageFormatCombobox.setEnabled(true);
  }

  @Override
  public Component getSaveComponent() {
    return this.getComponent();
  }

  @Override
  public String getSelectedFunction() {
    return (String) functionCombobox.getSelectedItem();
  }

  @Override
  public void setInstructions(String s) {
    instructions.setText(s);
  }

  @Override
  public void displayImage(ImageIcon icon) {
    imageLabel.setIcon(icon);
  }

  @Override
  public void displayHistogram(ImageIcon icon) {
    histogramLabel.setIcon(icon);
  }

  @Override
  public String getArg0() {
    return arg0.getText();
  }

  @Override
  public String getArg1() {
    return arg1.getText();
  }

  @Override
  public String getArg2() {
    return arg2.getText();
  }

  @Override
  public String getArgSplit() {
    return argSplit.getText();
  }

  @Override
  public void setArg0LabelEnable(boolean b) {
    arg0Label.setEnabled(b);
  }

  @Override
  public void setArg1LabelEnable(boolean b) {
    arg1Label.setEnabled(b);
  }

  @Override
  public void setArg2LabelEnable(boolean b) {
    arg2Label.setEnabled(b);
  }

  @Override
  public void setSplitPreview(boolean b) {
    if (b) {
      split.setText("Split");
    } else {
      split.setText("Resume");
    }
  }

}
