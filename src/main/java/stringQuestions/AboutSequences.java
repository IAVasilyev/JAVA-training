package stringQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class AboutSequences {
    /*
        Given a sequence of numbers, find a subsequence which sum of element equals given number
     */
    @Test
    void findSubsequenceWithSumTest() {
        Assertions.assertArrayEquals(new int[]{0, 0}, findSubsequenceWithSum(new int[]{1}, 1));
        Assertions.assertArrayEquals(new int[]{0, 1}, findSubsequenceWithSum(new int[]{0, 1}, 1));
        Assertions.assertArrayEquals(new int[]{0, 1}, findSubsequenceWithSum(new int[]{-1, 2}, 1));
        Assertions.assertArrayEquals(new int[]{0, 0}, findSubsequenceWithSum(new int[]{0, 1, -1, -2}, 0));
        Assertions.assertArrayEquals(new int[]{0, 3}, findSubsequenceWithSum(new int[]{2, -1, -2, 1}, 0));
        Assertions.assertNull(findSubsequenceWithSum(new int[]{2, -1, -2, 1}, -3));
        Assertions.assertNull(findSubsequenceWithSum(new int[]{}, 0));
    }

    private int[] findSubsequenceWithSum(int[] sequence, long sum) {
        if (sequence == null || sequence.length == 0) return null;
        Map<Long, int[]> subSequnceSum = new HashMap<>();
        AtomicLong subSum = new AtomicLong(0);
        IntStream.range(0, sequence.length).forEachOrdered(i -> subSequnceSum.compute(
                subSum.addAndGet(sequence[i]), (aLong, ints) -> {
                    if (ints == null) return new int[]{i, i};
                    else {
                        ints[0] = Math.min(ints[0], i);
                        ints[1] = Math.max(ints[1], i);
                        return ints;
                    }
                }));
        for (Long currentSubSum : subSequnceSum.keySet()) {
            long sumToFind = sum - currentSubSum;
            int[] leftIndex = subSequnceSum.get(currentSubSum);
            if (currentSubSum == sum) return new int[] {0, leftIndex[0]};
            int[] rightIndex = subSequnceSum.get(sumToFind);
            if (rightIndex != null && rightIndex[1] <= leftIndex[0]) return new int[]{leftIndex[0], rightIndex[1]};
        }
        return null;
    }
}
