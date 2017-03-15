import javax.microedition.midlet.*;
import javax.microedition.rms.RecordStoreException;

import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.microedition.lcdui.*;

public class MyMidlet extends MIDlet implements CommandListener {
	
	private static Display display;
	private List list; 
	private Command exit; 
	private AddItem addItem;
	private DisplayItem displayItem;
	private Image image;
	
	public MyMidlet() {
		image = loadImage("/image.jpg");
		exit = new Command("Exit", Command.EXIT, 1);
		display = Display.getDisplay(this);
		list = new List("Menu:", List.IMPLICIT); 
		list.append("image", image);
		list.append("Add Item",null);
		list.append("Display Items",null);
		list.addCommand(exit);	
		list.setCommandListener(this);
		addItem = new AddItem("Add Item", list);
		displayItem = new DisplayItem("Display items", list, display);
	}
	
	private Image loadImage(String source) {
		Image image = null;
		try {
			image = Image.createImage(source);
		} catch (Exception e) {}
		return image;
	}
	
	public void startApp() {
		display.setCurrent(list);
	}
	
	public void pauseApp() {
	
	}
	
	public void destroyApp(boolean unconditional) {
		System.err.println("*** wywolano destroyApp ***");
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == List.SELECT_COMMAND) {
			int selectedIndex = list.getSelectedIndex();
			
			if (selectedIndex == 0) {
				display.setCurrent(addItem);
			}
			else if (selectedIndex == 1) {
				display.setCurrent(displayItem);
			}
		}
		else if (command == exit) {
			destroyApp(false);
			notifyDestroyed();
		}
	}
	 
	public static Display myDisplay() {
		return display;
	}
}
