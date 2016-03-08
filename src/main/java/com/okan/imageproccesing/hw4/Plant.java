package com.okan.imageproccesing.hw4;

import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.GDilation;
import vpt.algorithms.point.Inversion;
import vpt.util.se.FlatSE;

public class Plant {

	public static void main(String[] args) {

		Image image = Load.invoke("./src/main/resources/images/samples/2/176.png");
		Display2D.invoke(image, "original");

		Image dilated = GDilation.invoke(image, FlatSE.square(3));
		Display2D.invoke(dilated, "dilated");

		Image inverse = Inversion.invoke(image);
		Display2D.invoke(inverse, "ters");

		// Image edge = Sobel.invoke(image, Sobel.GRAD, true);
		// edge = Sobel.invoke(image, Sobel.GRAD, true);
		// Display2D.invoke(edge, "kenarlar");

		// Image dilated = GDilation.invoke(edge,FlatSE.square(3));
		// Display2D.invoke(dilated,"dilated");
		//
		//
		// Image opened = GOpening.invoke(edge,FlatSE.square(3));
		// Display2D.invoke(opened,"opened");
		//

		// Image tophat = GTopHatByReconstruction.invoke(edge, FlatSE.disk(30));
		// Display2D.invoke(tophat, "tophat");

		// ----------
		// Image top = GTopHat.invoke(image, FlatSE.disk(5));
		// Display2D.invoke(top, "top");
		//
		// Image bot = GBotHat.invoke(image, FlatSE.disk(5));
		// Display2D.invoke(bot, "bot");
		//
		// Image opened = GOpeningByReconstruction.invoke(image,
		// FlatSE.disk(5));
		// Display2D.invoke(opened, "opened");
		//
		// Image cloesd = GClosingByReconstruction.invoke(image,
		// FlatSE.disk(5));
		// Display2D.invoke(cloesd, "cloesd");
		//
		// Image top2 = GTopHatByReconstruction.invoke(image, FlatSE.disk(5));
		// Display2D.invoke(top2, "top2");
		//
		// Image bot2 = GBotHatByReconstruction.invoke(image, FlatSE.disk(5));
		// Display2D.invoke(bot2, "bot2");

	}

}