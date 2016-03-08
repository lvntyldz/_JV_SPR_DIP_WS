package com.okan.imageproccesing.hw4;

import com.sun.media.jai.opimage.MedianFilterRIF;
import vpt.Image;
import vpt.algorithms.arithmetic.AbsSubtraction;
import vpt.algorithms.arithmetic.Subtraction;
import vpt.algorithms.arithmetic.SubtractionC;
import vpt.algorithms.color.BIC;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;
import vpt.algorithms.linear.MeanFilter;
import vpt.algorithms.mm.gray.GBotHat;
import vpt.algorithms.mm.gray.GClosing;
import vpt.algorithms.mm.gray.GOpening;
import vpt.algorithms.mm.gray.GRankFilter;
import vpt.algorithms.mm.gray.geo.GBotHatByReconstruction;
import vpt.algorithms.mm.gray.geo.GClosingByReconstruction;
import vpt.algorithms.mm.gray.geo.GOpeningByReconstruction;
import vpt.algorithms.mm.gray.geo.GTopHatByReconstruction;
import vpt.algorithms.point.Inversion;
import vpt.algorithms.statistical.ReversePCA;
import vpt.util.se.FlatSE;

import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;

/**
 * Created by leventyildiz on 06/03/16.
 */
public class Catlak {

	public static void main(String[] args) {

		Image image = Load.invoke("./src/main/resources/images/samples/2/catlak.png");
		Display2D.invoke(image, "original");

		Image newImg5 = GRankFilter.invoke(image, FlatSE.square(5), 12);
		Display2D.invoke(newImg5, "median");

		
		 Image newImg = Inversion.invoke(image);
		 Display2D.invoke(newImg,"ters");

		 Image newImg2 = GClosingByReconstruction.invoke(newImg,
		 FlatSE.square(10));
		 Display2D.invoke(newImg2,"Kapanım");

		 Image newImg3 = GTopHatByReconstruction.invoke(newImg,
		 FlatSE.square(10));
		 Display2D.invoke(newImg3,"bothat");

		// Image newImg4 = MeanFilter.invoke(newImg3, 10);
		// Display2D.invoke(newImg4,"mean");


	}

}
