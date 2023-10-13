package oxahex.servlet.web.frontcontroller.v1.controller;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;
import oxahex.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveControllerV1 implements ControllerV1 {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void process(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("UserSaveControllerV1.process");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(username, age);
        userRepository.save(user);

        request.setAttribute("user", user);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
