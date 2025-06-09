package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public boolean moving = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        collisionArea = new Rectangle(14, 26, 18, 15);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right2.png")));
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int tempWorldX = worldX;
        int tempWorldY = worldY;

        if (keyH.upPressed) {
            direction = "up";
            worldY -= speed;

            collisionY = false;
            gp.collisionChecker.checkTile(this);
            if (collisionY) {
                worldY = tempWorldY;
            }
        }

        if (keyH.downPressed) {
            direction = "down";
            worldY += speed;

            collisionY = false;
            gp.collisionChecker.checkTile(this);
            if (collisionY) {
                worldY = tempWorldY;
            }
        }

        if (keyH.leftPressed) {
            direction = "left";
            worldX -= speed;

            collisionX = false;
            gp.collisionChecker.checkTile(this);
            if (collisionX) {
                worldX = tempWorldX;
            }
        }

        if (keyH.rightPressed) {
            direction = "right";
            worldX += speed;

            collisionX = false;
            gp.collisionChecker.checkTile(this);
            if (collisionX) {
                worldX = tempWorldX;
            }
        }

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) spriteNum = 2;
                else spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage playerImage = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    playerImage = up1;
                }
                if (spriteNum == 2) {
                    playerImage = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    playerImage = down1;
                }
                if (spriteNum == 2) {
                    playerImage = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    playerImage = left1;
                }
                if (spriteNum == 2) {
                    playerImage = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    playerImage = right1;
                }
                if (spriteNum == 2) {
                    playerImage = right2;
                }
            }
        }
        g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);

//        Color oldColor = g2.getColor();
//        g2.setColor(new Color(255, 0, 0, 100)); // красный с прозрачностью
//        g2.fillRect(
//                screenX + collisionArea.x,
//                screenY + collisionArea.y,
//                collisionArea.width,
//                collisionArea.height
//        );
//        g2.setColor(Color.RED);
//        g2.drawRect(
//                screenX + collisionArea.x,
//                screenY + collisionArea.y,
//                collisionArea.width,
//                collisionArea.height
//        );
//        g2.setColor(oldColor);

    }

}