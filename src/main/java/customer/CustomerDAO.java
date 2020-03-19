package customer;
import customer.utilities.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {



    //4. UPDATE the record for CustomerID = 1 on the Customer table so that the name is “Lerato Mabitso”.
    private static final String UPDATE_USER = "UPDATE customer SET firstname = ?, lastname = ? WHERE customerid = ? ";
    //5. DELETE the record from the Customers table for customer 2 (CustomerID = 2)
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerid = ?";

    // should go to a ordersDAO
    //6. Select all unique statuses from the Orders table and get a count of the number of orders for each unique status.
    private static final String SELECT_UNIQUE = "SELECT COUNT(DISTINCT status) FROM orders ";

    // should go to a paymentsDAO
    //7. Return the MAXIMUM payment made on the PAYMENTS table.
    private static final String SELECT_MAX = "SELECT MAX(amount) FROM payments";






    private static final  String DELETE = " DELETE FROM customer WHERE customerid =?";
    //add customer to the database
    private static final  String INSERT = "INSERT INTO customer(firstname,lastname, email,phone,address,city, state, zipcode)" +
            "VALUES(?,?,?,?,?,?,?,?)";
    // update database
    private static final String UPDATE = "UPDATE customer SET first_name = ? , last_name = ? , gender = ? , address = ?, phone = ?, email = ?, city = ? , country = ?" +
            "WHERE customer_id = ?";
    //read customer details
    private static final String GET_ONE = "SELECT customerid, firstname, lastname, gender, address, phone, email, city, country FROM customer WHERE customerid=?";

    CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        Customer customer = new Customer();
        // prepared statement : ext of a statement used to precompile statements with inputs
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)){
            statement.setLong(1,customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                customer.setCustomerId(resultSet.getInt("customerid"));
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));
                customer.setGender(resultSet.getString("gender"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCity(resultSet.getString("city"));
                customer.setCountry(resultSet.getString("country"));
            }
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        Customer customer;
        try(PreparedStatement statement= this.connection.prepareStatement(INSERT)){
            statement.setString(1,dto.getFirstName());
            statement.setString(2,dto.getLastName());
            statement.setString(3, dto.getGender());
            statement.setString(4,dto.getAddress());
            statement.setString(5, dto.getPhone());
            statement.setString(6, dto.getEmail());
            statement.setString(7, dto.getCity());
            statement.setString(8, dto.getCountry());
            statement.setInt(9, dto.getCustomerId());

            statement.execute();
            customer = this.findByCustomerId(dto.getCustomerId());
        }catch (SQLException sql){
            sql.printStackTrace();
            throw new RuntimeException(sql);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto) {
        try(PreparedStatement statement= this.connection.prepareStatement(INSERT)){
            statement.setString(1, dto.getFirstName());
            statement.setString(2,dto.getLastName());
            statement.setString(3,dto.getGender());
            statement.setString(4,dto.getAddress());
            statement.setString(5, dto.getPhone());
            statement.setString(6, dto.getEmail());
            statement.setString(7,dto.getCity());
            statement.setString(8, dto.getCountry());

            int customerId = this.getLAstValue(CUSTOMER_SEQUENCE);
            statement.execute();
            return this.findByCustomerId(customerId);
        }catch (SQLException sql){
            sql.printStackTrace();
            throw new RuntimeException(sql);
        }
    }
    @Override
    public void delete(int customerId) {
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE)){
            statement.setInt(1,customerId);
            statement.execute();
        }catch (SQLException sql){
            sql.printStackTrace();
            throw new RuntimeException(sql);
        }
    }
}
