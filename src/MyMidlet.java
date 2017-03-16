import javax.microedition.midlet.*;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
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
	private DisplayItem displaySortedByStrengthItem;
	private Image image;
	private Form mainForm;
	private Command addItemCommand;
	private Command displayItemCommand;
	private Command displaySortedByStrengthCommand;
	static RecordStore recordStore;
	
	public MyMidlet() {
		display = Display.getDisplay(this);
		image = loadImage("image.jpg");
		mainForm = new Form("Main Form");
		
		exit = new Command("Exit", Command.EXIT, 1);
		addItemCommand = new Command("Add item", Command.ITEM, 1);
		displayItemCommand = new Command("Display items", Command.ITEM, 1);
		displaySortedByStrengthCommand = new Command("Display sorted", Command.ITEM, 1);
		
		addItem = new AddItem("Add Item", mainForm);
		displayItem = new DisplayItem("Display items", mainForm, display);
		displaySortedByStrengthItem = new DisplayItem("Display sorted", mainForm, display);
		
		mainForm.append(image);
		
		mainForm.addCommand(exit);
		mainForm.addCommand(addItemCommand);
		mainForm.addCommand(displayItemCommand);
		mainForm.addCommand(displaySortedByStrengthCommand);
		
		mainForm.setCommandListener(this);
		
		display.setCurrent(mainForm);
		
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
		try {
			recordStore = RecordStore.openRecordStore("record store", true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pauseApp() {
	
	}
	
	public void destroyApp(boolean unconditional) {
		System.err.println("*** wywolano destroyApp ***");
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == addItemCommand) {
			display.setCurrent(addItem);
		}
		else if (command == displayItemCommand) {
			display.setCurrent(displayItem);
			displayItems(new Comparator(), displayItem);
		}
		else if (command == exit) {
			destroyApp(false);
			notifyDestroyed();
		}
		else if (command == displaySortedByStrengthCommand) {
			display.setCurrent(displaySortedByStrengthItem);
			displayItems(new StrengthComparator(), displaySortedByStrengthItem);
		}
	}
	
	private void displayItems(RecordComparator recordComparator, DisplayItem displayItem) {
		RecordEnumeration iterator;
		String calyTekst = "";
		try {
			iterator = recordStore.enumerateRecords(null, recordComparator, false);
			while(iterator.hasNextElement()) {
				byte[] rekord = iterator.nextRecord();
				String tekst = new String(rekord);
				calyTekst += (tekst + "\n");
			}
		} catch(RecordStoreException ex) {
			ex.printStackTrace();
		}
		displayItem.setString(calyTekst);
	}
	 
	public static Display myDisplay() {
		return display;
	}
}
