package graphsQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AboutSearch {
    /*
        Sum values from right leaf
     */
    @Test
    void rightLeafSumTest() {
        BiNode<Integer> root = new BiNode<>(1);
        Assertions.assertEquals(0L, rightLeafSum(root));
        root.left = new BiNode<>(-1);
        Assertions.assertEquals(0L, rightLeafSum(root));
        root.left.left = new BiNode<>(-2);
        Assertions.assertEquals(0L, rightLeafSum(root));
        root.right = new BiNode<>(3);
        Assertions.assertEquals(3L, rightLeafSum(root));
        root.right.right = new BiNode<>(4);
        Assertions.assertEquals(4L, rightLeafSum(root));
        root.right.right.right = new BiNode<>(5);
        Assertions.assertEquals(5L, rightLeafSum(root));
        root.left.right = new BiNode<>(6);
        Assertions.assertEquals(11L, rightLeafSum(root));
    }

    private long rightLeafSum(BiNode<Integer> root) {
        return auxilarySum(root, false);
    }

    private long auxilarySum(BiNode<Integer> root, boolean isRight) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isRight) return root.value;
        long sum = 0;
        if (root.left != null) sum += auxilarySum(root.left, false);
        if (root.right != null) sum += auxilarySum(root.right, true);
        return sum;
    }

    // Auxiliary class
    class BiNode<T> {
        BiNode<T> right, left;
        final T value;

        BiNode(T value) {
            this.value = value;
        }
    }
}
