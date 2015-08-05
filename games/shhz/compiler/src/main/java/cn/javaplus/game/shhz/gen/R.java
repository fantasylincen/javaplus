package cn.javaplus.game.shhz.gen;import java.io.IOException;import java.io.InputStream;import java.io.InputStreamReader;import java.util.Properties;import cn.javaplus.shhz.util.Closer;import cn.javaplus.shhz.util.Util;import com.google.common.io.Resources;public class R {		public final static class AssetsloadLoadstart {		public static String getString() {			return getFileContent("assetsload/loadStart");		}	}
	public final static class AssetsloaderTemp {		public static String getString() {			return getFileContent("AssetsLoader.temp");		}	}
	public final static class ButtonlistTemp {		public static String getString() {			return getFileContent("ButtonList.temp");		}	}
	public final static class ButtonlistsTemp {		public static String getString() {			return getFileContent("ButtonLists.temp");		}	}
	public final static class CompilerrconfigTemp {		public static String getString() {			return getFileContent("CompilerRConfig.temp");		}	}
	public final static class CompilerrconfigclassTemp {		public static String getString() {			return getFileContent("CompilerRConfigClass.temp");		}	}
	public final static class ComponentsButtonlistsOd {		public static String getString() {			return getFileContent("components/ButtonLists.od");		}	}
	public final static class CorerconfigTemp {		public static String getString() {			return getFileContent("CoreRConfig.temp");		}	}
	public final static class CorerconfigclassTemp {		public static String getString() {			return getFileContent("CoreRConfigClass.temp");		}	}
	public final static class DProperties {		public static Properties getProperties() {			return getProp("D.properties");		}	}
	public final static class DTemp {		public static String getString() {			return getFileContent("D.temp");		}	}
	public final static class EventsTemp {		public static String getString() {			return getFileContent("Events.temp");		}	}
	public final static class RconfigProperties {		public static Properties getProperties() {			return getProp("RConfig.properties");		}	}
	public final static class SProperties {		public static Properties getProperties() {			return getProp("S.properties");		}	}
	public final static class STemp {		public static String getString() {			return getFileContent("S.temp");		}	}
	public final static class TextureshapesTemp {		public static String getString() {			return getFileContent("TextureShapes.temp");		}	}
	public final static class TextureshapesfieldTemp {		public static String getString() {			return getFileContent("TextureShapesField.temp");		}	}
	private static String getFileContent(String name) {		return Util.File.getContent(Resources.getResource(name));	}	private static Properties getProp(String string) {		Properties prop = new Properties();		InputStream is = null;		try {			is = Resources.getResource(string).openStream();			InputStreamReader r = new InputStreamReader(is, "utf8");			prop.load(r);		} catch (IOException e) {			throw new RuntimeException(e);		} finally {			Closer.close(is);		}		return prop;	}}