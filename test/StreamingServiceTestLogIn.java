import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StreamingServiceTestLogIn {

    @Test
    public void testCreateNewUser() {
        StreamingService service = new StreamingService();

        System.out.println("Please follow the prompts to create a new user:");

        // This will call your TextUI and pause for manual input
        service.createNewUser();

        // After creating a user, you can check if users list has a new entry
        assertEquals(1, service.getUsers().size());

        // You can also print the username to verify
        System.out.println("Created username: " + service.getUsers().get(0).getUsername());
    }

    @Test
    public void testLogIn() {
        StreamingService service = new StreamingService();

        // Add a user manually so we can log in
        service.getUsers().add(new User("alice", "password123"));

        System.out.println("Please log in with username: alice and password: password123");

        // This will pause for manual input
        User loggedInUser = service.logIn();

        assertNotNull(loggedInUser);
        assertEquals("alice", loggedInUser.getUsername());
    }
}