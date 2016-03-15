package com.okan.imageproccesing.ws5;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.display.Display2D;
import vpt.algorithms.io.Load;

public class Run3 {
	
	public static void main(String[] args) {
		
		Image immage = Load.invoke("./src/main/resources/images/samples/valve.png");
		Display2D.invoke(immage, "valve");
		
		//Yeni renkli görüntü oluştur
		ByteImage renkli = new ByteImage(immage.getXDim(), immage.getYDim(),3);
		
		for(int x=0; x<immage.getXDim(); x++){
			for(int y=0; y<immage.getYDim();  y++){
				
				int z = immage.getXYByte(x, y);

				int r = 0;
				if(z<=128){
					r=(int)(255.0/128)*z;
				}else{
					r = (int)(((-255.0/127)*z)+(255*255/127.0));
				}
				
				int g = 0;
				if(g>=100 && g<=170)g=z;
				
				int b=0;
				if(z<=100)b=z*2;
				
				renkli.setXYCByte(x, y, 0, r);
				renkli.setXYCByte(x, y, 1, g);
				renkli.setXYCByte(x, y, 2, b);
				
				
			}
		}
		
		Display2D.invoke(renkli,true);
		
	}

}
