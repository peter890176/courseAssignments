package controller.commands;

import java.util.Objects;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling Dithering operation.
 */
public class DitherCommand {

  /**
   * Method to call the model and view for Dither operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 3) {
        String imageToDither = arguments[1];
        String newImageName = arguments[2];
        imageModel.ditherImage(imageToDither, newImageName);
      } else if (arguments.length == 5) {
        if (!Objects.equals(arguments[3], "split")) {
          throw new Exception();
        }
        String imageToDither = arguments[1];
        String newImageName = arguments[2];
        int percent = Integer.parseInt(arguments[4]);
        if (percent < 0 || percent > 100) {
          throw new Exception();
        }
        String tempName = "tempName";
        imageModel.split(imageToDither, percent, tempName);
        imageModel.ditherImage(tempName, tempName);
        imageModel.combine(tempName, imageToDither, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
