package cn.mxz.fengshentai.ui;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.fengshentai.IExchangePropUI;
import cn.mxz.fengshentai.IPropTransform;
import cn.mxz.fengshentai.PropItem;

import com.google.common.collect.Lists;

public class ExchangePropUIImpl implements IExchangePropUI {

	private final List<IPropTransform> uiData;
	
	public ExchangePropUIImpl( List<PropItem> data, City user ){
		this.uiData = Lists.newArrayList();
		for (PropItem propItem : data) {
			PropTransformImpl p = new PropTransformImpl(propItem, user );
			uiData.add(p);
		}
	}
	
	@Override
	public List<IPropTransform> getData() {
		return uiData;
	}

}
