package com.okan.imageproccesing.ws1;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 18/02/16.
 */
public class Run {

    public static void main(String[] args) {

        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");
        Display2D.invoke(img, "Original");


        int xDim = img.getXDim();
        int yDim = img.getYDim();


        Image newImg = new ByteImage(xDim / 2, yDim / 2);

        for (int x = 0; x < xDim; x++) {


            for (int y = 0; y < yDim; y++) {

                int p = img.getXYByte(x, y);

                if (x % 2 == 0 && y % 2 == 0) {


                    newImg.setXYByte(x / 2, y / 2, p);


                }


            }


        }


        Display2D.invoke(newImg, "after");

    }

}
