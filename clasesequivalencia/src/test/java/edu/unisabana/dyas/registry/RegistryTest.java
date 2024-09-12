package edu.unisabana.dyas.registry;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RegistryTest {
    private Registry registry;

    @Before
    public void setUp() {
        registry = new Registry();
    }

    @After
    public void tearDown() {
        registry = null;
    }

    @Test
    public void validateRegistryResult() {
        Person person = new Person("Juan Martinez", 123, 30, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertTrue(result == RegisterResult.VALID);
    }

    @Test
    public void testDeadPerson() {
        Person person = new Person("Jaime Peña", 543, 45, Gender.FEMALE, false);
        RegisterResult result = registry.registerVoter(person);
        assertTrue(result == RegisterResult.DEAD);
    }

    @Test
    public void testInvalidAgeNegative() {
        try {
            Person person = new Person("Alicia Perez", 135, -5, Gender.FEMALE, true);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("La edad no puede ser negativa o superior a 130"));
        }
    }

    @Test
    public void testInvalidAgeTooHigh() {
        try {
            Person person = new Person("Bob Patiño", 280, 150, Gender.MALE, true);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("La edad no puede ser negativa o superior a 130"));
        }
    }

    @Test
    public void testDuplicatedPerson() {
        Person person1 = new Person("Charles Darwin", 113, 40, Gender.UNIDENTIFIED, true);
        registry.registerVoter(person1);
        Person person2 = new Person("Charles Darwin", 113, 40, Gender.UNIDENTIFIED, true);
        RegisterResult result = registry.registerVoter(person2);
        assertTrue(result == RegisterResult.DUPLICATED);
    }

    @Test
    public void testAgeExactly18() {
        Person person = new Person("David Mojica", 333, 18, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertTrue(result == RegisterResult.VALID);
    }
}
