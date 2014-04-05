public class MinimumSquareEasy {
  public long minArea(int[] x, int[] y) {
    int N = x.length;
    long minArea = Long.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int left = Integer.MAX_VALUE;
        int top = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;

        for (int n = 0; n < N; n++) {
          if (n == i || n == j) {
            continue;
          }

          left = Math.min(left, x[n]);
          top = Math.max(top, y[n]);
          right = Math.max(right, x[n]);
          bottom = Math.min(top, y[n]);
        }

        int side = Math.max(right - left, top - bottom) + 2;
        minArea = Math.min(minArea, (long) side * side);
      }
    }

    return minArea;
  }
}
