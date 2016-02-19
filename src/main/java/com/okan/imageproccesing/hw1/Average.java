package com.okan.imageproccesing.hw1;


import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 14/02/16.
 */
public class Average {

    public void calculateAverage() {

        Image img = Load.invoke("./src/main/resources/images/gokada/gokada1.jpg");

        int imageCount = 50;
        int imgHeigh = img.getYDim();
        int imgWidth = img.getXDim();

        Integer[][] imageTmpData = new Integer[imgWidth][imgHeigh];

        for (int i = 0; i < imageCount; i++) {
            img = Load.invoke("./src/main/resources/images/gokada/gokada" + (i + 1) + ".jpg");

            for (int x = 0; x < imgWidth; x++) {

                for (int y = 0; y < imgHeigh; y++) {

                    Integer point = img.getXYByte(x, y);
                    Integer tmpPoint = imageTmpData[x][y];

                    if (point == null) {
                        point = 0;
                    } // fi

                    if (tmpPoint == null) {
                        tmpPoint = 0;
                    } // fi

                    imageTmpData[x][y] = (tmpPoint + point);

                } // for finish

            } // for finish

        }


        Image newImg = new ByteImage(imgWidth, imgHeigh);
        for (int x = 0; x < imgWidth; x++) {

            for (int y = 0; y < imgHeigh; y++) {
                newImg.setXYByte(x, y, (imageTmpData[x][y] / imageCount));

            } // for finish

        } // for finish

        Display2D.invoke(img, "beore");// before
        Display2D.invoke(newImg, "after");// after

    }

}
