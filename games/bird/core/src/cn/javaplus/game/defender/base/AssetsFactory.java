package cn.javaplus.game.defender.base;

/**
 * 资源工厂
 */
public class AssetsFactory {
	
	protected String assetsPath = "";

	public void setAssetBasePath(String path) {
		if(path.endsWith("/") || path.length() == 0) {
			assetsPath = path;
		} else {
			throw new IllegalArgumentException("path must end with '/' or be lenght zero.");
		}
	}
	
	
	public String getFilePath(String name) {
		return assetsPath + name;
	}
	
}
