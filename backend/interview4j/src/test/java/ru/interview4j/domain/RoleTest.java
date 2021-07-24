package ru.interview4j.domain;
/*
 * Date: 24.07.2021
 * Time: 10:46 AM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(ERole.ROLE_USER);
        role.setId(1L);
    }

    @Test
    public void getters() {
        assertEquals(1L, role.getId());
        assertEquals(ERole.ROLE_USER, role.getName());
    }

    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        Role role2 = new Role(ERole.ROLE_USER);
        role2.setId(1L);
        assertTrue(role.equals(role2) && role2.equals(role));
        assertEquals(role.hashCode(), role2.hashCode());
    }

}
