package com.okan.imageproccesing.hwlast.util;


public class Hog {


    public static double[] callHog(vpt.Image img) {
        int width = img.getXDim();
        int height = img.getYDim();

        double G = 0;
        double angle = 0;
        double total_G = 0;

        double[] gradients = new double[width*height];
        double[] magnitudes = new double[width*height];

        // horizontal - vertical filter
        int filter[] = { -1, 0, 1 };
        int t=0;
        int offset = filter.length / 2;
        for (int i = offset; i < width - (offset + 1); i++) {
            for (int j = offset; j < height - (offset + 1); j++) {
                double vertical_value = 0;
                double horizontal_value = 0;
                for (int k = -offset; k <= offset; k++) {
                    int xloc = i + k;
                    int yloc = j + k;
                    horizontal_value += img.getXYByte(xloc, j) * filter[k+1];
                    vertical_value += img.getXYByte(i, yloc) * filter[k+1];

                }

                //
                G = Math.sqrt(Math.pow(vertical_value, 2) + Math.pow(horizontal_value, 2));
                angle = Math.atan2(vertical_value, horizontal_value) * 180 / Math.PI;

                if(angle<=0)
                    angle = angle + 360;
                //
                gradients[t] = G;
                magnitudes[t] = angle;
                total_G+=G;

                t++;

            }
        }

        return histogram(gradients,magnitudes,total_G);
    }


    public static double[] histogram(double[] gradients, double[] magnitudes, double total_G){
        double[] histogram = new double[361];

        for (int i = 0; i<gradients.length; i++) {
            histogram[(int)magnitudes[i]] += gradients[i];
        }

        for (int i = 0; i<histogram.length; i++) {
            histogram[i] /= total_G;
        }


        return histogram;
    }



}
