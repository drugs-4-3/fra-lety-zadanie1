import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

public class DisplayItem extends Form implements CommandListener{

	Command backCommand;
	Displayable mainScreen;
	Display display;
	
	public DisplayItem(String title, Displayable mainScreen, Display display) {
		super(title);
		this.mainScreen = mainScreen;
		this.display = display;
		backCommand = new Command("Back", Command.EXIT, 1);
		addCommand(backCommand);
		setCommandListener((CommandListener)this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			display.setCurrent(mainScreen);
		}
		
	}

	
	
}
