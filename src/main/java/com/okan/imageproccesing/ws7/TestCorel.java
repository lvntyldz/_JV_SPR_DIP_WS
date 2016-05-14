package com.okan.imageproccesing.ws7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vpt.Image;
import vpt.algorithms.color.BIC;
import vpt.algorithms.io.Load;
import vpt.util.Distance;

import java.io.File;
import java.util.Arrays;

/**
 *
 */
public class TestCorel
{


	private static final Logger LOGGER = LoggerFactory.getLogger(TestCorel.class);

	public static void main(String[] args){

		String path = "src/main/resources/images/corel34/";
		String[] dirs = {"kumsal","fil","cicek","dinozor"};
		
		Oznitelik[] oznitelikler = new Oznitelik[4 * 34];

		// ilk(0) görüntüde başla.
		int sorguGoruntusu = 2;
		
		TestCorel t = new TestCorel();
		
		try{
			// Once tum goruntuler icin oznitelikleri hesapla
			// her dizin icin
			for(int i = 0; i < dirs.length; i++){
				
				// dizindeki belge listesini edin
				File[] files = new File(path + dirs[i]).listFiles();
				
				// her belge icin
				for(int j = 0; j < files.length; j++){
					
					// adi *.jpg ile bitmiyorsa hesaba katma
					if(!files[j].getAbsolutePath().endsWith(".jpg")) continue;
					
					// Goruntuyu bellege yukle
					Image img = Load.invoke(files[j].getAbsolutePath());
					
					// ozniteligi hesapla
					LOGGER.info("öznitelik hesaplamaya başlıyor... j : " +j);
					LOGGER.info("file name: "+files[j].getName());
					oznitelikler[i * 34 + j] = t.new Oznitelik(oznitelikHesapla(img), i, files[j].getName());
					LOGGER.info("öznitelik hesaplamaya bitti.");
				}
			}

			// once sorgu goruntusunun tum digerlerine gore olan benzerliklerini hesapla
			for(int j = 0; j < oznitelikler.length; j++){
				LOGGER.info("sorguGoruntusu1 :"+sorguGoruntusu);
				oznitelikler[j].benzerlik = Distance.euclidean(oznitelikler[j].ozn, oznitelikler[sorguGoruntusu].ozn);
			}
			
			// azalan benzerlige gore onlari sirala
			Arrays.sort(oznitelikler);
			
			// simdi de bak bakalim en cok benzeyen ilk 34'e..bakalim kacta kaci, sorgu goruntusu ile ayni siniftan?
			double kactaKac = 0;
			
			for(int i = 0; i < 34; i++)
				if(oznitelikler[i].sinif == oznitelikler[sorguGoruntusu].sinif) kactaKac++;
			
			kactaKac = kactaKac/34 * 100;

			LOGGER.info("sorguGoruntusu 2:"+sorguGoruntusu);
			System.err.println("Sorgulanan goruntu: " + oznitelikler[sorguGoruntusu].belgeAdi);
			System.err.println("Geri erisim isabet orani: %" + kactaKac);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Oznitelik hesaplama yontemi
	private static double[] oznitelikHesapla(Image img){
		double[] feature = null;

		//feature =		ColorMoments.invoke(img);
		//feature =		AutoCorrelogramHSY733.invoke(img);
		feature =		BIC.invoke(img);
		//feature =		ColorMomentInvariants.invoke(img);
		//feature =		ColorStructureRGB64.invoke(img);


		return feature;
	}
	
	private class Oznitelik implements Comparable<Oznitelik>{
		double[] ozn;
		double benzerlik;
		int sinif;
		String belgeAdi;
		
		Oznitelik(double[] o, int sinif, String ad){
			ozn = o;
			this.sinif = sinif;
			this.belgeAdi = ad;
		}
		
		public int compareTo(Oznitelik r){
			
			if (r.benzerlik < this.benzerlik) return -1;
			else if (r.benzerlik > this.benzerlik) return 1;
			else return 0;
		}
	}
}