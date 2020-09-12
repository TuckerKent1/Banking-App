
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tucker Kent
 */
public class Customer {
    
    //instance variable declared
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int accountNumber;
    private String customerNotes;
    
    //empty constructor
    public Customer(){
        
    }
    
    //constructor to set all fields
    public Customer(String firstName, String lastName, String address, String phoneNumber, int accountNumber, String customerNotes){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        if(customerNotes == null){
            this.customerNotes = " -- No notes specified -- ";
        } else {
        this.customerNotes = customerNotes;
        }
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return the customerNotes
     */
    public String getCustomerNotes() {
        return customerNotes;
    }

    /**
     * @param customerNotes the customerNotes to set
     */
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }
    
    //method to connect to the database easier
    private static Connection getConnection() throws SQLException{
        String url = "jdbc:sqlite:banking.sqlite";
        Connection connection = DriverManager.getConnection(url); 
        return connection;
    }
    
    //method gets and returns all customers from the database in an arraylist
    public static ArrayList<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customer ORDER BY 'Last Name' ASC";
        ArrayList<Customer> customerList = new ArrayList();
        try(Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){            
            while(rs.next()){
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                int accountNumber = rs.getInt("AccountNumber");
                String customerNotes = rs.getString("Notes");
                
                Customer c = new Customer(firstName, lastName, address, phoneNumber, accountNumber, customerNotes);
                customerList.add(c);  
            }
        } catch (SQLException e) {
            //update 
            System.err.println("error during getAllCustomers()" + e.toString());
        }
        return customerList;
    }
    
    //method gets customer by first and last name -- returns an arraylist in case there are multiple matches
    public static ArrayList<Customer> getCustomerListByName(String first, String last){
        String sql = "SELECT * FROM Customer WHERE FirstName = ? AND LastName = ?";
        ArrayList<Customer> customerList = new ArrayList();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                int accountNumber = rs.getInt("AccountNumber");
                String customerNotes = rs.getString("Notes");
                
                Customer c = new Customer(firstName, lastName, address, phoneNumber, accountNumber, customerNotes);
                customerList.add(c); 
            }
        } catch(SQLException e){
            //update
            System.err.println("error during getCustomerByName()" + e.toString());
            return null;
        }
        return customerList;
    }
    
    //method gets customer by first and last name and returns the customer
    public static Customer getCustomerByName(String first, String last, int account){
        String sql = "SELECT * FROM Customer WHERE FirstName = ? AND LastName = ? AND AccountNumber = ?";
        Customer c = null;
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setInt(3, account);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                int accountNumber = rs.getInt("AccountNumber");
                String customerNotes = rs.getString("Notes");
                
                c = new Customer(firstName, lastName, address, phoneNumber, accountNumber, customerNotes); 
            }
        } catch(SQLException e){
            //update
            System.err.println("error during getCustomerByName()" + e.toString());
            return null;
        }
        return c;
    }
    
    //method creates a new customer and adds it to the database
    public static boolean createCustomer(Customer c){
        String first = c.getFirstName();
        String last = c.getLastName();
        String address = c.getAddress();
        String phone = c.getPhoneNumber();
        int account = c.getAccountNumber();
        String notes = c.getCustomerNotes();
        
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account);
            ps.setString(2, first);
            ps.setString(3, last);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, notes);
            ps.executeUpdate();
            return true;
        } catch(SQLException s){
            System.err.println("Error occurred during customer creation" + s.toString());
        }
        
        return false;
    }
    
    //method updates a customer account number -- used for savings account creation 
    public static boolean updateAccountNumber(Customer c){
        String sql = "UPDATE Customer SET"
                   + "   AccountNumber = ?"
                   + "WHERE FirstName = ? AND LastName = ?";
        try{
           Connection connection = getConnection();
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setInt(1, c.getAccountNumber());
           ps.setString(2, c.getFirstName());
           ps.setString(3, c.getLastName());
           ps.executeUpdate();
           return true;
        }catch (SQLException e){
            System.err.println("updateAccountNumber()" + e.toString());
        }
        return false;
    }
    
    //method updates all customer information that is in the customer table -- except for account number
    public static boolean updateCustomer(Customer c, String first, String last, String address, String phone, String notes){
        String sql = "UPDATE Customer SET "
                + "     FirstName = ?,"
                + "     LastName = ?,"
                + "     Address = ?,"
                + "     PhoneNumber = ?,"
                + "     Notes = ?"
                + "     WHERE FirstName = ? AND LastName = ? AND AccountNumber = ?";
        String currentFirst = c.getFirstName();
        String currentLast = c.getLastName();
        int accountNum = c.getAccountNumber();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, notes);
            ps.setString(6, currentFirst);
            ps.setString(7, currentLast);
            ps.setInt(8, accountNum);
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            System.err.println("updateCustomer()" + e.toString());
        }
        return false;
    }
    
    //method gets all customer account numbers and returns an integer one larger than the largest -- this method is used to get a correct and unused account number
    public static int getNextAccountNumber(){
        int largestAccount = 0;
        String sql = "SELECT AccountNumber FROM Customer ORDER BY AccountNumber ASC";
        try(Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int accountNum = rs.getInt("AccountNumber");
                if(accountNum > largestAccount){
                    largestAccount = accountNum;
                }
            }
        }catch(SQLException s){
            System.err.println("Error occurred during getNextAccountNumber()" + s.toString());
        }
        return (largestAccount + 1);
    }   
}
