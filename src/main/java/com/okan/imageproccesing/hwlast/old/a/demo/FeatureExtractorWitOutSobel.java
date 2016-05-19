package com.okan.imageproccesing.hwlast.old.a.demo;

import com.okan.imageproccesing.hwlast.util.ConvexHull;
import com.okan.imageproccesing.hwlast.util.Point;
import vpt.BooleanImage;
import vpt.DoubleImage;
import vpt.Image;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.binary.BDilation;
import vpt.algorithms.mm.binary.BInternGradient;
import vpt.algorithms.mm.binary.geo.BFillHole;
import vpt.algorithms.segmentation.Threshold;
import vpt.util.Tools;
import vpt.util.se.FlatSE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Extract a feature from a given collection of images
 * 
 * @author Erhan
 *
 */
public class FeatureExtractorWitOutSobel {
	public static void main(String[] args){

		String pathTrain = "/Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/train/";
		String pathTest = "/Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/test/";
		
		// TODO oznitelik yoneyi uzunlugunu güncelleyin
		//int featureVectorLength = 4;
		int featureVectorLength = 256;
		//int featureVectorLength = 1;

		String[] classNames = {"apple","bat","beetle","bell","bird","Bone","bottle","brick","butterfly","camel","car",
				"carriage","cattle","cellular_phone","chicken","children","chopper","classic","Comma","crown","cup","deer","device0","device1","device2",
				"device3","device4","device5","device6","device7","device8","device9","dog","elephant","face","fish","flatfish","fly","fork",
				"fountain","frog","Glas","guitar","hammer","hat","HCircle","Heart","horse","horseshoe","jar","key","lizzard","lmfish",
				"Misk","octopus","pencil","personal_car","pocket","rat","ray","sea_snake","shoe","spoon","spring","stef","teddy","tree","truck",
				"turtle","watch"};
		
		try{
			// eğitim kümesi
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("train_demo.arff")));
			
			pw.println("@RELATION mpeg7shape1-B");
			
			for(int i = 1; i <= featureVectorLength; i++)
				pw.println("@ATTRIBUTE o" + i +"	REAL");
			
			pw.println("@ATTRIBUTE o 	{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69}");
			pw.println("@DATA");
			
			for(int i = 0; i < classNames.length; i++){
				
				for(int j = 1; j <= 5; j++){
					Image img = Load.invoke(pathTrain + classNames[i] + "-" + j + ".png");

					double[] feature = oznitelikHesapla(img);
							
					for(int k = 0; k < feature.length; k++)
						pw.print(feature[k]+",");
							
					pw.println(i);
							
					System.err.println(i + " " + j);
				}
			}
			pw.close();
			
			// sinama kümesi
			PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter("test_demo.arff")));
			
			pw2.println("@RELATION mpeg7shape1-B");
			
			for(int i = 1; i <= featureVectorLength; i++)
				pw2.println("@ATTRIBUTE o" + i +"	REAL");
			
			pw2.println("@ATTRIBUTE o 	{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69}");
			pw2.println("@DATA");

			Date startDate = new Date();
			for(int i = 0; i < classNames.length; i++){
				
				for(int j = 11; j <= 15; j++){
					Image img = Load.invoke(pathTest + classNames[i] + "-" + j + ".png");

					double[] feature = oznitelikHesapla(img);
							
					for(int k = 0; k < feature.length; k++)
						pw2.print(feature[k]+",");
							
					pw2.println(i);
							
					System.err.println(i + " " + j);
				}
			}
			pw2.close();

			System.out.println("startDate :"+startDate.toString());
			Date endDate = new Date();
			System.out.println("endDate :"+endDate.toString());

		}catch(Exception e){
			e.printStackTrace();
		}
	}



	//convexhull
	private static double[] computeConvex(Image img) {
		int width = img.getXDim();
		int height = img.getYDim();
		int t = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int p = img.getXYByte(i, j);
				if (p == 255) {
					t++;
				}

			}
		}

		Point[] points = new Point[t];
		int tt = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int p = img.getXYByte(i, j);
				if (p == 255) {
					points[tt] = new Point(i, j);
					tt++;
				}
			}
		}

		ConvexHull hull = new ConvexHull(points);
		hull.computeConvexHull();

		Point[] ConvexPoints = hull.getConvexHull();
		BooleanImage output = new BooleanImage(img, false);
		for (int j = 0; j < ConvexPoints.length - 1; j++) {
			ArrayList<java.awt.Point> line = Tools.drawLine(new java.awt.Point((int) ConvexPoints[j].getX(), (int) ConvexPoints[j].getY()), new java.awt.Point((int) ConvexPoints[j + 1].getX(), (int) ConvexPoints[j + 1].getY()));

			for (int jj = 0; jj < line.size(); jj++)
				output.setXYBoolean(line.get(jj).x, line.get(jj).y, true);
		}

		BooleanImage output_borders = (BooleanImage) BDilation.invoke(output, FlatSE.square(5));
		BooleanImage output_hull = (BooleanImage) BFillHole.invoke(output_borders);

		double area = Tools.volume(img, 0);
		double convexArea = Tools.volume(output_hull, 0);

		Double c_area = (convexArea - area) / convexArea;

		double perimeter = Tools.volume(output_borders, 0);
		double convexPerimeter = Tools.volume(output_borders, 0);

		Double c_perimer = convexPerimeter / perimeter;

		return new double[]{c_area, c_perimer};

	}


	private static double[] oznitelikHesapla(Image img){
		// top sizde..
		double[] feature  = null;

		Image imgBinary = Threshold.invoke(img, 1 * DoubleImage.byteToDouble);
		Image borderImage = BInternGradient.invoke(imgBinary, FlatSE.square(3));
		feature = computeConvex(borderImage);

		return feature;
	}
}


///Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/train
