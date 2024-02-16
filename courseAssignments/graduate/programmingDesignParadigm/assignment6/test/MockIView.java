import java.awt.Component;

import javax.swing.ImageIcon;

/**
 * The interface for the view class. It provides several functions for the Java Swing components.
 */
public interface MockIView {

  /**
   * Set the features into the view class.
   *
   * @param features the functionalities also called features of the view controller.
   */
  void addFeatures(MockControllerFeatures features);

  /**
   * Set the current file path.
   *
   * @param s the file path.
   */
  void setCurrentPath(String s);

  /**
   * Set the general messages or error messages.
   *
   * @param s the messages.
   */
  void setMessage(String s);

  /**
   * To display the image on the component.
   *
   * @param icon the image icon.
   */
  void displayImage(ImageIcon icon);

  /**
   * To display the histogram image on the component.
   *
   * @param histogram the histogram image icon.
   */
  void displayHistogram(ImageIcon histogram);

  /**
   * To set current saved or unsaved status.
   *
   * @param s saved/unsaved status.
   */
  void setUnsaved(String s);

  /**
   * To get the designated file format for saving.
   *
   * @return the file format.
   */
  String getSaveFormat();

  /**
   * To enable the save button.
   */
  void setSaveButtonEnable();

  /**
   * To enable the Function button.
   */
  void setFunctionEnabled();

  /**
   * To enable or unable the arg0.
   *
   * @param b status of the arg0.
   */
  void setArg0(boolean b);

  /**
   * To set the arg0 text.
   *
   * @param s text contents.
   */
  void setArg0Text(String s);

  /**
   * To enable or unable the arg1.
   *
   * @param b status of the arg1.
   */
  void setArg1(boolean b);

  /**
   * To set the arg1 text.
   *
   * @param s text contents of arg1.
   */
  void setArg1Text(String s);

  /**
   * To enable or unable the arg2.
   *
   * @param b status of the arg2.
   */
  void setArg2(boolean b);

  /**
   * To set the arg2 text.
   *
   * @param s text contents of arg2.
   */
  void setArg2Text(String s);

  /**
   * To enable or unable the argSplit.
   *
   * @param b status of the argSplit.
   */
  void setArgSplit(boolean b);

  /**
   * To enable or unable the split button.
   *
   * @param b status of the split button.
   */
  void setSplit(boolean b);

  /**
   * To enable the Go button.
   */
  void setGoButtonEnable();

  /**
   * To enable the format choosing combo boxes.
   */
  void setImageFormatComboboxEnable();


  /**
   * To return the component type of the saving.
   *
   * @return component.
   */
  Component getSaveComponent();

  /**
   * To return the selected function for image processing.
   *
   * @return function name.
   */
  String getSelectedFunction();


  /**
   * To set the instruction contents to tell the user instructions.
   *
   * @param s instruction content.
   */
  void setInstructions(String s);

  /**
   * To set the arg0 Label.
   *
   * @param s label name.
   */
  void setArg0Label(String s);

  /**
   * To set the arg1 Label.
   *
   * @param s label name.
   */
  void setArg1Label(String s);

  /**
   * To set the arg2 Label.
   *
   * @param s label name.
   */
  void setArg2Label(String s);

  /**
   * To get the arg0 text contents.
   *
   * @return arg0 contents.
   */
  String getArg0();

  /**
   * To get the arg1 text contents.
   *
   * @return arg1 contents.
   */
  String getArg1();

  /**
   * To get the arg2 text contents.
   *
   * @return arg2 contents.
   */
  String getArg2();

  /**
   * To get the argSplit text contents.
   *
   * @return argSplit contents.
   */
  String getArgSplit();

  /**
   * To set Split or Resume.
   *
   * @param b status of Split/Resume.
   */
  void setSplitPreview(boolean b);

  /**
   * To enable or unable the arg0.
   *
   * @param b status of arg0.
   */
  void setArg0LabelEnable(boolean b);

  /**
   * To enable or unable the arg1.
   *
   * @param b status of arg1.
   */
  void setArg1LabelEnable(boolean b);

  /**
   * To enable or unable the arg2.
   *
   * @param b status of arg2.
   */
  void setArg2LabelEnable(boolean b);

}
