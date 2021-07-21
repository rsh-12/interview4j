package ru.interview4j.domain;
/*
 * Date: 17.07.2021
 * Time: 8:33 AM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
    }

    @Test
    public void getUsername_ShouldReturnUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void getPassword_ShouldReturnPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void getCreatedAt_ShouldReturnCreatedAt() {
        assertEquals(new Date().toString(), user.getCreatedAt().toString());
    }

    @Test
    public void getUpdatedAt_ShouldReturnUpdatedAt() {
        assertEquals(new Date().toString(), user.getUpdatedAt().toString());
    }

    @Test
    public void toString_ShouldBeEquals() {
        String toStr = String.format("User{id='%s', username='%s', password='%s', createdAt=%s, updatedAt=%s}",
                user.getId(), user.getUsername(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt());
        assertEquals(toStr, user.toString());
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
