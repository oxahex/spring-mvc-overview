package oxahex.servlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/spring-mvc/v3/users")
public class UserController {

    private final UserRepository userRepository = UserRepository.getInstance();

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {

        List<User> users = userRepository.findALl();

        model.addAttribute("users", users);

        return "users";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join() {
        return "join";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {
        User user = new User(username, age);
        userRepository.save(user);

        model.addAttribute("user", user);

        return "save-result";
    }
}
