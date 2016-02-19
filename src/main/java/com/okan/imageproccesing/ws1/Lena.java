package com.okan.imageproccesing.ws1;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

/**
 * Created by leventyildiz on 11/02/16.
 */
public class Lena {

    public static void lena2X() {

        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");

        // genisligi ogren
        int genislik = img.getXDim();

        // yuksekligi ogren
        int yukseklik = img.getYDim();
        System.err.println(genislik + " " + yukseklik);


        Image img2 = new ByteImage(512, 512);

        for (int i = 0; i < genislik; i++) {

            for (int j = 0; j < yukseklik; j++) {

                int p1 = img.getXYByte(i, j);

                img2.setXYByte(2 * i, 2 * j, p1);
                img2.setXYByte(2 * i, 2 * j + 1, p1);
                img2.setXYByte(2 * i + 1, 2 * j, p1);
                img2.setXYByte(2 * i + 1, 2 * j + 1, p1);

            }

        }


        // yeniden goruntule
        Display2D.invoke(img2);


    }


    public static void maskLena() {

        // Goruntu belgesini diskten bellege aktar
        Image img = Load.invoke("./src/main/resources/images/samples/lennaGray.png");

        Display2D.invoke(img);


        // genisligi ogren
        int genislik = img.getXDim();

        // yuksekligi ogren
        int yukseklik = img.getYDim();
        System.err.println(genislik + " " + yukseklik);


        Image img2 = new ByteImage(256, 256);

        for (int i = 0; i < genislik; i++) {

            for (int j = 0; j < yukseklik; j++) {

                int p1 = img.getXYByte(i, j);


                //0xFFFFFFF0 ile bitmask uygulayarak
                //her byte tan son 4 bit'i çöpe attık.
                //img2.setXYByte(i, j, (p1 & 0xFFFFFFF0));


                //0xFFFFFFE0 ile bitmask uygulayarak
                //her byte tan son 5 bit'i çöpe attık.
                //img2.setXYByte(i, j, (p1 & 0xFFFFFFE0));


                //0xFFFFFFC0 ile bitmask uygulayarak
                //her byte tan son 6 bit'i çöpe attık.
                //img2.setXYByte(i, j, (p1 & 0xFFFFFFC0));


                //0xFFFFFF0F ile bitmask uygulayarak
                //Ayrıntıları tutup ana hatları çöpe attık(üsttekilerin tam tersi).
                img2.setXYByte(i, j, (p1 & 0xFFFFFF0F));


            }
        }
        // yeniden goruntule
        Display2D.invoke(img2);

    }


    public static void main(String[] args) {

        lena2X();
        maskLena();


    }


}
