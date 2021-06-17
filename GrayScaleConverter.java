/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel : outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixel's red to average
            pixel.setRed(avg);
            //set pixel's green to average
            pixel.setGreen(avg);
            //set pixel's blue to average
            pixel.setBlue(avg);
        }
        //outImage is your answer
        return outImage;
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
	
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // create an image object using the file.
            ImageResource inImage = new ImageResource(f);
            // create an image object by making the previous image object gray.
            ImageResource gray = makeGray(inImage);
            // grep the name of the  orginal image objject
            String fname = inImage.getFileName();
            // create string object for the new name 
	    String newName = "grey-" + fname;
	    // assign the new name to the gray image object
	    gray.setFileName(newName);
	    // show image
            gray.draw();
            // save image
            gray.save();
        }
    }
}
