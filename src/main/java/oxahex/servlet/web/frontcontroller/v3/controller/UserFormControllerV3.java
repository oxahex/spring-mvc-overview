package oxahex.servlet.web.frontcontroller.v3.controller;

import oxahex.servlet.web.frontcontroller.ModelView;
import oxahex.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class UserFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> params) {
        return new ModelView("join");
    }
}
