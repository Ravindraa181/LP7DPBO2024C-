import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createShowPlayGame());
    }

    // Method untuk membuat dan menampilkan GUI Form MainMenu
    private static void createShowPlayGame() {
        JFrame mainMenuFrame = new JFrame("Menu Awal");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(460, 640); 
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            mainMenuFrame.dispose(); // Menutup GUI Form MainMenu
            startGame(); // Memulai permainan FlappyBird
        });

        panel.add(Box.createVerticalGlue());
        panel.add(startButton);
        panel.add(Box.createVerticalGlue());

        mainMenuFrame.add(panel);
        mainMenuFrame.setVisible(true);
    }

    // Method untuk memulai permainan FlappyBird
    private static void startGame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(460, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}
