package com.okan.imageproccesing.hw1;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 14/02/16.
 */
public class Contrast {


    public void rescaleImg() {


        //loade image file
        Image img = Load.invoke("./src/main/resources/images/samples/barbara.jpg");

        int imgHeight = img.getYDim();
        int imgWidth = img.getXDim();
        int point;


        //create new Image
        Image newImg = new ByteImage(imgWidth, imgHeight);


        for (int i = 0; i < imgWidth; i++) {

            for (int j = 0; j < imgHeight; j++) {


                point = img.getXYByte(i, j);

                newImg.setXYByte(i, j, (point & 0xFFFFFFC0));


            }//for finish

        }//for finish

        Display2D.invoke(img, "beore");// before
        Display2D.invoke(newImg, "after");// after

    }

}
