import java.awt.*;
import javax.swing.*;

public class Jabang {
    private JFrame frame;
    private JList<Note> noteList;
    private DefaultListModel<Note> noteListModel;
    private NoteManager noteManager;
    private JTextArea noteContent;
    private JTextField noteTitle;
    private JTextField noteCategory;

    public Jabang() {
        noteManager = new NoteManager();
        noteListModel = new DefaultListModel<>();
        noteManager.getNotes().forEach(noteListModel::addElement);

        frame = new JFrame("Jabang Note-Taking App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());

        noteList = new JList<>(noteListModel);
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteList.addListSelectionListener(e -> displaySelectedNote());
        JScrollPane noteListScrollPane = new JScrollPane(noteList);
        noteListScrollPane.setPreferredSize(new Dimension(250, 600)); // Set the desired width

        noteContent = new JTextArea();
        noteTitle = new JTextField();
        noteCategory = new JTextField();

        JPanel noteDetailsPanel = new JPanel(new BorderLayout());
        JPanel noteInfoPanel = new JPanel(new GridLayout(3, 2));

        noteInfoPanel.add(new JLabel("Title:"));
        noteInfoPanel.add(noteTitle);
        noteInfoPanel.add(new JLabel("Category:"));
        noteInfoPanel.add(noteCategory);

        noteDetailsPanel.add(noteInfoPanel, BorderLayout.NORTH);
        noteDetailsPanel.add(new JScrollPane(noteContent), BorderLayout.CENTER);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addNoteButton = new JButton("Add Note");
        addNoteButton.addActionListener(e -> addNote());
        JButton editNoteButton = new JButton("Save Edit");
        editNoteButton.addActionListener(e -> editNote());
        JButton deleteNoteButton = new JButton("Delete Note");
        deleteNoteButton.addActionListener(e -> deleteNote());
        JButton backupButton = new JButton("Backup Notes");
        backupButton.addActionListener(e -> backupNotes());
        JButton restoreButton = new JButton("Restore Notes");
        restoreButton.addActionListener(e -> restoreNotes());

        toolbar.add(addNoteButton);
        toolbar.add(editNoteButton);
        toolbar.add(deleteNoteButton);
        toolbar.add(backupButton);
        toolbar.add(restoreButton);

        panel.add(noteListScrollPane, BorderLayout.WEST);
        panel.add(noteDetailsPanel, BorderLayout.CENTER);
        panel.add(toolbar, BorderLayout.SOUTH);


        frame.add(panel);
        frame.setVisible(true);
    }

    private void displaySelectedNote() {
        Note selectedNote = noteList.getSelectedValue();
        if (selectedNote != null) {
            noteTitle.setText(selectedNote.getTitle());
            noteContent.setText(selectedNote.getContent());
            noteCategory.setText(selectedNote.getCategory());
        }
    }

    private void addNote() {
        String title = noteTitle.getText();
        String content = noteContent.getText();
        String category = noteCategory.getText();
        Note note = new Note(title, content, category);
        noteManager.addNote(note);
        noteListModel.addElement(note);
    }

    private void editNote() {
        Note selectedNote = noteList.getSelectedValue();
        if (selectedNote != null) {
            String title = noteTitle.getText();
            String content = noteContent.getText();
            String category = noteCategory.getText();
            Note newNote = new Note(title, content, category);
            noteManager.editNote(selectedNote, newNote);
            int selectedIndex = noteList.getSelectedIndex();
            noteListModel.setElementAt(newNote, selectedIndex);
        }
    }

    private void deleteNote() {
        Note selectedNote = noteList.getSelectedValue();
        if (selectedNote != null) {
            noteManager.deleteNote(selectedNote);
            noteListModel.removeElement(selectedNote);
        }
    }

    private void backupNotes() {
        noteManager.backupNotes();
        JOptionPane.showMessageDialog(frame, "Notes backed up successfully.");
    }

    private void restoreNotes() {
        noteManager.restoreNotes();
        noteListModel.clear();
        noteManager.getNotes().forEach(noteListModel::addElement);
        JOptionPane.showMessageDialog(frame, "Notes restored successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Jabang::new);
    }
}
