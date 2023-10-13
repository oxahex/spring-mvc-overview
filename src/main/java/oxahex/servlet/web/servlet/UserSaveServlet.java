package oxahex.servlet.web.servlet;

import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userSaveServlet", urlPatterns = "/servlet/users/save")
public class UserSaveServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(username, age);
        User savedUser = userRepository.save(user);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<!DOCTYPE html>\n" +
                "<html lang=\"ko\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Join</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>회원 등록 성공</p>\n" +
                "<ul>\n" +
                "    <li>id: "+ savedUser.getId() + "</li>\n" +
                "    <li>username: " + savedUser.getUsername() + "</li>\n" +
                "    <li>age: " + savedUser.getAge() + "</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">go to main page</a>\n" +
                "</body>\n" +
                "</html>");

    }
}
