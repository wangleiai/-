package NewDraw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class FloodFillScanLineAlgorithm {

	private BufferedImage image;

	private int newRGB;
	private int targetRGB;

	private int x;
	private int y;

	private int width;
	private int height;
	public ArrayList list;
	public Graphics2D g;

	public FloodFillScanLineAlgorithm(int x, int y, int newRGB, ArrayList list, Graphics2D g) {
		this.list = list;

		// this.image = image;
		this.image = new BufferedImage(572, 428, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, 572, 428);

		for (int i = 0; i < list.size(); i++) {
			Shape shape = (Shape) list.get(i);
			shape.Draw(graphics2d);
		}

		this.x = x;
		this.y = y;

		this.width = image.getWidth();
		this.height = image.getHeight();

		this.targetRGB = image.getRGB(x, y);

		this.newRGB = newRGB;

		this.g = g;
	}

	public void fill() {

		if (targetRGB == newRGB) {
			return;
		}

		floodFillScanLine(x, y);
	}

	public void floodFillScanLine(int x, int y) {

		int y0;

		y0 = y;
		while (y0 < height && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);

			g.drawOval(x, y0, 1, 1);
			Shape point = new Oval(x, y0, 1, 1, g.getColor(), 1);
			point.Draw(g);
			list.add(point);

			y0++;
		}

		y0 = y - 1;
		while (y0 >= 0 && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);

			g.drawOval(x, y0, 1, 1);
			Shape point = new Oval(x, y0, 1, 1, g.getColor(), 1);
			point.Draw(g);
			list.add(point);

			y0--;
		}

		y0 = y;
		while (y0 < height && image.getRGB(x, y0) == newRGB) {
			if (x > 0 && image.getRGB(x - 1, y0) == targetRGB) {
				floodFillScanLine(x - 1, y0);
			}
			y0++;
		}

		y0 = y - 1;
		while (y0 >= 0 && image.getRGB(x, y0) == newRGB) {
			if (x > 0 && image.getRGB(x - 1, y0) == targetRGB) {
				floodFillScanLine(x - 1, y0);
			}
			y0--;
		}
		
		y0 = y;
		while (y0 < height && image.getRGB(x, y0) == newRGB) {
			if (x < width - 1 && image.getRGB(x + 1, y0) == targetRGB) {
				floodFillScanLine(x + 1, y0);
			}
			y0++;
		}
		
		y0 = y - 1;
		while (y0 >= 0 && image.getRGB(x, y0) == newRGB) {
			if (x < width - 1 && image.getRGB(x + 1, y0) == targetRGB) {
				floodFillScanLine(x + 1, y0);
			}
			y0--;
		}
	}
}
