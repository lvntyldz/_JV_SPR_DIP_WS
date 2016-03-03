package com.okan.imageproccesing.ws3;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.*;
import vpt.util.se.FlatSE;

/**
 * Created by leventyildiz on 25/02/16.
 */
public class Run {


    public static void main(String[] args) {


        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/binaryGeometric.png");
        Display2D.invoke(img,"original");



        //Genleşeme
        Image dilated = GDilation.invoke(img, FlatSE.square(3));
        Display2D.invoke(dilated,"3X3 lük maske ile genleşme");

         dilated = GDilation.invoke(img, FlatSE.square(5));
        Display2D.invoke(dilated,"5X5 lük maske ile genleşme");

         dilated = GDilation.invoke(img, FlatSE.square(15));
        Display2D.invoke(dilated,"15X15 lük maske ile genleşme");



        //açılım (3x3 lük maske)
        Image opened = GOpening.invoke(img,FlatSE.square(3));
        Display2D.invoke(opened,"3X3 opening");


        opened = GOpening.invoke(img,FlatSE.square(5));
        Display2D.invoke(opened,"5X5 opening");

        opened = GOpening.invoke(img,FlatSE.square(15));
        Display2D.invoke(opened,"15X15 opening");


        //Kapanım
        Image closed = GClosing.invoke(img,FlatSE.square(5));
        Display2D.invoke(closed,"5X5 lik maske ile kapanım");

         closed = GClosing.invoke(img,FlatSE.square(30));
        Display2D.invoke(closed,"30X30 lik maske ile kapanım");



        //kenar bulalımç
        Image disKenar = GExternGradient.invoke(img,FlatSE.square(7));
        Display2D.invoke(disKenar,"7");


        Image icKenar = GInternGradient.invoke(img, FlatSE.square(7));
        Display2D.invoke(icKenar,"7");


        Image kenar = GGradient.invoke(img,FlatSE.square(7));
        Display2D.invoke(kenar,"7");



    }

}
