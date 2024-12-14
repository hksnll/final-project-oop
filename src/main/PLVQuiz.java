package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PLVQuiz {
    public boolean isFinished;
    public boolean isFailed;
    public void run() {
        // the main frame
        JFrame frame = new JFrame("Jemy The Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // custom panel for gradient 
        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);

        // Questions 
        String[] questions = {
            "<html>On June 5, 2002, the Council of Valenzuela approved Ordinance No. 14<br>that paved way for the establishment of the Pamantasan ng Lungsod ng Valenzuela (PLV).</html>",
            "<html>In 2005, the surge in the number of qualified enrollees required the acquisition<br>of an extension campus about 500 meters away from the main building.</html>",
            "<html>Under the administration of former Mayor Sherwin Gatchalian, a study group was formed to prepare a developmental plan as well as to formulate policies and regulations for the creation of a local university.</html>"
        };

        // answers 
        String[] answers = {"True", "False (2009)", "False (Jose Emmanuel Carlos)"};

        JLabel questionLabel = new JLabel(questions[0], SwingConstants.CENTER);
        questionLabel.setBounds(20, 30, 560, 100);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionLabel.setForeground(Color.BLACK);
        panel.add(questionLabel);

        //  True and False buttons
        JButton trueButton = new JButton("True");
        JButton falseButton = new JButton("False");

        trueButton.setBounds(200, 150, 80, 40);
        falseButton.setBounds(320, 150, 80, 40);

        panel.add(trueButton);
        panel.add(falseButton);

        //  action listeners for buttons
        final int[] currentQuestion = {0};
        ActionListener answerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the answer
                String userAnswer = ((JButton) e.getSource()).getText();
                String correctAnswer = answers[currentQuestion[0]];

                // pop-up message
                if ((userAnswer.equals("True") && correctAnswer.startsWith("True")) || 
                    (userAnswer.equals("False") && !correctAnswer.startsWith("True"))) {
                    JOptionPane.showMessageDialog(frame, "Correct!", "Answer Feedback", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    isFailed = true;
                    JOptionPane.showMessageDialog(frame, "Incorrect! Correct answer: " + correctAnswer, "Answer Feedback", JOptionPane.ERROR_MESSAGE);
                }

                // move to the next question
                currentQuestion[0]++;
                if (currentQuestion[0] < questions.length) {
                    questionLabel.setText(questions[currentQuestion[0]]);
                    panel.toggleGradient(); // Change gradient 
                    panel.repaint();
                } else {
                    isFinished = true;
                    frame.dispose(); // Close the frame 
                }
            }
        };

        trueButton.addActionListener(answerListener);
        falseButton.addActionListener(answerListener);

        
        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public boolean isFinished() {

        return isFinished;
    }
}

// Custom JPanel 
class GradientPanel extends JPanel {
    private boolean toggleGradient = false;

    public void toggleGradient() {
        toggleGradient = !toggleGradient;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Alternate gradient direction
        GradientPaint gradient;
        if (toggleGradient) {
            gradient = new GradientPaint(0, 0, Color.WHITE, getWidth(), getHeight(), Color.BLUE);
        } else {
            gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.WHITE);
        }

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
