package KBPBot;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public static UserVk getVkUserFromDB(int userId) throws SQLException {
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
        System.out.println(new UserVk(id,firstname,lastname,state,platform,last_message_id));
        return new UserVk(id,firstname,lastname,state,platform,last_message_id);
    }
    public static void insertVkUserInDB(UserVk userVk) throws SQLException {
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
    public static List<String> getSourcesInDB(String typeSource) throws SQLException {
        String query = String.format("SELECT * FROM sources WHERE type in ('%s')",typeSource); // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        List<String> listResult = new ArrayList<>();
        while(resultSet.next()) {
            listResult.add(resultSet.getString("name"));
        }
        return listResult;
    }
    public static List<String> getAllSpecialsInDB(String type) throws SQLException {
        String query = String.format("select distinct substring(\"name\",1,1) as name from sources where type='%S'",type); // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        List<String> listResult = new ArrayList<>();
        while(resultSet.next()) {
            listResult.add(resultSet.getString("name"));
        }

        return listResult;
    }
    public static List<String> getAllSourcesInDB(String nameSource, String type) throws SQLException {
        String query = String.format("select name from sources where name like N'%S%%' and type='%S'",nameSource,type); // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        List<String> listResult = new ArrayList<>();
        while(resultSet.next()) {
            listResult.add(resultSet.getString("name"));
        }
        listResult.sort(String.CASE_INSENSITIVE_ORDER);
        return listResult;
    }
    public static int getIdSourcesInDB(String columnName) throws SQLException {
        String query = String.format("select * from sources where name = N'%S'",columnName); // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
        // Обработаем результат
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet.getInt("id");
    }
    public static void addSourceUserConnectionsFromDB(String columnName,int userId) throws SQLException {
        int idSource = getIdSourcesInDB(columnName);
        String query = String.format("insert into source_user_connections values('%S','%S')",idSource,userId); // наш запрос к бд
        PreparedStatement statement = connection.prepareStatement(query); // создаём наш запрос к бд
        statement.execute(); // выполняем запрос к бд
    }


}
