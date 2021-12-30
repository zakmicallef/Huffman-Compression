import java.io.File;
import java.util.ArrayList;

public class Evaluation {
	//Compression Ratio
	float compressionRatio(int fileSizeAfter, int fileSizeBefore) {
		return (float) fileSizeAfter/fileSizeBefore;
	}
	//Compression Factor
	float compressionFactor(int fileSizeAfter, int fileSizeBefore) {
		return (float) fileSizeBefore/fileSizeAfter;
	}
	//Saving Percentage
	float savedPercentage(int fileSizeAfter, int fileSizeBefore) {
		return (float) (fileSizeBefore-fileSizeAfter)*100/fileSizeBefore;
	}
	//Size of file
	int sizeOfFile(String path) {
		File f = new File(path);
		// The return value is unspecified if this pathname denotes a directory. 
		// This I found that time 80 gets the size in bytes
		return (int) f.length()*8;
	}
	//size of compression (array of bits)
	int sizeOfCompressionFromString(String[] bitChunks) {
		int total = 0;
		for(String bits : bitChunks){
			total += bits.length();
		}
		return total;
	}
	int sizeOfCompressionFromString(String[][] pictureBits) {
		int total = 0;
		for(int x = 0;x < pictureBits[0].length; x++) {
			for(String bits : pictureBits[x]){
				total += bits.length();
			}			
		}
		return total;
	}
	//entropy
	float entropy(String[][] pictureBits) {
		ArrayList<String> bitWords = new ArrayList<String>();
		for(int x = 0;x < pictureBits[0].length; x++) {
			for(String bits : pictureBits[x]){
				bitWords.add(bits);
			}			
		}
		
		int totalAmoutOfVaulse = bitWords.size();
		ArrayList<Float> problitys = new ArrayList<Float>();
		while(!bitWords.isEmpty()) {
			problitys.add((float) FrequncyOfBitWord(bitWords.get(0), bitWords)/totalAmoutOfVaulse);
			bitWords = removeBitWord(bitWords.get(0), bitWords);
		}
		float entropy = 0;
		for(int i = 0;i < problitys.size();i ++)
		entropy += (problitys.get(i)*(Math.log(problitys.get(i)) / Math.log(2)));
		entropy *= -1;
		return entropy;
	}
	
	float entropy(int[][] pixelMatrix) {
		ArrayList<String> bitWords = new ArrayList<String>();
		for(int x = 0;x < pixelMatrix[0].length; x++) {
			for(int pixelValue : pixelMatrix[x]){
				bitWords.add(String.valueOf(pixelValue));
			}			
		}
		
		int totalAmoutOfVaulse = bitWords.size();
		ArrayList<Float> problitys = new ArrayList<Float>();
		while(!bitWords.isEmpty()) {
			problitys.add((float) FrequncyOfBitWord(bitWords.get(0), bitWords)/totalAmoutOfVaulse);
			bitWords = removeBitWord(bitWords.get(0), bitWords);
		}
		float entropy = 0;
		for(int i = 0;i < problitys.size();i ++)
		entropy += (problitys.get(i)*(Math.log(problitys.get(i)) / Math.log(2)));
		entropy *= -1;
		return entropy;
	}
	
	int FrequncyOfBitWord(String word,ArrayList<String> bitWords) {
		int count = 0;
		for(String _letter : bitWords) {
			if(_letter.equals(word)) count ++;
		}
		return count;
	}
	
	ArrayList<String> removeBitWord(String word, ArrayList<String> bitWords){
		int size = bitWords.size();
		for(int index = size-1; index >= 0; index --) {
			if(bitWords.get(index).equals(word)) bitWords.remove(index);
		}
		return bitWords;
	}
	//Average code Length
	float avarageCodeLength(String[][] compressedImage) {
		// TODO Auto-generated method stub
		int count = 0;
		int totalBits = 0;
		for(int x = 0;x < compressedImage[0].length; x++) {
			for(String bits : compressedImage[x]){
				count ++;
				totalBits += bits.length();
			}			
		}
		return (float) totalBits/count;
	}
}
