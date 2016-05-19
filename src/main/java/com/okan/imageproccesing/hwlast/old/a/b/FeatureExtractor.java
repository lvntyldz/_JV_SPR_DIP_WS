package com.okan.imageproccesing.hwlast.old.a.b;

import com.okan.imageproccesing.hwlast.util.ConvexHull;
import com.okan.imageproccesing.hwlast.util.Point;
import vpt.BooleanImage;
import vpt.DoubleImage;
import vpt.Image;
import vpt.algorithms.color.BIC;
import vpt.algorithms.conversion.Gray2Color;
import vpt.algorithms.histogram.Histogram;
import vpt.algorithms.io.Load;
import vpt.algorithms.linear.Sobel;
import vpt.algorithms.mm.binary.BDilation;
import vpt.algorithms.mm.binary.BInternGradient;
import vpt.algorithms.mm.binary.geo.BFillHole;
import vpt.algorithms.segmentation.Threshold;
import vpt.algorithms.statistical.Moment;
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
 */
public class FeatureExtractor {
    public static void main(String[] args) {


        ///Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/train

        //String pathTrain = "/Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/train/";
        //String pathTest = "/Users/leventyildiz/Desktop/delete/dip/mpeg7shapeB/test/";


        String pathTrain = "./src/main/resources/images/mpeg7shapeB/train/";
        String pathTest = "./src/main/resources/images/mpeg7shapeB/test/";


        // TODO oznitelik yoneyi uzunlugunu güncelleyin
        //int featureVectorLength = 4;
        //int featureVectorLength = 256;
        //int featureVectorLength = 2;
        //int featureVectorLength = 514;
        //int featureVectorLength = 518;
        int featureVectorLength = 774;

        String[] classNames = {"apple", "bat", "beetle", "bell", "bird", "Bone", "bottle", "brick", "butterfly", "camel", "car",
                "carriage", "cattle", "cellular_phone", "chicken", "children", "chopper", "classic", "Comma", "crown", "cup", "deer", "device0", "device1", "device2",
                "device3", "device4", "device5", "device6", "device7", "device8", "device9", "dog", "elephant", "face", "fish", "flatfish", "fly", "fork",
                "fountain", "frog", "Glas", "guitar", "hammer", "hat", "HCircle", "Heart", "horse", "horseshoe", "jar", "key", "lizzard", "lmfish",
                "Misk", "octopus", "pencil", "personal_car", "pocket", "rat", "ray", "sea_snake", "shoe", "spoon", "spring", "stef", "teddy", "tree", "truck",
                "turtle", "watch"};

        try {
            // eğitim kümesi
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("train_b.arff")));

            pw.println("@RELATION mpeg7shape1-B");

            for (int i = 1; i <= featureVectorLength; i++)
                pw.println("@ATTRIBUTE o" + i + "	REAL");

            pw.println("@ATTRIBUTE o 	{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69}");
            pw.println("@DATA");

            for (int i = 0; i < classNames.length; i++) {

                for (int j = 1; j <= 5; j++) {
                    Image img = Load.invoke(pathTrain + classNames[i] + "-" + j + ".png");

                    double[] featureMoment = oznitelikHesapla(img, "moment");//moment uygula

                    for (int k = 0; k < featureMoment.length; k++)
                        pw.print(featureMoment[k] + ",");

                    double[] featureMomentWithSobel = oznitelikHesapla(img, "momentwithsobel");//sobel ile kenar çıkar sonra moment al

                    for (int k = 0; k < featureMomentWithSobel.length; k++)
                        pw.print(featureMomentWithSobel[k] + ",");

                    double[] featureHistogram = oznitelikHesapla(img, "histogram");//histogram uygula

                    for (int k = 0; k < featureHistogram.length; k++)
                        pw.print(featureHistogram[k] + ",");

                    double[] featureHistogramWithSobel = oznitelikHesapla(img, "histogramwithsobel");//sobel ile kenar çıkar sonra histogram al

                    for (int k = 0; k < featureHistogramWithSobel.length; k++)
                        pw.print(featureHistogramWithSobel[k] + ",");

                    double[] featureConvexHog = oznitelikHesapla(img, "convexHog");//convexHog uygula

                    for (int k = 0; k < featureConvexHog.length; k++)
                        pw.print(featureConvexHog[k] + ",");

                    double[] featureConvexHogWithSobel = oznitelikHesapla(img, "convexHogwithsobel");//sobel ile kenar çıkar sonra convexHog al

                    for (int k = 0; k < featureConvexHogWithSobel.length; k++)
                        pw.print(featureConvexHogWithSobel[k] + ",");

                    double[] featureBic = oznitelikHesapla(img, "bic");//bic uygula

                    for (int k = 0; k < featureBic.length; k++)
                        pw.print(featureBic[k] + ",");

                    double[] featureBicWithSobel = oznitelikHesapla(img, "bicwithsobel");//sobel ile kenar çıkar sonra bic al

                    for (int k = 0; k < featureBicWithSobel.length; k++)
                        pw.print(featureBicWithSobel[k] + ",");


                    pw.println(i);

                    System.err.println(i + " " + j);
                }
            }
            pw.close();

