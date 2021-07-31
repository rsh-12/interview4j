package ru.interview4j.domain;
/*
 * Date: 22.07.2021
 * Time: 11:45 PM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SectionTest {

    private Section section;

    @BeforeEach
    void setUp() {
        section = new Section("Title", 1L);
        section.setId(1L);
        section.setCreatedAt(LocalDateTime.now());
        section.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void equals_ShouldBeEqual() {
        final Section section2 = new Section();
        section2.setId(1L);
        section2.setTitle("Title");

        assertEquals(this.section, section2);
    }

    @Test
    public void equals_ShouldNotBeEqual() {
        final Section section2 = new Section();
        section2.setId(1L);
        section2.setTitle("title");

        assertNotEquals(section, section2);
    }

    @Test
    public void equals_SameObjects_ShouldBeEqual() {
        assertEquals(section, section);
    }


    @Test
    public void equals_Null_ShouldNotBeEqual() {
        assertFalse(section.equals(null));
    }


    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        final Section section2 = new Section();
        section2.setId(1L);
        section2.setTitle("Title");

        assertTrue(section.equals(section2) && section2.equals(section));
        assertEquals(section.hashCode(), section2.hashCode());
    }

}
