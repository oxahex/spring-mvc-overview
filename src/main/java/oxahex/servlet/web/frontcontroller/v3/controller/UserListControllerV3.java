package oxahex.servlet.web.frontcontroller.v3.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class UserListControllerV3 implements ControllerV3 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> params) {

        List<User> users = userRepository.findALl();

        ModelView mv = new ModelView("users");
        mv.getModel().put("users", users);

        return mv;
    }
}
