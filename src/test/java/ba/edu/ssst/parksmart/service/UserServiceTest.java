package ba.edu.ssst.parksmart.service;

import ba.edu.ssst.parksmart.model.User;
import ba.edu.ssst.parksmart.repository.UserRepository;
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
    void getAllUsers_returnsListOfUsers() {
        User user1 = User.builder().id(1L).fullName("Alice").email("alice@example.com").password("pass1").build();
        User user2 = User.builder().id(2L).fullName("Bob").email("bob@example.com").password("pass2").build();
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(2).containsExactly(user1, user2);
        verify(userRepository).findAll();
    }

    @Test
    void getUserById_returnsUser_whenFound() {
        User user = User.builder().id(1L).fullName("Alice").email("alice@example.com").password("pass").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getFullName()).isEqualTo("Alice");
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserById_returnsEmpty_whenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(99L);

        assertThat(result).isEmpty();
        verify(userRepository).findById(99L);
    }

    @Test
    void createUser_savesAndReturnsUser() {
        User input = User.builder().fullName("Carol").email("carol@example.com").password("secret").build();
        User saved = User.builder().id(3L).fullName("Carol").email("carol@example.com").password("secret").build();
        when(userRepository.save(input)).thenReturn(saved);

        User result = userService.createUser(input);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getFullName()).isEqualTo("Carol");
        assertThat(result.getEmail()).isEqualTo("carol@example.com");
        verify(userRepository).save(input);
    }

    @Test
    void findByEmail_returnsUser_whenEmailExists() {
        User user = User.builder().id(1L).fullName("Alice").email("alice@example.com").password("pass").build();
        when(userRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("alice@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("alice@example.com");
        assertThat(result.get().getFullName()).isEqualTo("Alice");
        verify(userRepository).findByEmail("alice@example.com");
    }

    @Test
    void findByEmail_returnsEmpty_whenEmailNotFound() {
        when(userRepository.findByEmail("ghost@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("ghost@example.com");

        assertThat(result).isEmpty();
        verify(userRepository).findByEmail("ghost@example.com");
    }
}
