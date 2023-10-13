package oxahex.servlet.web.frontcontroller.v5.adapter;

import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v3.ControllerV3;
import oxahex.servlet.web.frontcontroller.v5.HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handler(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> params = createParams(request);

        return controller.process(params);
    }

    private Map<String, String> createParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(x -> params.put(x, request.getParameter(x)));
        return params;
    }
}
