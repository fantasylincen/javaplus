package cn.javaplus.plugins.console;

import java.io.PrintStream;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleFactory implements IConsoleFactory {

	private static MessageConsole	console;

	private static PrintStream		out;
	private static PrintStream		err;
	private static Display			display;

	@Override
	public void openConsole() {

	}

	public static void closeConsole() {
		IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
		if (console != null) {
			manager.removeConsoles(new IConsole[] { console });
		}
		console = null;
	}

	private static MessageConsole getConsole() {
		if (console == null) {
			console = new MessageConsole("", null);
			IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
			IConsole[] existing = manager.getConsoles();
			boolean exists = false;
			for (int i = 0; i < existing.length; i++) {
				if (console == existing[i])
					exists = true;
			}
			if (!exists) {
				manager.addConsoles(new IConsole[] { console });
			}
			manager.showConsoleView(console);

		}
		return console;
	}

	public static PrintStream getOut() {
		if (out == null) {
			MessageConsoleStream stream = getConsole().newMessageStream();
			out = new PrintStream(stream);
		}
		return out;
	}

	public static PrintStream getErr() {
		if (err == null) {
			MessageConsoleStream stream = getConsole().newMessageStream();
			Color c = new Color(display, 255, 0, 0);
			stream.setColor(c);
			err = new PrintStream(stream);
		}
		return err;
	}
}
