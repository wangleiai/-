package NewDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.activation.DataContentHandler;
import javax.lang.model.element.Element;

import java.awt.*;

public class Line extends Shape implements Serializable {

	// 子类构造函数
	public Line(int x1, int y1, int x2, int y2, Color color, int width) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.width = width;

	}

	public void MidpointLine(int x0, int y0, int x1, int y1, Graphics2D g) {
		int a, b, d1, d2, d, x, y;
		float m;
		if (x1 < x0) {

			d = x0;
			x0 = x1;
			x1 = d;
			d = y0;
			y0 = y1;
			y1 = d;
		}
		a = y0 - y1;
		b = x1 - x0;
		if (b == 0)
			m = -1 * a * 100;
		else
			m = (float) a / (x0 - x1);
		x = x0;
		y = y0;
		g.drawOval(x, y, 1, 1);
		if (m >= 0 && m <= 1) {
			d = 2 * a + b;
			d1 = 2 * a;
			d2 = 2 * (a + b);
			while (x < x1) {
				if (d <= 0) {
					x++;
					y++;
					d += d2;
				} else {
					x++;
					d += d1;
				}
				g.drawOval(x, y, 1, 1);
			}
		} else if (m <= 0 && m >= -1) {
			d = 2 * a - b;
			d1 = 2 * a - 2 * b;
			d2 = 2 * a;
			while (x < x1) {
				if (d > 0) {
					x++;
					y--;
					d += d1;
				} else {
					x++;
					d += d2;
				}
				g.drawOval(x, y, 1, 1);
			}
		} else if (m > 1) {
			d = a + 2 * b;
			d1 = 2 * (a + b);
			d2 = 2 * b;
			while (y < y1) {
				if (d > 0) {
					x++;
					y++;
					d += d1;
				} else {
					y++;
					d += d2;
				}
				g.drawOval(x, y, 1, 1);
			}
		} else {
			d = a - 2 * b;
			d1 = -2 * b;
			d2 = 2 * (a - b);
			while (y > y1) {
				if (d <= 0) {
					x++;
					y--;
					d += d2;
				} else {
					y--;
					d += d1;
				}
				g.drawOval(x, y, 1, 1);
			}
		}
	}

	// 重写父类的Draw方法，实现直线的绘制
	public void Draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		// g.drawLine(x1, y1, x2, y2);
		MidpointLine(x1, y1, x2, y2, g);
	}
}
