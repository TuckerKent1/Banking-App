import java.sql.*;
import java.util.ArrayList;

public class SavingsAccount {
    
    //declaring instance variables
    private int accountNumber;
    private double balance;
    private double interestRate;
    
    //empty constructor 
    private SavingsAccount(){
        
    }
    
    //constructor that accepts all savings account fields
    public SavingsAccount(int accountNumber, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
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
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate the interestRate to set
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    //method to make the database connection easier
    private static Connection getConnection() throws SQLException{
        String url = "jdbc:sqlite:banking.sqlite";
        Connection connection = DriverManager.getConnection(url); 
        return connection;
    }
    
    //method that takes a new savings account and adds it to database
    public static boolean createSavingsAccount(SavingsAccount s) {
        String sql = "INSERT INTO SavingsAccount VALUES (?, ?, ?)";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, s.getAccountNumber());
            ps.setDouble(2, s.getBalance());
            ps.setDouble(3, s.getInterestRate());
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            System.out.println("createSavingsAccount()" + e.toString());
        }
        return false;
    }
    
    //method gets a savings account by account number
    public static SavingsAccount getSavingsAccount(int accountNumber) {
        String sql = "SELECT * FROM SavingsAccount WHERE AccountNumber = ?";
        SavingsAccount s = new SavingsAccount();
        try{
           Connection connection = getConnection();
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setInt(1, accountNumber);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               double balance = rs.getDouble("Balance");
               double interestRate = rs.getDouble("InterestRate");
               
               //s = new SavingsAccount(accountNumber, balance, interestRate);
               s.setAccountNumber(accountNumber);
               s.setBalance(balance);
               s.setInterestRate(interestRate); 
           }
        } catch(SQLException e){
            System.err.println("Error occurred during getSavingsAccount()" + e.toString());
            return null;
        }
        return s;
    }
    
    //method gets the next savings account number -- gets all savings account numbers and returns one larger than the largest 
    public static int getNextAccountNumber(){
        int largestAccount = 0;
        ArrayList<SavingsAccount> accountList = new ArrayList();
        String sql = "SELECT AccountNumber FROM SavingsAccount ORDER BY AccountNumber DESC";
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
    
    //this method updates an account balance -- used for deposits and withdrawals
    public static boolean updateBalance(Customer c, double balance){
        String sql = "UPDATE SavingsAccount SET"
                + "     Balance = ?"
                + " WHERE AccountNumber = ?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setInt(2, c.getAccountNumber());
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            System.err.println("updateBalance()" + e.toString());
        }
        return false;
    }    
    
    //this method updates interest rates in the database -- used in customer update
    public static boolean updateInterestRate(Customer c, double interestRate){
         String sql = "UPDATE SavingsAccount SET"
                + "     InterestRate = ?"
                + " WHERE AccountNumber = ?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, interestRate);
            ps.setInt(2, c.getAccountNumber());
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            System.err.println("updateInterestRate()" + e.toString());
        }
        return false;
    }
}
