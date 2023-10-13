package oxahex.servlet.web.frontcontroller.v4;

import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v4.controller.UserFormControllerV4;
import oxahex.servlet.web.frontcontroller.v4.controller.UserListControllerV4;
import oxahex.servlet.web.frontcontroller.v4.controller.UserSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private final Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/users/join", new UserFormControllerV4());
        controllerMap.put("/front-controller/v4/users/save", new UserSaveControllerV4());
        controllerMap.put("/front-controller/v4/users", new UserListControllerV4());
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Param, Model 생성
        Map<String, String> params = createParams(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(params, model);

        // 논리 이름 -> 물리 위치
        View view = viewResolver(viewName);

        // 렌더링 로직 호출
        view.render(model, request, response);
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
