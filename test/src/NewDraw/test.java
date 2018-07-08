package NewDraw;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		DrawBorder dBorder = new DrawBorder();
		dBorder.initialize();
	}
}
