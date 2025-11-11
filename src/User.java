import java.util.ArrayList;

public class User {

// User authentication
    private String username;
    private String password;

// User media information
    private ArrayList<Media> seenMedia = new ArrayList<Media>();
    private ArrayList<Media> wantsToSee = new ArrayList<Media>();

//Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and setter on
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public ArrayList<Media> getWantsToSee() {return wantsToSee;}
    public void setWantsToSee(ArrayList<Media> wantsToSee) {this.wantsToSee = wantsToSee;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public ArrayList<Media> getSeenMedie() {return seenMedia;}
    public void setSeenMedie(ArrayList<Media> seenMedie) {this.seenMedia = seenMedie;}
}
