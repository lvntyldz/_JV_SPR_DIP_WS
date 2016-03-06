package com.okan.imageproccesing.ws4;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.*;
import vpt.util.se.FlatSE;

/**
 * Created by leventyildiz on 03/03/16.
 */
public class Run3 {


    public static void main(String[] args) {

        Image img  = Load.invoke("./src/main/resources/images/samples/lennaGray.png");
        Display2D.invoke(img, "original");

        //genleşme çevresine göre dha parlak olan yerleri genişletiyor
        //çevresine göre daha parlak olan yerleri de daraltıyor.

        Image eroded = GErosion.invoke(img, FlatSE.square(7));
        Display2D.invoke(eroded);

        Image dilated = GDilation.invoke(img, FlatSE.square(7));
        Display2D.invoke(dilated);

        //Kapanım çukurları doldurur.


        //Açılım her yönden traşlıyor
        //nekadar içeri gireceğini de maskein boyutu belirler.
        //yapıcı öğenin içine sığmayanları siler atar
        Image opened = GOpening.invoke(img, FlatSE.square(7));
        Display2D.invoke(opened,"açılmış");


        Image closed = GClosing.invoke(img, FlatSE.square(7));
        Display2D.invoke(closed,"kapanmış");



        Image grad = GGradient.invoke(img, FlatSE.square(3));
        Display2D.invoke(grad,"kenar");

    }

}
