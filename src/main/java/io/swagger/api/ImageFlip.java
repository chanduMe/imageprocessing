package io.swagger.api;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFlip {
    public void Execute(String inFile, String outFile, String flip) throws IOException {
        BufferedImage image = ImageIO.read(new File(inFile));
        BufferedImage output = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());

        AffineTransform transform = new AffineTransform();
        AffineTransform tx;
        AffineTransformOp op;

        if(flip.equals("vertical")){
            tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -image.getHeight(null));
        } else {
            tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(null), 0);
        }

        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        output = op.filter(image, null);
        ImageIO.write(output, outFile.substring(outFile.lastIndexOf(".") + 1), new File(outFile));

    }


}
