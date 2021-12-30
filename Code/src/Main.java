import java.io.IOException;

import javax.swing.JPanel;

public class Main	 {
	// Handles letters into frequency table
	static final String FILENAME = "lena_gray.bmp";
	
	// Evaluation Class
	static Evaluation evl = new Evaluation();

	public static void main(String[] args){
		// Handles letters into frequency table

		Images IH = null;
		//Try Catch just in case the file is not there
		try {
			IH = new Images(FILENAME);
			// Printing the results of the values and there compressed equivalent
			IH.printCompressedResults();
			// LINE BREAK
			System.out.println();

			// Display image
			IH.displayImage();
			
			// Convert and save image as JPEG
	        IH.saveImage();
	        
	        //Using the evaluation class
	        
	        // Gets size of file directly from file
	        int fileSizeBefore = evl.sizeOfFile(FILENAME);
	        
	        // counts the bit of compressed image 
	        int fileSizeAfter = evl.sizeOfCompressionFromString(IH.compressedImage);
	        
	        // Printing out evaluation of compression
	        System.out.println("SIZE BEFORE:		"+fileSizeBefore+" bits");
	        System.out.println("SIZE AFTER:		"+fileSizeAfter+" bits");
	        System.out.println("Saved Space:		"+evl.savedPercentage(fileSizeAfter, fileSizeBefore)+"%");
	        System.out.println("Compression Ratio:	"+evl.compressionRatio(fileSizeAfter, fileSizeBefore));
	        System.out.println("Compression Factor:	"+evl.compressionFactor(fileSizeAfter, fileSizeBefore));
	        // Takes a few seconds to load the entropy of Source image in gray scale
	        System.out.println("Entropy of Source:	"+ evl.entropy(IH.pixelMatrix));
	        // entropy of residue matrix
	        System.out.println("Entropy of Residue Matrix:	"+ evl.entropy(IH.residueMatrix));
	        // Average code length
	        System.out.println("Average Code Length:	"+evl.avarageCodeLength(IH.compressedImage));
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		
	}
	
}
