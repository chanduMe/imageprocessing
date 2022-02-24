package io.swagger.api;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRotate {

    public void Execute(String inFile, String outFile, final double angle) throws Exception {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(inFile));

            BufferedImage output = new BufferedImage(bufferedImage.getHeight(), bufferedImage.getWidth(), bufferedImage.getType());

            AffineTransform transform = new AffineTransform();
            transform.rotate(angle * Math.PI / 180, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            output = op.filter(bufferedImage, null);
            ImageIO.write(output, outFile.substring(outFile.lastIndexOf(".") + 1), new File(outFile));

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}