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

public class UserSaveControllerV2 implements ControllerV2 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public View process(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("UserSaveControllerV2.process");

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(username, age);
        userRepository.save(user);

        request.setAttribute("user", user);

        return new View("/WEB-INF/views/save-result.jsp");
    }
}
