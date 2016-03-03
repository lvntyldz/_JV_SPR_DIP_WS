package com.okan.imageproccesing.hw3;

import vpt.Image;
import vpt.algorithms.arithmetic.AbsSubtraction;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.GClosing;
import vpt.algorithms.mm.gray.GDilation;
import vpt.algorithms.mm.gray.GOpening;
import vpt.util.se.FlatSE;

/**
 * Created by leventyildiz on 28/02/16.
 */
public class Run {

    public static void main(String[] args) {

        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/1/sample1.png");
        Display2D.invoke(img, "original");


//       Image img2 = GOpening.invoke(img, FlatSE.hLine(10));
//       Display2D.invoke(img2, "after");


        Image img2 = GOpening.invoke(img, FlatSE.hLine(19));
        Display2D.invoke(img2, "after");

        Image img3 = GOpening.invoke(img, FlatSE.vLine(10));
        Display2D.invoke(img3, "after2");


        Image diff = AbsSubtraction.invoke(img2, img3);
        Display2D.invoke(diff, "diff");

    }
}
