package oxahex.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserSaveController {

    private final UserRepository userRepository = UserRepository.getInstance();

    @RequestMapping("/spring-mvc/v1/users/save")
    public ModelAndView process(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(username, age);
        userRepository.save(user);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("user", user);

        return mv;
    }
}
