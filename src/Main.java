

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final Main.Point mew1 = new Main.Point(3,3);
    public static final Main.Point mew2 = new Main.Point(6,2);
    public static final Main.Point mew3 = new Main.Point(8,5);

    public static void main(String[] args) {

        BufferedReader br = null;
        ArrayList<Point> points = new ArrayList<>();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\Users\\Danielle98\\workspace\\CMSC170Kmeans\\src\\kmdata.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                points.add(new Point(sCurrentLine));
            }
            int ans;
            for(Point p: points) {
                ans = compute(p);
                System.out.println(ans);
            }

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

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

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

    public static int compute(Main.Point xy) {
        float x;
        float y;
        double ans1 = 0;
        double ans2 = 0;
        double ans3 = 0;
        int rets = 0;

        x = xy.getX();
        y = xy.getY();

        ans1 = Math.sqrt(((x-mew1.getX())*(x-mew1.getX()))+((y-mew1.getY())*(y-mew1.getY())));
        ans2 = Math.sqrt(((x-mew2.getX())*(x-mew2.getX()))+((y-mew2.getY())*(y-mew2.getY())));
        ans3 = Math.sqrt(((x-mew3.getX())*(x-mew3.getX()))+((y-mew3.getY())*(y-mew3.getY())));

        if(ans1 < ans2 && ans1 < ans3){
            rets = 1;
        }

        if(ans2 < ans1 && ans2 < ans3){
            rets = 2;
        }

        if(ans3 < ans1 && ans3 < ans2){
            rets = 3;
        }

        return rets;
    }

}

