package Controller.Servlets;

import Controller.DataBaseHandler;
import Controller.mainServletProperties.OutMainPage;
import View.*;
import dao.entity.User;
import dao.repository.UserRepository;
import static Controller.dbSetting.Const.USER_SESSINO_ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "MainServlet", urlPatterns = {"/*"}, loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        UserRepository userRepository = new UserRepository();

        User user = new User();
        String Auth = request.getParameter("Auth");
        String Register = request.getParameter("Register");

        String id = "";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String height = request.getParameter("height");
        String weightForm = request.getParameter("weight");


        if (Auth.equals("1")) {


            if (!email.equals("")){
                user = userRepository.getUserByEmailByPassword(email, password);
                session.setAttribute("email",email);
                session.setAttribute("id",user.getId());

                String sId= String.valueOf(session.getAttribute("id")) ;
                int idU = Integer.valueOf(sId);
                USER_SESSINO_ID =idU;

                System.out.println(session.getAttribute("id"));

            }



        // reading from login-registr form


        }else if(Register.equals("2")){


                User userNew = new User(0L,email,password,name,phone,height,weightForm);
                userRepository.saveUser(userNew);
            }

        response.sendRedirect("/");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        OutMainPage a = new OutMainPage();
        a.getPage(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
// отримуємо посилання на об'єкт сингелтона
        IndexView indexView = IndexView.getInstance();
        // присвоємо змінній сингелтота шлях до папаки html проеку
        indexView.setPath(getServletContext().getRealPath("/html/"));

        Authorization authorization = Authorization.getInstance();
        authorization.setPath(getServletContext().getRealPath("/html/"));

        Calculator calculator = Calculator.getInstance();
        calculator.setPath(getServletContext().getRealPath("/html/"));

        Productsall productsall = Productsall.getInstance();
        productsall.setPath(getServletContext().getRealPath("/html/"));

        Dayall dayall = Dayall.getInstance();
        dayall.setPath(getServletContext().getRealPath("/html/"));

        Registration registration =Registration.getInstance();
        registration.setPath(getServletContext().getRealPath("/html/"));

        Planing planing = Planing.getInstance();
        planing.setPath(getServletContext().getRealPath("/html/"));

        PlaningDayCollection planingDayCollection = PlaningDayCollection.getInstance();
        planingDayCollection.setPath(getServletContext().getRealPath("/html/"));

    }
}
