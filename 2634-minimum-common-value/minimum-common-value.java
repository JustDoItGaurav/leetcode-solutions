import java.util.HashSet;

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        int minNumber = Integer.MAX_VALUE;

        for (int num : nums2) {
            if (set.contains(num)) {
                minNumber = Math.min(minNumber, num);
            }
        }

        return minNumber == Integer.MAX_VALUE ? -1 : minNumber;
    }
}