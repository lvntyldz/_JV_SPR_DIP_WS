package com.okan.imageproccesing.hw2;

import com.sun.media.jai.opimage.MedianFilterRIF;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

import javax.media.jai.operator.MedianFilterShape;

/**
 * Created by leventyildiz on 24/02/16.
 */
public class Run {



    public static void main(String[] args) {


        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");


        Display2D.invoke(img, "before");



    }

}
