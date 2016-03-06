package com.okan.imageproccesing.ws4;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.binary.geo.BFillHole;

/**
 * Created by leventyildiz on 03/03/16.
 */
public class Run2 {


    public static void main(String[] args) {


        //delik doldurmak için önce etrafı 1 px lik beyaz çerçeveyle kaplı boş bir görüntü oluştur.
        //bu görüntüyü işlemeye başla.
        //maske f nin tersi

        Image g = Load.invoke("./src/main/resources/images/samples/binaryGeometric.png");
        Display2D.invoke(g, "maske");


        Image dolue = BFillHole.invoke(g);
        Display2D.invoke(dolue);



    }

}
