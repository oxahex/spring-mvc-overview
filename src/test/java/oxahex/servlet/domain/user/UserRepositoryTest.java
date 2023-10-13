package oxahex.servlet.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    UserRepository userRepository = UserRepository.getInstance();

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void save() {
        // given
        User user = new User("user1", 89);
        User savedUser = userRepository.save(user);

        // when
        User findUser = userRepository.findById(user.getId());

        // then
        Assertions.assertEquals(savedUser.getId(), findUser.getId());
    }

    @Test
    void findAll() {
        // given
        User user1 = new User("user1", 89);
        User user2 = new User("user2", 90);

        userRepository.save(user1);
        userRepository.save(user2);

        // when
        List<User> userList = userRepository.findALl();

        // then
        Assertions.assertEquals(userList.size(), 2);
        assertThat(userList).contains(user1, user2);
    }

}