package Core;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by gev on 08.02.2017.
 */
public class FileUpload {
    private static final Double MAX_SIZE = Double.valueOf(350);
    public Double height;
    public Double width;

    public String upload(Part file) throws IOException {
        if (file != null) {
            String img = getRandomName() + file.getContentType().replace("image/", ".");
            try {
                file.write(getFileUploadUrl() + img);
                resizeImage(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return img;
        }
        return null;
    }

//    private static String getFilename(UploadedFile part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
//            }
//        }
//        return null;
//    }

    private String getFileUploadUrl() {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("fileUploadUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String getRandomName() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    private void resizeImage(String img){
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(getFileUploadUrl()+img));
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, "jpg", new File(getFileUploadUrl()+img));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type){
        defineActualSize(originalImage);
        BufferedImage resizedImage = new BufferedImage(width.intValue(), height.intValue(), type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width.intValue(), height.intValue(), null);
        g.dispose();

        return resizedImage;
    }

    private void defineActualSize(BufferedImage originalImage){
        height = Double.valueOf(originalImage.getHeight());
        width = Double.valueOf(originalImage.getWidth());

        if (width>MAX_SIZE){
            height = height*(MAX_SIZE/width);
            width=MAX_SIZE;
        }
        if (height>MAX_SIZE){
            width = width*(MAX_SIZE/height);
            height=MAX_SIZE;
        }
    }

}

