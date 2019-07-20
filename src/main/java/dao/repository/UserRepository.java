package dao.repository;

import Controller.DataBaseHandler;
import dao.entity.User;

import java.sql.*;

public class UserRepository {


    public User getUserByEmailByPassword(String email, String password) {

        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        String query= "SELECT id, email, password, name, phone, height, weight"+" FROM Users "+
                " WHERE users.email='"+email+"' AND password='"+password+"'";

        try (Connection connection = dataBaseHandler.getDbConnection();
             PreparedStatement resSql = connection.prepareStatement(query);
             ResultSet resultSet = resSql.executeQuery();
        ) {
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
               user.setHeight(resultSet.getString("height"));
                user.setWeight(resultSet.getString("weight"));

                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByEmail(String email) {

        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        String query= "SELECT id, email, password, name, role, phone, height, weight"+" FROM Users "+
                " WHERE users.email='"+email+"'";

        try (Connection connection = dataBaseHandler.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getString("phone"),
                        resultSet.getString("height"),
                        resultSet.getString("weight")
                );
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     *  Save User in DB, if new - insert
    */
    public boolean saveUser (User user){

        DataBaseHandler dataSourse = new DataBaseHandler();
        try ( Connection connection = dataSourse.getDbConnection();
              PreparedStatement preparedStatement =  connection.prepareStatement("INSERT INTO users (email, password, name,  phone, height, weight) values (?,?,?,?,?,?)")
                      ){
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getHeight());
            preparedStatement.setString(6, user.getWeight());


            preparedStatement.executeLargeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
