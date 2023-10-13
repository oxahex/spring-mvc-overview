package oxahex.servlet.web.frontcontroller.v3;

import oxahex.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> params);
}
