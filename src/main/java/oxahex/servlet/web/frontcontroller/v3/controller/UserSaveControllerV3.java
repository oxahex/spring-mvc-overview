package oxahex.servlet.web.frontcontroller.v3.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class UserSaveControllerV3 implements ControllerV3 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> params) {

        String username = params.get("username");
        int age = Integer.parseInt(params.get("age"));

        User user = new User(username, age);
        userRepository.save(user);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("user", user);

        return mv;
    }
}
