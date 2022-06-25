COS126 Final Project: Implementation

Please complete the following questions and upload this readme.txt to the
TigerFile assignment for the "Final Project Implementation".


/**********************************************************************
 * Basic Information                                                  *
 **********************************************************************/

Name 1: Helen Zhao

NetID 1: hz7945

Name 2: Anlon Zhu

NetID 2: az4244

Project preceptor name: Charles Smith

Project title: Rhythmix

CodePost link for proposal feedback: https://codepost.io/code/488272

Link to project video: https://youtu.be/6L05NV3IvcU

Approximate number of hours to complete the final project
Number of hours: 16

/**********************************************************************
 * Required Questions                                                 *
 **********************************************************************/

Describe your project in a few sentences.

We created a fun rhythm game similar to Guitar Hero that lets the user interact with a GUI. The user can press 8 different keyboard keys as they fall and display on screen, and gets points depending on how on-rhythm to the background music they press the keys. There are 3 different songs that the user can choose from.


Describe in detail your three features and where in your program
they are implemented (e.g. starting and ending line numbers,
function names, etc.).

1. Point Scoring System:
public static void keyPressed()
lines 103-141
We coded the rhythm so that the user should press the key when it falls down the screen and crosses a horizontal line at the bottom of the screen. For the point system, we record the difference in y-coordinates between when the user clicks the key and the horizontal line at the bottom of the screen. Based on this difference, we assigned either "Perfect!" or "Good!", where perfect means they clicked within 1/20 of the frame height, and good means the clicked within 1/10 of the frame height. If the user clicks more than 1/10 of a frame height away from the horizontal line, is it counted as a "Miss!" and the user is awarded zero points. For "Perfect!", we award a constant number of points based on on the max possible score of 1000000.0 divided by the total number of notes in the song. For "Good!", we also award a constant number of points that is half of that awarded for a "Perfect!" hit.
The score is then summed and updated after every key press.

2. Converting a song into notes:

lines 177-186 (done in the main method)
We took a song that we wanted and recorded the main rhythm beats for song. We then took all of these time stamps and divided them amongst the 8 possible key presses and put it into a text file. We take the text file from StdIn and converted it into a symbol table of 8 keys and their corresponding time stamps.  Since the set of all note and times are predetermined, they can be taken in from the set input file.


3. GUI
lines 47-101, 149-163
The notes are preassigned to the 8 possible keys, and fall down in 8 separate columns (labeled 'asdfhjkl') depending on their associated key press. The GUI displays notes at their given time. There is a horizontal line at the bottom of the screen. Once the notes (rectangles) cross the line, they are erased.
In order to draw the notes falling down the screen, we created a linked list of notes that should currently be displayed on the screen (currentNotes). We then draw all the notes at that instant, animate them, and update the linked list based on the notes that should now be on the screen.
The notes are defined in a separate class called Note. This class assigns each note with a key, x coordinate, y coordinate, width, height, and speed. In order to animate the keys, we constantly update the y-coordinate according to the set speed.
Each note also has a different color, and the background is set to a retro-esque cream color. We also added a text animation showing "Perfect!", "Good!", or "Miss" that displays according to how precise the user pressed the key. There is also background music that syncs with the rhythm, and a click that sounds every time the user presses a key.




Describe in detail how to compile and run your program. Include a few example
run commands and the expected results of running your program. For non-textual
outputs (e.g. graphical or auditory), feel free to describe in words what the
output should be or reference output files (e.g. images, audio files) of the
expected output.


In order to run our program, you need to use StdIn to load up a song that you want to play.
example: java-introcs Game < waiting_for_love.txt

In the StdIn txt file, there should be a character, followed by the number of notes associated with that character, followed by the time stamps of every note associated with that character. Repeat this for all 8 keys.
example:
a
5
0.24
0.48
0.91
1.12
1.34
1.58
b
3
0.89
1.04
1.59
...

For the output, there should be a StdDraw window that pops up with our game. The notes should start falling down from the top of the screen based on the rhythm of the song you are playing, which should also be playing in the background.


Describe how your program accepts user input and mention the line number(s) at
which your program accepts user input.

We use StdIn to let the user input which song they want to play. This happens in the main method in lines 177-187. Also, we record user input based on the key that they press. This is in the keyPressed method, in lines 109-110. We detect when a user presses a key using the StdDraw method .hasNextKeyTyped(), then determine which key they clicked using .nextKeyTyped().


Describe how your program produces output based on user input (mention line
numbers).

For the text file of the song received through StdIn, the program produces a GUI that has the notes falling at the times specified in the GUI. Each of the 8 columns will have their associated times that their notes should fall at, which should be in sync with the rhythm of the background music. (lines 47-101, 149-163)

For the key press, based on what note the user clicks, we are able to calculate their score, erase the note from the GUI, and play a click sound. (lines 112-137)



Describe the data structure your program uses and how it supports your program's
functionality (include the variable name and the line number(s) at which it is
declared and initialized).

Our program uses a symbol table of Character and Double[] to hold all of the keys and their associated time stamps. (Declared at 16, initialized at 173 and 184) This is very useful because we can reference the time stamps associated with specific keys. The ST also does not have a size restriction, so we can upload any song.

We also created a linked list called currentNotes (declared at 20, initialized at 32-36) to hold all of the Notes that are supposed to be displayed on the game screen. We used a linked list because there is no fixed size, and also it is easy to remove items from the middle of the list, as opposed to a stack or queue. We need this functionality because we need to be able to remove a note from the middle of the screen if the user clicks it too early.




List the two custom functions written by your project group, including function
signatures and line numbers; if your project group wrote more than two custom
functions, choose the two functions that were most extensively tested.

1. public static void run() (lines 144-165)


2. public static void keyPressed() (lines 103-139)


