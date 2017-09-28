package stringQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AboutPalindromes {
    /*
        Is a given string a palindrome (ignoring case and not alphabetic symbols)
     */

    @Test
    void isPalindromeTest() {
        Assertions.assertTrue(isPalindrome("I did, did I?"));
        Assertions.assertTrue(isPalindrome("Eva, can I see bees in a cave?"));
        Assertions.assertTrue(isPalindrome(""));
        Assertions.assertTrue(isPalindrome("A"));
        Assertions.assertTrue(isPalindrome(";';'"));
        Assertions.assertTrue(isPalindrome("!B"));
        Assertions.assertFalse(isPalindrome("Not a palindrome"));
    }

    private static boolean isPalindrome(String forTest) {
        int leftCharacterIndex = 0, rightCharacterIndex = forTest.length() - 1;
        while (leftCharacterIndex < rightCharacterIndex) {
            char left;
            while (!Character.isLetter((left = forTest.charAt(leftCharacterIndex++)))) {
                if (leftCharacterIndex >= rightCharacterIndex) return true;
            }
            char right;
            while (!Character.isLetter((right = forTest.charAt(rightCharacterIndex--)))) {
                if (leftCharacterIndex >= rightCharacterIndex) return true;
            }
            if (Character.toLowerCase(left) != Character.toLowerCase(right)) return false;
        }
        return true;
    }
}
