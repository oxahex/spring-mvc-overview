package oxahex.servlet.web.servlet;

import org.springframework.stereotype.Controller;
import oxahex.servlet.domain.user.User;
import oxahex.servlet.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userListServlet", urlPatterns = "/servlet/users")
public class UserListServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<User> users = userRepository.findALl();

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
                "<p>회원 목록 조회</p>\n" +
                "<table>\n" +
                "    <thead>\n" +
                "        <tr>id</tr>\n" +
                "        <tr>username</tr>\n" +
                "        <tr>age</tr>\n" +
                "    </thead>\n" +
                "    <tbody>");

        for (User user : users) {
            w.write("<tr>\n" +
                    "<td>" + user.getId() + "</td>\n" +
                    "<td>" + user.getUsername() + "</td>\n" +
                    "<td>" + user.getAge() + "</td>\n" +
                    "</tr>");
        }

        w.write("    </tbody>\n" +
                "</table>\n" +
                "<a href=\"/index.html\">go to main page</a>\n" +
                "</body>\n" +
                "</html>");
    }
}


