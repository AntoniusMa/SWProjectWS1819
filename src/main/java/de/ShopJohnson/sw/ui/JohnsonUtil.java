package de.ShopJohnson.sw.ui;

public class JohnsonUtil {
    public static void setTimeOut(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e) {
            }
        }).start();
    }
}
