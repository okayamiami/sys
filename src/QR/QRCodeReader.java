package QR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeReader {

    public static void main(String[] args) {
        try {
            File file = new File("./Webcontent/syashin/MyQRCode.png");
            BufferedImage bufferedImage = ImageIO.read(file);

            BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

            Result result = new MultiFormatReader().decode(binaryBitmap);

            System.out.println("QR Code text: " + result.getText());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
