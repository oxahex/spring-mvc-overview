package oxahex.servlet.web.frontcontroller.v4.controller;

import oxahex.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class UserFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        return "join";
    }
}
