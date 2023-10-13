package oxahex.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserFormControllerV1 {

    @RequestMapping("/spring-mvc/v1/users/join")
    public ModelAndView process() {
        return new ModelAndView("join");
    }
}
