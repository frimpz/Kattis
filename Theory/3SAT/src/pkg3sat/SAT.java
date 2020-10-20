//package pkg3sat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SAT {

    private static String[][] clauses;
    private static Integer[][] iClauses;
    private static Integer[][] iNeg;

    public static void main(String[] args) throws IOException {
        Boolean sat = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        clauses = new String[n][3];
        iClauses = new Integer[n][3];
        iNeg = new Integer[n][3];

        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int mVal = 0;

        for (int i = 0; i < n; i++) {
            clauses[i] = split(br.readLine());

            for (int j = 0; j < 3; j++) {
                int num;
                if (clauses[i][j].length() > 1) {
                    num = clauses[i][j].charAt(1) - 'a';
                    iNeg[i][j] = 1;
                } else {
                    num = clauses[i][j].charAt(0) - 'a';
                    iNeg[i][j]= 0;
                }

                if (!hm.containsKey(num)) {
                    hm.put(num, mVal);
                    mVal++;
                }

                iClauses[i][j] = hm.get(num);
            }
        }

        int number = 1;
        for (int i = 0; i <= mVal; i++) {
            number = number * 2;
        }

        for (int i = 0; i < number; i++) {
            sat = false;
            for (int j = 0; j < n; j++) {
                int val = computeValue(j, i);
                if (val == 0) {
                    break;
                }
                if (j == (n - 1) && val > 0) {
                    sat = true;
                }
            }
            if (sat == true)
                break;
        }

        if (sat)
            System.out.println("yes");
        else
            System.out.println("no");

    }

    private static int computeValue(int j, int byt) {
        int a, b, c;
        
        a = (byt >> iClauses[j][0]) & 1;
        if (iNeg[j][0] == 1) {
           a = 1 - a;
        }

        b = (byt >> iClauses[j][1]) & 1;
        if (iNeg[j][1] == 1) {
           b = 1 - b;
        }

        c = (byt >> iClauses[j][2]) & 1;
        if (iNeg[j][2] == 1) {
           c = 1 - c;
        }
        
        // System.out.println(a+" "+b+" "+ c);
        return a + b + c;
    }

    private static String[] split(String x) {
        return x.split(" ");
    }
}
