import java.util.Arrays;

public class TaroFriends {

	public int getNumber(int[] coordinates, int X) {
    Arrays.sort(coordinates);

    int N = coordinates.length;
	  int r = Integer.MAX_VALUE;

	  for (int i = 0; i <= N; i++) {
	    int min = Integer.MAX_VALUE;
	    int max = Integer.MIN_VALUE;

	    for (int j = 0; j < N; j++) {
	      int newPos = coordinates[j];
	      if (j >= i) {
          newPos -= X;
	      } else {
          newPos += X;
	      }

	      min = Math.min(min, newPos);
	      max = Math.max(max, newPos);
	    }

	    r = Math.min(r, max - min);
	  }

		return r;
	}

}
