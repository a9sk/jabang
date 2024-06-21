import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Note> searchNotes(String keyword) {
        return notes.stream()
                .filter(note -> note.getTitle().contains(keyword) || note.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void backupNotes() {
        fileHandler.writeBackup(noteToString(notes));
    }

    public void restoreNotes() {
        this.notes = fileHandler.readBackup();
    }
    
    private List<String> noteToString(List<Note> notesList){
        List<String> stringList;
        for(int i=0; i<notesList.size(); i++){
            stringList.addLast(notesList.get(i).toString());
        }
        return stringList;
    }
}
