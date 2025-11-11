import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuCategory {
    private final List<Media> mediaLibrary;

    public MainMenuCategory(List<Media> mediaLibrary) {
        this.mediaLibrary = mediaLibrary;
    }
    
    public List<Media> searchByCategory(String category) {
        if (category == null || category.isBlank()) {
            return List.of();
        }

        return mediaLibrary.stream()
                .filter(m -> m.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
