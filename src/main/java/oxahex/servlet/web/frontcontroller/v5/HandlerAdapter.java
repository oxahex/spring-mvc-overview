package oxahex.servlet.web.frontcontroller.v5;

import oxahex.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HandlerAdapter {

    boolean supports(Object handler);

    ModelView handler(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws ServletException, IOException;
}
