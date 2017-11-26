

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static Double prevJ;
    private static final File rawSource = new File("kmdata1.txt");

    public static void main(String[] args) {

        BufferedReader br = null;
        ArrayList<Point> points = new ArrayList<>();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(rawSource));

            while ((sCurrentLine = br.readLine()) != null) {
                points.add(new Point(sCurrentLine));
            }
            Point[] centroids = new Point[]{new Point(3, 3), new Point(6, 2), new Point(8, 5)};
            for (int i = 0; i < 10; i++) {
                centroids = compute(points, centroids, i);
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

    private static Point[] compute(ArrayList<Point> points, Point[] centroids, int i) throws IOException {
        Point[] newCentroids = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        BufferedWriter ca = new BufferedWriter(new FileWriter(new File("iter" + i + "_ca.txt")));
        BufferedWriter cm = new BufferedWriter(new FileWriter(new File("iter" + i + "_cm.txt")));
        ArrayList<Double>[] cent = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
        for (Point p : points) {
            float x;
            float y;
            double ans1, ans2, ans3;

            x = p.getX();
            y = p.getY();

            System.out.println("x = " + x);
            System.out.println("x - centroids[0].getX()" + (centroids[0].getX()) + "at iteration " + i);

            ans1 = Math.sqrt(((x - centroids[0].getX()) * (x - centroids[0].getX())) + ((y - centroids[0].getY()) * (y - centroids[0].getY())));
            System.out.println(ans1);
            ans2 = Math.sqrt(((x - centroids[1].getX()) * (x - centroids[1].getX())) + ((y - centroids[1].getY()) * (y - centroids[1].getY())));
            System.out.println(ans2);
            ans3 = Math.sqrt(((x - centroids[2].getX()) * (x - centroids[2].getX())) + ((y - centroids[2].getY()) * (y - centroids[2].getY())));
            System.out.println(ans3);
            if (ans1 < ans2 && ans1 < ans3) {
                ca.append("1");
                newCentroids[0].setX(newCentroids[0].getX() + x);
                newCentroids[0].setY(newCentroids[0].getY() + y);
                cent[0].add(ans1);
                p.setCentroid(1);
            } else if (ans2 < ans1 && ans2 < ans3) {
                ca.append("2");
                newCentroids[1].setX(newCentroids[1].getX() + x);
                newCentroids[1].setY(newCentroids[1].getY() + y);
                cent[1].add(ans2);
                p.setCentroid(2);
            } else {
                ca.append("3");
                newCentroids[2].setX(newCentroids[2].getX() + x);
                newCentroids[2].setY(newCentroids[2].getY() + y);
                cent[2].add(ans3);
                p.setCentroid(3);
            }
            ca.newLine();
        }
        for (int j = 0; j < newCentroids.length; j++) {
            Point newP = newCentroids[j];
            newP.setX(newP.getX() / cent[j].size());
            newP.setY(newP.getY() / cent[j].size());
            cm.append(String.valueOf(newP.getX())).append(" ").append(String.valueOf(newP.getY()));
            cm.newLine();
        }
        double J = 0;
        for (int j = 0; j < 3; j++) {
            for (Double d : cent[j]) {
                J += d;
            }
        }
        J = J / 300;
        cm.append("J=").append(String.valueOf(J));
        cm.newLine();
        cm.append("dJ=");
        if (prevJ == null) {
            cm.append("-");
        } else {
            cm.append(String.valueOf(prevJ - J)).append("");
        }
        prevJ = J;

        ca.close();
        cm.close();
        return newCentroids;
    }

    public static class Point{
        float x, y;
        int centroid;

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
        }

        float getX() {
            return x;
        }

        float getY() {
            return y;
        }

        public int getCentroid() {
            return centroid;
        }

        void setX(float x) {
            this.x = x;
        }

        void setY(float y) {
            this.y = y;
        }

        void setCentroid(int centroid) {
            this.centroid = centroid;
        }
    }
}

