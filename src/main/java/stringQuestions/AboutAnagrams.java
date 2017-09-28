package stringQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        class StringWithSymbols {
            final Map<Integer, Long> symbols;
            final String original;

            private StringWithSymbols(String original) {
                this.original = original;
                symbols = original.chars().map(Character::toLowerCase).boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            }
        }

        return strings.stream().map(StringWithSymbols::new)
                .collect(Collectors.groupingBy((StringWithSymbols s) -> s.symbols,
                        Collectors.mapping((StringWithSymbols o) -> o.original, Collectors.toList()))).values();
    }
}
