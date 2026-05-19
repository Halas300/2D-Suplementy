import javax.swing.*;
import java.awt.*;

public class AIWindow {
    private JFrame frame;

    public AIWindow() {
        this.frame = new JFrame("AI Poradce pro Suplementy");
    }

    public void showWindow() {
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JLabel infoLabel = new JLabel("Napiš svůj cíl:", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(infoLabel, BorderLayout.NORTH);

        JTextArea responseArea = new JTextArea("Zde se zobrazí doporučení od AI");
        responseArea.setWrapStyleWord(true);
        responseArea.setLineWrap(true);
        responseArea.setEditable(false);
        responseArea.setFont(new Font("Arial", Font.PLAIN, 15));
        responseArea.setMargin(new Insets(10, 10, 10, 10));
        frame.add(new JScrollPane(responseArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton sendButton = new JButton("Odeslat dotaz");

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            String userText = inputField.getText();
            if (userText.isEmpty()) return;

            responseArea.setText("Umělá inteligence přemýšlí, prosím čekejte");
            sendButton.setEnabled(false);
            inputField.setText("");

            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    return AIAssistantAPI.askAI(userText);
                }

                @Override
                protected void done() {
                    try {
                        String result = get();
                        responseArea.setText(result);
                    } catch (Exception ex) {
                        responseArea.setText("Chyba při komunikaci s API:\n" + ex.getMessage());
                    }
                    sendButton.setEnabled(true);
                }
            };
            worker.execute();
        });

        frame.setVisible(true);
    }
}
