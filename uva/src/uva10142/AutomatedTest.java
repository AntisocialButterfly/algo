package uva10142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import org.junit.Test;

public class AutomatedTest {

  @Test
  public void test() throws IOException {
    Random r = new Random();

    for (int input = 0; input < 1_000_000; input++) {
      StringBuilder sb = new StringBuilder();
      int c = 1 + r.nextInt(20);
      sb.append(c);
      sb.append("\n");
      for (int i = 0; i < c; i++) {
        sb.append((char)('A' + i));
        sb.append("\n");
      }

      int ballots = 1 + r.nextInt(1000);
      for (int i = 0; i < ballots; i++) {
        int[] a = new int[c];
        for (int j = 0; j < c; j++) {
          a[j] = j + 1;
        }

        for (int j = 0; j < c; j++) {
          int k = j + r.nextInt(c - j);
          int tmp = a[k];
          a[k] = a[j];
          a[j] = tmp;
          sb.append(a[j]);
          sb.append(" ");
        }
        sb.append("\n");
      }
      sb.append("\n");

      BufferedReader reader = new BufferedReader(new StringReader(sb.toString()));
      for (String winner : Main.read(reader)) {
        System.out.println(winner);
      }
    }
  }
}
