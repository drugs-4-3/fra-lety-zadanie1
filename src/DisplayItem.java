import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

public class DisplayItem extends TextBox implements CommandListener{

	Command backCommand;
	Command deleteRecordCommand;
	Displayable mainScreen;
	Display display;
	TextBox textBox;
	TextBox indexBox;
	Command saveCommand;
	
	public DisplayItem(String title, Displayable mainScreen, Display display) {
		super("Display Item", "", 256, 0);
		this.mainScreen = mainScreen;
		this.display = display;
		backCommand = new Command("Back", Command.EXIT, 1);
		deleteRecordCommand = new Command("delete record", Command.ITEM, 1);
		addCommand(backCommand);
		addCommand(deleteRecordCommand);
		setCommandListener((CommandListener)this);
		indexBox = new TextBox("index", "", 2, TextField.NUMERIC);
		saveCommand = new Command("save", Command.ITEM, 1);
		indexBox.addCommand(saveCommand);
		indexBox.setCommandListener(new DeleteCommandListener());
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			display.setCurrent(mainScreen);
		}
		if (c == deleteRecordCommand) {
			display.setCurrent(indexBox);
		}
	}
	
	private class DeleteCommandListener implements CommandListener {
		public void commandAction(Command c, Displayable d) {
			if (c == saveCommand) {
				RecordStore rs = MyMidlet.recordStore;
				int index = Integer.parseInt(indexBox.getString());
				try {
					rs.deleteRecord(index);
				} catch(Exception e) {
					// handle
				}
				display.setCurrent(mainScreen);
			}
		}
	}

	
	
}
