package com.okan.imageproccesing.ws2;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.linear.MeanFilter;
import vpt.algorithms.mm.gray.GRankFilter;
import vpt.algorithms.noise.Speckle;
import vpt.util.se.FlatSE;

/**
 * Created by leventyildiz on 18/02/16.
 */
public class Run3 {


    public static void main(String[] args) {
        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");
        Display2D.invoke(img, "original");


        //görüntüyü %15 oranında bozalım
        Image noisy = Speckle.invoke(img, 0.15);
        Display2D.invoke(noisy, "karabiber");


        //Ortalama filtresiyle gürültüden arındır
        Image mean = MeanFilter.invoke(noisy, 9);
        Display2D.invoke(mean, "ortalama filtresi uygulandi");


        //Ortanca filtresiyle gürültüden arındır
        Image median = GRankFilter.invoke(noisy, FlatSE.square(5), 12);
        Display2D.invoke(median, "ortanca filtresinden sonra");


    }

}
