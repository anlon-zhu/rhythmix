import java.awt.Color;

public class Note {
    private char key;
    private double x;
    private double y;
    private double width;
    private double height;
    private double speed;

    public Note(char key, double width, double height, double speed) {
        this.key = key;

        switch (key) {
            case 'a':
                x = 0.0 + width / 2;
                break;
            case 's':
                x = width + width / 2;
                break;
            case 'd':
                x = 2 * width + width / 2;
                break;
            case 'f':
                x = 3 * width + width / 2;
                break;
            case 'h':
                x = 4 * width + width / 2;
                break;
            case 'j':
                x = 5 * width + width / 2;
                break;
            case 'k':
                x = 6 * width + width / 2;
                break;
            case 'l':
                x = 7 * width + width / 2;
                break;
        }
        this.y = 0.0;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }


    public void drawNote() {
        /* @citation: Colors generated from: https://coolors.co/
        color palette:
        #FE5D62
        #FFCB77
        #52B77E
        #17C3B2
        #227C9D
        #A7ACD9
        #B68FCC
        #483C46 */

        switch (key) {
            case 'a':
                StdDraw.setPenColor(Color.decode("#FE5D62"));
                break;
            case 's':
                StdDraw.setPenColor(Color.decode("#FFCB77"));
                break;
            case 'd':
                StdDraw.setPenColor(Color.decode("#52B77E"));
                break;
            case 'f':
                StdDraw.setPenColor(Color.decode("#17C3B2"));
                break;
            case 'h':
                StdDraw.setPenColor(Color.decode("#227C9D"));
                break;
            case 'j':
                StdDraw.setPenColor(Color.decode("#A7ACD9"));
                break;
            case 'k':
                StdDraw.setPenColor(Color.decode("#B68FCC"));
                break;
            case 'l':
                StdDraw.setPenColor(Color.decode("#483C46"));
                break;
        }
        StdDraw.filledRectangle(x, y, width / 2, height / 2);
        /* @end-citation */
    }


    public void moveNote() {
        y -= speed;
    }

    public double getY() {
        return y;
    }

    public char getKey() {
        return key;
    }

    public static void main(String[] args) {
        Note note = new Note('a', 2.0, 2.0, 4.0);
        StdOut.println(note.getKey());
        StdOut.println(note.getY());
        note.moveNote();
        StdOut.println(note.getY());
        note.drawNote();

    }
}
