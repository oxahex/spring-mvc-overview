package oxahex.servlet.web.frontcontroller.v1;

import oxahex.servlet.web.frontcontroller.v1.controller.UserFormControllerV1;
import oxahex.servlet.web.frontcontroller.v1.controller.UserListControllerV1;
import oxahex.servlet.web.frontcontroller.v1.controller.UserSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/users/join", new UserFormControllerV1());
        controllerMap.put("/front-controller/v1/users/save", new UserSaveControllerV1());
        controllerMap.put("/front-controller/v1/users", new UserListControllerV1());
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);

        System.out.println("requestURI: " + requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
