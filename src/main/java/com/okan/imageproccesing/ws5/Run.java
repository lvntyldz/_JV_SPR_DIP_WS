package com.okan.imageproccesing.ws5;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

public class Run {

	public static void main(String[] args) {

		// load lena
		Image lena = Load.invoke("./src/main/resources/images/samples/lenna512.png");
		Display2D.invoke(lena, "true", true);
		Display2D.invoke(lena, "false", false);

		int x = 10;
		int y = 34;

		// piksel değeri okuma
		int[] rgb = lena.getVXYByte(x, y);

		// veya teker teker
		int r = lena.getXYCByte(x, y, 0);
		int g = lena.getXYCByte(x, y, 1);
		int b = lena.getXYCByte(x, y, 2);

		// renkli piksel değeri yazma (3'ü bir arada)
		int[] yeniDeger = { 200, 250, 150 };

		lena.setVXYByte(x, y, yeniDeger);

		// teker teker
		lena.setXYCByte(x, y, 0, 200);
		lena.setXYCByte(x, y, 1, 250);
		lena.setXYCByte(x, y, 2, 150);

		// yeni renkli görüntü oluştur
		//640X480 boyutunda 3 kanallı(RGB) görüntüsü oluşturur
		ByteImage yeniGoruntu = new ByteImage(640, 480, 3);

	}

}
