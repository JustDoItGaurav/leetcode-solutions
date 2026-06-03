import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        int n = landStartTime.length;
        int m = waterStartTime.length;

        int[][] land = new int[n][2];
        int[][] water = new int[m][2];

        for (int i = 0; i < n; i++) {
            land[i][0] = landStartTime[i];
            land[i][1] = landDuration[i];
        }

        for (int i = 0; i < m; i++) {
            water[i][0] = waterStartTime[i];
            water[i][1] = waterDuration[i];
        }

        Arrays.sort(land, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(water, (a, b) -> Integer.compare(a[0], b[0]));

        int[] wStart = new int[m];
        long[] wPrefDur = new long[m];
        long[] wSuffFinish = new long[m];

        for (int i = 0; i < m; i++) {
            wStart[i] = water[i][0];
        }

        wPrefDur[0] = water[0][1];
        for (int i = 1; i < m; i++) {
            wPrefDur[i] = Math.min(wPrefDur[i - 1], water[i][1]);
        }

        wSuffFinish[m - 1] = (long) water[m - 1][0] + water[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            wSuffFinish[i] = Math.min(
                    wSuffFinish[i + 1],
                    (long) water[i][0] + water[i][1]
            );
        }

        int[] lStart = new int[n];
        long[] lPrefDur = new long[n];
        long[] lSuffFinish = new long[n];

        for (int i = 0; i < n; i++) {
            lStart[i] = land[i][0];
        }

        lPrefDur[0] = land[0][1];
        for (int i = 1; i < n; i++) {
            lPrefDur[i] = Math.min(lPrefDur[i - 1], land[i][1]);
        }

        lSuffFinish[n - 1] = (long) land[n - 1][0] + land[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            lSuffFinish[i] = Math.min(
                    lSuffFinish[i + 1],
                    (long) land[i][0] + land[i][1]
            );
        }

        // Land -> Water
        for (int i = 0; i < n; i++) {
            long finishLand = (long) landStartTime[i] + landDuration[i];

            int pos = upperBound(wStart, (int) finishLand);

            if (pos > 0) {
                ans = Math.min(ans,
                        finishLand + wPrefDur[pos - 1]);
            }

            if (pos < m) {
                ans = Math.min(ans,
                        wSuffFinish[pos]);
            }
        }

        // Water -> Land
        for (int i = 0; i < m; i++) {
            long finishWater = (long) waterStartTime[i] + waterDuration[i];

            int pos = upperBound(lStart, (int) finishWater);

            if (pos > 0) {
                ans = Math.min(ans,
                        finishWater + lPrefDur[pos - 1]);
            }

            if (pos < n) {
                ans = Math.min(ans,
                        lSuffFinish[pos]);
            }
        }

        return (int) ans;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}