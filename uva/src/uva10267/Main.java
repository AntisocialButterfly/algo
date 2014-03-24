package uva10267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @see <a href="http://uva.onlinejudge.org/external/102/10267.html">Graphical Editor</a>
 */
public class Main {
  private static class Editor {
    private static class Point {
      int x, y;

      public Point(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

    private char[][] image;

    public boolean processCommand(String command) {
      Scanner scanner = new Scanner(command);

      char cmd = scanner.next().charAt(0);
      int x1, y1, x2, y2;

      switch (cmd) {
      case 'I':
        init(scanner.nextInt(), scanner.nextInt());
        break;
      case 'C':
        rect(0, 0, image[0].length - 1, image.length - 1, 'O');
        break;
      case 'L':
        x1 = scanner.nextInt() - 1;
        y1 = scanner.nextInt() - 1;
        rect(x1, y1, x1, y1, scanner.next().charAt(0));
        break;
      case 'V':
        x1 = scanner.nextInt() - 1;
        y1 = scanner.nextInt() - 1;
        y2 = scanner.nextInt() - 1;
        rect(x1, y1, x1, y2, scanner.next().charAt(0));
        break;
      case 'H':
        x1 = scanner.nextInt() - 1;
        x2 = scanner.nextInt() - 1;
        y1 = scanner.nextInt() - 1;
        rect(x1, y1, x2, y1, scanner.next().charAt(0));
        break;
      case 'K':
        rect(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt() - 1,
            scanner.nextInt() - 1, scanner.next().charAt(0));
        break;
      case 'F':
        x1 = scanner.nextInt() - 1;
        y1 = scanner.nextInt() - 1;
        fill(x1, y1, image[y1][x1], scanner.next().charAt(0));
        break;
      case 'S':
        System.out.print(save(scanner.next()));
        break;
      case 'X':
        return false;
      }

      return true;
    }

    private void init(int cols, int rows) {
      image = new char[rows][cols];
      rect(0, 0, cols - 1, rows - 1, 'O');
    }

    private void rect(int x1, int y1, int x2, int y2, char c) {
      int tmp = x1;
      if (x1 > x2) {
        x1 = x2;
        x2 = tmp;
      }

      tmp = y1;
      if (y1 > y2) {
        y1 = y2;
        y2 = tmp;
      }

      for (int y = y1; y <= y2; y++) {
        for (int x = x1; x <= x2; x++) {
          image[y][x] = c;
        }
      }
    }

    private void fill(int x, int y, char p, char c) {
      if (p == c) {
        return;
      }

      Queue<Point> q = new LinkedList<Point>();
      q.add(new Point(x, y));

      while (!q.isEmpty()) {
        Point t = q.remove();
        if (isInBounds(t.x, t.y) && image[t.y][t.x] == p) {
          image[t.y][t.x] = c;

          q.add(new Point(t.x - 1, t.y));
          q.add(new Point(t.x + 1, t.y));
          q.add(new Point(t.x, t.y - 1));
          q.add(new Point(t.x, t.y + 1));
        }
      }
    }

    private boolean isInBounds(int x, int y) {
      return x >= 0 && y >= 0 && x < image[0].length && y < image.length;
    }

    private String save(String filename) {
      StringBuilder output = new StringBuilder();
      output.append(filename);
      output.append("\n");
      for (int y = 0; y < image.length; y++) {
        output.append(image[y]);
        output.append("\n");
      }

      return output.toString();
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String line;
    Editor editor = new Editor();
    while ((line = reader.readLine()) != null) {
      if (!editor.processCommand(line)) {
        break;
      }
    }
  }
}
