import java.sql.*;

public class WeatherDB {

    private static Connection connection;
    private static Statement statement;

    public static void start() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lesson08.db");
            statement = connection.createStatement();
            dropTable();
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try {
            statement.executeUpdate("Drop table if exists Weather");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        try {
            statement.executeUpdate("Create table if not exists Weather (ID integer primary key autoincrement," +
                    "City string, Date string, Weather string, Temperature real);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void write(Location location, Weather_Response weather_response) {
        try {
            for (int i = 0; i < weather_response.getDate().size(); i++) {
                statement.executeUpdate("Insert into Weather (City, Date, Weather, Temperature)" + "values (\"" +
                        location.getCity() + "\", \"" + weather_response.getDate().get(i) + "\", \"" +
                        weather_response.getWeatherText().get(i) + "\", \"" + weather_response.getTemperature().get(i)
                        + "\");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            ResultSet resultSet = statement.executeQuery("Select * from Weather");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " +
                        resultSet.getString(3) + " | " + resultSet.getString(4) + " | " +
                        resultSet.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
