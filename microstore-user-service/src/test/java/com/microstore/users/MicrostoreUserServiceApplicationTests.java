package com.microstore.users;

import com.microstore.users.model.User;
import com.microstore.users.repository.UserRepository;
import com.microstore.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = new User("Lucas", "lucas@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);

        assertThat(created.getName()).isEqualTo("Lucas");
        assertThat(created.getEmail()).isEqualTo("lucas@example.com");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User("Maria", "maria@example.com");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User found = userService.getUserById(1L);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Maria");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob@example.com");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userService.getAllUsers();

        assertThat(users).hasSize(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        User user = new User("Carlos", "carlos@example.com");
        user.setId(1L);
        when(userRepository.save(user)).thenReturn(user);

        User updated = userService.updateUser(1L, user);

        assertThat(updated.getName()).isEqualTo("Carlos");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
