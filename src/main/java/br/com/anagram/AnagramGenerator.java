package br.com.anagram;

import br.com.anagram.exception.InvalidAnagramInputException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class AnagramGenerator {

    public static List<String> generateAnagrams(String input) {
        log.info("Generating anagrams for input: '{}'", input);
        validate(input);

        List<String> result = getStrings(input);
        log.info("Generated {} anagrams", result.size());
        return result;
    }

    private static void validate(String input) {
        if(StringUtils.isBlank(input))
            throw new InvalidAnagramInputException("Input cannot be null or empty.");

        if (!input.matches("[a-zA-Z]+"))
            throw new InvalidAnagramInputException("Input must contain only letters.");

        if (input.length() != 3)
            throw new InvalidAnagramInputException("This implementation supports only 3-letter strings.");
    }

    private static List<String> getStrings(String input) {
        List<String> result = new ArrayList<>();
        List<Integer> indices = List.of(0, 1, 2);

        indices.forEach(i -> indices.forEach(j -> {
            if (!j.equals(i)) {
                indices.forEach(k -> {
                    if (!k.equals(i) && !k.equals(j)) {
                        String anagram = "" + input.charAt(i) + input.charAt(j) + input.charAt(k);
                        result.add(anagram);
                    }
                });
            }
        }));
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            log.info("Please enter a 3-letter word (or press Enter to use default 'abc'):");
            String input = scanner.nextLine();

            if (StringUtils.isBlank(input)) {
                input = "abc";
                log.info("No input detected, using default '{}'", input);
            }

            List<String> anagrams = generateAnagrams(input);
            anagrams.forEach(anagram -> log.info("Anagram: {}", anagram));
        } catch (InvalidAnagramInputException e) {
            log.error("Input error: {}", e.getMessage());
        }
    }
}
