import java.util.ArrayList;

public class User {

// User authentication
    private String username;
    private String password;

// User media information
    private ArrayList<String> seenMedie = new ArrayList<String>();
    private ArrayList<String> wantsToSee = new ArrayList<String>();

//Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and setter on
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public ArrayList<String> getWantsToSee() {return wantsToSee;}
    public void setWantsToSee(ArrayList<String> wantsToSee) {this.wantsToSee = wantsToSee;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public ArrayList<String> getSeenMedie() {return seenMedie;}
    public void setSeenMedie(ArrayList<String> seenMedie) {this.seenMedie = seenMedie;}
}
