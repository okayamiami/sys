package QR;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    private static final String QR_CODE_IMAGE_PATH = "./Webcontent/syashin/MyQRCode.png";

    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        System.out.println("Writing QR code to: " + path.toAbsolutePath());
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR code generated successfully.");
    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("https://plum-chloride.jp/kotonoha-tango/index.html?appsule=2", 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}