List the line numbers where you test each of your two custom functions twice.
For each of the four tests (two for each function), explain what was being
tested and the expected result. For non-textual results (e.g. graphical or
auditory), you may describe in your own words what the expected result
should be or reference output files (e.g. images, audio files).

1. public static void run() (lines 144-165)
Test 1: We test this function in the main method (line 200) for the song Waiting for Love by Acivi. For this song, the function plays the correct audio file for Waiting for Love, starts the stopwatch, and calls our other functions like keyPressed, display, tick, and existingNotes. The expected result of this function is just a working game interface.


2. public static void run() (lines 144-165)
Test 1: We test this function in the main method (line 200) for the song Twinkle Twinkle Little Star. For this song, the function plays the correct audio file for Twinkle Twinkle Little Star, starts the stopwatch, and calls our other functions like keyPressed, display, tick, and existingNotes. The excepted result of this function is just a working game interface.


3. public static void keyPressed() (lines 103-139)
Test 1: We test this function in the run() method (line 159). A successful result of this function for Waiting for Love should mean that the keys disappear from the GUI when they are pressed on the keyboard (which means it was remove from the currentNotes linked list). There should also be a score change if you click within a certain threshold of the current time stamp.

4. public static void keyPressed() (lines 103-139)
Test 2: We test this function in the run() method (line 159). A successful result of this function for Twinkle Twinkle Little Star should mean that the keys disappear from the GUI when they are pressed on the keyboard (which means it was remove from the currentNotes linked list).There should also be a score change if you click within a certain threshold of the current time stamp.

*Note: our main method does not test these functions individually because they are used within each other


/**********************************************************************
 * Citing Resources                                                   *
 **********************************************************************/

List below *EVERY* resource your project group looked at (bullet lists and
links suffice).

- Blue: https://www.youtube.com/watch?v=-ncIVUXZla8
- Waiting for love: https://www.youtube.com/watch?v=QjrQ1rmS44M&t=42s
- Twinkle twinkle: https://www.youtube.com/watch?v=zA52uNzx7Y4
- Click sound: https://www.youtube.com/watch?v=h8y0JMVwdmM
- Timing: https://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html
- Delayed Function Call: https://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
- Linked List: https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html#remove(java.lang.Object)
- Colors: https://coolors.co/


Remember that you should *ALSO* be citing every resource that informed your
code at/near the line(s) of code that it informed.

Did you receive help from classmates, past COS 126 students, or anyone else?
If so, please list their names.  ("A Sunday lab TA" or "Office hours on
Thursday" is ok if you don't know their name.)
Yes or no?
No


Did you encounter any serious problems? If so, please describe.
Yes or no?
No



List any other comments here.




/**********************************************************************
 * Submission Checklist                                               *
 **********************************************************************/

Please mark that you’ve done all of the following steps:
[X] Created a project.zip file, unzipped its contents, and checked that our
    compile and run commands work on the unzipped contents. Ensure that the .zip
    file is under 50MB in size.
[X] Created and uploaded a Loom or YouTube video, set its thumbnail/starting
    frame to be an image of your program or a title slide, and checked that
    the video is viewable in an incognito browser.
[X] Uploaded all .java files to TigerFile.
[X] Uploaded project.zip file to TigerFile.

After you’ve submitted the above on TigerFile, remember to do the following:
[X] Complete and upload readme.txt to TigerFile.
[X] Complete and submit Google Form
    (https://forms.cs50.io/de2ccd26-d643-4b8a-8eaa-417487ba29ab).


/**********************************************************************
 * Partial Credit: Bug Report(s)                                      *
 **********************************************************************/

For partial credit for buggy features, you may include a bug report for at
most 4 bugs that your project group was not able to fix before the submission
deadline. For each bug report, copy and paste the following questions and
answer them in full. Your bug report should be detailed enough for the grader
to reproduce the bug. Note: if your code appears bug-free, you should not
submit any bug reports.

BUG REPORT #1:
1. Describe in a sentence or two the bug.




2. Describe in detail how to reproduce the bug (e.g. run commands, user input,
   etc.).




3. Describe the resulting effect of bug and provide evidence (e.g.
   copy-and-paste the buggy output, reference screenshot files and/or buggy
   output files, include a Loom video of reproducing and showing the effects of
   the bug, etc.).




4. Describe where in your program code you believe the bug occurs (e.g. line
   numbers).




5. Please describe what steps you tried to fix the bug.





/**********************************************************************
 * Extra Credit: Runtime Analysis                                     *
 **********************************************************************/

You may earn a small amount of extra credit by analyzing the efficiency of one
substantial component of your project. Please answer the following questions if
you would like to be considered for the extra credit for program analysis
(remember to copy and paste your answers to the following questions into the
Google form as well for credit).

Specify the scope of the component you are analyzing (e.g. function name,
starting and ending lines of specific .java file).




What is the estimated runtime (e.g. big-O complexity) of this component?
Provide justification for this runtime (i.e. explain in your own words why
you expect this component to have this runtime performance).




Provide experimental evidence in the form of timed analysis supporting this
runtime estimate. (Hint: you may find it helpful to use command-line
arguments/flags to run just the specified component being analyzed).





/**********************************************************************
 * Extra Credit: Packaging project as an executable .jar file         *
 **********************************************************************/

You may earn a small amount of extra credit by packaging your project as an
executable .jar file. Please answer the following question if you would like to
be considered for this extra credit opportunity (remember to copy and paste your
answers to the following questions into the Google form as well for credit).

Describe in detail how to execute your .jar application (e.g. what execution
command to use on the terminal). Include a few example execution commands and
the expected results of running your program. For non-textual outputs
(e.g. graphical or auditory), feel free to describe in words what the output
should be or reference output files (e.g. images, audio files) of the expected
output.



