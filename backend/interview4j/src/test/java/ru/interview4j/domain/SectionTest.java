package ru.interview4j.domain;
/*
 * Date: 22.07.2021
 * Time: 11:45 PM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectionTest {

    private Section section;

    @BeforeEach
    void setUp() {
        section = new Section("Title", 1L);
        section.setId(1L);
        section.setCreatedAt(new Date());
        section.setUpdatedAt(new Date());
    }

    @Test
    public void getters_ShouldReturnCorrectData() {
        assertEquals(1L, section.getId());
        assertEquals(1L, section.getUserId());
        assertEquals("Title", section.getTitle());
        assertEquals(new Date().toString(), section.getCreatedAt().toString());
        assertEquals(new Date().toString(), section.getUpdatedAt().toString());
    }

}
