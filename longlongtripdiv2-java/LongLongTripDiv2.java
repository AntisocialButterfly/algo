public class LongLongTripDiv2 {

	public String isAble(long D, int T, int B) {
    if (T > D) {
      return "Impossible";
    }

	  long b = D / B;
	  long r = D % B;

	  do {
	    if (T == b + r) {
	      return "Possible";
	    }
	    b--;
	    r = D - B * b;
	  } while (b >= 0 && b + r <= T);

		return "Impossible";
	}

}
