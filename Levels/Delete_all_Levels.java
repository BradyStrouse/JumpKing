package Levels;

import java.io.File;

public class Delete_all_Levels {
    public Delete_all_Levels() {
        for (int i = 0; i <= 45; i++) {
            try {
                deleteFile(i);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private static void deleteFile(int Level) throws InterruptedException {
        new File("D:\\Coding Stuff\\Square\\Levels\\Level_" + Level + ".png").delete();
    }
}