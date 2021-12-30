import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Images {

	static FrequencyTable frequencyTable = null;
	static BinaryTree binaryTree = null;

	// Properties of Image
	int[][] pixelMatrix;
	static int[][] residueMatrix;

	static int height;
	static int width;

	String[][] compressedImage;
	BufferedImage decompressedImage;

	public Images(String imageName) throws IOException {
		// Gets the pixels at a gray scale value
		pixelMatrix = readImageGrayScale(imageName);
		// Set the with and the height of the image based on the matrix
		height = pixelMatrix.length;
		width = pixelMatrix[0].length;

		// Gets the residueized matrix of the image
		residueMatrix = residueMatrix(pixelMatrix);

		// Make a frequency table of residue matrix
		frequencyTable = new FrequencyTable(residueMatrix);

		// Make a binary tree with the frequency table
		binaryTree = new BinaryTree(frequencyTable);

		compressedImage = getCompressedPixelMartix();
		decompressedImage = getDecomprressedImage(compressedImage);
	}

	// Read Image and return an array of in in gray scale
	int[][] readImageGrayScale(String fileName) throws IOException {

		// Open image
		BufferedImage bufferedImage = (BufferedImage) ImageIO.read(new File(fileName));

		// Convert coloured image to a gray scale pixel matrix
		int grayScalePixelMatrix[][] = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {
				Color c = new Color(bufferedImage.getRGB(x, y));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int grayScale = (red + green + blue) / 3;
				grayScalePixelMatrix[x][y] = grayScale;
			}
		}
		return grayScalePixelMatrix;
	}
	// readImage ..

	static int[][] residueMatrix(int[][] pixelMatrix) {
		int residueMatrix[][] = new int[height][width];
		int diffToLeft = 0;
		// print image matrix
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if (!(y - 1 < 0))
					diffToLeft = pixelMatrix[x][y - 1];
				else
					diffToLeft = 0;

				residueMatrix[x][y] = pixelMatrix[x][y] - diffToLeft;
			}
		}
		return residueMatrix;
	}

	// Decompressses image
	static BufferedImage getDecomprressedImage(String[][] compressedImage) {
		// Gets the value (the residualised pixel) from compressed word (in bit)
		int[][] residueMatrix = new int[height][width];
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				String compressedPixel = compressedImage[x][y];
				String DecompressedResiduePixel = null;
				for (Element result : binaryTree.dictionary) {
					if (result.getBits().equals(compressedPixel)) {
						DecompressedResiduePixel = result.getUnit();
					}
				}
				residueMatrix[x][y] = Integer.valueOf(DecompressedResiduePixel);
			}
		}

		// reversing the residualized pixels
		int[][] pixelMatrix = new int[height][width];
		int diffToLeft = 0;
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if (!(y - 1 < 0))
					diffToLeft = pixelMatrix[x][y - 1];
				else
					diffToLeft = 0;

				pixelMatrix[x][y] = residueMatrix[x][y] + diffToLeft;
			}
		}

		// Constructing the image
		BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < pixelMatrix[0].length; y++) {
				Color newColor = new Color(pixelMatrix[x][y], pixelMatrix[x][y], pixelMatrix[x][y]);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
		return image;
	}

	// Save image after compression
	void saveImage() {
		File outputfile = new File("output.jpeg");
		try {
			ImageIO.write(decompressedImage, "jpeg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Displays image in window
	void displayImage() {
		JFrame jFrame = new JFrame();
		jFrame.add(new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(decompressedImage, 0, 0, null);
			}
		});
		jFrame.setSize(width, height);
		jFrame.setVisible(true);
	}

	// Get the compressed Image
	public static String[][] getCompressedPixelMartix() {
		String[][] compressedImage = new String[height][width];
		// Loops true every pixel and gets the compressed results from the tree
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				for (Element result : binaryTree.dictionary) {
					if (result.getUnit().equals(String.valueOf(residueMatrix[x][y]))) {
						compressedImage[x][y] = result.getBits();
						break;
					}
				}
			}
		}
		return compressedImage;
	}

	// Print results of compressed code
	void printCompressedResults() {
		binaryTree.printResults();
	}

}
