package vendor.management.model;

// User class representing a user entity in the application
public class User {
    protected int id;
    protected String fname;
    protected String lname;
    protected String street;
    protected String address;
    protected String city;
    protected String state;
    protected String email;
    protected long phone;
    protected String password;

    // Default constructor
    public User() {}

    // Constructor with parameters for creating a user with specified attributes
    public User(int id, String fname, String lname, String street, String address, String city, String state, String email, long phone) {
        super();
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }

    // Constructor with parameters for creating a user with password
    public User(String fname, String lname, String street, String address, String city, String state, String email, long phone, String password) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Constructor with parameters for creating a user without password
    public User(String fname, String lname, String street, String address, String city, String state, String email, long phone) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }

    // Getter method for retrieving user ID
    public int getId() {
        return id;
    }

    // Getter method for retrieving user first name
    public String getFname() {
        return fname;
    }

    // Setter method for setting user first name
    public void setFname(String fname) {
        this.fname = fname;
    }

    // Getter method for retrieving user last name
    public String getLname() {
        return lname;
    }

    // Setter method for setting user last name
    public void setLname(String lname) {
        this.lname = lname;
    }

    // Getter method for retrieving user street
    public String getStreet() {
        return street;
    }

    // Setter method for setting user street
    public void setStreet(String street) {
        this.street = street;
    }

    // Getter method for retrieving user address
    public String getAddress() {
        return address;
    }

    // Setter method for setting user address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for retrieving user city
    public String getCity() {
        return city;
    }

    // Setter method for setting user city
    public void setCity(String city) {
        this.city = city;
    }

    // Getter method for retrieving user state
    public String getState() {
        return state;
    }

    // Setter method for setting user state
    public void setState(String state) {
        this.state = state;
    }

    // Getter method for retrieving user email
    public String getEmail() {
        return email;
    }

    // Setter method for setting user email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for retrieving user phone number
    public long getPhone() {
        return phone;
    }

    // Setter method for setting user phone number
    public void setPhone(long phone) {
        this.phone = phone;
    }

    // Getter method for retrieving user password
    public String getPassword() {
        return password;
    }
}
