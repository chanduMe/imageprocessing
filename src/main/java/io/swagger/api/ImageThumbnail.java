package io.swagger.api;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class ImageThumbnail {
    public void Execute(String inFile, String outFile, Integer width) throws IOException {

        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;

        BufferedImage img = ImageIO.read(new File(inFile));
        thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);

        String formatName = outFile.substring(outFile.lastIndexOf(".") + 1);
        ImageIO.write(thumbImg, formatName , new File(outFile));
    }
}
