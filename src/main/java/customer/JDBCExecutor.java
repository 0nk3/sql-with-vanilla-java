package customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String args[]) {
        DataBaseConnectionManager localCon = new DataBaseConnectionManager("localhost", "Umuzi","dbuser", "dbuser123");
        try(Connection connection = localCon.getConnection()){
            System.out.println("Connection established on 127.0.0.1 . . .\n");
            System.out.println("=========================================================================================================");
            //1. SELECT ALL records from table Customers.
            final String GET_ALL = "SELECT * FROM customer";

            Statement statement = connection.createStatement();
            // execute Query is for read only sql statement. result is rows and column. v execute Update for Update/Insert/Delete in CRUD
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                System.out.println( resultSet.getInt("customerId") +", " + resultSet.getString("firstname") + ", " + resultSet.getString("lastname" ) + ", "
                                                    + resultSet.getString("gender") + ", " + resultSet.getString("Address") + ", " + resultSet.getString("Phone") +
                                                    ", " + resultSet.getString("Email") + ", " + resultSet.getString("City") + ", " + resultSet.getString("Country"));
            }
            System.out.println("=========================================================================================================");
            //2. SELECT records only from the name column in the Customers table.
            final String SELECT_NAME = "SELECT firstname FROM customer";

            ResultSet resultSet1 = statement.executeQuery(SELECT_NAME);
            while (resultSet1.next()){
                System.out.println(resultSet1.getString("firstname"));
            }
            System.out.println("=========================================================================================================");

            //3. Show the name of the Customer whose CustomerID is 1.
            final String SHOW_CUSTOMER = "SELECT first_name FROM customer WHERE customerid = ?";
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.findByCustomerId(4);
            System.out.println(customer.getFirstName() + " " + customer.getLastName());

        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }
}
