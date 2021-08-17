package ru.interview4j.router;
/*
 * Date: 05.08.2021
 * Time: 11:40 AM
 * */

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;
import ru.interview4j.exception.CustomException;
import ru.interview4j.service.SectionService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class SectionRouterTest extends AbstractRouterTestClass {

    private static final String API_SECTIONS = "/api/sections";

    @MockBean
    private SectionService sectionService;

    @Test
    public void getSection_ShouldReturnNotFound() {
        given(sectionService.findSectionById(anyLong()))
                .willThrow(CustomException.notFound("Section not found"));

        webClient.get().uri(API_SECTIONS + "/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void getSection_ShouldReturnSectionDto() {
        Section section = mock(Section.class);
        given(section.getId()).willReturn(1L);
        given(section.getTitle()).willReturn("section");
        given(section.getUserId()).willReturn(1L);
        given(section.getCreatedAt()).willReturn(LocalDateTime.now());
        given(section.getUpdatedAt()).willReturn(LocalDateTime.now());
        given(sectionService.findSectionById(anyLong())).willReturn(Mono.just(section));

        webClient.get().uri(API_SECTIONS + "/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("title").isEqualTo("section")
                .jsonPath("createdAt").exists()
                .jsonPath("updatedAt").exists();
    }

}
