package model;

import java.util.Random;

public class Utils {
    
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String createMessage() {
        StringBuilder builder = new StringBuilder();
        
        Random r = new Random();
        
        int count = r.nextInt((5096 - 4096) + 1) + 4096;
        
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        
        return builder.toString();
    }

}
