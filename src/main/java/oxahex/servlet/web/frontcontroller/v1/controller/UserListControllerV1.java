package oxahex.servlet.web.frontcontroller.v1.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListControllerV1 implements ControllerV1 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void process(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("UserListControllerV1.process");
        List<User> users = userRepository.findALl();

        request.setAttribute("users", users);

        String viewPath = "/WEB-INF/views/users.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
