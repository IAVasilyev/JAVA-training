package stringQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AboutAnagrams {
    /*
        Group anagrams
     */
    @Test
    void groupAnagramTest() {
        Assertions.assertEquals(groupAnagram(List.of("aA", "Aa", "b", "B", "c", "nothing")).size(), 4);
        Assertions.assertEquals(groupAnagram(List.of("c", "C", "c")).size(), 1);
        Assertions.assertEquals(groupAnagram(List.of("nothing")).size(), 1);
        Assertions.assertTrue(groupAnagram(Collections.emptyList()).isEmpty());
    }

    private Collection<List<String>> groupAnagram(Collection<String> strings) {
        return strings.stream().map(StringWithSymbols::new)
                .collect(Collectors.groupingBy(s -> s.symbols,
                        Collectors.mapping(o -> o.original, Collectors.toList()))).values();
    }

    /*
        Find anagram substring and return start and end index or null if text doesn't contain anagram for searched string
     */
    @Test
    void findAnagramSubstringTest() {
        Assertions.assertArrayEquals(new int[]{0, 1}, findAnagramSubstring("abca", "ba"));
        Assertions.assertArrayEquals(new int[]{1, 2}, findAnagramSubstring("abca", "cb"));
        Assertions.assertArrayEquals(new int[]{2, 3}, findAnagramSubstring("abca", "ac"));
        Assertions.assertArrayEquals(new int[]{0, 3}, findAnagramSubstring("abca", "caab"));
        Assertions.assertArrayEquals(new int[]{2, 2}, findAnagramSubstring("abca", "c"));
        Assertions.assertArrayEquals(new int[]{0, 0}, findAnagramSubstring("c", "c"));
        Assertions.assertNull(findAnagramSubstring("abca", "z"));
    }

    private int[] findAnagramSubstring(String text, String string) {
        if (string.isEmpty()) return null;
        final Map<Integer, Long> toFind = new StringWithSymbols(string).symbols;
        int startIndex = 0, endIndex = startIndex + string.length();

        HashMap<Integer, Long> symbolsUnderWork = new HashMap<>();
        while (endIndex <= text.length()) {
            if (symbolsUnderWork.isEmpty()) {
                for (int i = startIndex; i < endIndex; i++) {
                    Integer characterToAdd = (int) Character.toLowerCase(text.charAt(i));
                    // optimization: if we caught unexpected symbol, we can skip some positions
                    if (!toFind.containsKey(characterToAdd)) {
                        symbolsUnderWork.clear();
                        startIndex = i+1;
                        endIndex = startIndex + string.length();
                        break;
                    } else {
                        symbolsUnderWork.compute(characterToAdd, (key, counter) -> counter == null ? 1 : counter + 1);
                    }
                }
            } else {
                Integer characterToLeave = (int) Character.toLowerCase(text.charAt(startIndex++));
                Integer characterToAdd = (int) Character.toLowerCase(text.charAt(endIndex++));
                symbolsUnderWork.compute(characterToLeave, (key, counter) -> counter - 1);
                if (!toFind.containsKey(characterToAdd)) {
                    symbolsUnderWork.clear();
                    startIndex = endIndex;
                    endIndex = startIndex + string.length();
                } else {
                    symbolsUnderWork.compute(characterToAdd, (key, counter) -> counter == null ? 1 : counter + 1);
                }
            }
            if (symbolsUnderWork.equals(toFind)) return new int[]{startIndex, endIndex - 1};
        }
        return null;
    }

    // Auxiliary class
    private class StringWithSymbols {
        private final Map<Integer, Long> symbols;
        private final String original;

        private StringWithSymbols(String original) {
            this.original = original;
            symbols = original.chars().map(Character::toLowerCase).boxed()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
    }
}
