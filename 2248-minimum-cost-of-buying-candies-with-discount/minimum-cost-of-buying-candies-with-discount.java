import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int ans = 0;
        int count = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            count++;
            if (count == 3) { // free candy
                count = 0;
                continue;
            }
            ans += cost[i];
        }

        return ans;
    }
}