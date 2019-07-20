package Controller.mainServletProperties;

import Controller.DataBaseHandler;
import View.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class OutMainPage {
    public void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();;
        String productsCollected = "";
        HttpSession session = request.getSession();




        IndexView indexView = IndexView.getInstance();
        Authorization authorization = Authorization.getInstance();
        Dayall dayall = Dayall.getInstance();
        Calculator calculator = Calculator.getInstance();
        Registration registration = Registration.getInstance();
        Planing planing = Planing.getInstance();
        PlaningDayCollection planingDayCollection = PlaningDayCollection.getInstance();

        String htmlIndex = indexView.getFilename();
        String htmlAuthorization = authorization.getFilename();
        String htmlRegistration = registration.getFilename();
        String htmlCalculator = Calculator.getFilename();
        String htmlPlaning = Planing.getFilename();
        String htmlPlaningDayCollection = planingDayCollection.getFilename();

        //request.setCharacterEncoding("UTF-8");


        String actionName = request.getParameter("name");
        String actionPass = request.getParameter("password");
        String actionID = request.getParameter("auth_form");
        DataBaseHandler dbHandler = new DataBaseHandler();
        DataBaseHandler b = new DataBaseHandler();
        String[] ac = b.planingList();
        String a = ac[0];
        String bb = ac[1];
        String c = ac[2];
        String d = ac[3];
        String e = ac[4];
        String f = ac[5];
        String g = ac[6];
        if (session.getAttribute("email") != null){

            String productsList = htmlCalculator.replaceAll("PRODUCTS_ALL",dbHandler.getProducts()).replaceAll("PRODUCTS_DAY",dbHandler.getDays(request));
            productsCollected = htmlIndex.replaceAll("<!--TEAM_PROJECT_CONTENT-->", productsList).replaceAll("PLANNING_LIST",htmlPlaning).replaceAll(
                        "DAYS_LIST", htmlPlaningDayCollection).replaceAll("PRODUCTS_COLLECTION_DAY_ONE",a).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_TWO",bb).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_THREE",c).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_FOUR",d).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_FIVE",e).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_SIX",f).replaceAll(
                    "PRODUCTS_COLLECTION_DAY_SEVEN",g
            );

            out.println(productsCollected);
        } else {

            productsCollected = htmlIndex.replaceAll("<!--TEAM_PROJECT_CONTENT-->", htmlAuthorization);
            out.println(productsCollected);

        }

    }



}
