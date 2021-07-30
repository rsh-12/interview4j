package ru.interview4j.domain;
/*
 * Date: 17.07.2021
 * Time: 8:33 AM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void equals_ShouldBeEqual() {
        User newUser = new User();
        newUser.setUsername("username");
        assertEquals(user, newUser);
    }

    @Test
    public void equals_ShouldNotBeEqual() {
        User newUser = new User("username", "password");
        newUser.setUsername("none");
        assertNotEquals(user, newUser);
    }

    @Test
    public void equals_SameObjects_ShouldBeEqual() {
        assertEquals(user, user);
    }

    @Test
    public void equals_Null_ShouldNotBeEqual() {
        assertFalse(user.equals(null));
    }

    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        User newUser = new User("username", "password");
        newUser.setUsername("username");
        assertTrue(user.equals(newUser) && newUser.equals(user));
        assertEquals(user.hashCode(), newUser.hashCode());
    }

}
