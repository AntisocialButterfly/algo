package uva10142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @see <a href="http://uva.onlinejudge.org/external/101/10142.html">Australian Voting</a>
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int numberOfCases = Integer.parseInt(reader.readLine());
    reader.readLine();

    for (int i = 0; i < numberOfCases; i++) {
      Elections e = Elections.read(reader);
      System.out.println(e.getWinner());
    }
  }

  private static class Elections {
    public String getWinner() {
      return null;
    }

    public static Elections read(BufferedReader reader) {
      try {
        return new Builder(reader).read();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    private static class Builder {
      private final BufferedReader reader;
      public Builder(BufferedReader reader) {
        this.reader = reader;
      }

      public Elections read() throws  IOException {
        int numberOfCandidates = Integer.parseInt(reader.readLine());
        String[] candidates = new String[numberOfCandidates + 1];

        for (int i = 0; i < numberOfCandidates; i++) {
          candidates[i + 1] = reader.readLine();
        }

        Collection<ArrayList<Integer>> votes = new ArrayList<>();
        String ballot;
        while ((ballot = reader.readLine()) != null) {
          String[] preferences = ballot.split("\\s+");
          ArrayList<Integer> q = new ArrayList<>();
          for (int i = 0; i < preferences.length; i++) {
            q.add(Integer.parseInt(preferences[i]));
          }

          votes.add(q);
        }
      }
    }
  }
}
