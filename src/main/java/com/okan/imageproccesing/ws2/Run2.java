package com.okan.imageproccesing.ws2;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.linear.Sobel;

/**
 * Created by leventyildiz on 18/02/16.
 */
public class Run2 {


    public static void main(String[] args) {
        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");
        Display2D.invoke(img, "original");


//        Yatay yöndeki kenarları bul
        Image hor = Sobel.invoke(img, Sobel.GX, true);
        Display2D.invoke(hor, "Horizontal");

//        Dikey yöndeki kenarları bul
        Image ver = Sobel.invoke(img, Sobel.GY, true);
        Display2D.invoke(ver, "Vertical");


//        çift yöndeki kenarla
        Image both = Sobel.invoke(img, Sobel.GRAD, true);
        Display2D.invoke(both, "Çift Yönde");


    }

}
