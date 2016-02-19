package com.okan.imageproccesing.hw1;

import vpt.Image;
import vpt.algorithms.io.Load;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leventyildiz on 14/02/16.
 */
public class Histogram {


    public void calculateHistogram() {

        //loade image file
        Image img = Load.invoke("./src/main/resources/images/samples/barbara.jpg");


        int imgHeight = img.getYDim();
        int imgWidth = img.getXDim();
        int point;

        Map<Integer, Integer> histogram = new HashMap<Integer, Integer>();

        for (int i = 0; i < imgWidth; i++) {

            for (int j = 0; j < imgHeight; j++) {

                point = img.getXYByte(i, j);

                if (histogram.containsKey(point)) {//if exist update

                    histogram.put(point, (histogram.get(point) + 1));

                } else {//if not exist update
                    histogram.put(point, 1);

                }//fi


            }//for finish

        }//for finish

        System.out.println(histogram.toString());

    }


}
