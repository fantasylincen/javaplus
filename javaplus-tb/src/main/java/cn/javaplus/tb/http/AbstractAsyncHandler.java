package cn.javaplus.tb.http;

import java.io.ByteArrayOutputStream;

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;

public abstract class AbstractAsyncHandler implements AsyncHandler<String> {

	private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

	@Override
	public STATE onStatusReceived(HttpResponseStatus status) throws Exception {
		if (status.getStatusCode() >= 500) {
			errorAndFinish();
			return STATE.ABORT;
		}
		return STATE.CONTINUE;
	}

	private void errorAndFinish() {
		try {
			onError();
		} finally {
			safetyFinish();
		}
	}

	public abstract void onError();

	@Override
	public STATE onHeadersReceived(HttpResponseHeaders h) throws Exception {
		return STATE.CONTINUE;
	}

	@Override
	public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart)
			throws Exception {
		bytes.write(bodyPart.getBodyPartBytes());
		return STATE.CONTINUE;
	}

	@Override
	public final String onCompleted() throws Exception {
		String bodyContent = bytes.toString("UTF-8");
		try {
			onCompleted(bodyContent);
		} finally {
			safetyFinish();
		}
		return bodyContent;
	}

	private void safetyFinish() {
		try {
			onFinish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void onCompleted(String bodyContent);

	@Override
	public void onThrowable(Throwable t) {
		errorAndFinish();
	}

	protected abstract void onFinish();
}