import javax.swing.*;

public class Jabang {
    private JFrame frame;
    private JList<Note> noteList;
    private DefaultListModel<Note> noteListModel;
    private NoteManager noteManager;
    private JTextArea noteContent;
    private JTextField noteTitle;
    private JTextField noteCategory;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Jabang::new);
    }
}
