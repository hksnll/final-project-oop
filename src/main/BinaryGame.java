package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BinaryGame {
    public static JFrame frame;
    public static JLabel questionLabel;
    public static JLabel currentInputLabel; // current input  
    public static String correctAnswer;
    public static int stage = 1; // 1: Binary to Decimal, 2: Hex to Decimal, 3: Binary Addition  
    public static int totalScore = 0; // To store the total score
    public static Random random = new Random();
    private static boolean gameFinished = false;

    public static void createAndShowGUI() {
        frame = new JFrame("Quiz");

        frame.setSize(400, 400);

        // panel with gradient  
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color startColor = Color.BLUE;
                Color endColor = Color.WHITE;
                GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(new BorderLayout());

        // equation label  
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // current input label  
        currentInputLabel = new JLabel("", SwingConstants.CENTER);
        currentInputLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // numeric keypad panel  
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3)); // 4x3 grid for buttons  
        addNumberButtons(keypadPanel);

        // add components to gradient panel  
        gradientPanel.add(questionLabel, BorderLayout.NORTH);
        gradientPanel.add(currentInputLabel, BorderLayout.CENTER);
        gradientPanel.add(keypadPanel, BorderLayout.SOUTH);

        frame.add(gradientPanel);

        generateQuestion();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure only this frame closes
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                gameFinished = true; // Mark the game as finished
            }
        });
        frame.setVisible(true);
    }

    private static void addNumberButtons(JPanel keypadPanel) {
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(e -> appendToInput(button.getText()));
            keypadPanel.add(button);
        }

        // button for '0'  
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(e -> appendToInput("0"));
        keypadPanel.add(zeroButton);

        // DEL Button  
        JButton delButton = new JButton("DELETE");
        delButton.addActionListener(e -> deleteLastCharacter());
        keypadPanel.add(delButton);

        // Submit Button  
        JButton submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(new SubmitButtonListener());
        keypadPanel.add(submitButton);
    }

    private static void appendToInput(String value) {
        String currentInput = currentInputLabel.getText();
        currentInputLabel.setText(currentInput + value);
    }

    private static void deleteLastCharacter() {
        String currentInput = currentInputLabel.getText();
        if (currentInput.length() > 0) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            currentInputLabel.setText(currentInput);
        }
    }

    private static void generateQuestion() {
        currentInputLabel.setText("");

        if (stage > 3) {
            gameFinished = true; // Mark the game as finished
            frame.dispose(); // Close only this frame
            return; // Ensure no further code runs after disposing this frame
        }

        switch (stage) {
            case 1: // Binary to Decimal  
                String binary = Integer.toBinaryString(random.nextInt(16));
                correctAnswer = String.valueOf(Integer.parseInt(binary, 2));
                questionLabel.setText("Convert Binary to Decimal: " + binary);
                break;

            case 2: // Hexadecimal to Decimal  
                String hex = Integer.toHexString(random.nextInt(32)).toUpperCase();
                correctAnswer = String.valueOf(Integer.parseInt(hex, 16));
                questionLabel.setText("Convert Hexadecimal to Decimal: " + hex);
                break;

            case 3: // Binary Addition  
                int num1 = random.nextInt(4) + 1;
                int num2 = random.nextInt(4) + 1;
                correctAnswer = Integer.toBinaryString(num1 + num2);
                questionLabel.setText("Calculate Sum of Binaries: " + Integer.toBinaryString(num1) + " + " + Integer.toBinaryString(num2));
                break;
        }
    }

    private static class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userAnswer = currentInputLabel.getText();

            // show if the user was correct or not  
            if (userAnswer.equals(correctAnswer)) {
                totalScore++;
                showAnswerDialog("Correct!", "Your answer is correct.");
            } else {
                showAnswerDialog("Incorrect!", "The correct answer was: " + correctAnswer);
            }

            // move to the next   
            stage++; // move to the next stage (1, 2, 3)  

            if (stage <= 3) {
                generateQuestion(); // Generate the next question if it's stage 1, 2, or 3  
            } else {
                gameFinished = true; // Mark the game as finished
                frame.dispose(); // Close only this frame
            }
        }

        private void showAnswerDialog(String title, String message) {
            JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static int getTotalScore() {
        return totalScore;
    }

    public boolean isGameFinished() {
        return this.gameFinished;
    }
}
