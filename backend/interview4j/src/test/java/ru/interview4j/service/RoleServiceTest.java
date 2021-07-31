package ru.interview4j.service;
/*
 * Date: 31.07.2021
 * Time: 11:23 PM
 * */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.interview4j.domain.Role;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.service.impl.RoleServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void findUserRoles_ShouldReturnFluxEmpty() {
        given(roleRepository.findRolesByUserId(anyLong())).willReturn(Flux.empty());
        StepVerifier.create(roleService.findUserRolesById(1L))
                .verifyComplete();
    }

}
