package oxahex.servlet.web.frontcontroller.v2.controller;

import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFormControllerV2 implements ControllerV2 {

    @Override
    public View process(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("UserFormControllerV2.process");
        return new View("/WEB-INF/views/join.jsp");
    }
}
