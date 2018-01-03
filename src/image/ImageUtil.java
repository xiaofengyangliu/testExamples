package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static void main(String[] args) {
		File picture = new File("F:\\图片\\QQ截图20171116163713.jpg");
		BufferedImage sourceImg;
		try {
			sourceImg = ImageIO.read(new FileInputStream(picture));
			System.out.println(String.format("%.1f", picture.length() / 1024.0));// 源图大小
			System.out.println(sourceImg.getWidth()); // 源图宽度
			System.out.println(sourceImg.getHeight()); // 源图高度
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
