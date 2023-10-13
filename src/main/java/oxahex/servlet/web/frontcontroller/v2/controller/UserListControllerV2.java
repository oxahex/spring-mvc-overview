package oxahex.servlet.web.frontcontroller.v2.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListControllerV2 implements ControllerV2 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public View process(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("UserListControllerV2.process");

        List<User> users = userRepository.findALl();
        request.setAttribute("users", users);

        return new View("/WEB-INF/views/users.jsp");
    }
}
