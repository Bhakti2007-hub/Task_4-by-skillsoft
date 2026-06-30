import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class KeyboardEventAnalyzer extends JFrame implements KeyListener {

    private JTextArea textArea;
    private JLabel statusLabel;
    private StringBuilder keyLog;

    public KeyboardEventAnalyzer() {

        keyLog = new StringBuilder();

        setTitle("Keyboard Event Analyzer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.addKeyListener(this);

        JScrollPane scrollPane = new JScrollPane(textArea);

        statusLabel = new JLabel("Type inside the text area. Keys will be logged.");

        JButton saveButton = new JButton("Save Log");
        saveButton.addActionListener(e -> saveLogToFile());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(saveButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyLog.append(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        statusLabel.setText("Last Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void saveLogToFile() {
        try {
            FileWriter writer = new FileWriter("keylog.txt");
            writer.write(keyLog.toString());
            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Log saved successfully to keylog.txt"
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving file: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KeyboardEventAnalyzer::new);
    }
}