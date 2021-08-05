package ru.interview4j.router;
/*
 * Date: 05.08.2021
 * Time: 11:40 AM
 * */

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import ru.interview4j.service.SectionService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class SectionRouterTest extends AbstractRouterTestClass {

    private static final String API_SECTIONS = "/api/sections";

    @MockBean
    private SectionService sectionService;

    @Test
    public void getSection_ShouldReturnNotFound() {
        given(sectionService.findSectionById(anyLong())).willReturn(Mono.empty());

        webClient.get().uri(API_SECTIONS + "/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

}
