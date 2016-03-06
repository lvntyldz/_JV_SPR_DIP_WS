package com.okan.imageproccesing.ws4;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.binary.geo.BGeodesicDilation;
import vpt.algorithms.mm.binary.geo.BReconstructionByDilation;
import vpt.algorithms.mm.gray.GOpening;
import vpt.util.se.FlatSE;

/**
 * Created by leventyildiz on 03/03/16.
 */
public class Run {


    public static void main(String[] args) {

        // Goruntuyü g maskesi olarak seç
        Image g = Load.invoke("./src/main/resources/images/samples/binaryGeometric.png");
        Display2D.invoke(g, "maske");


        Image f = GOpening.invoke(g, FlatSE.square(41));
        Display2D.invoke(f,"Açılım uygulanmış görüntü");



        //maske g
        //işlenecek görüntü f
        //g(p) >= f(p) her p için
        Image yerolcumselGenlesme = BGeodesicDilation.invoke(f, FlatSE.square(51), g);
        Display2D.invoke(yerolcumselGenlesme,"yer ölçümsel");



        //resimler eşit oluncyaa kadar devam et
        Image reco  = BReconstructionByDilation.invoke(f, FlatSE.square(3), g);
        Display2D.invoke(reco,"genleşme tabanlı yeniden ikili yapılandırma");







    }

}
