package uva10189;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
  static boolean hasMine(char[][] field, int row, int col) {
    int height = field.length;
    int width = field[0].length;
    if (row < 0 || row >= height || col < 0 || col >= width) {
      return false;
    }
    return field[row][col] == '*';
  }

  static byte[][] loadField(BufferedReader reader, int n, int m) throws IOException {
    char[][] field = new char[n][m];
    for (int i = 0; i < n; i++) {
      String line = reader.readLine().trim();
      for (int j = 0; j < m; j++) {
        field[i][j] = line.charAt(j);
      }
    }

    byte[][] result = new byte[n][m];
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < m; col++) {
        if (hasMine(field, row, col)) {
          result[row][col] = -1;
          continue;
        }

        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            if (i == j && i == 0) {
              continue;
            }

            if (hasMine(field, row + i, col + j)) {
              result[row][col]++;
            }
          }
        }
      }
    }

    return result;
  }

  static void printField(byte[][] field) {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[0].length; j++) {
        if (field[i][j] == -1) {
          System.out.print('*');
        } else {
          System.out.print(field[i][j]);
        }
      }

      System.out.println();
    }
  }

  public static void main(String[] args) throws IOException {
    String line;
    int n = 1;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while ((line = reader.readLine()) != null) {
      String[] dimensions = line.trim().split("\\s+");
      int height = Integer.parseInt(dimensions[0]);
      int width = Integer.parseInt(dimensions[1]);

      if (height > 0) {
        if (n > 1) {
          System.out.println();
        }
        System.out.println("Field #" + (n++) + ":");
        printField(loadField(reader, height, width));
      }
    }
  }
}
