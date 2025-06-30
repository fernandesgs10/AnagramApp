package br.com.anagram;

import br.com.anagram.exception.InvalidAnagramInputException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AnagramGeneratorTest {

    @Test
    void testGenerateAnagrams_ValidInput() {
        log.info("▶️ Starting: testGenerateAnagrams_ValidInput");
        List<String> result = AnagramGenerator.generateAnagrams("abc");
        log.info("Generated anagrams: {}", result);
        assertEquals(6, result.size());
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
        log.info("✅ Passed: testGenerateAnagrams_ValidInput");
    }

    @Test
    void testGenerateAnagrams_NullInput_ThrowsException() {
        log.info("▶️ Starting: testGenerateAnagrams_NullInput_ThrowsException");
        InvalidAnagramInputException thrown = assertThrows(InvalidAnagramInputException.class, () -> AnagramGenerator.generateAnagrams(null));
        log.info("Caught exception as expected: {}", thrown.getMessage());
        assertEquals("Input cannot be null or empty.", thrown.getMessage());
        log.info("✅ Passed: testGenerateAnagrams_NullInput_ThrowsException");
    }

    @Test
    void testGenerateAnagrams_EmptyInput_ThrowsException() {
        log.info("▶️ Starting: testGenerateAnagrams_EmptyInput_ThrowsException");
        InvalidAnagramInputException thrown = assertThrows(InvalidAnagramInputException.class, () -> AnagramGenerator.generateAnagrams(""));
        log.info("Caught exception as expected EmptyInput: {}", thrown.getMessage());
        assertEquals("Input cannot be null or empty.", thrown.getMessage());
        log.info("✅ Passed: testGenerateAnagrams_EmptyInput_ThrowsException");
    }

    @Test
    void testGenerateAnagrams_InvalidCharacters_ThrowsException() {
        log.info("▶️ Starting: testGenerateAnagrams_InvalidCharacters_ThrowsException");
        InvalidAnagramInputException thrown = assertThrows(InvalidAnagramInputException.class, () -> AnagramGenerator.generateAnagrams("a1b"));
        log.info("Caught exception as expected InvalidCharacters: {}", thrown.getMessage());
        assertEquals("Input must contain only letters.", thrown.getMessage());
        log.info("✅ Passed: testGenerateAnagrams_InvalidCharacters_ThrowsException");
    }

    @Test
    void testGenerateAnagrams_WrongLength_ThrowsException() {
        log.info("▶️ Starting: testGenerateAnagrams_WrongLength_ThrowsException");
        InvalidAnagramInputException thrown = assertThrows(InvalidAnagramInputException.class, () -> AnagramGenerator.generateAnagrams("abcd"));
        log.info("Caught exception as expected WrongLength: {}", thrown.getMessage());
        assertEquals("This implementation supports only 3-letter strings.", thrown.getMessage());
        log.info("✅ Passed: testGenerateAnagrams_WrongLength_ThrowsException");
    }
}
