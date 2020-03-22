package customer;

import org.apache.logging.log4j.LogManager;
import java.sql.*;
import org.apache.logging.log4j.Logger;

public class JDBCExecutor {
    private static final Logger logger = LogManager.getLogger(JDBCExecutor.class.getName());
    public static void main(String ... args) {
        DataBaseConnectionManager localCon = new DataBaseConnectionManager("localhost", "Umuzi","dbuser", "dbuser123");
        try(
                Connection connection = localCon.getConnection()

        ){
            logger.info("Connection to the datbase established at 127.0.0.1 . . .");

            System.out.println("=========================================== 1 ==============================================================");
            //1. SELECT ALL records from table Customers.
            final String GET_ALL = "SELECT * FROM customer";
            try(
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
            ) {

                // execute Query is for read only sql statement. result is rows and column. v execute Update for Update/Insert/Delete in CRUD
                ResultSet resultSet = statement.executeQuery(GET_ALL);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("customerId") + ", " + resultSet.getString("firstname") + ", " + resultSet.getString("lastname") + ", "
                            + resultSet.getString("gender") + ", " + resultSet.getString("Address") + ", " + resultSet.getString("Phone") +
                            ", " + resultSet.getString("Email") + ", " + resultSet.getString("City") + ", " + resultSet.getString("Country"));
                    logger.info("Database read successfully");
                }

            }catch (SQLException e){
                logger.error(e.getMessage());
            }

            System.out.println("=========================================== 2 ==============================================================");
            //2. SELECT records only from the name column in the Customers table.
            final String SELECT_NAME = "SELECT firstname FROM customer";
            try(
                    Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ){

                ResultSet resultSet1 = statement1.executeQuery(SELECT_NAME);
                while (resultSet1.next()){
                    System.out.println(resultSet1.getString("firstname"));
                }
            }catch (SQLException e1){
                logger.error(e1.getMessage());
            }
            System.out.println("=========================================== 3 ==============================================================");
            //3. Show the name of the Customer whose CustomerID is 1.
            final String SHOW_CUSTOMER = "SELECT firstname FROM customer WHERE customerid = 1";
            try(
                    Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ){

                ResultSet resultSet2 = statement2.executeQuery(SHOW_CUSTOMER);
                while (resultSet2.next()){
                    System.out.println(resultSet2.getString("firstname"));
                }
            }catch (SQLException e2){
                logger.error(e2.getMessage());
            }
            System.out.println("=========================================== 4 ==============================================================");
            //4. UPDATE the record for CustomerID = 1 on the Customer table so that the name is “Lerato Mabitso”.
            final String UPDATE_USER = "UPDATE customer SET firstname = Lerato, lastname = Mabitso WHERE customerid = 1 ";
            try {
                Statement statement3 = connection.createStatement();
                int update = statement3.executeUpdate(UPDATE_USER);   // update user information
                System.out.println("Number of row affected : " + update);
            }catch (SQLException e3){
                System.err.println("Cant update user with CustomerID of 1");
                logger.error(e3.getMessage());
            }
            System.out.println("=========================================== 5 ==============================================================");
            //5. DELETE the record from the Customers table for customer 2 (CustomerID = 2)
            final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerid = 2";
            try {

                Statement statement4 = connection.createStatement();
                int delete = statement4.executeUpdate(DELETE_CUSTOMER);  //remove user
                System.out.println("Number of row affected : " + delete);
            }catch (SQLException e4){
                System.err.println("Customer cannot be deleted");
                logger.error(e4.getMessage());
            }
            System.out.println("=========================================== 6 ==============================================================");
            //6. Select all unique statuses from the Orders table and get a count of the number of orders for each unique status.
            final String SELECT_UNIQUE = "SELECT COUNT(DISTINCT status) FROM orders ";
            try(
                    Statement statement5 = connection.createStatement();
            ){

                ResultSet resultSet5 = statement5.executeQuery(SELECT_UNIQUE);
                while (resultSet5.next()){
                    System.out.println(resultSet5.getInt(1));
                }
            }catch (SQLException e5){
                logger.error(e5.getMessage());
            }
            System.out.println("=========================================== 7 ==============================================================");
            //7. Return the MAXIMUM payment made on the PAYMENTS table.
            final String SELECT_MAX = "SELECT MAX(amount) FROM payments";
            try(
                    Statement statement7 = connection.createStatement();
            ) {

                ResultSet resultSet7 = statement7.executeQuery(SELECT_MAX);
                while (resultSet7.next()) {
                    System.out.println(resultSet7.getInt(1));
                }
            }catch (SQLException e7){
                logger.error(e7.getMessage());
            }
            System.out.println("=========================================== END ==============================================================");
        }catch (SQLException sql){
            logger.fatal("There was a problem the database");
            sql.printStackTrace();
        }
    }
}