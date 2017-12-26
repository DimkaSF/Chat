package messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Class.forName;

/**
 * Created by Дмитрий on 16.12.2017.
 */
public class ConnectionDB {

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "";
    private static final String password = "";

    public Connection getC() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }


    public void CreateTableProfile() throws SQLException{

        getC().createStatement().execute("CREATE TABLE IF NOT EXISTS Profile(Id long NOT NULL AUTO_INCREMENT, USERNAME varchar(255), FIRSTNAME varchar(255),LASTNAME varchar(255), PRIMARY KEY (Id))");
    }

    public void CreateUser (String username, String firstname, String lastname) throws SQLException{
        getC();
        getC().createStatement().execute("INSERT INTO Profile (USERNAME, FIRSTNAME , LASTNAME) VALUES ('"+username+"', '"+firstname+"', '"+lastname+"')");
    }


    public void CreateTableFile() throws SQLException{

        getC().createStatement().execute("CREATE TABLE IF NOT EXISTS File(Id long NOT NULL AUTO_INCREMENT, FILENAME varchar(255) PRIMARY KEY (Id))");

    }
    public void CreateFile (String filename) throws SQLException{
        getC();
        getC().createStatement().execute("INSERT INTO File VALUES ('D:\\Chatty\\src\\main\\resources\\files\\"+filename+"')");
    }

    public long checkUser (String login) throws SQLException
    {
        getC();
        ResultSet rs;
        rs = getC().createStatement().executeQuery("SELECT Id from Profile WHERE (username = '"+login+"')");
        long id = rs.getLong("Id");
        return id;
    }

    public ResultSet getAllProfiles() throws SQLException
    {
        getC();
        ResultSet rs;
        rs = getC().createStatement().executeQuery("SELECT * from Profile");
        return rs;
    }
}
