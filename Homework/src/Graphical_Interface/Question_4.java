package Graphical_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question_4 extends JFrame implements ActionListener {
    private final JTextArea textArea;
    private final JButton bgButton;
    private final JButton fgButton;

    public Question_4() {
        super("Text Color Chooser");

        // Create the text area
        textArea = new JTextArea("Hello, world!");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Create the background color button
        bgButton = new JButton("Background Color");
        bgButton.addActionListener(this);

        // Create the foreground color button
        fgButton = new JButton("Foreground Color");
        fgButton.addActionListener(this);

        // Create the panel and add the components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(bgButton);
        buttonPanel.add(fgButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        setContentPane(panel);

        // Set the frame properties
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bgButton) {
            // Show the color chooser for the background color
            Color color = JColorChooser.showDialog(this, "Select Background Color", textArea.getBackground());
            if (color != null) {
                textArea.setBackground(color);
            }
        } else if (e.getSource() == fgButton) {
            // Show the color chooser for the foreground color
            Color color = JColorChooser.showDialog(this, "Select Foreground Color", textArea.getForeground());
            if (color != null) {
                textArea.setForeground(color);
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");
        new Question_4();
    }
}
