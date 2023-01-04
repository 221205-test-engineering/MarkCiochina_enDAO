package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    private static ConnectionUtility connectionObject;

    private ConnectionUtility(){}

    public static ConnectionUtility getConnectionObject(){
        if(connectionObject == null){
            return new ConnectionUtility(); //
        }
        return connectionObject;
    }

    public Connection getConnection(){
        Connection connection = null;

        String url = System.getenv("DB_URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        try{
           connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //make instance of connection utility
        //requires making a constructor (here added manually to make private)
    //make class method to get or create a connection utility instance (container)
    //make method to create a connection, which is accessed through the instantiated connection object


}
