package Test.TestEmployee;

import Domain.DomainEmployee.SHA_256_Hasher;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class Test_SHA256_Hasher {
    SHA_256_Hasher hasher = new SHA_256_Hasher();
    @Test
    public void testHashing() {
        String input = "password123";
        String hashed = hasher.hashPassword(input);

        assertNotNull(hashed);
        assertEquals(64, hashed.length()); // SHA-256 hash is 64 characters long
    }

    @Test
    public void testEmptyInput() {
        String input = "";
        String hashed = hasher.hashPassword(input);

        assertNotNull(hashed);
        assertEquals(64, hashed.length());
    }

    @Test
    public void testNullInput() {
        try {
            hasher.hashPassword(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testDifferentInputs() {
        String input1 = "password123";
        String input2 = "password124";
        String hashed1 = hasher.hashPassword(input1);
        String hashed2 = hasher.hashPassword(input2);

        assertNotEquals(hashed1, hashed2);
    }
}