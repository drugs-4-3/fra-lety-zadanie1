import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStoreException;

public class AddItem extends Form implements CommandListener{
	
	Command backCommand;
	TextField nameField;
	TextField strengthField;
	Gauge statusGauge;
	ChoiceGroup weaponChoiceGroup;
	Display display;
	Displayable mainScreen;
	Command saveCommand;
	Gauge gauge;
	
	public AddItem(String title, Displayable mainScreen) {
		super(title);
		this.mainScreen = mainScreen;
		display = MyMidlet.myDisplay();
		saveCommand = new Command("Save", Command.ITEM, 1);
		addCommand(saveCommand);
		
		nameField = new TextField("name", "", 100, 0);
		strengthField = new TextField("strenght", "strength", 100, 0);
		strengthField.setConstraints(TextField.NUMERIC);
		gauge = new Gauge("balance", true, 100, 50);
		append(nameField);
		append(strengthField);
		append(gauge);
		backCommand = new Command("Back", Command.EXIT, 1);
		addCommand(backCommand);
		setCommandListener((CommandListener)this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			System.err.println("back command");
			display.setCurrent(mainScreen);
		}
		else if (c == saveCommand) {
			String name = "name: " + nameField.getString() + "\n";
			String strength = "strength: " + strengthField.getString() + "\n";
			String power = "power: " + Integer.toString(gauge.getValue()) + "\n \n";
			
			String data = name + strength + power + "\n";
			byte[] record = data.getBytes();
			
			if (record.length > 0) {
				try {
					MyMidlet.recordStore.addRecord(record, 0, record.length);
					nameField.setString("");
					strengthField.setString("");
					gauge.setValue(50);
				} catch(RecordStoreException ex) {
					ex.printStackTrace();
				}
				System.out.println("zapisano! :)");
			}
		}
	}

	
}
