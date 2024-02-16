Dither an image implementation: Y
Script command to dither an image: Y
Dither an image from GUI: Y
(Note: When we were testing the functionalities, we figured out that the save and load operations for .ppm files in this program were not running correctly. 
We tried to use our own prepared PPM images as well as ASCII PPM images from multiple external websites, but none of them worked. It was necessary to use the customized .ppm format provided by the team for this program to ensure proper functionality of .ppm image I/O.)

Regarding the addition of image dithering, these were the steps taken:
1. We added a new class, "DitherCommand", which is stored in the commands folder alongside other commands. Following the same pattern as other commands, it serves as a command, awaiting calling from the controller.
2. In Controller's runCommands method, within the switch-case, we added a new case, "dither,". This change enables the Controller to call the dither command and subsequently invoke the corresponding operation in the Model.
3. In the InteractiveController, within the switch-case, we added a new case, "dither," This change enables the GUI Controller's ability to call the dither command and invoke the corresponding operation in the Model.
4. In the operations folder, a new operation DitherOperation, was added. It is alongside with other operations, serving as a function awaiting for being called by the Model.
5. Due to the requirement of following the original design, we added the ditherImage method in the ModelInterface, which is alongside with other functionalities (which is how the other team has been adding their functionalities in all the previous assignments).
6. The implementation of the ditherImage method was also added in the Model.
7. The "Dither" option was added to the comboBox in the View.
8. We also revised the MockView class and MockModel class and test classes, because there were a lot of tests dependent on the hard coded test_image path. However, we are not allowed to use the other team's image for this assignment.
9. Tests for checking the dithering functionality were also implemented (before we wrote the dithering function).

References(This part is the same as Assignment 4-6):
We use "JPG_Example_Original.jpg" as an example for demonstrating operations.
Photograph by Yue Wen Peter Li(one of the team member student), taken on 2020.07.20 at Taipei, Taiwan.
