import java.awt.*;
import java.math.RoundingMode;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import java.text.NumberFormat;

public class BankingApp extends JFrame{
    
    //declaring class variables
    private JPanel mainPanel;
    private JPanel createAccountPanel;
    private JPanel customerPanel;
    private JPanel navPanel;
    private JPanel rightPanel;
    private JPanel createButtonPanel;
    private JPanel savingsPanel;
    private JPanel savingsButtonPanel;
    
    private JTextField firstField;
    private JTextField lastField;
    private JLabel firstName2;
    private JLabel lastName2;
    private JLabel address2;
    private JLabel phoneNumber2;
    private JLabel accountNumber2;
    private JLabel accountBalance2;
    private JLabel interestRate2;
    private JLabel customerNotes2;     
    private JTextField firstField2;
    private JTextField lastField2;
    private JTextField addressField2;
    private JTextField phoneField2;
    private JTextField accountNumberField2;
    private JTextField accountBalanceField2;
    private JTextField interestRateField2;
    private JTextArea customerNotesField2;
    private JLabel accountNumber3;
    private JLabel interestRate3;
    private JTextField accountNumberField3;
    private JTextField interestRateField3;
    
    //constructor that initializes initComponents()
    public BankingApp() {
        initComponents();
    }
    
    //initComponents called via constructor of BankingApp
    private void initComponents() {
        setTitle("KNB Customer Management App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        //attempting to set the window style to the system default
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        //adding Banner and Label to the top(NORTH) section of JFrame
        JPanel bannerPanel = new JPanel();
        JLabel bannerLabel = new JLabel("Kent National Bank");
        bannerLabel.setFont(new Font("Sans Serif", Font.BOLD, 30)); //large, bold, serif font -- for effect
        bannerPanel.setBackground(Color.GRAY);
        bannerPanel.add(bannerLabel);
        add(bannerPanel, BorderLayout.NORTH);
        
        //creating label for main page
        JLabel displayLabel = new JLabel("Customer Lookup");
        displayLabel.setFont(new Font("Sans Serif",Font.PLAIN, 18));

        //creating main panel and its components -- this is the center of the front of the application
        mainPanel = new JPanel();
        JLabel firstName = new JLabel("First Name:");
        JLabel lastName = new JLabel("Last Name:");
        firstField = new JTextField(12);
        lastField = new JTextField(12);
        JButton searchButton = new JButton("Search Customer");
        JButton listButton = new JButton("View All Customers");
        
        //dimension created and preferred and min size set for the two labels
        Dimension d = new Dimension(75, 10);
        firstName.setPreferredSize(d);
        firstName.setMinimumSize(d);
        lastName.setPreferredSize(d);
        lastName.setMinimumSize(d);
        
        //begin front page layout  forcenter area -- layout contains first/last name search and view all search
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 25, 5);
        
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 2;
        mainPanel.add(displayLabel, c);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 1;
        mainPanel.add(firstName, c);
        
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(firstField, c);
        
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0; c.gridy = 2;
        mainPanel.add(lastName, c);
        
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1; c.gridy = 2;
        mainPanel.add(lastField, c);
        
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 30, 10, 2);
        c.gridx = 0; c.gridy = 3;
        mainPanel.add(searchButton, c);
        
        c.insets = new Insets(10, 2, 10, 30);
        c.gridx = 1; c.gridy = 3;
        mainPanel.add(listButton, c);
        // -- end front page layout --
        //adding mainPanel (front center page)
        add(mainPanel, BorderLayout.CENTER);
        
        //Creating panel for the customer information interface
        customerPanel = new JPanel();
        
