package uva10196;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {
  private static class InvalidBoardException extends Exception {
    private static final long serialVersionUID = 1L;
  }
  
  private static class CheckChecker {
    public static enum CheckResult {
      WHITE("white king"),
      BLACK("black king"),
      NONE("no king");
      
      private final String message;
      
      private CheckResult(String message) {
        this.message = message;
      }
      
      @Override
      public String toString() {
        return message;
      }
    }
    
    private final char[] board;
    
    private static Map<Character, int[][]> attackVectors = 
        new HashMap<Character, int[][]>();
        
    static {
      int[][] whitePawnAttackVectors = new int[][] {
        { getVectorTo(-1, -1) },
        { getVectorTo(-1, 1) }
      };
      
      int[][] blackPawnAttackVectors = new int[][] {
        { getVectorTo(1, 1) },
        { getVectorTo(1, -1) }
      };
      
      int[][] rookAttackVectors = new int[][] {
        repeat(getVectorTo(1, 0)),
        repeat(getVectorTo(-1, 0)),
        repeat(getVectorTo(0, 1)),
        repeat(getVectorTo(0, -1)),        
      };
      
      int[][] bishopAttackVectors = new int[][] {
        repeat(getVectorTo(1, 1)),
        repeat(getVectorTo(-1, 1)),
        repeat(getVectorTo(-1, -1)),
        repeat(getVectorTo(1, -1)),        
      };

      int[][] queenAttackVectors = new int[][] {
        repeat(getVectorTo(1, 0)),
        repeat(getVectorTo(1, 1)),
        repeat(getVectorTo(0, 1)),
        repeat(getVectorTo(-1, 1)),
        repeat(getVectorTo(-1, 0)),
        repeat(getVectorTo(-1, -1)),
        repeat(getVectorTo(0, -1)),        
        repeat(getVectorTo(1, -1)),        
      };
      
      int[][] knightAttackVectors = new int[][] {
        { getVectorTo(2, 1) },
        { getVectorTo(1, 2) },
        { getVectorTo(-1, 2) },
        { getVectorTo(-2, 1) },
        { getVectorTo(-2, -1) },
        { getVectorTo(-1, -2) },
        { getVectorTo(1, -2) },        
        { getVectorTo(2, -1) },        
      };      
      
      int[][] kingAttackVectors = new int[][] {
        { getVectorTo(1, 0) },
        { getVectorTo(1, 1) },
        { getVectorTo(0, 1) },
        { getVectorTo(-1, 1) },
        { getVectorTo(-1, 0) },
        { getVectorTo(-1, -1) },
        { getVectorTo(0, -1) },        
        { getVectorTo(1, -1) },        
      };
      
      attackVectors.put('P', whitePawnAttackVectors);
      attackVectors.put('p', blackPawnAttackVectors);
      attackVectors.put('R', rookAttackVectors);
      attackVectors.put('r', rookAttackVectors);
      attackVectors.put('B', bishopAttackVectors);
      attackVectors.put('b', bishopAttackVectors);
      attackVectors.put('Q', queenAttackVectors);
      attackVectors.put('q', queenAttackVectors);
      attackVectors.put('N', knightAttackVectors);
      attackVectors.put('n', knightAttackVectors);
      attackVectors.put('K', kingAttackVectors);
      attackVectors.put('k', kingAttackVectors);
    }

    private CheckChecker(char[] board) {
      this.board = board;
    }
    
    public static CheckChecker create(BufferedReader reader) 
        throws IOException, InvalidBoardException {
      char[] board = new char[120];
      boolean isBoardEmpty = true;
      
      for (int rank = 0; rank < 8; rank++) {
        String line = reader.readLine();
        for (int file = 0; file < 8; file++) {
          char square = line.charAt(file);
          board[getIndex(rank, file)] = square;
          
          if (square != '.') {
            isBoardEmpty = false;
          }
        }
      }
      
      if (isBoardEmpty) {
        throw new InvalidBoardException();
      }
      
      return new CheckChecker(board);
    }
    
    public CheckResult check() {
      for (int rank = 0; rank < 8; rank++) {
        for (int file = 0; file < 8; file++) {
          int index = getIndex(rank, file);
          char piece = board[index];
          if (piece == '.') {
            continue;
          }
          CheckResult result = checkAttacks(index, piece);
          if (result != CheckResult.NONE) {
            return result;
          }
        }
      }
      return CheckResult.NONE;
    }
    
    private CheckResult checkAttacks(
        int attackerPosition, char attackingPiece) {      
      for (int[] attackVector : attackVectors.get(attackingPiece)) {
        for (int vector : attackVector) {
          char target = board[attackerPosition + vector];
          
          if (target == 0) {
            break;
          }

          if (target == '.') {
            continue;
          }          
          
          if (isEnemyKing(target, attackingPiece)) {
            return target == 'K' ? CheckResult.WHITE : CheckResult.BLACK;
          }
          
          break;
        }
      }
      return CheckResult.NONE;
    }
    
    private static boolean isEnemyKing(char target, char attacker) {
      return Character.toUpperCase(target) == 'K' &&
          Character.isUpperCase(attacker) != Character.isUpperCase(target);
    }
    
    private static int getIndex(int rank, int file) {
      return (rank + 2) * 10 + file + 1;
    }
    
    private static int getVectorTo(int rank, int file) {
      return getIndex(rank, file) - getIndex(0, 0);
    }
    
    private static int[] repeat(int index) {
      int[] result = new int[7];
      
      for (int i = 1; i < 8; i++) {
        result[i - 1] = i * index;
      }
      
      return result;
    }    
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = 1;
    try {
      do {
        System.out.printf(
            "Game #%d: %s is in check.%n", 
            n, 
            CheckChecker.create(reader).check());
        reader.readLine();
        n++;
      } while (true);
    }
    catch (InvalidBoardException e) {}
  }
}
