import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private List<Note> notes;
    private FileHandler fileHandler;

    public NoteManager() {
        this.fileHandler = new FileHandler("notes.dat");
        this.notes = fileHandler.readNotes();
    }

    public void addNote(Note note) {
        notes.add(note);
        fileHandler.writeNotes(notes);
    }

    public void editNote(Note oldNote, Note newNote) {
        notes.remove(oldNote);
        notes.add(newNote);
        fileHandler.writeNotes(notes);
    }

    public void deleteNote(Note note) {
        notes.remove(note);
        fileHandler.writeNotes(notes);
    }
}
