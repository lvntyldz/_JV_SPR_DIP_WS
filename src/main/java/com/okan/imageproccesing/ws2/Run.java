package com.okan.imageproccesing.ws2;

import vpt.GlobalException;
import vpt.Image;
import vpt.algorithms.arithmetic.AbsSubtraction;
import vpt.algorithms.arithmetic.Addition;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.linear.MeanFilter;

/**
 * Created by leventyildiz on 18/02/16.
 */
public class Run {


    public static void main(String[] args) {


        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");


        Display2D.invoke(img, "before");

//
//        //11X11 maske uygulayarak görüntüyü bulandırma
//        Image newImage = MeanFilter.invoke(img, 11);
//        Display2D.invoke(newImage, "after11X11");
//
//        //5X5 maske uygulayarak görüntüyü bulandırma
//        Image newImage2 = MeanFilter.invoke(img, 5);
//        Display2D.invoke(newImage, "after5X5");
//


        //3X3 maske uygulayarak görüntüyü bulandırma
        Image newImage3 = MeanFilter.invoke(img, 3);
        Display2D.invoke(newImage3, "after3X3");

        Image diff = AbsSubtraction.invoke(img, newImage3);
        Display2D.invoke(diff, "diff");

        try {
            Image sum = Addition.invoke(img, diff);
            Display2D.invoke(sum, "sum");
        } catch (GlobalException e) {
            e.printStackTrace();
        }


    }


}
