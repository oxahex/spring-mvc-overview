package oxahex.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
public class UserListController {

    private final UserRepository userRepository = UserRepository.getInstance();

    @RequestMapping("/spring-mvc/v1/users")
    public ModelAndView process(Map<String, String> params) {

        List<User> users = userRepository.findALl();

        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", users);

        return mv;
    }
}
