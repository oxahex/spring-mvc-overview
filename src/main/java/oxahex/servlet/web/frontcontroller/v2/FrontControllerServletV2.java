package oxahex.servlet.web.frontcontroller.v2;

import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v2.controller.UserFormControllerV2;
import oxahex.servlet.web.frontcontroller.v2.controller.UserListControllerV2;
import oxahex.servlet.web.frontcontroller.v2.controller.UserSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/users/join", new UserFormControllerV2());
        controllerMap.put("/front-controller/v2/users/save", new UserSaveControllerV2());
        controllerMap.put("/front-controller/v2/users", new UserListControllerV2());
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        System.out.println("FrontControllerServletV2.service");
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);

        System.out.println("requestURI: " + requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        View view = controller.process(request, response);
        view.render(request, response);
    }
}
