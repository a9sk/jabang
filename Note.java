import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String content;
    private String category;

    public Note(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.dateModified = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.dateModified = new Date();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.dateModified = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    @Override
    public String toString() {
        return title + " (" + category + ")";
    }
}
