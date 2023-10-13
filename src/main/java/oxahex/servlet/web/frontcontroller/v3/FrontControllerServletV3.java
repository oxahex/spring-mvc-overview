package oxahex.servlet.web.frontcontroller.v3;

import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v2.ControllerV2;
import oxahex.servlet.web.frontcontroller.v2.controller.UserFormControllerV2;
import oxahex.servlet.web.frontcontroller.v2.controller.UserListControllerV2;
import oxahex.servlet.web.frontcontroller.v2.controller.UserSaveControllerV2;
import oxahex.servlet.web.frontcontroller.v3.controller.UserFormControllerV3;
import oxahex.servlet.web.frontcontroller.v3.controller.UserListControllerV3;
import oxahex.servlet.web.frontcontroller.v3.controller.UserSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/users/join", new UserFormControllerV3());
        controllerMap.put("/front-controller/v3/users/save", new UserSaveControllerV3());
        controllerMap.put("/front-controller/v3/users", new UserListControllerV3());
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Param 생성
        Map<String, String> params = createParams(request);

        // ModelView
        ModelView mv = controller.process(params);

        // 논리 이름 -> 물리 위치
        View view = viewResolver(mv.getViewName());

        view.render(mv.getModel(), request, response);
    }

    private View viewResolver(String viewName) {
        return new View("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(x -> params.put(x, request.getParameter(x)));
        return params;
    }
}
