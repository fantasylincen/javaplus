package cn.javaplus.crazy.supportv4fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class DialogBox {

	protected Context mContext;
	protected String mstrTitle = "test dialog";
	protected String mstrInfo = "test dialog display page";

	protected String mStringOk = "确定";
	protected String mStringCancel = "取消";
	protected String mStringNeutral = "其余";
	
	private DialogInterface.OnClickListener mDialogConfirmListener;
	private DialogInterface.OnClickListener mDialogCancelListener;

	public DialogBox(Context context) {
		mContext = context;
	}
	
	public AlertDialog buildTipDialog() {
		Builder b1 = new AlertDialog.Builder(mContext);
		if(mstrTitle != null)
			b1.setTitle(mstrTitle);
		
		if(mstrInfo != null)
			b1.setMessage(mstrInfo);

		if (mDialogConfirmListener != null) {
			b1.setPositiveButton(mStringOk, mDialogConfirmListener);
		}

		if (mDialogCancelListener != null) {
			b1.setNegativeButton(mStringCancel, mDialogCancelListener);
		}
		
		
		return b1.create();
	}

	public void setDialog(DialogInterface.OnClickListener dialogConfirmListener,
			DialogInterface.OnClickListener dialogCancelListener) {
		mDialogConfirmListener = dialogConfirmListener;
		mDialogCancelListener = dialogCancelListener;
	}

}