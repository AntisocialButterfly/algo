package uva10142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @see <a href="http://uva.onlinejudge.org/external/101/10142.html">Australian Voting</a>
 */
public class Main {
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static final String[] candidates = new String[21];
  private static final boolean[] eliminated = new boolean[21];
  private static final int[][] ballots = new int[1000][20];
  private static int totalCandidates = 0;
  private static int totalBallots = 0;

  public static void main(String[] args) {
    String line;

    while ((line = readLine()) != null) {
      int numCases = Integer.parseInt(line);
      readLine();

      for (int i = 0; i < numCases; i++) {
        readCase();
        if (i > 0) {
          System.out.println();
        }
        for (String winner : countVotes()) {
          System.out.println(winner);
        }
      }
    }
  }

  private static String[] countVotes() {
    while (true) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      int[] votes = count();
      for (int v : votes) {
        if (v > 0) {
          min = Math.min(min, v);
        }
        max = Math.max(max, v);
      }

      if (max > totalBallots / 2 || min == max) {
        return findMax(votes, max);
      } else {
        for (int i = 0; i < totalCandidates + 1; i++) {
          if (votes[i] == min) {
            eliminated[i] = true;
          }
        }
      }
    }
  }

  private static String[] findMax(int[] votes, int max) {
    List<String> winners = new LinkedList<String>();
    for (int i = 0; i < votes.length; i++) {
      if (votes[i] == max) {
        winners.add(candidates[i]);
      }
    }

    return winners.toArray(new String[winners.size()]);
  }

  private static int[] count() {
    int[] votes = new int[totalCandidates + 1];
    for (int ballot = 0; ballot < totalBallots; ballot++) {
      for (int choice = 0; choice < totalCandidates; choice++) {
        int candidate = ballots[ballot][choice];
        if (!eliminated[candidate]) {
          votes[candidate]++;
          break;
        }
      }
    }

    return votes;
  }

  private static void readCase() {
    totalCandidates = Integer.parseInt(readLine());

    for (int i = 0; i < totalCandidates; i++) {
      candidates[i + 1] = readLine();
      eliminated[i + 1] = false;
    }

    totalBallots = 0;
    String line;
    while((line = readLine()) != null && !line.isEmpty()) {
      StringTokenizer st = new StringTokenizer(line);
      for (int i = 0; st.hasMoreTokens(); i++) {
        ballots[totalBallots][i] = Integer.parseInt(st.nextToken());
      }
      totalBallots++;
    }
  }

  private static String readLine() {
    try {
      String line = reader.readLine();
      if (line != null) {
        line = line.trim();
      }
      return line;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
