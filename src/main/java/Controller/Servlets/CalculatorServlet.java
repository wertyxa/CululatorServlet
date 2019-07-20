package Controller.Servlets;

import Controller.DataBaseHandler;
import Controller.mainServletProperties.OutMainPage;
import dao.entity.User;
import dao.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static Controller.dbSetting.Const.USER_SESSINO_ID;
import static java.lang.Math.ceil;

@WebServlet(name = "CalculatorServlet", urlPatterns = {"/calc"})
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String calculatorCount = request.getParameter("calculatorcount");



        String sId= String.valueOf(session.getAttribute("id")) ;
        int id = Integer.valueOf(sId);

       if (calculatorCount.equals("1")) {


            String productId[];
            String weight[];
            String dayOfWeek = request.getParameter("productsday");
            int dayofweek = Integer.valueOf(dayOfWeek);
            weight = request.getParameterValues("weight");
            productId= request.getParameterValues("productid");


           Map<String, String> products = new HashMap<String, String>();

           for (int i=0;i<productId.length;i++){
               String a = productId[i];
               String b = weight[i];
               products.put(a,b);
           }

           for (Map.Entry<String, String> entry : products.entrySet()) {
               if (!entry.getValue().equals("")){
                   int productIdM =Integer.valueOf(entry.getKey());
                   int weightM = Integer.valueOf(entry.getValue());

                   DataBaseHandler insertUserPlan = new DataBaseHandler();

                   insertUserPlan.userPlan(id, productIdM, weightM, dayofweek);


               }

           }

            OutMainPage a = new OutMainPage();
            a.getPage(request, response);

        } else if (calculatorCount.equals("2")){

           String idProductDayUser= request.getParameter("deleteday");
           int idUser= id;

           DataBaseHandler deleteFromTable = new DataBaseHandler();

           deleteFromTable.deleteProductsday(Integer.valueOf(idProductDayUser), idUser);
           OutMainPage a = new OutMainPage();
           a.getPage(request, response);
       } else {
            System.out.println("No");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
