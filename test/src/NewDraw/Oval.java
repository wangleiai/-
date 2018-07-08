package NewDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.imageio.event.IIOReadWarningListener;

public class Oval extends Shape implements Serializable {

	public Oval() {

	}

	public Oval(int x1, int y1, int x2, int y2, Color color, int width) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.width = width;
	}

	// 画圆形
	public void Draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));

		// 圆心
		int r = Math.min(x2, y2) / 2;
		int x0 = x1 + x2 / 2;
		int y0 = y1 + y2 / 2;
		for (int i = x0 - r; i <= x0 + r; i++) {
			g.drawOval(i, (int) (y0 - Math.sqrt(r * r - (x0 - i) * (x0 - i))), 1, 1);
			g.drawOval(i, (int) (y0 + Math.sqrt(r * r - (x0 - i) * (x0 - i))), 1, 1);
		}
		for (int i = y0 - r; i <= y0 + r; i++) {
			g.drawOval((int) (x0 - Math.sqrt(r * r - (y0 - i) * (y0 - i))), i, 1, 1);
			g.drawOval((int) (x0 + Math.sqrt(r * r - (y0 - i) * (y0 - i))), i, 1, 1);
		}
	}

}