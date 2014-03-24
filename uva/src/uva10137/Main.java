package uva10137;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="http://uva.onlinejudge.org/external/101/10137.html">The Trip</a>
 */
public class Main {
  static double[] readExpenses(BufferedReader reader, int n) throws IOException {
    double[] e = new double[n];

    for (int i = 0; i < n; i++) {
      e[i] = Double.parseDouble(reader.readLine().trim());
    }

    return e;
  }

  static double sum(double[] a) {
    double s = 0;
    for (double b : a) {
      s += b;
    }

    return s;
  }

  static double calculate(double[] e, int N) {
    double sum = sum(e);
    double avg = sum / N;
    double toDecrease = 0;
    double toIncrease = 0;

    for (double a : e) {
      double diff = Math.round(100.0 * (avg - a)) / 100.0;
      if (diff > 0) {
        toIncrease += diff;
      } else {
        toDecrease += -diff;
      }
    }

    return Math.min(toIncrease, toDecrease);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String line;
    while ((line = reader.readLine()) != null) {
      int N = Integer.parseInt(line.trim());
      if (N > 0) {
        System.out.printf("$%.2f%n", calculate(readExpenses(reader, N), N));
      }
    }
  }
}
