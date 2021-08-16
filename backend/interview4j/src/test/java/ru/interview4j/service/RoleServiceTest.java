package ru.interview4j.service;
/*
 * Date: 31.07.2021
 * Time: 11:23 PM
 * */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.Role;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.service.impl.RoleServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void findUserRolesById_ShouldReturnFluxEmpty() {
        given(roleRepository.findRolesByUserId(anyLong())).willReturn(Flux.empty());
        StepVerifier.create(roleService.findUserRolesById(1L))
                .verifyComplete();
    }

    @Test
    public void findUserRolesById_ShouldReturnRoles() {
        Role role_user = mock(Role.class);
        given(role_user.getId()).willReturn(1L);
        given(role_user.getName()).willReturn(ERole.ROLE_USER);

        Role role_admin = mock(Role.class);
        given(role_user.getId()).willReturn(2L);
        given(role_user.getName()).willReturn(ERole.ROLE_ADMIN);

        given(roleRepository.findRolesByUserId(anyLong())).willReturn(Flux.just(role_user, role_admin));

        StepVerifier.create(roleService.findUserRolesById(1L))
                .expectNext(role_user)
                .expectNext(role_admin)
                .verifyComplete();
    }

}
