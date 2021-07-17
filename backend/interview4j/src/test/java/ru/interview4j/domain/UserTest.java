package ru.interview4j.domain;
/*
 * Date: 17.07.2021
 * Time: 8:33 AM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("id");
        user.setUsername("username");
        user.setPassword("password");
    }

    @Test
    public void getId_ShouldReturnId() {
        assertEquals("id", user.getId());
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
        assertEquals(new Date(), user.getCreatedAt());
    }

    @Test
    public void getUpdatedAt_ShouldReturnUpdatedAt() {
        assertEquals(new Date(), user.getUpdatedAt());
    }

    @Test
    public void toString_ShouldBeEquals() {
        String toStr = String.format("User{id='%s', username='%s', password='%s', createdAt='%s', updatedAt='%s'}",
                user.getId(), user.getUsername(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt());
        assertEquals(toStr, user.toString());
    }

}
