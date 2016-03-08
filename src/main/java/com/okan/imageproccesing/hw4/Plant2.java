package com.okan.imageproccesing.hw4;

import vpt.Image;
import vpt.algorithms.arithmetic.AbsSubtraction;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.GClosing;
import vpt.algorithms.mm.gray.GOpening;
import vpt.algorithms.mm.gray.geo.GTopHatByReconstruction;
import vpt.util.se.FlatSE;

public class Plant2 {

	public static void main(String[] args) {

		Image image = Load.invoke("./src/main/resources/images/samples/2/14.png");
		Display2D.invoke(image, "original");


		Image closed = GClosing.invoke(image, FlatSE.disk(2));
		Display2D.invoke(closed, "kapanmış");

		Image opened = GOpening.invoke(image, FlatSE.disk(2));
		Display2D.invoke(opened, "açılmış");

		Image diff = AbsSubtraction.invoke(opened, closed);
		Display2D.invoke(diff, "diff");

		
		
		 Image tophat = GTopHatByReconstruction.invoke(diff,
		 FlatSE.disk(20));
		 Display2D.invoke(tophat, "tophat");

		
		
		// Image lap = Laplacian.invoke(image, true);
		// Display2D.invoke(lap, "lap");

		// Image inverse = Inversion.invoke(image);
		// Display2D.invoke(inverse, "inverse");

		// Image diff = AbsSubtraction.invoke(image, inverse);
		// Display2D.invoke(diff, "diff");

		// Image closed = GClosing.invoke(image, FlatSE.disk(5));
		// Display2D.invoke(closed, "kapanmış");
		//
		// Image opened = GOpening.invoke(image, FlatSE.disk(3));
		// Display2D.invoke(opened, "açılmış");
		//
		// Image diff = AbsSubtraction.invoke(opened, closed);
		// Display2D.invoke(diff, "diff");
		//
		// Image diff3 = AbsSubtraction.invoke(diff, image);
		// Display2D.invoke(diff3, "diff3");
		//
		// Image inverse = Inversion.invoke(image);
		// Display2D.invoke(inverse, "inverse");
		//
		// Image edge = Sobel.invoke(image, Sobel.GRAD, true);
		// Display2D.invoke(edge, "Çift Yönde");
		//
		// Image diff2 = AbsSubtraction.invoke(edge, image);
		// Display2D.invoke(diff2, "diff2");

		// Image median = GRankFilter.invoke(closed, FlatSE.square(5), 12);
		// median = GRankFilter.invoke(median, FlatSE.square(5), 12);
		// Display2D.invoke(median, "ortanca filtresinden sonra");

		// Image edge = Sobel.invoke(image, Sobel.GRAD, true);
		// Display2D.invoke(edge, "Çift Yönde");

		// Image grad = GGradient.invoke(median, FlatSE.square(3));
		// Display2D.invoke(grad,"kenar");

		// Image tophat = GTopHatByReconstruction.invoke(diff3,
		// FlatSE.disk(20));
		// Display2D.invoke(tophat, "tophat");

	}

}
