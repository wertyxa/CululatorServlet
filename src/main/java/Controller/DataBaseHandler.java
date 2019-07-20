package Controller;

import Controller.dbSetting.Configs;
import View.Dayall;
import View.Productsall;
import com.sun.deploy.net.HttpRequest;
import dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static Controller.dbSetting.Const.*;
public class DataBaseHandler extends Configs {


    //Створення посилання для підключенння до бази

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"
                + dbHost + ":"
                + dbPort + "/"
                + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver"); //тут у мене була помилка і я добавив cj
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    // Вигрузка списку продуктів для юзера
    public String getProducts() {
        int productId;
        String productName;
        int productCallories;
        double productPrice;
        String productsAll = "";
        Productsall htmlProductsall25 = Productsall.getInstance();
        String htmlProductsall = htmlProductsall25.getFilename();
        try {
            String sql = "SELECT id, name, callories, price FROM products";
            PreparedStatement resSql = getDbConnection().prepareStatement(sql);
            ResultSet productQuery = resSql.executeQuery();

            while (productQuery.next()) {
                productId = productQuery.getInt(1);
                productName = productQuery.getString(2);
                productCallories = productQuery.getInt(3);
                productPrice = productQuery.getDouble(4);
                productsAll += htmlProductsall.replaceAll("PRODUCT_ID", String.valueOf(productId)).replaceAll("PRODUCT_NAME", productName).replaceAll("PRODUCT_CALLORIES", String.valueOf(productCallories)).replaceAll("PRODUCT_PRICE", String.valueOf(productPrice));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productsAll;
    }

    //Вибір днів тижня
    public String getDays(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sId= String.valueOf(session.getAttribute("id")) ;
        int id = Integer.valueOf(sId);
        String dayAll = "";
        int dayFromProduct;
        Map<Integer, String> days = new HashMap<Integer, String>();
        Map<Integer, String> sortedDays = new HashMap<Integer, String>();
        ArrayList<Integer> userDayProductCollect = new ArrayList<Integer>();
        Dayall htmlDayall25 = Dayall.getInstance();
        String htmlDayall = htmlDayall25.getFilename();

        days.put(1, "Понеділок");
        days.put(2, "Вівторок");
        days.put(3, "Середа");
        days.put(4, "Четвер");
        days.put(5, "Пятниця");
        days.put(6, "Субота");
        days.put(7, "Неділя");

        try {
            String sql = "SELECT id, dayofweek FROM planing WHERE userid = "+id;
            PreparedStatement resSql = getDbConnection().prepareStatement(sql);
            ResultSet productQuery = resSql.executeQuery();

            while (productQuery.next()) {
                userDayProductCollect.add(productQuery.getInt(2));
            }

            for(Map.Entry<Integer,String> entry:days.entrySet()){
                if(!userDayProductCollect.contains(entry.getKey())){
                    sortedDays.put(entry.getKey(), entry.getValue());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, String> entry : sortedDays.entrySet()) {
            dayAll += htmlDayall.replaceAll("DAY_ID", String.valueOf(entry.getKey())).replaceAll("DAY_NAME", entry.getValue());

        }


        return dayAll;
    }

    //Добавлення раціону на день
    public void userPlan(int userId, int productId, int weight, int dayofweek) {

        String insert = "INSERT INTO planing (userid, productId, weight, dayofweek) VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, userId);
            prSt.setInt(2, productId);
            prSt.setInt(3, weight);
            prSt.setInt(4, dayofweek);

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Виведення списку днів з вибранними продуктами

    public String[] planingList() {

        String sql = "SELECT id, weight, dayofweek, productid FROM planing WHERE userid = " + USER_SESSINO_ID + " ORDER BY dayofweek ASC";
        DataBaseHandler productsFromList = new DataBaseHandler();
        int productId;
        String collectedProducts = "";
        String day1 = "";
        String day2 = "";
        String day3 = "";
        String day4 = "";
        String day5 = "";
        String day6 = "";
        String day7 = "";
        String[] ourProduct =new String[7];
        try {

            PreparedStatement resSql = getDbConnection().prepareStatement(sql);
            ResultSet productQuery = resSql.executeQuery();

            while (productQuery.next()) {
                int id = productQuery.getInt(1);
                int weight = productQuery.getInt(2);
                int dayOfWeek = productQuery.getInt(3);
                productId = productQuery.getInt(4);

                collectedProducts=productsFromList.test(productId, weight);

                if (dayOfWeek == 1) {
                    day1 += collectedProducts;
                } else if (dayOfWeek == 2){
                    day2 += collectedProducts;

                } else if (dayOfWeek == 3){
                    day3 += collectedProducts;

                } else if (dayOfWeek == 4){
                    day4 += collectedProducts;

                } else if (dayOfWeek == 5){
                    day5 += collectedProducts;

                } else if (dayOfWeek == 6){
                    day6 += collectedProducts;

                } else if (dayOfWeek == 7){
                    day7 += collectedProducts;

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!day1.equals("")){
            ourProduct[0]=day1;
        } else {
            ourProduct[0]="<tr><td colspan=\"5\">Раціон на понеділок не складено</td></tr>";
        }
        if (!day2.equals("")){
            ourProduct[1]=day2;
        } else {
            ourProduct[1]="<tr><td colspan=\"5\">Раціон на вівторок не складено</td></tr>";
        }
        if (!day3.equals("")){
            ourProduct[2]=day3;
        } else {
            ourProduct[2]="<tr><td colspan=\"5\">Раціон на середу не складено</td></tr>";
        }
        if (!day4.equals("")){
            ourProduct[3]=day4;
        } else {
            ourProduct[3]="<tr><td colspan=\"5\">Раціон на четвер не складено</td></tr>";
        }
        if (!day5.equals("")){
            ourProduct[4]=day5;
        } else {
            ourProduct[4]="<tr><td colspan=\"5\">Раціон на п'ятницю не складено</td></tr>";
        }
        if (!day6.equals("")){
            ourProduct[5]=day6;
        } else {
            ourProduct[5]="<tr><td colspan=\"5\">Раціон на суботу не складено</td></tr>";
        }
        if (!day7.equals("")){
            ourProduct[6]=day7;
        } else {
            ourProduct[6]="<tr><td colspan=\"5\">Раціон на неділю не складено</td></tr>";
        }

        return ourProduct;
    }

        public String test(int productId, int weight) {

            String sql = "SELECT id, name, callories, price FROM products WHERE id = "+productId;
            String ourProduct = "";
            try {

                PreparedStatement resSql = getDbConnection().prepareStatement(sql);
                ResultSet productQuery = resSql.executeQuery();

                while (productQuery.next()) {
                    int id = productQuery.getInt(1);
                    String name = productQuery.getString(2);
                    int callories = productQuery.getInt(3);
                    double price  = productQuery.getDouble(4);

                    callories = (weight/100)*callories;
                    price = (price/1000)*weight;
                    String formattedDouble = new DecimalFormat("#0.00").format(price);
                    ourProduct += "<tr><td><!--" + id + "--></td><td>" + name + "</td><td>" + weight + "</td><td>" + callories + "</td><td>" + formattedDouble + "</td></tr>";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        return ourProduct;
    }


    //Видалення дня з раціону

    public void deleteProductsday(int id, int userid){
        String sql = "DELETE FROM planing WHERE dayofweek = " + id + " AND userid = " + userid;
        PreparedStatement resSql = null;
        try {
            resSql = getDbConnection().prepareStatement(sql);
            resSql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    }
