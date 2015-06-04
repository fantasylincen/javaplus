package cn.mxz.notice;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.NoticeService;
import cn.mxz.protocols.user.NoticeP.NoticePro;
import cn.mxz.protocols.user.NoticeP.TextItemPro;

@Component("noticeService")
@Scope("prototype")

public class NoticeServiceImpl extends AbstractService implements NoticeService {

	@Override
	public NoticePro getData() {

		NoticePro.Builder b = NoticePro.newBuilder();

		NoticeCenter center = NoticeCenter.getInstance();

		List<NoticeItem> items = center.getItems();
		for (NoticeItem i : items) {
			b.addItems(buildItem(i));
		}

		return b.build();
	}

	private TextItemPro buildItem(NoticeItem i) {
		TextItemPro.Builder b = TextItemPro.newBuilder();
		b.setText(i.getText());
		b.setTitle(i.getTitle());
		b.setImportentText(i.isImportent() ? "importent" : "unimportent");//重要
		b.setImportentText(i.isNew() ? "new" : "unnew");	//新


		//0 活动  1 公告  2 温馨提示
//		required int32 type = 222;
		b.setType(i.getType());

		//TODO LC 公告
		return b.build();
	}
}
