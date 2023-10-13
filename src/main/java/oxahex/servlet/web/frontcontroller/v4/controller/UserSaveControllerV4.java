package oxahex.servlet.web.frontcontroller.v4.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class UserSaveControllerV4 implements ControllerV4 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        String username = params.get("username");
        int age = Integer.parseInt(params.get("age"));

        User user = new User(username, age);
        userRepository.save(user);

        model.put("user", user);

        return "save-result";
    }
}
