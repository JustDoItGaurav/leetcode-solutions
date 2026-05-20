import java.util.*;

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        int result[] = new int[A.length];

        for (int i = 0; i < A.length; i++) {

            list1.add(A[i]);
            list2.add(B[i]);

            ArrayList<Integer> temp = new ArrayList<>(list1);

            temp.retainAll(list2);

            result[i] = temp.size();
        }

        return result;
    }
}