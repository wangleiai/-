package NewDraw;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.nio.channels.NonWritableChannelException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSInput;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class DrawBorder extends JFrame {
	public DrawBorder() {
	}

	ArrayList<Shape> list = new ArrayList<Shape>();
	public Color color = Color.red;
	public JButton button;
	public String c1 = "0.0";
	public String c2 = "0.0";
	public String c3 = "0.0";

	// private JFrame frame;
	public JTextField bigger_textField;
	public JTextField smaller_textField;
	public JTextField ratory_textField;
	public JTextField red_textField;
	public JTextField green_textField;
	public JTextField blue_textField;

	public BufferedImage bufferedImage;

	JPanel panelcenter;

	public static void main(String[] args) {

		DrawBorder window = new DrawBorder();

	}

	public void DrawBorder1() {
		initialize();
	}

	public void initialize() {

		ArrayList<String> lt = new ArrayList<String>();
		lt.add("未知");
		lt.add("虚线矩形");
		lt.add("橡皮擦");
		lt.add("油漆桶");
		lt.add("滴管");
		lt.add("放大镜");
		lt.add("铅笔");
		lt.add("刷子");
		lt.add("喷桶");
		lt.add("字");
		lt.add("直线");
		lt.add("曲线");
		lt.add("矩形");
		lt.add("多边形");
		lt.add("圆");
		lt.add("圆角矩形");

		// frame = new JFrame();
		this.setBounds(100, 100, 773, 520);
		this.setTitle("我的画板");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// 上面板
		JPanel panelup = new JPanel();
		panelup.setBounds(0, 0, 742, 36);
		this.getContentPane().add(panelup);

		// 添加菜单条
		JMenuBar bar = new JMenuBar();
		panelup.add(bar);
		JMenuItem menu1 = new JMenuItem("新建");
		JMenuItem menu2 = new JMenuItem("打开");
		JMenuItem menu3 = new JMenuItem("保存");
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		// 给菜单添加添加监听事件
		ItemListener itemListener = new ItemListener(this);
		menu1.addActionListener(itemListener);
		menu2.addActionListener(itemListener);
		menu3.addActionListener(itemListener);

		// 左面板
		JPanel panelleft = new JPanel();
		panelleft.setBounds(0, 36, 25, 428);
		this.getContentPane().add(panelleft);
		panelleft.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JPanel panelright = new JPanel();
		panelright.setBounds(599, 36, 143, 428);
		this.getContentPane().add(panelright);
		panelright.setLayout(null);

		JPanel panelrightup = new JPanel();
		panelrightup.setBounds(0, 0, 143, 120);
		panelright.add(panelrightup);
		panelrightup.setLayout(null);

		Panel panelcolor = new Panel();
		panelcolor.setBackground(color);
		panelcolor.setBounds(0, 59, 143, 56);
		panelrightup.add(panelcolor);

		/*
		 * 
		 * 完成选择颜色功能
		 * 
		 */

		JButton colorButton = new JButton("选择颜色");
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color t = new Color(200, 200, 200);
				t = JColorChooser.showDialog(null, "选则颜色", t);
				if (t == null) {
					color = Color.red;
				} else {
					color = t;
				}
				panelcolor.setBackground(color);
			}
		});
		colorButton.setBounds(0, 13, 143, 40);
		panelrightup.add(colorButton);

		JPanel panelrightcenter = new JPanel();
		panelrightcenter.setBounds(0, 133, 143, 147);
		panelright.add(panelrightcenter);
		panelrightcenter.setLayout(null);

		JLabel lblNewLabel = new JLabel("其他功能");
		lblNewLabel.setBounds(41, 5, 60, 18);
		panelrightcenter.add(lblNewLabel);

		JButton bigger = new JButton("放缩");
		bigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Text = bigger_textField.getText();
				double bs = Double.valueOf(Text);

				Shape shape = (Shape) list.get(list.size() - 1);
				String s = (list.get(list.size() - 1)).toString();
				// //圆的放缩
				if (s.charAt(8) == 'O' && s.charAt(9) == 'v' && s.charAt(10) == 'a' && s.charAt(11) == 'l') {
					shape.x2 = (int) (shape.x2 * bs);
					shape.y2 = (int) (shape.y2 * bs);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
				}
				// 矩形放缩
				else if (s.charAt(8) == 'R' && s.charAt(9) == 'e' && s.charAt(10) == 'c' && s.charAt(11) == 't') {
					shape.x2 = (int) (shape.x2 * bs);
					shape.y2 = (int) (shape.y2 * bs);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
				}
				// 直线放缩
				else if (s.charAt(8) == 'L' && s.charAt(9) == 'i' && s.charAt(10) == 'n' && s.charAt(11) == 'e') {
					int lengthx = Math.abs(shape.x1 - shape.x2);
					int lengthy = Math.abs(shape.y1 - shape.y2);
					
					int xc = (shape.x1 + shape.x2)/2; 
					int yc = (shape.y1 + shape.y2)/2;
					
					shape.x1 = (int) (xc - bs*lengthx/2);
					shape.x2 = (int) (xc + bs*lengthx/2);
					
					shape.y1 = (int) (yc - bs*lengthy/2);
					shape.y2 = (int) (yc + bs*lengthy/2);
 					
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
					System.out.println("bs line");
				}
			}
		});
		bigger.setBounds(0, 41, 81, 27);
		bigger.setVerticalAlignment(SwingConstants.BOTTOM);
		panelrightcenter.add(bigger);
		bigger.setActionCommand("bs");

		JButton smaller = new JButton("移动");
		smaller.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = smaller_textField.getText();
				// 分别得到x和y方向的移动大小
				String text1 = "", text2 = "";
				int i = 0, j = 0;
				for (i = 0; i < text.length(); i++) {
					if (text.charAt(i) == ',') {
						j = i;
						break;
					}
					text1 += text.charAt(i);
				}

				for (int k = i + 1; k < text.length(); k++) {
					text2 += text.charAt(k);
				}
 
				double movex = Double.valueOf(text1);
				double movey = Double.valueOf(text2);
				Shape shape = list.get(list.size() - 1);
				String s = (list.get(list.size() - 1)).toString();
				// //圆的移动
				if (s.charAt(8) == 'O' && s.charAt(9) == 'v' && s.charAt(10) == 'a' && s.charAt(11) == 'l') {
					shape.x1 = (int) (shape.x1 + movex);
					shape.y1 = (int) (shape.y1 + movey);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
				}
				// 矩形的移动
				else if (s.charAt(8) == 'R' && s.charAt(9) == 'e' && s.charAt(10) == 'c' && s.charAt(11) == 't') {
					shape.x1 = (int) (shape.x1 + movex);
					shape.y1 = (int) (shape.y1 + movey);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
				}
				// 直线移动
				if (s.charAt(8) == 'L' && s.charAt(9) == 'i' && s.charAt(10) == 'n' && s.charAt(11) == 'e') {
					shape.x1 = (int) (shape.x1 + movex);
					shape.y1 = (int) (shape.y1 + movey);
					shape.x2 = (int) (shape.x2 + movey);
					shape.y2 = (int) (shape.y2 + movey);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
					System.out.println("move line ");
				}
			}
		});
		smaller.setBounds(0, 74, 81, 27);
		panelrightcenter.add(smaller);
		smaller.setActionCommand("move");

		JButton rotate = new JButton("旋转");
		rotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = ratory_textField.getText();
				double ang = Double.valueOf(text);
				Shape shape = list.get(list.size() - 1);
				String string = shape.toString();

				if (string.charAt(8) == 'L' && string.charAt(9) == 'i' && string.charAt(10) == 'n'
						&& string.charAt(11) == 'e') {
					double degree = ang % 360;
					if (degree < 0) {
						degree = 360 + degree;

					}
					System.out.println(shape.x2 + " " + shape.y2);
					ang = degree * 0.0174532925;

					shape.x2 = (int) (shape.x1 + (shape.x2 - shape.x1) * Math.cos(ang)
							- (shape.y2 - shape.y1) * Math.sin(ang) + 0.5);
					shape.y2 = (int) (shape.y1 + (shape.x2 - shape.x1) * Math.sin(ang)
							+ (shape.y2 - shape.y1) * Math.cos(ang) + 0.5);
					System.out.println(shape.x2 + " " + shape.y2);
					list.remove(list.size() - 1);
					list.add(shape);
					panelcenter.repaint();
				}
			}
		});
		rotate.setBounds(0, 107, 81, 27);
		panelrightcenter.add(rotate);
		rotate.setActionCommand("rotate");

		bigger_textField = new JTextField();
		bigger_textField.setToolTipText("size>=0");
		bigger_textField.setText("1.0");
		bigger_textField.setBounds(86, 43, 57, 24);
		panelrightcenter.add(bigger_textField);
		bigger_textField.setColumns(10);

		smaller_textField = new JTextField();
		smaller_textField.setToolTipText("x,y");
		smaller_textField.setText("1.0,1.0");
		smaller_textField.setBounds(86, 74, 57, 24);
		panelrightcenter.add(smaller_textField);
		smaller_textField.setColumns(10);

		ratory_textField = new JTextField();
		ratory_textField.setToolTipText("0-360度");
		ratory_textField.setText("0");
		ratory_textField.setBounds(86, 108, 57, 24);
		panelrightcenter.add(ratory_textField);
		ratory_textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(0, 276, 143, 152);
		panelright.add(panel);
		panel.setLayout(null);

		JRadioButton dropper = new JRadioButton("取色器");
		dropper.setBounds(34, 13, 77, 27);
		dropper.setActionCommand("drop");

		// 添加按钮组
		ButtonGroup bGroup = new ButtonGroup();
		for (int i = 0; i < 16; i++) {
			JRadioButton jRadioButton = new JRadioButton();
			ImageIcon img1 = new ImageIcon(this.getClass().getResource("/images/draw" + i + ".jpg"));
			ImageIcon img2 = new ImageIcon(this.getClass().getResource("/images/draw" + i + "-1.jpg"));
			ImageIcon img3 = new ImageIcon(this.getClass().getResource("/images/draw" + i + "-2.jpg"));
			ImageIcon img4 = new ImageIcon(this.getClass().getResource("/images/draw" + i + "-3.jpg"));
//
//			ImageIcon img1 = new ImageIcon("res/images/draw" + i + ".jpg");
//			ImageIcon img2 = new ImageIcon("res/images/draw" + i + "-1.jpg");
//			ImageIcon img3 = new ImageIcon("res/images/draw" + i + "-2.jpg");
//			ImageIcon img4 = new ImageIcon("res/images/draw" + i + "-3.jpg");
			
			jRadioButton.setToolTipText(lt.get(i));

			jRadioButton.setIcon(img1);
			jRadioButton.setRolloverIcon(img2);
			jRadioButton.setPressedIcon(img3);
			jRadioButton.setSelectedIcon(img4);
			jRadioButton.setBorder(null);

			// //设置默认选中的按钮
			if (i == 10) {
				jRadioButton.setSelected(true);
			}
			jRadioButton.setActionCommand("pic" + i);
			bGroup.add(jRadioButton);
			panelleft.add(jRadioButton);
		}
		bGroup.add(dropper);
		panel.add(dropper);

		JLabel L_RED = new JLabel("RED");
		L_RED.setBounds(14, 53, 45, 18);
		panel.add(L_RED);

		JLabel L_GREEN = new JLabel("GREEN");
		L_GREEN.setBounds(14, 88, 40, 18);
		panel.add(L_GREEN);

		JLabel L_BLUE = new JLabel("BLUE");
		L_BLUE.setBounds(14, 121, 40, 18);
		panel.add(L_BLUE);

		red_textField = new JTextField();
		red_textField.setText(c1);
		red_textField.setBounds(77, 53, 66, 24);
		panel.add(red_textField);
		red_textField.setColumns(10);

		green_textField = new JTextField();
		green_textField.setText(c2);
		green_textField.setBounds(77, 84, 66, 24);
		panel.add(green_textField);
		green_textField.setColumns(10);

		blue_textField = new JTextField();
		blue_textField.setText(c3);
		blue_textField.setBounds(77, 118, 66, 24);
		panel.add(blue_textField);
		blue_textField.setColumns(10);

		// System.out.println("fdsaf");
		panelcenter = new JPanel() {

			public void paint(Graphics graphics) {
				// System.out.println("huhui"+graphics.getColor());
				Graphics2D graphics2d = (Graphics2D) graphics;
				super.paint(graphics2d);

				bufferedImage = new BufferedImage(panelcenter.getWidth(), panelcenter.getHeight(),
						BufferedImage.TYPE_INT_RGB);

				Graphics2D graphics2d2 = (Graphics2D) bufferedImage.getGraphics();
				graphics2d2.setColor(Color.WHITE);
				graphics2d2.fillRect(0, 0, panelcenter.getWidth(), panelcenter.getHeight());

				if (!list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						Shape shape = (Shape) list.get(i);
						shape.Draw(graphics2d2);
					}
				}
				graphics2d.drawImage(bufferedImage, 0, 0, panelcenter);
			}

			@Override
			public void repaint() {
				// TODO Auto-generated method stub
				super.repaint();
			}

		};

		panelcenter.setBounds(26, 36, 572, 428);
		this.getContentPane().add(panelcenter);
		panelcenter.setBackground(Color.WHITE);

		this.setVisible(true); // 加上就多余了

		// 画笔必须在setVisible后才能拿
		Graphics graphics = panelcenter.getGraphics();// 只有中间面板绘制图像572, 428
		bufferedImage = new BufferedImage(572, 428, BufferedImage.TYPE_INT_RGB);
		graphics.drawImage(bufferedImage, 0, 0, panelcenter);

		// 调用DrawListener的第三个构造函数，传递画笔，选中的那个图像按钮，this
		System.out.println(graphics);
		// System.out.println();
		DrawListener dListener = new DrawListener(graphics, bGroup, this, list);
		dListener.getcp(panelcenter, bufferedImage, this);
		panelcenter.addMouseListener(dListener);
		panelcenter.addMouseMotionListener(dListener);

	}

	public Color getColor(int x, int y) throws AWTException {
		Graphics graphics = bufferedImage.getGraphics();
		return graphics.getColor();

	}

}
