package com.okan.imageproccesing.hw5;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 15/03/16.
 */
public class Run1 {


    public static void main(String[] args) {

        Rgb rgb = new Rgb();
        Image renkli = rgb.colorful("./src/main/resources/images/samples/3/eski.png");
        Display2D.invoke(renkli, true);

        Image renkli2 = rgb.colorful("./src/main/resources/images/samples/3/xray.png");
        Display2D.invoke(renkli2, true);

    }


}
