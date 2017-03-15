import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;

public class AddItem extends Form implements CommandListener{
	
	Command backCommand;
	TextField nameField;
	TextField strengthField;
	Gauge statusGauge;
	ChoiceGroup weaponChoiceGroup;
	Display display;
	Displayable mainScreen;
	
	public AddItem(String title, Displayable mainScreen) {
		super(title);
		this.mainScreen = mainScreen;
		display = MyMidlet.myDisplay();
		
		nameField = new TextField("name", "name", 100, 0);
		strengthField = new TextField("strenght", "strength", 100, 0);
		strengthField.setConstraints(TextField.NUMERIC);
		Gauge gauge = new Gauge("power", true, 100, 50);
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
	}

	
}
