Note: Our program works in Windows System by double click the .jar file, but it does not support double click to run the program in the macbook.
If user is using macbook, should use belows:  
java -jar Program.jar -file path-of-script-file
java -jar Program.jar -text
java -jar Program.jar

Description: 
This is an image processing application. Our user interface supports interactions via the text-based commands, script files and the graphical user interface(GUI). All assignment 6 reqiuresments are complete. In addition to meet the explicitly required assignment instructions, we've implemented additional functionalities. 
These include the abilities to:
1.Convert between different file formats (e.g., .ppm to .jpg, .ppm to .png, .jpg to .png, etc.) and running with script files, which can include other script-file running commands. 
2.Split and Resume
We support the split functions for blur, sharpen, sepia, luma-component, color-correct and levels-adjust as required
We also support the one click resume to see the current image. 
For example, after sepia the image, user may enter percentage for split, and click split button to see the splitted image. After that the user may click the resume button to switch back the current after-sepia image. 
3.Clearly instructions, messages feedback and errors notifications in our GUI
While user dragging the combo boxes to choose the functionalities, system will display corresponding instructions.
For example, when user choose the level-adjust function , system will display:
"Please enter integers 'b', 'm' and 'w', before you click 'Go' to run."
4. Unsave notification
If the image is unsaved, a red color unsaved label will display to the user. Once the image is saved, it will change to green colored Saved label.
5. Error handling 
We've also supported error handling for various exceptions, such as arguments out of bound and wrong file naming format.

Overview of our design:
We follow the GeneralCommandCallbacks and using the features interface as part of our GUI controller(we called it ControllerFeatures). In order to loosen the coupling between the view and the controller, generalized the ActionListener from the controller and from the IView interface. .

The project consists of five interfaces:
1.ImageModel: We follow the open-closed principle to keep this interface the same as possible. Only I/O related methods are removed according to last assignment feedback.
2.Controller:We follow the open-closed principle to keep this interface the same as possible. Only I/O related methods are added according to last assignment feedback.
3.IView: This is new interface and specific for the GUI view class.  It provides several functions for the Java Swing components.
4.ControllerFeatures: It provides various high-level view features the for the controllers to implements
5.ConverterInterface: This interface provides several useful format conversion methods, in order to reduce the code duplication. 

The project consists of six classes:
1.ImageUtil: This is the old version model class. We follow the open-closed principle to keep it. Only I/O related methods are removed according to last assignment feedback. Only I/O related methods are added according to last assignment feedback.
2.ImageContoller: This is the old version controller class
3.ImageView: This is the view class. It extends the JFrame and implements the IView interface.
4.ViewController: This is the latest controller class. It extends the original controller class.
5.Converter: It provides several useful format conversion methods, in order to reduce the code duplication. 
6.Main: It provides the program starting method. In the main method, it also support differential three ways to run the program, including the text-based commands, script files or the graphical user interface(GUI).


Changes and justifications: 
1. We moved the I/O related methods from the original Model to the original Controller, according to TA's feedback.
However, the Histogram transformation method is still in the Model class, because it is a function that previous assignment required. 
2.We added the converter interface and class to reduce code duplication issue.
3. Based on the previous feedback from TAs, we modified the lack of constructor for ImageController class and ImageUtil class, and make the instanciation using interface variables instead of concrete class.
4.We try to use interface class List to modify the ArrayList<ArrayList<ArrayList<Integer>>>, however we met some serious issues with regard to the mismatch in generics type, so it remains unchanged.
5.We modified the addImage and getImage into public methods since the initial design did not anticipate future interactions in view, model and controller. Subsequently, in order to enable the controller to directly invoke the model to add images or get image, we make them public methods instead of having them concealed within a private/protected method in the model.
6.Since the main method cannot be extends, and this assignment requires us to support for a GUI interaction, we modify the main method to accommodate GUI interaction.
7.The -text mode will prompt the user the program runs successfully and waiting for the commands.
8.Class diagram is updated.



Quick Start Guide:
To start the application, follow these steps:

1.a Execute the main() function.

1.b.Command line: key in "java -jar Program.jar"

1.c Double click the assignment6.jar file(only for Windows )

2.Load an image by clicking the Open button

3.Processing the image by choosing functions combo box and click the "Go" button

4.Save the image by clicking the "Save button" and choose the preferred format next to the "Save as..." label.

5.Exit the program by clicking the default close button.

Example image(This part is the same as Assignment 4):
The example images were included in the folder(res/). All the images were generated by the JPG_Example_Original.jpg image to demonstrate the assignment required operations results, using the command lines in the runFile.txt and runFile2.txt. 
For example, you can run the following commands in the terminal:
java -jar assignment6.jar -file sample_script.txt

Example script and execution way for .jar:(This part is the same as Assignment 5):
We have included two example scripts in "res" folder for .jar to run: (a)sample_script (b)script_all_functions.
The (a) script could generate the images that conform the assignment5 's requirements.
The (b) script contains all the features that our program could support.
For example, you can run the following commands in the terminal:
java -jar assignment6.jar -file sample_script.txt
java -jar assignment6.jar -file script_all_functions.txt


References(This part is thesame as Assignment 4-5):
We use "JPG_Example_Original.jpg" as an example for demonstrating operations.
Photograph by Yue Wen Peter Li(one of the team member student), taken on 2020.07.20 at Taipei, Taiwan.

We also use another source image for testing demonstrations. These images are derived from "PNG_image.png", which was freely provided for the developers to use or test. Source: Sample Videos. (URL: https://sample-videos.com/download-sample-png-image.php)