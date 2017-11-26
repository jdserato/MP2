

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("kmdata1.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                Point one = new Point(sCurrentLine);
            }

            System.out.println("goodbye");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    boolean kmeans(float x, float y){

        return true;
    }

    public static class Point{
        float x, y;
        Point(String raw) {
            String stringToParse = "";
            int i = 1;
            for (; raw.charAt(i) != ' '; i++) {
                stringToParse = stringToParse.concat(raw.charAt(i) + "");
            }
            x = Float.valueOf(stringToParse);
            stringToParse = "";
            for (; i < 32; i++) {
                stringToParse = stringToParse.concat(raw.charAt(i) + "");
            }
            y = Float.valueOf(stringToParse);
            System.out.println(x);
            System.out.println(y);
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }
}