        //initializing labels
        firstName2 = new JLabel("First Name:");
        lastName2 = new JLabel("Last Name:");
        address2 = new JLabel("Address:");
        phoneNumber2 = new JLabel("Phone Number:");
        accountNumber2 = new JLabel("Account Number:");
        accountBalance2 = new JLabel("Balance:");
        interestRate2 = new JLabel("Interest Rate:");
        customerNotes2 = new JLabel("Notes:");
        //initializing fields
        firstField2 = new JTextField(22);
        lastField2 = new JTextField(22);
        addressField2 = new JTextField(22);
        phoneField2 = new JTextField(22);
        accountNumberField2 = new JTextField(22);
        accountBalanceField2 = new JTextField(22);
        interestRateField2 = new JTextField(22);
        customerNotesField2 = new JTextArea(3, 22);
        
        //setting the account balance field to be non-editable -- and setting notes text area to enable text wrap while keeping whole word integrity
        accountBalanceField2.setEditable(false);
        customerNotesField2.setLineWrap(true);
        customerNotesField2.setWrapStyleWord(true);
        customerNotesField2.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
       
        //begin customer information layout
        customerPanel.setLayout(new GridBagLayout());
        GridBagConstraints cc = new GridBagConstraints();
        cc.anchor = GridBagConstraints.LINE_START;
        cc.insets = new Insets(5, 5, 5, 5);
        
        cc.gridx = 0; cc.gridy = 0;
        customerPanel.add(firstName2, cc);
        
        cc.gridx = 1; cc.gridy = 0;
        customerPanel.add(firstField2, cc);
        
        cc.gridx = 0; cc.gridy = 1;
        customerPanel.add(lastName2, cc);
        
        cc.gridx = 1; cc.gridy = 1;
        customerPanel.add(lastField2, cc);
        
        cc.gridx = 0; cc.gridy = 2;
        customerPanel.add(address2, cc);
        
        cc.gridx = 1; cc.gridy = 2;
        customerPanel.add(addressField2, cc);
        
        cc.gridx = 0; cc.gridy = 3;
        customerPanel.add(phoneNumber2, cc);
        
        cc.gridx = 1; cc.gridy = 3;
        customerPanel.add(phoneField2, cc);
        
        cc.gridx = 0; cc.gridy = 4;
        customerPanel.add(accountNumber2, cc);
        
        cc.gridx = 1; cc.gridy = 4;
        customerPanel.add(accountNumberField2, cc);
        
        cc.insets = new Insets(5, 25, 5, 5);
        cc.gridx = 0; cc.gridy = 5;
        customerPanel.add(accountBalance2, cc);
        
        cc.insets = new Insets(5, 5, 5, 5);
        cc.gridx = 1; cc.gridy = 5;
        customerPanel.add(accountBalanceField2, cc);
        
        cc.insets = new Insets(5, 25, 5, 5);
        cc.gridx = 0; cc.gridy = 6;
        customerPanel.add(interestRate2, cc);
        
        cc.insets = new Insets(5, 5, 5, 5);
        cc.gridx = 1; cc.gridy = 6;
        customerPanel.add(interestRateField2, cc);
        
        cc.gridx = 0; cc.gridy = 7;
        customerPanel.add(customerNotes2, cc);
        
        cc.insets = new Insets(38, 5, 20, 5);
        cc.gridx = 1; cc.gridy = 7;
        customerPanel.add(customerNotesField2, cc);
        //end customer information layout
        
        //begin layout for customer/savings account buttons
        createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton newAccount = new JButton("New Savings Account");
        JButton newCustomer = new JButton("New Customer");
        createAccountPanel.add(newCustomer);
        createAccountPanel.add(newAccount);
        
        //setting titled border on account creation panel
        createAccountPanel.setBorder(BorderFactory.createTitledBorder("Or create a new account:"));
        
        //adding account creation panel to JFrame
        add(createAccountPanel, BorderLayout.SOUTH);
        
        navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        navPanel.add(backButton);
        
        //begin layout for account creation button panel
        createButtonPanel = new JPanel();
        JButton createButton = new JButton("Create Account");
        JButton cancelButton = new JButton("Cancel");
        
        createButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        createButtonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        createButtonPanel.add(createButton);
        createButtonPanel.add(cancelButton);
        //end layout for account creation button panel
        
