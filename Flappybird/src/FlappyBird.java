import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 460;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    Player player;

    // pipe attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = frameWidth - 300;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;
    int score = 0;
    JLabel scoreLabel;

    boolean gameOver = false;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // pipes cooldown timer
        pipesCooldown = new Timer(1180, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        pipesCooldown.start(); // Start the pipesCooldown timer

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Initialize score label
        scoreLabel = new JLabel("Score: ");
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(scoreLabel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Draw Game Over text if the game is over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
            int x = (frameWidth - fm.stringWidth("Game Over")) / 2;
            int y = frameHeight / 2;
            g.drawString("Game Over", x, y);
        }
    }

    public void placePipes() {
        int openingSpace = frameHeight / 8;
        int minPipeHeight = frameHeight / 4; // Tinggi pipa minimal
        int maxPipeHeight = frameHeight - openingSpace - minPipeHeight; // Tinggi pipa maksimal

        int randomPipeHeight = (int) (Math.random() * (maxPipeHeight - minPipeHeight + 1)) + minPipeHeight;

        int randomPipePosY = (pipeStartPosY - randomPipeHeight);

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, randomPipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        int lowerPipeHeight = frameHeight - randomPipeHeight - openingSpace;
        Pipe lowerPipe = new Pipe(pipeStartPosX, frameHeight - lowerPipeHeight, pipeWidth, lowerPipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (!gameOver) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

                // Check for collision with pipes
                if (player.intersects(pipe)) {
                    gameOver = true;
                    break;
                }

                // Check for passing pipes
                if (!pipe.isPassed() && pipe.getPosX() < player.getPosX() && pipe.getPosY() < player.getPosY()) {
                    pipe.setPassed(true);
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
            }

            // Check for collision with ground
            if (player.getPosY() + player.getHeight() >= frameHeight) {
                gameOver = true;
            }
        }
    }

    public void restartGame() {
        pipes.clear();
        player.setPosY(playerStartPosY);
        score = 0;
        scoreLabel.setText("Score: " + score);
        gameOver = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocityY(-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void requestFocus() {}
}
