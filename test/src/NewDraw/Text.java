package NewDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Text extends Shape implements Serializable {

	public Text() {

	}

	public Text(int x1, int y1, int x2, int y2, Color color, int width, String string) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.width = width;
		this.string = string;
	}

	public void Draw(Graphics2D g) {
		g.drawString(string, x1, y1);

	}

}