        //begin layout for savings account info panel
        accountNumber3 = new JLabel("Account Number:");
        accountNumberField3 = new JTextField(18);
        interestRate3 = new JLabel("Interest Rate:");
        interestRateField3 = new JTextField(18);
        
        savingsPanel = new JPanel();
        savingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints cx = new GridBagConstraints();
        cx.anchor = GridBagConstraints.LINE_START;
        cx.insets = new Insets(5, 5, 5, 5);
        
        cx.gridx = 0; cx.gridy = 0;
        savingsPanel.add(accountNumber3, cx);
        
        cx.gridx = 1; cx.gridy = 0;
        savingsPanel.add(accountNumberField3, cx);
        
        cx.gridx = 0; cx.gridy = 1;
        savingsPanel.add(interestRate3, cx);
        
        cx.gridx = 1; cx.gridy = 1;
        savingsPanel.add(interestRateField3, cx);
        //end savings panel layout
        
        //begin panel layou for savings account creation buttons
        savingsButtonPanel = new JPanel();
        savingsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton createSavings = new JButton("Create Savings Account");
        JButton savingsBackButton = new JButton("Back");
        savingsButtonPanel.add(createSavings);
        savingsButtonPanel.add(savingsBackButton);
        //end panel layout for savings account creation buttons
        
