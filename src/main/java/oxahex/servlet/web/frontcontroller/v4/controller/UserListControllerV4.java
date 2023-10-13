package oxahex.servlet.web.frontcontroller.v4.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class UserListControllerV4 implements ControllerV4 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        List<User> users = userRepository.findALl();

        model.put("users", users);

        return "users";
    }
}
