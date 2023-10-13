package oxahex.servlet.web.frontcontroller.v2;

import oxahex.servlet.web.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    View process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}

