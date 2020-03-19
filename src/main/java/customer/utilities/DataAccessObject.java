package customer.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {
    protected  final Connection connection;
    private final  static String LAST_VAL = "SELECT last_value FROM ";
    protected final static String CUSTOMER_SEQUENCE = "umuzi";

    protected DataAccessObject(Connection connection){
        this.connection = connection;
    }

    public abstract T findByCustomerId(int customerId);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(int customerId);

    protected int getLAstValue(String sequence){
        // statement : representational SQL to be executed
        //ResultSet : response from the db in a logical tabular format
        int key = 0;
        String sql = LAST_VAL + sequence;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                key = resultSet.getInt(1);
            }
            return key;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(sql);
        }
    }

}