            // sinama kümesi
            PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter("test_b.arff")));

            pw2.println("@RELATION mpeg7shape1-B");

            for (int i = 1; i <= featureVectorLength; i++)
                pw2.println("@ATTRIBUTE o" + i + "	REAL");

            pw2.println("@ATTRIBUTE o 	{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69}");
            pw2.println("@DATA");

            Date startDate = new Date();
            for (int i = 0; i < classNames.length; i++) {

                for (int j = 11; j <= 15; j++) {
                    Image img = Load.invoke(pathTest + classNames[i] + "-" + j + ".png");

                    double[] featureMoment = oznitelikHesapla(img, "moment");//moment uygula

                    for (int k = 0; k < featureMoment.length; k++)
                        pw2.print(featureMoment[k] + ",");

                    double[] featureMomentWithSobel = oznitelikHesapla(img, "momentwithsobel");//sobel ile kenar çıkar sonra moment al

                    for (int k = 0; k < featureMomentWithSobel.length; k++)
                        pw2.print(featureMomentWithSobel[k] + ",");

                    double[] featureHistogram = oznitelikHesapla(img, "histogram");//histogram uygula

                    for (int k = 0; k < featureHistogram.length; k++)
                        pw2.print(featureHistogram[k] + ",");

                    double[] featureHistogramWithSobel = oznitelikHesapla(img, "histogramwithsobel");//sobel ile kenar çıkar sonra histogram al

                    for (int k = 0; k < featureHistogramWithSobel.length; k++)
                        pw2.print(featureHistogramWithSobel[k] + ",");


                    double[] featureConvexHog = oznitelikHesapla(img, "convexHog");//convexHog uygula

                    for (int k = 0; k < featureConvexHog.length; k++)
                        pw2.print(featureConvexHog[k] + ",");

                    double[] featureConvexHogWithSobel = oznitelikHesapla(img, "convexHogwithsobel");//sobel ile kenar çıkar sonra convexHog al

                    for (int k = 0; k < featureConvexHogWithSobel.length; k++)
                        pw2.print(featureConvexHogWithSobel[k] + ",");


                    double[] featureBic = oznitelikHesapla(img, "bic");//bic uygula

                    for (int k = 0; k < featureBic.length; k++)
                        pw2.print(featureBic[k] + ",");

                    double[] featureBicWithSobel = oznitelikHesapla(img, "bicwithsobel");//sobel ile kenar çıkar sonra bic al

                    for (int k = 0; k < featureBicWithSobel.length; k++)
                        pw2.print(featureBicWithSobel[k] + ",");



                    pw2.println(i);

                    System.err.println(i + " " + j);
                }
            }
            pw2.close();

            System.out.println("startDate :" + startDate.toString());
            Date endDate = new Date();
            System.out.println("endDate :" + endDate.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double[] oznitelikHesapla(Image img, String type) {
        double[] feature = null;


        if (type.equalsIgnoreCase("moment")) {
            feature = Moment.invoke(img, 1, 1);
        } else if (type.equalsIgnoreCase("momentwithsobel")) {
            img = Sobel.invoke(img, Sobel.GRAD, true);
            feature = Moment.invoke(img, 1, 1);
        } else if (type.equalsIgnoreCase("histogram")) {
            feature = Histogram.invoke(img, false);
        } else if (type.equalsIgnoreCase("histogramwithsobel")) {
            img = Sobel.invoke(img, Sobel.GRAD, true);
            feature = Histogram.invoke(img, false);

        } else if (type.equalsIgnoreCase("convexHog")) {

            Image imgBinary = Threshold.invoke(img, 1 * DoubleImage.byteToDouble);
            Image borderImage = BInternGradient.invoke(imgBinary, FlatSE.square(3));
            feature = computeConvex(borderImage);

        } else if (type.equalsIgnoreCase("convexHogwithsobel")) {
            img = Sobel.invoke(img, Sobel.GRAD, true);

            Image imgBinary = Threshold.invoke(img, 1 * DoubleImage.byteToDouble);
            Image borderImage = BInternGradient.invoke(imgBinary, FlatSE.square(3));
            feature = computeConvex(borderImage);

        } else if (type.equalsIgnoreCase("bic")) {
            feature = BIC.invoke(Gray2Color.invoke(img));
        } else if (type.equalsIgnoreCase("bicwithsobel")) {
            img = Sobel.invoke(img, Sobel.GRAD, true);
            feature = BIC.invoke(Gray2Color.invoke(img));

        }


        return feature;
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


}