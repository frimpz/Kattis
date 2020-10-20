package turingcuberec;

import java.util.*;
import java.io.*;

public class TuringMachineTape {
    public static final int BLANK_SYMBOL = -1;
    public static final int BEGIN_SYMBOL = 1;
    public static final int UTILITY_SYMBOL = -9999;

    // construct a tape with input read from the first line of the given
    // stream (or no input at all)
    public TuringMachineTape() throws IOException { this(null); }

    public TuringMachineTape(Reader input) throws IOException {
        if (input != null) {
            String line = (new BufferedReader(input)).readLine();
            for (char c : line.toCharArray()) { tape.add((int)c); }
        }
        if (tape.size() == 0) {
            tape.add(BLANK_SYMBOL);
        }
    }

    // return the symbol at the head
    public int read() { return tape.get(position); }

    // write given symbol at the head
    public void write(int symbol) { if (symbol == BLANK_SYMBOL) reject(); tape.set(position, symbol); }

    // move right / left
    public void right() { ++position; if (tape.size() <= position) tape.add(BLANK_SYMBOL); }
    public void left() { if (position <= 0) reject(); --position; }

    // dump the contents of the tape
    public void debug() throws IOException { debug(new OutputStreamWriter(System.out)); }

    public void debug(Writer out) throws IOException {
        ArrayList<Integer> widths = new ArrayList<Integer>();
        for (int i : tape) {
            String term = Character.isISOControl(i) ? "<" + i + ">" : ("" + (char)i);
            widths.add(term.length());
            out.write("| " + term + " ");
        }
        out.write("|\n");

        for (int i = 0; i < tape.size(); ++i) {
            char c = (i == position) ? '^' : ' ';
            out.write(String.format("| %" + widths.get(i) + "s ", c));
        }
        out.write("|\n");
        out.flush();
    }

    // accept / reject special methods
    public static void accept() { System.out.println("accept"); System.exit(0); }
    public static void reject() { System.out.println("reject"); System.exit(0); }

    private ArrayList<Integer> tape = new ArrayList<Integer>();
    private int position = 0;
};


// Utility functions -- Use these and examine them to see how they work.
class TuringMachineUtility {
    // seek to the left until we find the given symbol
    public static void findLeft(TuringMachineTape t, int symbol) {
        while (t.read() != symbol)
            t.left();
    }

    // seek to the right until we find the given symbol (or blank, causing
    // us to reject)
    public static void findRight(TuringMachineTape t, int symbol) {
        while (t.read() != symbol && t.read() != TuringMachineTape.BLANK_SYMBOL)
            t.right();
        if (t.read() != symbol)
            t.reject();
    }

    // go left, back to BEGIN_SYMBOL, then advance one
    public static void rewind(TuringMachineTape t) {
        findLeft(t, TuringMachineTape.BEGIN_SYMBOL);
        t.right();
    }

    // open a space at the current head position by shifting everything to
    // the right by one, then write the given symbol at that position.
    public static void shiftAndInsert(TuringMachineTape t, int symbol) {
        int saved = t.read();
        t.write(TuringMachineTape.UTILITY_SYMBOL);
        while (saved != TuringMachineTape.BLANK_SYMBOL) {
            t.right();
            int savedNext = t.read();
            t.write(saved);
            saved = savedNext;
        }

        findLeft(t, TuringMachineTape.UTILITY_SYMBOL);
        t.write(symbol);
        t.right();
    }

    // insert the special BEGIN_SYMBOL at the current position of the head
    public static void insertBegin(TuringMachineTape t) {
        shiftAndInsert(t, TuringMachineTape.BEGIN_SYMBOL);
    }
}
