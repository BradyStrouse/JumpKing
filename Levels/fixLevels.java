package Levels;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

/*
 * this program moves from the top down on the image of the entire map
 * its very messy :(
 * but works
 * 
 * if you want a multiple have addition be false
 * if you want a more precise scaling do addition true and have the scale be more images
 */
public class fixLevels {
    public fixLevels(int importScale, boolean addition) throws IOException {
        int scale = importScale; // the larger scale the smaller the image will be
        File file = new File("C:\\Users\\Brady\\Downloads\\1578186080_JUMPKING_MAP.png");
        Rectangle rect = new Rectangle(1440, 1080);
        int x = 0, y = (int) rect.getHeight();
        BufferedImage image;
        image = ImageIO.read(file);
        boolean went_trough = false;
        for (int i = 0; i < 44; i++) {
            while (!went_trough) {
                if (i < 43) {
                    try {
                        went_trough = makeFile(i, image, x, y, rect, scale);
                    }
                    // this catch should only go off when it reaches the final level, so it sets the
                    // y to the final screen
                    catch (java.awt.image.RasterFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    File tempFile = new File("D:\\Coding Stuff\\Square\\Level_Backgrounds\\Level_" + 0 + ".png");
                    ImageIO.write(cropImage(image, rect, x, (45359)), "png", tempFile);
                    tempFile = new File("D:\\Coding Stuff\\Square\\Level_Backgrounds\\Level_" + 0 + ".png");
                    // ImageIO.write(resizeImageMultiple(ImageIO.read(tempFile),
                    // (int)rect.getWidth(), (int)rect.getHeight(), scale), "png", new
                    // File("D:\\Coding Stuff\\Square\\Levels\\Level_" + 0 +".png"));
                    went_trough = true;
                }
            }
        }
        went_trough = false;
    }

    private static boolean makeFile(int i, BufferedImage image, int x, int y, Rectangle rect, int scale)
            throws IOException {
        // this creates a new image by resizeing the cropped image to whatever scale,
        // then cropping it
        File tempFile = new File("D:\\Coding Stuff\\Square\\Level_Backgrounds\\Level_" + (int) (43 - (i + 1)) + ".png");
        ImageIO.write(cropImage(image, rect, x, (y * i)), "png", tempFile);
        tempFile = new File("D:\\Coding Stuff\\Square\\Levels\\Level_" + (int) (43 - (i + 1)) + ".png");
        ImageIO.write(resizeImageMultiple(ImageIO.read(tempFile), (int) rect.getWidth(), (int) rect.getHeight(), scale),
                "png", new File("D:\\Coding Stuff\\Square\\Levels\\Level_" + (int) (43 - (i + 1)) + ".png"));
        return true;
    }

    private static BufferedImage cropImage(BufferedImage src, Rectangle rect, int x, int y) {
        return src.getSubimage(x, y, rect.width, rect.height);
    }

    private static BufferedImage resizeImageMultiple(Image originalImage, int width, int height, int scale)
            throws IOException {
        Image image = originalImage.getScaledInstance(width / scale, height / scale, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = newImage.createGraphics();
        g2D.drawImage(image, 0, 0, null);
        g2D.dispose();
        return newImage;
    }

    private static BufferedImage resizeImageaddition(Image originalImage, int width, int height, int scale)
            throws IOException {
        Image image = originalImage.getScaledInstance(width - scale, height - scale, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = newImage.createGraphics();
        g2D.drawImage(image, 0, 0, null);
        g2D.dispose();
        return newImage;
    }

    public static void newFile(int level_num) {
        String strPath = "D:\\Coding Stuff\\Square\\Levels\\", strName = "Level_" + level_num;

        // Try-catch Block
        try {
            // Creating File Object
            File file1 = new File(strPath + "" + strName + ".png");

            // Method createNewFile() method creates blank
            // file.
            file1.createNewFile();
        }

        // Try-Catch Block
        catch (Exception ex1) {
        }
    }
}