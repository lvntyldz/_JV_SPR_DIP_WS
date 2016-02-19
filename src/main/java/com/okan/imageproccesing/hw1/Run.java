package com.okan.imageproccesing.hw1;

/**
 * Created by leventyildiz on 14/02/16.
 */
public class Run {


    public static void main(String[] args) {

        //calculate histogram
        Histogram histogram = new Histogram();
        histogram.calculateHistogram();

        Contrast contrast;
        //Rescale Img(Change Contrast)
        contrast = new Contrast();
        contrast.rescaleImg();


        Average average = new Average();
        average.calculateAverage();

    }

}
