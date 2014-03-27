package uva10142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @see <a href="http://uva.onlinejudge.org/external/101/10142.html">Australian Voting</a>
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int numberOfCases = Integer.parseInt(reader.readLine().trim());
    reader.readLine();

    for (int i = 0; i < numberOfCases; i++) {
      if (i > 0) {
        System.out.println();
      }
      for (String winner : read(reader)) {
        System.out.println(winner);
      }
    }
  }

  public static String[] read(BufferedReader reader) throws IOException {
    int totalCandidates = Integer.parseInt(reader.readLine().trim());
    String[] candidates = new String[totalCandidates + 1];
    for (int i = 0; i < totalCandidates; i++) {
      candidates[i + 1] = reader.readLine().trim();
    }

    List<Integer[]> ballots = new LinkedList<>();

    String ballot;
    while (!isNullOrEmpty(ballot = reader.readLine())) {
      String[] choices = ballot.split("\\s+");
      Integer[] vote = new Integer[choices.length];
      for (int i = 0; i < vote.length; i++) {
        vote[i] = Integer.parseInt(choices[i]);
      }
      ballots.add(vote);
    }

    return getWinner(candidates, ballots);
  }

  private static boolean isNullOrEmpty(String s) {
    return s == null || s.trim().isEmpty();
  }

  private static String[] getWinner(String[] candidates, List<Integer[]> votes) {
    int N = candidates.length;
    boolean[] eliminated = new boolean[N];
    while (true) {
      int[] r = count(votes, eliminated);
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;

      for (int i = 1; i < N; i++) {
        if (r[i] > 0) {
          min = Math.min(min, r[i]);
        }
        max = Math.max(max, r[i]);
      }

      if (min == max) {
        // all tired
        List<String> winners = new LinkedList<>();
        for (int i = 0; i < N; i++) {
          if (r[i] == max) {
            winners.add(candidates[i]);
          }
        }
        return winners.toArray(new String[winners.size()]);
      } else if (max > votes.size() / 2) {
        // clear winner
        for (int i = 0; i < N; i++) {
          if (r[i] == max) {
            return new String[] { candidates[i] };
          }
        }
      } else {
        // no one has majority, eliminate candidates who scored min votes and recount
        for (int i = 0; i < N; i++) {
          if (r[i] == min) {
            eliminated[i] = true;
          }
        }
      }
    }
  }

  private static int[] count(List<Integer[]> votes, boolean[] e) {
    int[] r = new int[votes.get(0).length + 1];

    for(Integer[] v : votes) {
      for (Integer c : v) {
        if (!e[c]) {
          r[c]++;
          break;
        }
      }
    }

    return r;
  }
}
