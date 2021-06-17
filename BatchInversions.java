
/**
 * 在这里给出对类 BatchInversions 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage){
        // create an empty image object with the same size
        ImageResource invertedImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // work on each pixel
        for (Pixel px : invertedImage.pixels()) {
            // get the corresponding pixel on inImage
            Pixel inPx = inImage.getPixel(px.getX(),px.getY());
            // invert RGB values by (255-original values)
            px.setRed(255-inPx.getRed());
            px.setBlue(255-inPx.getBlue());
            px.setGreen(255-inPx.getGreen());
        }
        
        return invertedImage;
    }
    
    public void testInvert() {
        ImageResource ir = new ImageResource();
        ImageResource invert = makeInversion(ir);
        invert.draw();
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // create an image object using the file.
            ImageResource inImage = new ImageResource(f);
            // create an image object by making the previous image object gray.
            ImageResource outImage = makeInversion(inImage);
            // grep the name of the  orginal image objject
            String fname = inImage.getFileName();
            // create string object for the new name 
	    String newName = "invert-" + fname;
	    // assign the new name to the gray image object
	    outImage.setFileName(newName);
	    // show image
            outImage.draw();
            // save image
            outImage.save();
        }
    }
    
}
