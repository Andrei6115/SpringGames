package screensaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Component
@Scope("prototype")
public abstract class ColorFrame extends JFrame {

	@Autowired
	private Color color;

	public ColorFrame() {
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void showOnRandomPlace(){
		Random random = new Random();
		setLocation(
			random.nextInt(
				GraphicsEnvironment.
					getLocalGraphicsEnvironment().
					getDefaultScreenDevice().
					getDisplayMode().
					getWidth()),
			random.nextInt(
				GraphicsEnvironment.
					getLocalGraphicsEnvironment().
					getDefaultScreenDevice().
					getDisplayMode().
					getHeight()));
		getContentPane().setBackground(getColor());
		repaint();
	}

	protected abstract Color getColor();
}
