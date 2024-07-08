package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
public class CaptchaGenerator {
    public String generateRandomStringForPassword(){
        String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
        String numbers = "0123456789";
        String specialChars = "#!%$";
        String allChars = upperCase + lowerCase + numbers + specialChars;
        Random random = new Random();
        int length = random.nextInt(4) + 8;
        StringBuilder sb = new StringBuilder();
        sb.append(upperCase.charAt(random.nextInt(upperCase.length())));
        sb.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        sb.append(numbers.charAt(random.nextInt(numbers.length())));
        sb.append(specialChars.charAt(random.nextInt(specialChars.length())));
        for(int i = 4; i < length; i++)
            sb.append(allChars.charAt(random.nextInt(allChars.length())));
        String result = sb.toString();
        return new String(result);
    }
    private String generateRandomStringForCaptcha(){
        String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
        String allChars = upperCase + lowerCase;
        Random random = new Random();
        int length = 5;
        StringBuilder sb = new StringBuilder();
        sb.append(upperCase.charAt(random.nextInt(upperCase.length())));
        sb.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        for(int i = 2; i < length; i++)
            sb.append(allChars.charAt(random.nextInt(allChars.length())));
        String result = sb.toString();
        return result;
    }
    public String asciiArt(){
        String str = generateRandomStringForCaptcha();
        int width = 10;
        int height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(str, 2, 16);
        StringBuilder stringBuilder = new StringBuilder();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : randomChar());
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
    private char randomChar(){
        String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
        String allChars = upperCase + lowerCase + "0123456789";
        Random random = new Random();
        int i = random.nextInt(62);
        return allChars.charAt(i);
    }
    public String simpleCaptcha(){
        Random random = new Random();
        int num1 = random.nextInt(20);
        int num2 = random.nextInt(10);
        int n = random.nextInt();
        String function;
        if(n % 3 == 0) function = "PLUS";
        else if(n % 3 == 1) function = "MINUS";
        else function = "MULTIPLY BY";
        String captcha = new String(num1 + " " + function + " " + num2);
        return captcha;
    }
//    public void asciiArtCaptcha(Scanner scanner){
//        String captcha = generateRandomStringForCaptcha();
//        asciiArt(captcha);
//        String answer = scanner.nextLine();
//        if(!answer.equals(captcha))
//            asciiArtCaptcha(scanner);
//    }
    public int resultOfSimpleCaptcha(String a){
        String[] parts = a.split(" ");
        int result = 0;
        int num1 = Integer.parseInt(parts[0]);
        if(parts[1].equals("PLUS")){
            int num2 = Integer.parseInt(parts[2]);
            result = num1 + num2;
        }
        else if(parts[1].equals("MINUS")){
            int num2 = Integer.parseInt(parts[2]);
            result = num1 - num2;
        }
        else if(parts[1].equals("MULTIPLY")){
            int num2 = Integer.parseInt(parts[3]);
            result = num1 * num2;
        }
        return result;
    }
}
