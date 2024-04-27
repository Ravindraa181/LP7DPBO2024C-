import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAwal extends JFrame {

    public MenuAwal() {
        setTitle("Menu Awal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Menutup GUI Form
                // Membuka JFrame game FlappyBird
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FlappyBird().setVisible(true);
                    }
                });
            }
        });

        panel.add(startButton, BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuAwal().setVisible(true);
            }
        });
    }
}
