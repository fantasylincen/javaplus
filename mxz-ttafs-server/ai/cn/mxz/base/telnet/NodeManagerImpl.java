package cn.mxz.base.telnet;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.collect.Lists;
import com.lemon.commons.logger.Logs;

public class NodeManagerImpl implements NodeManager {

	/**
	 * 存放当前所在节点路径 比如 1-3-5 或者 1-2-3 (第1大节点的第二小节点的第3小节点)
	 */
	private Stack<Integer>			path		= new Stack<Integer>();

	private Element					root;

	private List<String>			args		= Lists.newArrayList();

	private List<CommandListener>	listeners	= Lists.newArrayList();

	public NodeManagerImpl(String configPath) {

		File inputXml = new File(configPath);

		SAXReader r = new SAXReader();

		try {

			root = r.read(inputXml).getRootElement();

		} catch (MalformedURLException e) {

			throw new RuntimeException(e);

		} catch (DocumentException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public void back() {

		path.clear();

		args.clear();

		onEntered(new EnteredEvent(getCurrentElement()));
	}

	@Override
	public boolean isCommandNode() {

		Element c = getCurrentElement();

		return !c.elements("command").isEmpty();
	}

	@Override
	public Element getCurrentElement() {

		Element e = root;

		for (Integer i : path) {

			e = (Element) e.elements().get(i);
		}

		return e;
	}

	@Override
	public void enterNode(int select) {

		List<Element> es = nextNodes();

		if (select < 0 || es.isEmpty() || select >= es.size()) {

			throw new CommandException("You can't select " + select + "!");
		}

		path.push(select);

		onEntered(new EnteredEvent(getCurrentElement()));

		if (isCommandNode()) {

			CommandEvent e = new CommandEvent(getCurrentElement());

			if (getArgsSize() == 0) {

				onArgsFull(e);

			} else {

				onEnterCommandNode(e);
			}
		}

		args.clear();

		Logs.debug.debug("NodeManagerImpl.enterNode()" + this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Element> nextNodes() {
		return getCurrentElement().elements("node");
	}

	private void onEntered(EnteredEvent e) {

		for (CommandListener c : listeners) {

			c.onEntered(e);
		}
	}

	private void onEnterCommandNode(CommandEvent commandEvent) {

		for (CommandListener c : listeners) {

			c.onEnterCommandNode(commandEvent);
		}
	}

	@Override
	public void addCommandListener(CommandListener l) {

		this.listeners.add(l);
	}

	@Override
	public String[] getArgs() {

		String[] ss = new String[args.size()];

		for (int i = 0; i < ss.length; i++) {

			ss[i] = args.get(i);
		}

		return ss;
	}

	@Override
	public void addArg(String arg) {

		args.add(arg);

		if (isArgsFull()) {

			onArgsFull(new CommandEvent(getCurrentElement()));
		}
	}

	private void onArgsFull(CommandEvent e) {

		for (CommandListener c : listeners) {

			c.onArgsFull(e);
		}
	}

	@Override
	public String toString() {

		Element e = root;

		String PATH = "FSL Server";

		Iterator<Integer> it = path.iterator();

		while (it.hasNext()) {

			PATH += "/";

			Integer next = it.next();

			e = (Element) e.elements().get(next);

			PATH += e.getTextTrim();
		}

		PATH += ">";

		return PATH;
	}

	@Override
	public boolean isArgsFull() {

		int size = getArgsSize();

		return args.size() >= size;
	}

	@SuppressWarnings("unchecked")
	private int getArgsSize() {

		Element currentElement = getCurrentElement();

		List<Element> elements = currentElement.elements("arg");

		return elements.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Element nextArgNode() {

		List<Element> elements = getCurrentElement().elements();

		if (isArgsFull()) {

			return null;
		}

		return elements.get(getArgsIndex());

	}

	private int getArgsIndex() {

		return args.size();
	}
}
