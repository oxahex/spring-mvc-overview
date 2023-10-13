package oxahex.servlet.web.frontcontroller.v5;

import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.View;
import oxahex.servlet.web.frontcontroller.v3.controller.UserFormControllerV3;
import oxahex.servlet.web.frontcontroller.v3.controller.UserListControllerV3;
import oxahex.servlet.web.frontcontroller.v3.controller.UserSaveControllerV3;
import oxahex.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import oxahex.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {

        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/users/join", new UserFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/users/save", new UserSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/users", new UserListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Handler Adapter 찾음
        HandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handler(request, response, handler);

        String viewName = mv.getViewName();
        View view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private View viewResolver(String viewName) {
        return new View("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) return adapter;
        }

        throw new IllegalArgumentException(
                "Handler Adapter를 찾을 수 없음 -> handler: " + handler
        );
    }
}
