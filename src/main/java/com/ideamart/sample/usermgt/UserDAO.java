package com.ideamart.sample.usermgt;

import com.ideamart.sample.database.DatabaseConnection;
import com.ideamart.sample.subcription.Subscription;

import java.io.IOException;
import java.sql.*;

/**
 * Created by tharinda on 10/20/16.
 */
public class UserDAO {

    private Connection connection;
    private Statement stmt;

    public void AddUser(User user) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "INSERT INTO tracking VALUES (" + "\"" + user.getAddress() + "\"" + "," + "\"" + user.getMessage() + "\"" +
                "," + "\"" + user.getFlow() + "\"" + "," + "\"" + String.valueOf(user.getSubscription()) + "\"" + ");";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();


    }

    public void updateFlow(String address, String flow) throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "UPDATE tracking SET flow=" + "\"" + flow + "\"" + " WHERE address= "+ "\"" + address + "\""+";";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();
    }

    public String getFlow(String address) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking where address= " + "\"" + address + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("flow");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return null;
    }

    public void updateCount(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "UPDATE tracking SET subscription = subscription + 1" + " WHERE address= "+ "\"" + address + "\""+";";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();
    }

    public int getCount(String address) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking where address= " + "\"" + address + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("subscription");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return 0;
    }

    public boolean userAvailability(String address)  {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking where address =" + "\"" + address + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return false;

    }

    public boolean userPinAvailability(String address) {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_pin where address =" + "\"" + address + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return false;

    }

    public void AddUserPin(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "INSERT INTO tracking_pin VALUES (" + "\"" + address + "\"" + "," +  "NULL"  + ");";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();

    }

    public String getUserAddressByPin(String pin) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_pin where pin= " + "\"" + pin + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "null";
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return "null";
    }

    public String getUserPinByAddress(String address) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_pin where address= "+ "\"" + address + "\""  +";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("pin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Please Register";
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return "Please Register";
    }

    public int getTotalUsers() {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select COUNT(*) AS total FROM tracking";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return 0;
    }

    public int getPendingUsers() {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select COUNT(*) AS total FROM tracking WHERE subscription=" + "1";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return 0;
    }

    public void updateUserStatus(String address, int status) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "UPDATE tracking SET status = "+ status +"" + " WHERE address= "+ "\"" + address + "\""+";";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();
    }


    public int[] getTotalSubscribers() {
        ResultSet resultSet = null;
        String address;
        int reg = 0, unReg = 0, pending = 0;
        int [] array = new int[2];
        Subscription subscription = new Subscription();
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select address from tracking;";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                address = resultSet.getString("address");
                if(subscription.getStatusNumber(address) == 1) {
                    reg++;
                    updateUserStatus(address, 1);

                } else if (subscription.getStatusNumber(address) == 0) {
                    unReg++;
                    updateUserStatus(address, 0);
                } else {
                    pending++;
                    updateUserStatus(address, 2);
                }
            }
            array[0] = reg;
            array[1] = unReg;
            array[2]  = pending;
            return array;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return array;
    }
}
