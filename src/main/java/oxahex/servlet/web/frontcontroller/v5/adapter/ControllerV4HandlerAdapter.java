package oxahex.servlet.web.frontcontroller.v5.adapter;

import org.springframework.web.servlet.DispatcherServlet;
import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v4.ControllerV4;
import oxahex.servlet.web.frontcontroller.v5.HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handler(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> params = createParams(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(params, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(x -> params.put(x, request.getParameter(x)));
        return params;
    }
}
