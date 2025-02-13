package com.example.Projekt.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setup() {
        userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        user = new User();
        user.setName("John Doe");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setRoles(Collections.emptyList());
    }

    @Test
    void testSaveUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByName(anyString())).thenReturn(new Role());

        userService.saveUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testFindUserByEmail_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User foundUser = userService.findUserByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void testFindUserByEmail_NotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);

        User foundUser = userService.findUserByEmail("notfound@example.com");

        assertNull(foundUser);
    }

    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserDto> users = userService.findAllUsers();

        assertEquals(1, users.size());
        assertEquals("test@example.com", users.get(0).getEmail());
    }
}
