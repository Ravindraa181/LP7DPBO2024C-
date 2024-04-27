import java.awt.*;

public class Pipe {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX;
    private boolean passed;

    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = -5;
        this.passed = false;
    }

    // Getter untuk posX
    public int getPosX() {
        return posX;
    }

    // Setter untuk posX
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter untuk posY
    public int getPosY() {
        return posY;
    }

    // Setter untuk posY
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter untuk width
    public int getWidth() {
        return width;
    }

    // Setter untuk width
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter untuk height
    public int getHeight() {
        return height;
    }

    // Setter untuk height
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter untuk image
    public Image getImage() {
        return image;
    }

    // Setter untuk image
    public void setImage(Image image) {
        this.image = image;
    }

    // Getter untuk velocityX
    public int getVelocityX() {
        return velocityX;
    }

    // Setter untuk velocityX
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    // Getter untuk passed
    public boolean isPassed()
    {
        return passed;
    }

    // Setter untuk passed
    public void setPassed(boolean passed) {

        this.passed = passed;
    }
}
