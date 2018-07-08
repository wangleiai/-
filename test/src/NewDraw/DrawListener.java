package NewDraw;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.security.DomainLoadStoreParameter;
import java.security.cert.X509CRLEntry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import org.w3c.dom.css.ElementCSSInlineStyle;
import org.w3c.dom.ls.LSInput;


public class DrawListener extends DrawBorder implements MouseListener, MouseMotionListener {

	public Graphics2D g;
	public int x1, y1, x2, y2, ox, oy, x3, y3;
	public ButtonGroup bg;
	public String command;
	public Color color;
	public DrawBorder db;
	public ArrayList list;
	public boolean flag = true;
	public JPanel cPanel;
	public BufferedImage bufferedImage0;
	public DrawBorder paint;

	public static final Stroke s1 = new BasicStroke(1);
	public static final Stroke s2 = new BasicStroke(10);
	public static final Stroke s3 = new BasicStroke(15);

	public Random r = new Random();

	// 构造函数1
	public DrawListener(Graphics g1) {
		g = (Graphics2D) g1;
	}

	// 构造函数2
	public DrawListener(Graphics g2, ButtonGroup bg2) {
		g = (Graphics2D) g2;
		bg = bg2;
	}

	// 构造函数3
	public DrawListener(Graphics g2, ButtonGroup bg2, DrawBorder db1, ArrayList list) {
		g = (Graphics2D) g2;
		bg = bg2;
		db = db1;
		this.list = list;

	}

	public void getcp(JPanel panelcenter, BufferedImage bufferedImage, DrawBorder drawBorder) {
		cPanel = panelcenter;
		bufferedImage0 = bufferedImage;
		paint = drawBorder;
	}

	// 鼠标按下事件监听
	public void mousePressed(MouseEvent e) {
		// 获取鼠标按下点的坐标
		x1 = e.getX();
		y1 = e.getY();
		System.out.println(x1 + " " + y1);

		// 判断选择的是左面板中的那个按钮被选中（前面已经设置每个按钮的名称了）
		ButtonModel bm = bg.getSelection();// 拿到按钮组中被选中的按钮
		command = bm.getActionCommand();// 拿到选中按钮的名字

	}

	public void mouseReleased(MouseEvent e) {
		// 获取鼠标释放的坐标
		x2 = e.getX();
		y2 = e.getY();

		if ("pic1".equals(command)) {
			Shape vr = new VRect(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2), g.getColor(),
					1);
			vr.Draw(g);
			list.add(vr);
		}

