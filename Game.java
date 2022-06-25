import java.awt.Color;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    // CONSTANTS
    private static final double FRAME_HEIGHT = 100.0; // height of game window
    private static final double FRAME_WIDTH = 32.0; // width of game window
    private static final int FPS = 24; // frames per second of animation
    private static final double SPEED = FRAME_HEIGHT / (1.5 * FPS); // speed of falling notes
    private static final double Y_BAR = -FRAME_HEIGHT + 3 * (FRAME_HEIGHT / FPS);
    // height of horizontal bar
    private static final double THRESHOLD = 0.05;
    // threshold is the computation time for displaying 1 frame
    private static final double MAX_SCORE = 1000000.0;  // maximum score for a game

    /* @citation: Color generated from: https://coolors.co/ */
    /* @end-citation */
    private static final Color GREY = Color.decode("#5C6684");

    // global variables
    private static ST<Character, Double[]> song; // inputted song to play
    private static double songLength = 0.0; // time of song
    private static int totalNotes = 0; // number of notes of song
    private static LinkedList<Note> currentNotes = new LinkedList<Note>();
    // current displayed notes
    private static double width = FRAME_WIDTH / 8; // width of a note
    private static double height = FRAME_HEIGHT / FPS; // height of a note

    private static double score = 0.0; // score counter
    private static String text = ""; // text updates

    // add new notes to linked list of displayed notes based on time
    public static void existingNotes(double currentTime) {
         /* @citation: Adapted code from Linked List API:
         https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html#remove(java.lang.Object) */
        for (char key : song.keys()) {
            for (int i = 0; i < song.get(key).length; i++) {
                if (Math.abs(currentTime - song.get(key)[i]) < THRESHOLD) {
                    currentNotes.add(new Note(key, width, height, SPEED));
                    song.get(key)[i] = Double.NEGATIVE_INFINITY;
                }
            }
        }

        // removes displayed notes from linked list at the bottom
        if (!currentNotes.isEmpty() && currentNotes.peek().getY() < -FRAME_HEIGHT) {
            currentNotes.removeFirst();
            text = "Miss!";
        }
        /* @end-citation */
    }

    // updates score and current notes based on user input on the keyboard
    public static void keyPressed() {
        char typedKey;
        double perfect;
        double good;
        double difference;

        while (StdDraw.hasNextKeyTyped()) {
            typedKey = StdDraw.nextKeyTyped();

            perfect = FRAME_HEIGHT / 20;
            good = FRAME_HEIGHT / 10;

            for (Note note : currentNotes) {
                if (note.getKey() == typedKey) {
                    currentNotes.removeFirstOccurrence(note);
                    StdAudio.playInBackground("click.wav");

                    difference = Math.abs(note.getY() - Y_BAR);
                    if (difference < perfect) {
                        double scoreChange = Math.round(MAX_SCORE / totalNotes);
                        score += scoreChange;
                        // show score on screen
                        text = "Perfect!";
                    }
                    else if (difference > perfect && difference < good) {
                        double scoreChange = Math.round(0.5 * MAX_SCORE / totalNotes);
                        score += scoreChange;
                        // show score on screen
                        text = "Good!";
                    }
                    else {
                        // show Miss! on screen
                        text = "Miss!";
                    }
                    break;
                }
            }
        }
    }

    // sets the background lines and labels in the game
    public static void setBackground() {
        StdDraw.clear(Color.decode("#F9DBBD"));

        // vertical lines
        for (int i = 0; i < 8; i++) {
            double x = i * width;
            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.line(x, 0.0, x, -FRAME_HEIGHT);
        }

        // labels to each line
        String letters = "asdfhjkl";

        for (int i = 0; i < 8; i++) {
            double x = i * width + width / 2;
            StdDraw.setPenColor(GREY);
            StdDraw.text(x, Y_BAR - 3.0, letters.charAt(i) + "");
        }

        // horizontal bar
        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(GREY);
        StdDraw.line(0.0, Y_BAR, FRAME_WIDTH, Y_BAR);

    }

    // displays one frame of all current notes and text
    public static void display() {
        // show updates
        StdDraw.setPenColor(GREY);
        StdDraw.text(FRAME_WIDTH / 2, Y_BAR - (FRAME_HEIGHT + Y_BAR) / 2, text);

        // show score
        StdDraw.text(FRAME_WIDTH - 4.0, -2 * height, score + "");

        // draw all notes
        for (Note note : currentNotes) {
            note.drawNote();
        }

        // animate
        StdDraw.show();
        StdDraw.pause((int) 1000.0 / FPS);

        // clear and set background
        setBackground();
    }

    // moves all current notes down by one
    public static void tick() {
        for (Note note : currentNotes) {
            note.moveNote();
        }
    }

    // main game loop
    public static void run(String filename) {

        // make a stopwatch
        /* @citation: Code adapted from Stopwatch class: https://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html
         */
        Stopwatch stopwatch = new Stopwatch();
        boolean gameover = false;
        double currentTime;
        /* @end-citation */

        // play song
        /* @citation: Code for delayed function call adapted from StackOverflow:
         https://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
         */
        new Timer().schedule(
                new TimerTask() {
                    public void run() {
                        StdAudio.playInBackground(filename);
                    }
                },
                1000
        );
        /* @end-citation */

        while (!gameover) {
            // get current time
            currentTime = stopwatch.elapsedTime();
            if (currentTime > songLength) {
                gameover = true;
            }

            existingNotes(currentTime);
            keyPressed();
            display();
            tick();
        }
    }

    // creates song and returns audio file name from StdIn
    public static String createSong() {
        String s = StdIn.readString();

        // creates ST for the song from StdIn
        song = new ST<Character, Double[]>();

        while (!StdIn.isEmpty()) {
            char letter = StdIn.readString().charAt(0);
            int length = StdIn.readInt();
            totalNotes += length;

            Double[] theseNotes = new Double[length];
            for (int j = 0; j < length; j++) {
                theseNotes[j] = StdIn.readDouble();
            }
            song.put(letter, theseNotes);
        }

        // updates max songLength and adds a brief pause at end
        for (char key : song) {
            for (double time : song.get(key)) {
                if (time > songLength) {
                    songLength = time;
                }
            }
        }
        songLength += 3.0;

        return s;
    }

    public static void main(String[] args) {

        // initialize standard drawing
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, FRAME_WIDTH);
        StdDraw.setYscale(-FRAME_HEIGHT, 0);

        // create the song from StdIn
        String audioFile = createSong();

        // runs the game
        run(audioFile);

        // stop song
        StdAudio.close();

        /* @citation: Code for System exit copied from XOXO walkthrough:
        https://www.loom.com/share/c2e4864382854edfbc16149fed453642
         */
        System.exit(0);
        /* @end-citation */
    }
}


