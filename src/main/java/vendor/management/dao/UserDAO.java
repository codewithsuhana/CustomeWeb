package vendor.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendor.management.model.User;

public class UserDAO {
    // Database connection details
    private String url = "jdbc:mysql://localhost:3306/Customer?useSSL=false";
    private String un = "root";
    private String pwd = "sjsuhana";

    // SQL queries for user operations
    private static final String INSERT_USER_SQL = "insert into `customers` (`fname`, `lname`,`street`, `address`, `city`, `state`,`email`, `phone`,`password`) values (?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM `customers` where `id` = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM `customers` where `email` = ?";
    private static final String SELECT_USER_BY_FNAME = "SELECT * FROM `customers` where `fname` = ?";
    private static final String SELECT_USER_BY_CITY = "SELECT * FROM `customers` where `city` = ?";
    private static final String SELECT_USER_BY_PHONE = "SELECT * FROM `customers` where `phone` = ?";
    private static final String SELECT_ALL_USERS = "select * from `customers`";
    private static final String DELETE_USER_SQL = "DELETE FROM `customers` where `id` =?";
    private static final String UPDATE_USERS_SQL = "UPDATE `customers` SET `fname` = ?, `lname` = ?, `street` = ?, `address` = ?, `city` = ?, `state` = ?, `email` = ?, `phone` = ? WHERE `id` = ?";

    // Default constructor
    public UserDAO() {}

    // Establish database connection
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, un, pwd);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert a new user into the database
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getStreet());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setLong(8, user.getPhone());
            preparedStatement.setString(9, user.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update user information in the database
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getFname());
            statement.setString(2, user.getLname());
            statement.setString(3, user.getStreet());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getCity());
            statement.setString(6, user.getState());
            statement.setString(7, user.getEmail());
            statement.setLong(8, user.getPhone());
            statement.setInt(9, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Retrieve a user by their ID
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                user = new User(fname, lname, street, address, city, state, email, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Retrieve a list of users by their first name
    public List<User> selectUserByFname(String fn) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_FNAME)) {
            preparedStatement.setString(1, fn);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                users.add(new User(id, fname, lname, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a list of users by their email
    public List<User> selectUserByEmail(String mail) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, mail);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                User user = new User(id, fname, lname, street, address, city, state, email, phone);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a list of users by their phone number
    public List<User> selectUserByPhone(long phone1) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PHONE)) {
            preparedStatement.setLong(1, phone1);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                users.add(new User(id, fname, lname, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a list of users by their city
    public List<User> selectUserByCity(String cityy) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_CITY)) {
            preparedStatement.setString(1, cityy);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                users.add(new User(id, fname, lname, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a list of all users
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");

                users.add(new User(id, fname, lname, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Delete a user from the database
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    // Print detailed information about SQL exceptions
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
