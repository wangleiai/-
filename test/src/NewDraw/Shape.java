package NewDraw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

//抽象父类  
public abstract class Shape implements Serializable {

	public int x1, y1, x2, y2;// 绘制图形的坐标
	public Color color;// 画笔颜色
	public int width;// 画笔粗细
	public String string;

	// 抽象的Draw方法
	public abstract void Draw(Graphics2D g);

}