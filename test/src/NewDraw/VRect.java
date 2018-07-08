package NewDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class VRect extends Shape implements Serializable {

	public VRect() {

	}

	// 矩形的构造方法
	public VRect(int x1, int y1, int x2, int y2, Color color, int width) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.width = 2;
	}

	// 重写父类的Draw方法，实现矩形的绘制
	public void Draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		for (int i = x1; i <= x1 + x2; i++) {
			if (i % 3 != 0) {
				i = i + 3;

			}
			g.drawOval(i, y1, 1, 1);
		}

		for (int i = y1; i <= y1 + y2; i++) {
			if (i % 3 == 0) {
				i = i + 3;

			}
			g.drawOval(x1, i, 1, 1);
		}
		for (int i = y1; i <= y1 + y2; i++) {
			if (i % 3 == 0) {
				i = i + 2;
			}
			g.drawOval(x1 + x2, i, 1, 1);
		}
		for (int i = x1; i <= x1 + x2; i++) {
			if (i % 3 == 0)
				i = i + 2;
			g.drawOval(i, y1 + y2, 1, 1);
		}
	}

}