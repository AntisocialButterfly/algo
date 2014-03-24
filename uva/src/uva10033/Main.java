package uva10033;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @see <a href="http://uva.onlinejudge.org/external/100/10033.html">Interpreter</a>
 */
public class Main {
  private static class Computer {
    private static final int HALT = 1;
    int[] r = new int[10];
    int[] m = new int[1000];
    int ip = 0;

    public Computer(Iterable<Integer> instructions) {
      int address = 0;
      for (int instruction : instructions) {
        m[address++] = instruction;
      }
    }

    public int run() {
      int instructionCounter = 0;
      int op, arg1, arg2;

      do {
        op = m[ip] / 100;
        arg1 = (m[ip] % 100) / 10;
        arg2 = (m[ip] % 10);

        switch (op) {
        case 2:
          set(arg1, arg2);
          break;
        case 3:
          add(arg1, arg2);
          break;
        case 4:
          mult(arg1, arg2);
          break;
        case 5:
          setReg(arg1, arg2);
          break;
        case 6:
          addReg(arg1, arg2);
          break;
        case 7:
          multReg(arg1, arg2);
          break;
        case 8:
          readMem(arg1, arg2);
          break;
        case 9:
          setMem(arg1, arg2);
          break;
        case 0:
          jnz(arg1, arg2);
          break;
        }

        ip++;
        instructionCounter++;
      } while (op != HALT);
      return instructionCounter;
    }

    private void set(int d, int n) {
      r[d] = n;
    }

    private void add(int d, int n) {
      r[d] += n;
      r[d] %= 1000;
    }

    private void mult(int d, int n) {
      r[d] *= n;
      r[d] %= 1000;
    }

    private void setReg(int d, int s) {
      r[d] = r[s];
    }

    private void addReg(int d, int s) {
      r[d] += r[s];
      r[d] %= 1000;
    }

    private void multReg(int d, int s) {
      r[d] *= r[s];
      r[d] %= 1000;
    }

    private void readMem(int d, int a) {
      r[d] = m[r[a]];
    }

    private void setMem(int s, int a) {
      m[r[a]] = r[s];
    }

    private void jnz(int d, int s) {
      if (r[s] != 0) {
        ip = r[d] - 1;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int numCases = Integer.parseInt(reader.readLine().trim());
    reader.readLine();

    String line;
    while (numCases > 0) {
      List<Integer> instructions = new LinkedList<Integer>();
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }
        instructions.add(Integer.parseInt(line));
      }
      numCases--;
      System.out.println(new Computer(instructions).run());
      if (numCases > 0) {
        System.out.println();
      }
    }
  }
}
