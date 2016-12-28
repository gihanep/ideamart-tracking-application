package com.ideamart.sample.dashboardMgt;

import com.ideamart.sample.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tharinda on 12/28/16.
 */
public class DashboardDAO {

    private Connection connection;
    private Statement stmt;

    public void AddDashboard(Dashboard dashboard) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "INSERT INTO tracking_dashboard VALUES (" + "\"" + dashboard.getDate() + "\"" + "," + "\"" + dashboard.getReg() + "\"" +
                "," + "\"" + dashboard.getUnReg() + "\"" + "," + "\"" + String.valueOf(dashboard.getPending()) + "\"" + ");";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        connection.close();


    }

    public void updateParams(Dashboard dashboard) throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "UPDATE tracking_dashboard SET reg=" + "\"" + dashboard.getReg() + "\"" + " WHERE date= " + "\"" + dashboard.getDate() + "\"" + ";";
        System.out.println(sql);
        stmt.executeUpdate(sql);

        String sql2 = "UPDATE tracking_dashboard SET unReg=" + "\"" + dashboard.getUnReg() + "\"" + " WHERE date= " + "\"" + dashboard.getDate() + "\"" + ";";
        System.out.println(sql);
        stmt.executeUpdate(sql);

        String sql3 = "UPDATE tracking_dashboard SET pending=" + "\"" + dashboard.getPending() + "\"" + " WHERE date= " + "\"" + dashboard.getDate() + "\"" + ";";
        System.out.println(sql);
        stmt.executeUpdate(sql);

        connection.close();
    }

    public void deteleTable() throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "DELETE * FROM tracking_dashboard;";
        System.out.println(sql);
        stmt.executeUpdate(sql);

        connection.close();

    }

    public boolean dateAvailable(String date) {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_dashboard where date =" + "\"" + date + "\"" + ";";
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

    public int getRegCount(String date) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_dashboard where date= " + "\"" + date + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("reg");
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

    public int getUnRegCount(String date) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_dashboard where date= " + "\"" + date + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("unReg");
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

    public int getPendingCount(String date) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_dashboard where date= " + "\"" + date + "\"" + ";";
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("pending");
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

    public int[] getDailyTraffic(String date) throws ClassNotFoundException, SQLException {
        int[] array = new int[3];
        if (dateAvailable(date)) {
            array[0] = getRegCount(date);
            array[1] = getUnRegCount(date);
            array[2] = getPendingCount(date);
        } else {
            deteleTable();
            for (int i = 0; i < 3; i++) {
                array[i] = 0;
            }
        }
        return array;
    }
}
