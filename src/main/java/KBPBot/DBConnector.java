package KBPBot;

import org.junit.Test;

import java.sql.*;

public class DBConnector {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://letsfuckingparceallkbp.database.windows.net:1433;database=kbp_bots_db;user=Nananosh@letsfuckingparceallkbp;password=Kbpbots.1488228;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private int executeUpdate(String query) throws SQLException {
//        Statement statement = connection.createStatement();
//        // Для Insert, Update, Delete
//        int result = statement.executeUpdate(query);
//        return result;
//    }
    public static UserVK getVkUserFromDB(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE platform='Vk' and id= " + userId; // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        int id = resultSet.getInt("id");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String state = resultSet.getString("state");
        String platform = resultSet.getString("platform");
        int last_message_id = resultSet.getInt("last_message_id");
        System.out.println(new UserVK(id,firstname,lastname,state,platform,last_message_id));
        return new UserVK(id,firstname,lastname,state,platform,last_message_id);
    }
    public static void insertVkUserInDB(UserVK userVk) throws SQLException {
        String query = String.format("INSERT INTO users VALUES(%d,N'%s',N'%s',N'%s',N'%s',%d)", userVk.getId(),userVk.getFirstname(),userVk.getLastname(),userVk.getState(),userVk.getPlatform(),userVk.getLast_message_id());
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
    }
    public static void changeStateVkUserInDB(String state, int userId) throws SQLException {
        String query = "UPDATE Users Set state='"+state+"'WHERE platform='Vk' and id=" + userId; // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
    }
    public static boolean isVkUserInDb(int userId) throws SQLException {
        String query = "SELECT * FROM Users Where id = "+userId; // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
    public static int getLastMessageIdVkUserFromDB(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE platform='Vk' and id= " + userId; // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        changeIdVkUserInDB(userId);
        return resultSet.getInt("last_message_id");
    }
    public static void changeIdVkUserInDB(int userId) throws SQLException {
        String query = "UPDATE Users Set last_message_id='"+(getVkUserFromDB(userId).getLast_message_id()+1)+"'WHERE platform='Vk' and id=" + userId; // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute();
    }

}
