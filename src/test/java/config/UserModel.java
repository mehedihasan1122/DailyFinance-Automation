package config;

public class UserModel {
    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserModel(String firstname,String lastname,String email,String password,String phoneNumber, String address){
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.address=address;

    }

    public UserModel(){

    }
}