		// 如果选中的是绘制直线的按钮，那么根据鼠标按下点的坐标和释放点的左边绘制直线（两点确定一条直线）
		if ("pic10".equals(command)) {
			Shape line = new Line(x1, y1, x2, y2, g.getColor(), 1);
			line.Draw(g);
			list.add(line);
		} // 同理选中的是矩形按钮，那么绘制矩形（这里有绘制矩形的纠正，不纠正的话从右下角往左上角方向绘制矩形会出现问题，参看后面难点解析）
		else if ("pic12".equals(command)) {
			Shape rect = new Rect(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2),
					g.getColor(), 1);
			rect.Draw(g);
			list.add(rect);
		} // 绘制圆
		else if ("pic14".equals(command)) {
			Shape oval = new Oval(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2),
					g.getColor(), 1);
			oval.Draw(g);
			list.add(oval);
		} else if ("pic15".equals(command)) {
			Shape roundrect = new RoundRect(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2),
					40, 40, g.getColor(), 1);
			roundrect.Draw(g);
			list.add(roundrect);
		} // 绘制曲线
		else if ("pic13".equals(command)) {

			// 第一次画直线，设置标志
			if (flag) {
				Shape line = new Line(x1, y1, x2, y2, g.getColor(), 1);
				line.Draw(g);
				list.add(line);
				flag = false;
				// 记录这次鼠标释放的坐标，作为下次绘制直线的起点
				x3 = x2;
				y3 = y2;
				// 记录第一点击的坐标，绘制封闭的曲线
				ox = x1;
				oy = y1;
			} else {
				Shape line = new Line(x3, y3, x2, y2, g.getColor(), 1);
				line.Draw(g);
				list.add(line);
				// 记录上次鼠标释放的坐标
				x3 = x2;
				y3 = y2;
			}
		}
		// 取色功能
		else if ("pic4".equals(command)) {

			// 拿到相对面板的那个坐标
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			try {
				Robot robot = new Robot();// Robot类的使用

				// 拿到坐标点的那个矩形
				Rectangle rect = new Rectangle(x, y, 1, 1);
				// 生成该矩形的缓冲图片
				BufferedImage bi = robot.createScreenCapture(rect);
				// 得到图片的背景颜色
				int c = bi.getRGB(0, 0);
				// 将该颜色进行封装
				Color color = new Color(c);
				// 将取色笔取来的图片设置成画笔的颜色
				db.color = color;
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		} else if ("drop".equals(command)) {
			// 拿到相对面板的那个坐标
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			try {

				Robot robot = new Robot();// Robot类的使用

				// 拿到坐标点的那个矩形
				Rectangle rect = new Rectangle(x, y, 1, 1);
				// 生成该矩形的缓冲图片
				BufferedImage bi = robot.createScreenCapture(rect);
				// 得到图片的背景颜色
				int c = bi.getRGB(0, 0);
				// 将该颜色进行封装
				// System.out.println(c);
				Color color = new Color(c);

				int red = c >> 16 & 0xFF;
				int green = c >> 8 & 0xFF;
				int blue = c & 0xFF;
				String rString = red + "";
				String gString = green + "";
				String bString = blue + "";
				// System.out.println(rString);
				db.c1 = rString;
				db.c2 = gString;
				db.c3 = bString;
				db.red_textField.setText(rString);
				db.green_textField.setText(gString);
				db.blue_textField.setText(bString);

			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		// 多边形图形双击封闭
		int count = e.getClickCount();
		if (count == 2 && "pic13".equals(command)) {
			Shape line = new Line(ox, oy, x2, y2, g.getColor(), 1);
			line.Draw(g);
			list.add(line);

			flag = true;
		}

		// 文本框
		if (command.equals("pic9")) {
			String string = JOptionPane.showInputDialog("请输入内容");
			// System.out.println(string);

			Shape text = new Text(e.getX(), e.getY(), 0, 0, Color.black, 1, string);
			text.Draw(g);
			list.add(text);
		}
		// 填充颜色
		if (command.equals("pic3")) {

			Shape shape = (Shape) list.get(list.size() - 1);
			String s = (list.get(list.size() - 1)).toString();
			// System.out.println(s);
			if (s.charAt(8) == 'O' && s.charAt(9) == 'v' && s.charAt(10) == 'a' && s.charAt(11) == 'l') {
				// 圆填充
				// System.out.println(s);
				FloodFillScanLineAlgorithm fillScanLineAlgorithm = new FloodFillScanLineAlgorithm(e.getX(), e.getY(),
						g.getColor().getRGB(), list, g);
				fillScanLineAlgorithm.fill();

			} else if (s.charAt(8) == 'R' && s.charAt(9) == 'e' && s.charAt(10) == 'c' && s.charAt(11) == 't') {
				// 矩形填充
				FloodFillScanLineAlgorithm fillScanLineAlgorithm = new FloodFillScanLineAlgorithm(e.getX(), e.getY(),
						g.getColor().getRGB(), list, g);
				fillScanLineAlgorithm.fill();
			} else {
				FloodFillScanLineAlgorithm fillScanLineAlgorithm = new FloodFillScanLineAlgorithm(e.getX(), e.getY(),
						g.getColor().getRGB(), list, g);
				fillScanLineAlgorithm.fill();
			}

		}
	}

	public void mouseEntered(MouseEvent e) {
		color = db.color;// 设置画笔颜色
		g.setColor(color);
		g.setStroke(s1);
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		// 直线
		if ("pic10".equals(command)) {
			Shape line = new Line(x1, y1, x, y, g.getColor(), 1);
			list.add(line);
			// cPanel.setBackground(Color.white);
			// System.out.println(list.size());

			cPanel.repaint();
			line.Draw(g);
			if (!list.isEmpty())
				list.remove(list.size() - 1);
			// line.Draw(g);
		}

		// 画笔功能
		if ("pic6".equals(command)) {

			Shape line = new Line(x1, y1, x, y, g.getColor(), 1);
			line.Draw(g);
			list.add(line);

			x1 = x;
			y1 = y;
		}
		// 橡皮擦功能
		else if ("pic2".equals(command)) {
			db.color = Color.white;
			g.setColor(db.color);
			g.setStroke(s3);

			Shape line = new Line(x1, y1, x, y, g.getColor(), 15);
			line.Draw(g);
			list.add(line);

			x1 = x;
			y1 = y;
		}
		// 刷子功能
		else if ("pic7".equals(command)) {
			g.setStroke(s2);// 设置画笔 粗细

			Shape line = new Line(x1, y1, x, y, g.getColor(), 10);
			line.Draw(g);
			list.add(line);

			x1 = x;
			y1 = y;
		}
		// 喷桶功能
		else if ("pic8".equals(command)) {
			// 随机产生30个-15到15之间的整数
			for (int i = 0; i < 30; i++) {
				int xp = r.nextInt(31) - 15;
				int yp = r.nextInt(31) - 15;
				// 在x，y附件绘制原点

				Shape line = new Line(x + xp, y + yp, x + xp, y + yp, g.getColor(), 1);
				line.Draw(g);
				list.add(line);

			}

		}
	}

	public void mouseMoved(MouseEvent e) {

	}

	// 填充算法
	public void ScanLineFill(int x, int y, Color oldcolor, Color newcolor) throws AWTException {
		int xl, xr, i;
		boolean spanNeedFill = false;// 可能是false
		Seed pt = new Seed();
		Stack<Seed> stack = new Stack<Seed>();
		pt.x = x;
		pt.y = y;
		stack.push(pt);
		// compare( getColor(x, y), oldcolor) ;
		while (!stack.isEmpty()) {
			pt = stack.peek();
			stack.pop();
			y = pt.y;
			x = pt.x;

			// 向右填充
			while (compare(getColor(x, y), oldcolor)) {
				g.drawOval(x, y, 1, 1);
				Shape point = new Oval(x, y, 1, 1, g.getColor(), 1);
				point.Draw(g);
				list.add(point);
				System.out.println("right");
				x++;
			}
			xr = x - 1;
			x = pt.x - 1;
			// 向左填充
			while (compare(getColor(x, y), oldcolor)) {
				g.drawOval(x, y, 1, 1);
				Shape point = new Oval(x, y, 1, 1, g.getColor(), 1);
				point.Draw(g);
				list.add(point);
				System.out.println("left");
				x--;
			}
			xl = x + 1;
			// 处理上面一条扫描线
			x = xl;
			y = y + 1;
			while (x <= xr) {
				spanNeedFill = false;
				while (compare(getColor(x, y), oldcolor)) {
					spanNeedFill = true;
					x++;
				}
				if (spanNeedFill) {
					pt.x = x - 1;
					pt.y = y;
					stack.push(pt);
					spanNeedFill = false;
				}
				while ((!compare(getColor(x, y), oldcolor)) && x <= xr) {
					x++;
				}
				System.out.println("up");
			}

			// 处理下面一条扫描线
			x = xl;
			y = y - 2;
			while (x <= xr) {
				System.out.println("down2");
				spanNeedFill = false;
				System.out.println(compare(getColor(x, y), oldcolor));

				while (compare(getColor(x, y), oldcolor)) {
					spanNeedFill = true;
					x++;
				}
				if (spanNeedFill) {
					pt.x = x - 1;
					pt.y = y;
					stack.push(pt);
					System.out.println("push");
					spanNeedFill = false;
				}
				while (getColor(x, y).getRGB() != oldcolor.getRGB() && x <= xr) {
					x++;
				}
			}
		}
	}

	public Color getColor(int x, int y) throws AWTException {

		//
		BufferedImage bufferedImage1 = new BufferedImage(572, 428, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d2 = (Graphics2D) bufferedImage1.getGraphics();
		graphics2d2.setColor(Color.WHITE);
		graphics2d2.fillRect(0, 0, 572, 428);

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Shape shape = (Shape) list.get(i);
				shape.Draw(graphics2d2);
			}
		}
		int c = bufferedImage1.getRGB(x, y);
		Color color = new Color(c);
		// System.out.println(x+" "+color);
		return color;
	}

	public boolean compare(Color color, Color color2) {

		if (color.getRGB() == color2.getRGB()) {
			return true;
		} else
			return false;
	}
}