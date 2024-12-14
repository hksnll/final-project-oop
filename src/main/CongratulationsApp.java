package main;

import javax.swing.*;
import java.awt.*;

public class CongratulationsApp {
    public void congrats() {
        // Fixed image path and message
        String imagePath = "/objects/COR.png"; // Replace with your image file path
        String message = "Congratulations, here's the new printed COR.";

        // Create the frame
        JFrame frame = new JFrame("Congratulations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Load and add the image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getIconWidth() == -1) {
            System.out.println("Error: Image not found. Please check the file path.");
            return;
        }
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        // Add the congratulatory message
        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.BLACK);
        frame.add(messageLabel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }
}
