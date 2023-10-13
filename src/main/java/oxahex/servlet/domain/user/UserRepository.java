package oxahex.servlet.domain.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;
    private static final UserRepository instance = new UserRepository();

    // 싱글톤
    private UserRepository() {}
    public static UserRepository getInstance() {
        return instance;
    }

    // 회원 저장
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);

        return user;
    }

    // 회원 조회
    public User findById(Long id) {
        return store.get(id);
    }

    public List<User> findALl() {
        return new ArrayList<>(store.values());
    }

    // 전체 삭제
    public void clearStore() {
        store.clear();
    }
}
