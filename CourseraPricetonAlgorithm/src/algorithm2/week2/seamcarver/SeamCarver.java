package algorithm2.week2.seamcarver;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
	private Picture currentPicture;
	private int[][] rgbs;
	
	public SeamCarver(Picture picture) // create a seam carver object based on the given picture
	{
		if (picture == null) throw new IllegalArgumentException();
		// copy图片
		currentPicture = new Picture(picture);			
		int height = picture.height();
		int width = picture.width();
		// 1.求RGB
		rgbs = new int[height][width];
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				rgbs[row][col] = currentPicture.getRGB(col, row);
			}
		}
		/**
		 * 这里的代码是将enregy当成成员变量，这样内存会多，
		 * 
		 * 
		// 计算energy
		// 2.边界赋值1000
		energy = new double[width()][height()];
		for (int row = 0; row < height(); row++) {
			energy[0][row] = 1000.0;
			energy[width() - 1][row] = 1000.0;
		}
		for (int col = 0; col < width(); col++) {
			energy[col][0] = 1000.0;
			energy[col][height() - 1] = 1000.0;
		}
		
		// 3. 根据rgb值计算energy
		for (int col = 1; col < width() - 1; col++) {
			for (int row = 1; row < height() - 1; row++) {
				int rx = getRed(rgbs[col - 1][row])   - getRed(rgbs[col + 1][row]);
				int gx = getGreen(rgbs[col - 1][row]) - getGreen(rgbs[col + 1][row]);
				int bx = getBlue(rgbs[col - 1][row])  - getBlue(rgbs[col + 1][row]);
				int ry = getRed(rgbs[col][row - 1])   - getRed(rgbs[col][row + 1]);
				int gy = getGreen(rgbs[col][row - 1]) - getGreen(rgbs[col][row + 1]);
				int by = getBlue(rgbs[col][row - 1])  - getBlue(rgbs[col][row + 1]);
				energy[col][row] = Math.sqrt(rx * rx + gx * gx + bx * bx +
						ry * ry + gy * gy + by * by);
			}
		}
		*/
	}
	
	private int getRed(int rgb) {
		return (rgb >> 16) & 0xFF;
	}
	private int getGreen(int rgb) {
		return (rgb >>  8) & 0xFF;
	}
	private int getBlue(int rgb) {
		return (rgb >>  0) & 0xFF;
	}
	
	public Picture picture() // current picture
	{
		Picture picture = new Picture(width(), height());
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				picture.setRGB(col, row, rgbs[row][col]);
			}
		}
		currentPicture = picture;
		return currentPicture;
	}

	public int width() // width of current picture
	{
		return rgbs[0].length;
	}

	public int height() // height of current picture
	{
		return rgbs.length;
	}

	public double energy(int x, int y) // energy of pixel at column x and row y
	{
		validateColumnIndex(x);
		validateRowIndex(y);
		
		double energy = 1000.0;
		if (x != 0 && x != width() - 1 && y != 0 && y != height() - 1) {
			int rx = getRed(rgbs[y - 1][x])   - getRed(rgbs[y + 1][x]);
			int gx = getGreen(rgbs[y - 1][x]) - getGreen(rgbs[y + 1][x]);
			int bx = getBlue(rgbs[y - 1][x])  - getBlue(rgbs[y + 1][x]);
			int ry = getRed(rgbs[y][x - 1])   - getRed(rgbs[y][x + 1]);
			int gy = getGreen(rgbs[y][x - 1]) - getGreen(rgbs[y][x + 1]);
			int by = getBlue(rgbs[y][x - 1])  - getBlue(rgbs[y][x + 1]);
			energy = Math.sqrt(rx * rx + gx * gx + bx * bx +
					ry * ry + gy * gy + by * by);
		}
		return energy;
	}
	
	private void transposeRGBs() {
		int[][] newRGBs = new int[width()][height()];
		for (int col = 0; col < height(); col++) {
			for (int row = 0; row < width(); row++) {
				newRGBs[row][col] = rgbs[col][row];
			}
		}
		rgbs = newRGBs;
	}
	
	public int[] findHorizontalSeam() // sequence of indices for horizontal seam
	{
		transposeRGBs();
		int[] shortestPath = findVerticalSeam();
		transposeRGBs();
		return shortestPath;
	}

	public int[] findVerticalSeam() // sequence of indices for vertical seam
	{
		// 使用拓扑排序， 每次循环一行，因为每行只能从上一行得到
		// 遍历到最后一行的时候，选出energyTo最小的，然后一直找edgeTo，放入stack，返回
		
		// 1. 初始化energy
		double energy[][] = new double[height()][width()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				energy[row][col] = energy(col, row);
			}
		}
		
		double[][] energyTo = new double[height()][width()];
		// 只要记录 col就好，因为row必然是row - 1
		int[][] edgeTo = new int[height()][width()];
		// 第一行到达的energyTo都是1000，初始化第一行
		for (int row = 0; row < height(); row++) {
			energyTo[row][0] = energy(0, row);
		}
		
		// 2. 外循环遍历row，内循环遍历col
		// 计算每层每个点达到的时候的energy，并记录edgeTo
		for (int row = 1; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				// 返回能量最小的，如果有相同的 返回col - 1
				int minEnergyCol = col;
				if (col > 0  && energyTo[row - 1][col - 1] <= energyTo[row - 1][minEnergyCol])
					minEnergyCol = col - 1;
				if (col < width() - 1  && energyTo[row - 1][col + 1] < energyTo[row - 1][minEnergyCol])
					minEnergyCol = col + 1;
				energyTo[row][col] = energyTo[row - 1][minEnergyCol] + energy[row][col];
				edgeTo[row][col] = minEnergyCol;
			}
		}
		
		// 3. 遍历最后一行，找到最小的energyTo
		int minEnergyCol = 0;
		double minEnergy = energyTo[height() - 1][0];
		for (int col = 0; col < width(); col++) {
			if (energyTo[height() - 1][col] < minEnergy) {
				minEnergyCol = col;
				minEnergy = energyTo[height() - 1][col]; 
			}
		}
		
		// 4. 根据edgeTo从最后一行往回找，既然要返回int数组，可以倒着开始输入即可
		int[] shortestPath = new int[height()];
		for (int row = height() - 1; row >= 0; row--) {
			shortestPath[row] = minEnergyCol;
			minEnergyCol = edgeTo[row][minEnergyCol];
		}
		
		return shortestPath;
	}

	public void removeHorizontalSeam(int[] seam) // remove horizontal seam from current picture
	{
		validateSeam(seam, width());
		transposeRGBs();
		removeVerticalSeam(seam);
		transposeRGBs();
	}

	public void removeVerticalSeam(int[] seam) // remove vertical seam from current picture
	{
		validateSeam(seam, height());
		// 成员变量只有picture 只更新他就行了
		int[][] newRGBs = new int[height()][width() - 1];
		for (int row = 0; row < height(); row++) {
				int delIndx = seam[row];
				if (delIndx != 0)
					System.arraycopy(rgbs[row], 0, newRGBs[row], 0, delIndx);
				if (delIndx != width() - 1)
					System.arraycopy(rgbs[row], delIndx + 1, newRGBs[row], delIndx, width() - delIndx - 1);
			}
		rgbs = newRGBs;
	}
	
	private void validateRowIndex(int row) {
        if (row < 0 || row >= height())
            throw new IllegalArgumentException("row index must be between 0 and " + (height() - 1) + ": " + row);
    }

    private void validateColumnIndex(int col) {
        if (col < 0 || col >= width())
            throw new IllegalArgumentException("column index must be between 0 and " + (width() - 1) + ": " + col);
    }
    
    private void validateSeam(int[] seam, int length) {
    	if (seam == null) throw new IllegalArgumentException();
		if (seam.length != length) throw new IllegalArgumentException();
		int range = width();
		if (length == range) range = height();
		for (int i = 0; i < length; i++) {
			if (seam[i] < 0 || seam[i] > range - 1) {
				throw new IllegalArgumentException("超出范围");
			}
			if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
				throw new IllegalArgumentException("格式不正确");
			}
		}
    }
}
