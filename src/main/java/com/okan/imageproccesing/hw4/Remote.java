package com.okan.imageproccesing.hw4;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.geo.GOpeningByReconstruction;
import vpt.algorithms.mm.gray.geo.GTopHatByReconstruction;
import vpt.algorithms.point.Inversion;
import vpt.util.se.FlatSE;

public class Remote {

	public static void main(String[] args) {

		Image image = Load.invoke("./src/main/resources/images/samples/2/remote.png");
		Display2D.invoke(image, "original");

//		Image inverse = Inversion.invoke(image);
//		Display2D.invoke(inverse, "ters");
		
		
		//Image opened =  GOpeningByReconstruction.invoke(image, FlatSE.disk(6));
		//Display2D.invoke(opened,"opend");

//		Görüntüyü 6r lik DISK ile traşla ve sadece traşlanan alanları geri getir.
       Image tophat =  GTopHatByReconstruction.invoke(image, FlatSE.disk(6));
       Display2D.invoke(tophat,"tophat");
		
	}

}
