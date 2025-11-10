public class MainMenuCategory {
    private List<Content> contentLibrary;

    public MainMenuCategory() {
        this.contentLibrary = new ArrayList<>();
    }

    public List<Content> searchByCategory(String category) {
        List<Content> results = new ArrayList<>();
        for (Content content : contentLibrary) {
            if (content.getCategory().equalsIgnoreCase(category)) {
                results.add(content);
            }
        }
        return results;
    }

    public static class Content {
        private String title;
        private String category;

        public Content(String title, String category) {
            this.title = title;
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public String toString() {
            return String.format("%s [%s]", title, category);
        }
    }
}