        //begin layout for right panel buttons -- update, interest, deposit, and withdrawal
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 0));
        JButton updateButton = new JButton("Update Current Customer");
        JButton depositButton = new JButton("Make a Deposit");
        JButton withdrawalButton = new JButton("Make a Withdrawal");
        JButton interestButton = new JButton("Calculate Interest");
        rightPanel.add(updateButton);
        rightPanel.add(depositButton);
        rightPanel.add(withdrawalButton);
        rightPanel.add(interestButton);
        //emd rightPanel layout
        
        //event listener added for the back button - sets up layout and adds and removes correct panels 
        backButton.addActionListener(event -> {
            remove(customerPanel);
            remove(createButtonPanel);
            remove(savingsPanel);
            remove(savingsButtonPanel);
            remove(rightPanel);
            add(mainPanel, BorderLayout.CENTER);
            add(createAccountPanel, BorderLayout.SOUTH);
            clearFields();
            pack();
        
        });
        
        //event listener added for the back button on the savings account creation page -- sets up layout and adds and removes correct panels 
        savingsBackButton.addActionListener(event -> {
            remove(savingsPanel);
            remove(savingsButtonPanel);
            add(mainPanel, BorderLayout.CENTER);
            add(createAccountPanel, BorderLayout.SOUTH);
            pack();
        });
        
        //event listener added for the cancel button on the create customer account panel -- sets up layout and adds and removes correct panels 
        cancelButton.addActionListener(event -> {
            remove(customerPanel);
            remove(createButtonPanel);
            add(mainPanel, BorderLayout.CENTER);
            add(createAccountPanel, BorderLayout.SOUTH);
            pack();
        });
        
        //event listener added for the update account button on the rightPanel
        updateButton.addActionListener(event -> {
            
            //formats input and attempts to update
            formatInputCustomer();
            
            //this try catch is for if the user changed the name -- then it will fail to update
            try{
            Customer custToUpdate = Customer.getCustomerByName(firstField2.getText(), lastField2.getText(), Integer.parseInt(accountNumberField2.getText()));
            updateCustomerAccount(custToUpdate);
            } catch(Exception e){
                String message = "First or Last name cannot be edited. To change name, an account must be created.";
                JOptionPane.showMessageDialog(this, message, "Attention!", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        //event listener added for the deposit button on the rightPanel
        depositButton.addActionListener(event -> {
            Customer custToDeposit = Customer.getCustomerByName(firstField2.getText(), lastField2.getText(), Integer.parseInt(accountNumberField2.getText()));
            makeDeposit(custToDeposit);
        });
        
        //event listener added for the withdrawal button on the rightPanel
        withdrawalButton.addActionListener(event -> {
            Customer custToWithdraw = Customer.getCustomerByName(firstField2.getText(), lastField2.getText(), Integer.parseInt(accountNumberField2.getText()));
            makeWithdrawal(custToWithdraw);
        });
        
        //event listener added for the calculate interest button on the rightPanel
        interestButton.addActionListener(event -> {
            Customer custToCalc = Customer.getCustomerByName(firstField2.getText(), lastField2.getText(), Integer.parseInt(accountNumberField2.getText()));
            calculateInterest(custToCalc);
        });
        
        //adding event handler for searchbutton -- this searches for customers based on first and last name
        searchButton.addActionListener(event -> {
            formatInputMain();
            searchButtonClicked();
        });
        
        //event handler for view all customers button -- searches based on all Customers returned from database
        listButton.addActionListener(event -> {
            listButtonClicked();  
        });      
        
        //event handler added for the create customer button on main panel
        newCustomer.addActionListener(event -> {
            //setting up JFrame layout
            //setting panel visibilty to show new screen
            remove(mainPanel);
            remove(createAccountPanel);
            remove(navPanel);
            add(customerPanel, BorderLayout.CENTER);
            add(createButtonPanel, BorderLayout.SOUTH);
            pack();
            clearFields();
        });
        
        //event listener added for the create savings account button on main panel
        newAccount.addActionListener(event -> { 
            remove(mainPanel);
            remove(createAccountPanel);
            add(savingsPanel, BorderLayout.CENTER);
            add(savingsButtonPanel, BorderLayout.SOUTH);
            pack();
            clearFields();
            String popUp = "NOTE -- By default an account number is set to Customer account number.\n"
                    + "Specify the account number to add Customer to an existing account";
            JOptionPane.showMessageDialog(this, popUp, "Account Number", JOptionPane.INFORMATION_MESSAGE);
        });
        
        //event listener added for the create account button on the customer panel
        createButton.addActionListener(event -> {
            formatInputCustomer();
            createButtonClicked();    
        });
        
        //event listener added for the create account button on the savings account panel
        createSavings.addActionListener(event -> {
            createSavingsClicked();
        });
        
        //packing app to set effective size of window and setting to visible
        pack();
        setVisible(true);
    }
    
    
    
    
    
    //main method calling run and creating the BankingApp instance
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BankingApp();
            }
        });
    }
    
    //method to check if an integer was entered into textfield -- supplied with a string
    public static boolean checkInt(String input){
        try{
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    
    //method to check if the passed string input contains a parsable double
    public static boolean checkDouble(String input){
        try{
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    
    //method that checks if any fields were left blank -- returns a string with formatted errors for all necessary fields
    public String checkInputFields(){
        String errorMessage = "";
        String errorString = "";
        if(firstField2.getText().equals("")){
            errorString += "First name is a required field.\n";
        }
        if(lastField2.getText().equals("")){
            errorString += "Last name is a required field.\n";
        }
        if(addressField2.getText().equals("")){
            errorString += "Address is a required field.\n";
        }
        if(phoneField2.getText().equals("")){
            errorString += "Phone number is a required field.";
        }
        if(errorString.equals("")){
            return errorMessage;
        } else {
            errorMessage = "The following errors were found:\n";
            errorMessage += errorString;
        }
        return errorMessage;
    }
    
    //method that compares a string value to all of the values in a returned ArrayList -- used this for comparing multiple possible selections in a JOptionPane
    public Customer checkListChoice(ArrayList<Customer> customerList, String choice) {
        for(Customer customer : customerList){
            String completeName = customer.getAccountNumber() + " " + customer.getFirstName() + " " + customer.getLastName();
            if(completeName.equalsIgnoreCase(choice)){
                return customer;
            }
        }
        return null;
    }
    
    //methed that receives customer object and gets savings account info to fill in the form fields of the customer panel
    public void fillCustomerInfo(Customer c) {
        //getting customer specific savings account
        SavingsAccount s = SavingsAccount.getSavingsAccount(c.getAccountNumber());
        
        //setting up number formats
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(3);
        percent.setMinimumFractionDigits(3);
        percent.setRoundingMode(RoundingMode.UNNECESSARY);
        
        //filling out form data with customer passed to method -- and the savings account data
        firstField2.setText(c.getFirstName());
        lastField2.setText(c.getLastName());
        addressField2.setText(c.getAddress());
        phoneField2.setText(c.getPhoneNumber());
        accountNumberField2.setText(String.valueOf(c.getAccountNumber()));
        accountBalanceField2.setText(String.valueOf(currency.format(s.getBalance())));
        interestRateField2.setText(String.valueOf(percent.format((s.getInterestRate())/100)));
        customerNotesField2.setText(c.getCustomerNotes());
    }
    
    //method that resets all of the textfields of the customer panel
    public void clearFields() {
        firstField.setText("");
        lastField.setText("");
        firstField2.setText("");
        lastField2.setText("");
        addressField2.setText("");
        phoneField2.setText("");
        accountNumberField2.setText("");
        accountBalanceField2.setText("");
        interestRateField2.setText("");
        customerNotesField2.setText("");
    }
    
    //method checks if the customer entered matches any customers in the database
    public Customer checkIfCustomerExists(String first, String last){
        ArrayList<Customer> customerList = Customer.getAllCustomers();
        first = first.trim();
        last = last.trim();
        String completeName = first + " " + last;
        for(Customer customer : customerList){
            String customerName = customer.getFirstName() + " " + customer.getLastName();
            if(completeName.equalsIgnoreCase(customerName)){
                return customer;
            }
        }
        return null;
    }
    
    //this method populates a string array with customer info to be used with a JoptionPane
    public String[] populateCustArray(){
        ArrayList<Customer> customerList = new ArrayList();
        customerList = Customer.getAllCustomers();
        String[] customersRetrieved = new String[customerList.size()];
        int counter = 0;
        for(Customer customer : customerList){
            customersRetrieved[counter] = customer.getAccountNumber() + " " + customer.getFirstName() + " " + customer.getLastName();
            counter ++;
        }
        return customersRetrieved;
    }
    
    //method that formats the first and last name entered in the customer search fields
    public void formatInputMain(){
        String first = firstField.getText();
        first = first.trim();
        String firstLetter = first.substring(0, 1).toUpperCase();
        String remaining = first.substring(1).toLowerCase();
        first = firstLetter + remaining;
        firstField.setText(first);
        
        String last = lastField.getText();
        last = last.trim();
        String firstLetter2 = last.substring(0, 1).toUpperCase();
        String remaining2 = last.substring(1).toLowerCase();
        last = firstLetter2 + remaining2;
        lastField.setText(last);
    }
    
    //method formats the first and last name of the customer panel -- this works for both format and account creation format
    public void formatInputCustomer(){
        String first = firstField2.getText();
        first = first.trim();
        String firstLetter = first.substring(0, 1).toUpperCase();
        String remaining = first.substring(1).toLowerCase();
        first = firstLetter + remaining;
        firstField2.setText(first);
        
        String last = lastField2.getText();
        last = last.trim();
        String firstLetter2 = last.substring(0, 1).toUpperCase();
        String remaining2 = last.substring(1).toLowerCase();
        last = firstLetter2 + remaining2;
        lastField2.setText(last);
    }
    
    //the search customer button invokes this method -- pulls the entered and preformatted names from the Customer database
    public void searchButtonClicked(){
        String first = firstField.getText();
            String last = lastField.getText();
            ArrayList<Customer> customerList = new ArrayList(); //in the case that there are multiple first and last name matches
            Customer custChoice = null;
            if(firstField.getText().equals("") || lastField.getText().equals("")){ //if first and last name were blank
                String errorString = "First and last name fields must both be filled out!";
                JOptionPane.showMessageDialog(this, errorString, "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                customerList = Customer.getCustomerListByName(first, last); //gets customers from database that match the name inputs
                if(customerList.isEmpty()){ //if empty arraylist is returned
                    String emptyMessage = "Sorry, no customers were found by that name.";
                    JOptionPane.showMessageDialog(this, emptyMessage, "Customer Not Found", JOptionPane.PLAIN_MESSAGE);
                } else if(customerList.size() > 1) { //if more than one customer is returned opens a selection dialog
                    String[] customersRetrieved = populateCustArray();
                    String choice = (String) JOptionPane.showInputDialog(this, "Select customer:", "Customer List", JOptionPane.PLAIN_MESSAGE, null, customersRetrieved, customersRetrieved[0]);
                    custChoice = checkListChoice(customerList, choice);
                    remove(mainPanel);
                    remove(createAccountPanel);
                    add(customerPanel, BorderLayout.CENTER);
                    add(navPanel, BorderLayout.SOUTH);
                    add(rightPanel, BorderLayout.EAST);
                    fillCustomerInfo(custChoice);
                    pack();
                } else { //if successful 
                    remove(mainPanel);
                    remove(createAccountPanel);
                    add(customerPanel, BorderLayout.CENTER);
                    add(navPanel, BorderLayout.SOUTH);
                    add(rightPanel, BorderLayout.EAST);
                    Customer custRetrieved = customerList.get(0);
                    fillCustomerInfo(custRetrieved);
                    pack();
                }              
            }
    }
    
    //if the user chooses to view a list of all customers
    public void listButtonClicked(){
        ArrayList<Customer> customerList = new ArrayList();
            customerList = Customer.getAllCustomers();
            String[] customersRetrieved = populateCustArray();
            
            //user chooses the name then it is compared to all customers to get the correct information from database
            String choice = (String) JOptionPane.showInputDialog(this, "Select customer:", "Customer List", JOptionPane.PLAIN_MESSAGE, null, customersRetrieved, customersRetrieved[0]);
            Customer custChoice = checkListChoice(customerList, choice);
            if(checkListChoice(customerList, choice) != null){
                remove(mainPanel);
                remove(createAccountPanel);
                add(customerPanel, BorderLayout.CENTER);
                add(navPanel, BorderLayout.SOUTH);
                add(rightPanel, BorderLayout.EAST);
                fillCustomerInfo(custChoice);
                pack();
            } 
    }
    
    //when the create account button is clicked
    public void createButtonClicked(){
        String errorCheck = checkInputFields(); //checks for empty fields
            boolean boolCheck = false; //boolean flag variable for errors
            if(!errorCheck.equals("")){ //if the errorCheck string contains anything error is thrown
                JOptionPane.showMessageDialog(this, errorCheck, "Insufficient Data", JOptionPane.ERROR_MESSAGE);
            } else { //if there are no string errors
                Customer checkCust = checkIfCustomerExists(firstField2.getText(), lastField2.getText());
                if(checkCust != null){ //if checkIfCustomerExists method returns a customer -- fills out information to a JOptionPane
                    String customerInfoMessage = "Customer found in database:\n"
                            + checkCust.getFirstName() + " " + checkCust.getLastName() + "\n"
                            + checkCust.getAddress() + "\n" + checkCust.getPhoneNumber() + "\n"
                            + "This customer may already have an account. To continue with account creation, click yes. Otherwise click no.";
                    int dialogChoice = JOptionPane.showConfirmDialog(this, customerInfoMessage, "Customer Name Found", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(dialogChoice == 0){ //if user chooses to continue --in case another customer has the same name
                        Customer newCust = new Customer(firstField2.getText(),lastField2.getText(), addressField2.getText(), phoneField2.getText(), Customer.getNextAccountNumber(), customerNotesField2.getText());
                        boolCheck = Customer.createCustomer(newCust);
                        clearFields(); 
                        remove(customerPanel);
                        remove(createButtonPanel);
                        add(mainPanel, BorderLayout.CENTER);
                        add(createAccountPanel, BorderLayout.SOUTH);                        
                        pack();
                        
                    } else if (dialogChoice == 1){ //if user chooses to cancel account creation
                        clearFields(); 
                        remove(customerPanel);
                        remove(createButtonPanel);
                        add(mainPanel, BorderLayout.CENTER);
                        add(createAccountPanel, BorderLayout.SOUTH);                        
                        pack();
                        boolCheck = true;
                    }
                } else { //if there were no matching account names
                    Customer newCust = new Customer(firstField2.getText(),lastField2.getText(), addressField2.getText(), phoneField2.getText(), Customer.getNextAccountNumber(), customerNotesField2.getText());
                        boolCheck = Customer.createCustomer(newCust);
                        String dialogString = newCust.getFirstName() + " " + newCust.getLastName() + " was added successfully";
                        JOptionPane.showMessageDialog(this, dialogString, "Customer Added!", JOptionPane.PLAIN_MESSAGE);
                        clearFields(); 
                        remove(customerPanel);
                        remove(createButtonPanel);
                        add(mainPanel, BorderLayout.CENTER);
                        add(createAccountPanel, BorderLayout.SOUTH);                        
                        pack();                       
                    }               
              }
            if(boolCheck == false) { //if any database operations failed
                    String errorMessage = "Something went wrong while creating customer. If this problem persists, contact your system administrator for help.";
                    JOptionPane.showMessageDialog(this, errorMessage, "Database Issue Occurred", JOptionPane.ERROR_MESSAGE);
                }
    }
    
    //method invoked when creat account button is clicked on the create savings account page
    public void createSavingsClicked(){
        if(interestRateField3.getText().equals("")){ //if interest rate was blank
                String message = "Interest Rate is a required field";
                JOptionPane.showMessageDialog(this, message, "Required Field", JOptionPane.ERROR_MESSAGE);
            }
            boolean checkDouble = checkDouble(interestRateField3.getText());
            if(checkDouble == false){ //if a parsable double value was not entered into interest rate
                String message = "Interest Rate field must contain a number value";
                JOptionPane.showMessageDialog(this, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            }
            ArrayList<Customer> customerList = new ArrayList();
            customerList = Customer.getAllCustomers();
            String[] customersRetrieved = populateCustArray();
            boolean boolCheck = false;
            String choice = (String) JOptionPane.showInputDialog(this, "Select customer:", "Customer List", JOptionPane.PLAIN_MESSAGE, null, customersRetrieved, customersRetrieved[0]);
            Customer custChoice = checkListChoice(customerList, choice);
            
            //if no account number was specified -- a new account number is generated
            if(accountNumberField3.getText().equals("")){
                SavingsAccount s = new SavingsAccount(custChoice.getAccountNumber(), 0, Double.parseDouble(interestRateField3.getText()));
                boolCheck = SavingsAccount.createSavingsAccount(s);
            } else { //if a current account number was entered -- customer account number is updated to the current number in the database
                custChoice.setAccountNumber(Integer.parseInt(accountNumberField3.getText()));
                boolCheck = Customer.updateAccountNumber(custChoice);
            }
            if(boolCheck == false){ //if any database operations failed
                String errorMessage = "Something went wrong while creating this account. If this problem persists, contact your system administrator for help.";
                JOptionPane.showMessageDialog(this, errorMessage, "Database Issue Occurred", JOptionPane.ERROR_MESSAGE);
            } 
            remove(savingsPanel);
            remove(savingsButtonPanel);
            add(mainPanel, BorderLayout.CENTER);
            add(createAccountPanel, BorderLayout.SOUTH);                        
            pack(); 
            clearFields();
    }
    
    //this method is invoked when the update customer button is clicked on the customer info panel
    public void updateCustomerAccount(Customer c){
        String first = firstField2.getText();
        String last = lastField2.getText();
        String address = addressField2.getText();
        String phone = phoneField2.getText();
        String notes = customerNotesField2.getText();
        
        //creates a substring to remove the formatted percent symbol
        String removeSymbol = interestRateField2.getText();
        String removedSymbol = removeSymbol.substring(0, removeSymbol.length() - 1);
        double interest = Double.parseDouble(removedSymbol);
        
        //updating accounts in the database
        boolean boolCheck = Customer.updateCustomer(c, first, last, address, phone, notes);
        boolean boolCheck2 = SavingsAccount.updateInterestRate(c, interest);
        if(boolCheck == false){
            String errorMessage = "Something went wrong while updating this account. If this problem persists, contact your system administrator for help.";
            JOptionPane.showMessageDialog(this, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String successMessage = c.getFirstName() + " " + c.getLastName() + " was successfully updated.";
            JOptionPane.showMessageDialog(this, successMessage, "Database Operation Successful", JOptionPane.PLAIN_MESSAGE);
        }
        
        //updating accounts and displaying updated data in the customer panel
        SavingsAccount s = SavingsAccount.getSavingsAccount(c.getAccountNumber());
        c.setFirstName(first);
        c.setLastName(last);
        c.setAddress(address);
        c.setPhoneNumber(phone);
        c.setCustomerNotes(notes);
        s.setInterestRate(interest);
        
        fillCustomerInfo(c);
    }
    
    //method called if the deposit button is clicked
    public void makeDeposit(Customer c) {
        SavingsAccount s = SavingsAccount.getSavingsAccount(c.getAccountNumber());
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        //gets, calculates, and updates the savings account database with the new balance
        double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount to Deposit:")); //input dialog to get deposit amount
        double oldBalance = s.getBalance();
        double newBalance = oldBalance + amount;
        boolean boolCheck = SavingsAccount.updateBalance(c, newBalance);
        if(boolCheck == false){ //if error occurred
            String errorMessage = "Something went wrong while updating this account. If this problem persists, contact your system administrator for help.";
            JOptionPane.showMessageDialog(this, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
        } else { //if all operations were successful
            String successMessage = currency.format(amount) + " was added to account " + c.getAccountNumber();
            JOptionPane.showMessageDialog(this, successMessage, "Database Operation Successful", JOptionPane.PLAIN_MESSAGE);
        }
        //displays correct and updated info
        fillCustomerInfo(c);
    }
    
    //method invoked when withdrawal button is clicked
    public void makeWithdrawal(Customer c){
        SavingsAccount s = SavingsAccount.getSavingsAccount(c.getAccountNumber());
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        
        double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount to Withdraw:")); //dialog to get user input for withdrawal amount
        double oldBalance = s.getBalance();
        double newBalance = oldBalance - amount;
        boolean boolCheck = SavingsAccount.updateBalance(c, newBalance);
        if(boolCheck == false){ //if error occurred
            String errorMessage = "Something went wrong while updating this account. If this problem persists, contact your system administrator for help.";
            JOptionPane.showMessageDialog(this, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
        } else { //if all operations were successful
            String successMessage = currency.format(amount) + " was withdrawn from account " + c.getAccountNumber();
            JOptionPane.showMessageDialog(this, successMessage, "Database Operation Successful", JOptionPane.PLAIN_MESSAGE);
        }
        //displays correct and updated information
        fillCustomerInfo(c);
    }
    
    //method called when calculate interest button is clicked
    public void calculateInterest(Customer c){
        SavingsAccount s = SavingsAccount.getSavingsAccount(c.getAccountNumber());
        double interestRate = s.getInterestRate();
        
        //setting array with all months of the year for the dialog box
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String monthChosen = (String) JOptionPane.showInputDialog(this, "Select current month:", "Calculate Interest", JOptionPane.PLAIN_MESSAGE, null, months, months[0]);
        int monthIndex = -1;
        for(int i = 0; i < months.length; i++){
            if(monthChosen.equals(months[i])){
                monthIndex = i + 1;
            }
        }
        //calculating the interest
        double balance = s.getBalance();
        double futureValue = balance * (monthIndex * ((interestRate / 100) /12));
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        String message = "The current interest accrued is " + currency.format(futureValue); //formatting and displaying interest accrued in dialog
        JOptionPane.showMessageDialog(this, message, "Calculated Interest", JOptionPane.INFORMATION_MESSAGE);
    }            
}
