public class User {
    private  String user;
    private  String password;



    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public User withUser(String user) {
        this.user = user;
        return  this;
    }

    public User withPassword(String password) {
        this.password = password;
        return  this;
    }
}
