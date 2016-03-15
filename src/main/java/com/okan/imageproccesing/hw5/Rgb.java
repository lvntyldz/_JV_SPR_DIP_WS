package com.okan.imageproccesing.hw5;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 15/03/16.
 */
public class Rgb {


    public Image colorful(String path) {

        //load image
        Image immage = Load.invoke(path);
        Display2D.invoke(immage, "original(Grayscale)");//display original

        //Yeni renkli görüntü oluştur
        ByteImage renkli = new ByteImage(immage.getXDim(), immage.getYDim(), 3);

        for (int x = 0; x < immage.getXDim(); x++) {
            for (int y = 0; y < immage.getYDim(); y++) {

                int p = immage.getXYByte(x, y);


                int r = 0;
                //if(p>=50 && p<100) r=300-p;
                r = (p < 150) ? (p + 100) : (p / 2);
                //r=(p>100 && p<255)?(p+100):(p*2);
                //r=(p>128)?(p):(p*2);


                int g = 0;
                //if(p>=100 && p<=200)g=(p*2)-150;
                //g=(p>50 && p<128)?(p*2):(p+20);
                g = (p > 100 && p < 255) ? (p - 100) : (p * 2);
                //g=(p>128)?(p-100):(p*2);


                int b = 0;
                //if(p<=50 || p>200)b=p/2;
                //b=(p>50 && p<128)?(p*2):(p+20);
                // b=(p>100)?(p/2-50):(p*2);
                b = (p > 128) ? (p / 2) : (p);


                renkli.setXYCByte(x, y, 0, r);
                renkli.setXYCByte(x, y, 1, g);
                renkli.setXYCByte(x, y, 2, b);


            }
        }

        return renkli;
    }


}
