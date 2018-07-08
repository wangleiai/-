package NewDraw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ItemListener implements ActionListener {

	public DrawBorder paint;
	// public Graphics2D graphics2d;

	public ItemListener(DrawBorder paint) {
		this.paint = paint;

	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if ("新建".equals(command)) {

			int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
			if (value == 0) {

				saveFile();
			}
			if (value == 1) {
				paint.list.removeAll(paint.list);
				paint.panelcenter.repaint();
			}
		}

		else if ("打开".equals(command)) {

			int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
			if (value == 0) {

				saveFile();
			}
			if (value == 1) {
				// 清空容器里面的东西
				paint.list.removeAll(paint.list);
				paint.panelcenter.repaint();

				try {
					// 弹出选择对话框，选择需要读入的文件
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File file = chooser.getSelectedFile();
					// 如果为选中文件
					if (file == null) {
						JOptionPane.showMessageDialog(null, "没有选择文件");
					} else {
						// 选中了相应的文件，则柑橘选中的文件创建对象输入流
						FileInputStream fis = new FileInputStream(file);

						BufferedImage bufferedImage = ImageIO.read(file);
						Graphics2D graphics2d = (Graphics2D) paint.panelcenter.getGraphics();
						graphics2d.drawImage(bufferedImage, 0, 0, paint.panelcenter);

					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		} else if ("保存".equals(command)) {

			saveFile();

		}

	}

	public void saveFile() {
		// 选择要保存的位置以及文件名字和信息
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("文本文件(*.jpeg,*.png,*.bmp)",
				"jpeg", "png", "bmp");
		chooser.setFileFilter(fileNameExtensionFilter);
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();

		if (file == null) {
			JOptionPane.showMessageDialog(null, "没有选择文件");
		} else {

			try {
				String fname = chooser.getName(file);
				if ((fname.indexOf(".jpeg") == -1) && (fname.indexOf(".png") == -1)
						&& ((fname.indexOf(".bmp") == -1))) {
					file = new File(chooser.getCurrentDirectory(), fname + ".bmp");
				}

				// 根据要保存的文件创建对象输出流
				FileOutputStream fis = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fis);

				Robot robot = new Robot();
				Rectangle tRectangle = paint.getBounds();

				Rectangle rectangle = new Rectangle((int) (27 + tRectangle.getX()), (int) (tRectangle.getY() + 37), 571,
						427);
				BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

				Graphics2D graphics2d = (Graphics2D) bufferedImage.getGraphics();
				graphics2d.setColor(Color.WHITE);
				graphics2d.fillRect(0, 0, 571 - 26, 428 - 36);
				for (int i = 0; i < paint.list.size(); i++) {
					Shape shape = (Shape) paint.list.get(i);
					shape.Draw(graphics2d);
				}

				ImageIO.write(bufferedImage, "BMP", file);
				JOptionPane.showMessageDialog(null, "保存成功！");
				oos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}