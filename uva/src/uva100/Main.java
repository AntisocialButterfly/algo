package uva100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  private static int len(long n) {
    int len = 1;
    while (n != 1) {      
      if (n % 2 == 0) {
        n /= 2;
      } else {
        n = 3 * n + 1;
      }
      len++;
    }

    return len;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String line;
    while ((line = reader.readLine()) != null) {
      String[] input = line.trim().split("\\s+");
      int a = Integer.parseInt(input[0]);
      int b = Integer.parseInt(input[1]);
      if (b < a) {
        int tmp = b;
        b = a;
        a = tmp;
      }

      int max = 0;
      for (int i = a; i <= b; i++) {
        max = Math.max(max, len(i));
      }

      System.out.printf("%s %s %d%n", input[0], input[1], max);
    }
  }
}
