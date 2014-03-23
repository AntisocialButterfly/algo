package uva706;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static class LcdDisplay {    
    private static final String[] pattern = { 
      " -     -  -     -  -  -  -  - ",
      "| |  |  |  || ||  |    || || |",
      "       -  -  -  -  -     -  - ",
      "| |  ||    |  |  || |  || |  |",
      " -     -  -     -  -     -  - ",    
    };

    private final int S;
    
    public LcdDisplay(int s) {
      S = s;
    }
    
    private int getRow(int row) {
      if (row == 0) { 
        return 0;
      } else if (row <= S) {
        return 1;
      } else if (row == S + 1) {
        return 2;
      } else if (row <= 2 * S + 1) {
        return 3;
      } else {
        return 4;
      }
    }
    
    private int getCol(int col) {
      if (col == 0) {
        return 0;
      } else if (col < S + 1) {
        return 1;
      } else { 
        return 2;
      }
    }
    
    private char getCharFor(int d, int row, int col) {
      return pattern[getRow(row)].charAt(d * 3 + getCol(col));
    }
    
    public String print(String n) {
      StringBuilder sb = new StringBuilder();
      
      for (int row = 0; row < 2 * S + 3; row++) {
        for (int c = 0; c < n.length(); c++) {
          for (int col = 0; col < S + 2; col++) {
            sb.append(getCharFor(n.charAt(c) - '0', row, col));
          }
          if (c < n.length() - 1) {
            sb.append(" ");
          }
        }
        sb.append("\n");
      }
      
      return sb.toString();
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String line;
    while ((line = reader.readLine()) != null) {
      String[] params = line.trim().split("\\s+");
      int s = Integer.parseInt(params[0]);
      int n = Integer.parseInt(params[1]);
      if (s == 0) {
        break;
      }
      System.out.print(new LcdDisplay(s).print(String.valueOf(n)));
      System.out.println();
    }
  }
}
