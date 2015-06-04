package com.taobao.protobuf.editors.contentassist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;

import cn.javaplus.plugins.console.ConsoleFactory;
import cn.javaplus.plugins.console.Debuger;

import com.taobao.protobuf.editors.PBConstants;
import com.taobao.protobuf.perference.PBPluginImages;

public class PBContentAssistProcessor implements IContentAssistProcessor {

	private static String	messageRegex	= "message\\s+(\\w+)";

	private static String	enumRegex		= "enum\\s+(\\w+)";

	private Pattern			messagePattern	= Pattern.compile(messageRegex);

	private Pattern			enumPattern		= Pattern.compile(enumRegex);

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {

		if (offset - 1 > 0) {
			try {
				char c = viewer.getDocument().getChar(offset - 1);
				if (Character.isWhitespace(c)) {
					return getDefualtCompletionProposal(viewer, offset);
				} else {
					int line = viewer.getDocument().getLineOfOffset(offset);
					int lineoffset = viewer.getDocument().getLineOffset(line);
					String word = getWord(lineoffset, offset, viewer.getDocument());
					if (word != null) {
						List<String> keywords = new ArrayList<String>();
						List<String> allKeywords = getAllKeyWords(viewer.getDocument());
						for (String keyword : allKeywords) {
							if (keyword.startsWith(word)) {
								keywords.add(keyword);
							}
						}
						if (keywords.size() > 0) {
							ICompletionProposal[] cps = new CompletionProposal[keywords.size()];
							int i = 0;
							for (String s : keywords) {
								cps[i] = new CompletionProposal(s + " ", offset - word.length(), word.length(), s.length() + 1, getImage(s), s, null, null);
								i++;
							}
							return cps;
						} else {
							return null;
						}
					}
				}
			} catch (BadLocationException e) {
				e.printStackTrace(ConsoleFactory.getErr());
				return getDefualtCompletionProposal(viewer, offset);
			}
		}

		return getDefualtCompletionProposal(viewer, offset);
	}

	private String getWord(int lineoffset, int offset, IDocument document) {
		StringBuilder sb = new StringBuilder();
		while (offset > lineoffset) {
			try {
				char c = document.getChar(offset - 1);
				if (Character.isWhitespace(c)) {
					return sb.reverse().toString();
				}
				sb.append(c);
				offset--;
			} catch (BadLocationException e) {
				e.printStackTrace(ConsoleFactory.getErr());
				return null;
			}
		}
		return sb.reverse().toString();
	}

	private List<String> getAllKeyWords(IDocument doc) {
		List<String> messageAndEnum = getMessageAndEnum(doc);
		for (String keyWord : PBConstants.KEYWORDS) {
			messageAndEnum.add(keyWord);
		}
		return messageAndEnum;
	}

	private List<String> getMessageAndEnum(IDocument doc) {
		String text = doc.get();
		String[] lines = text.split("\n");
		List<String> rs = new ArrayList<String>();
		for (String line : lines) {
			Matcher match = messagePattern.matcher(line);
			if (match.find()) {
				String msgName = match.group(1);
				if (!rs.contains(msgName)) {
					rs.add(msgName);
				}

			} else {
				match = enumPattern.matcher(line);
				if (match.find()) {
					String msgName = match.group(1);
					if (!rs.contains(msgName)) {
						rs.add(msgName);
					}
				}
			}
		}
		return rs;
	}

	private ICompletionProposal[] getDefualtCompletionProposal(ITextViewer viewer, int offset) {
		List<String> allKeywords = getAllKeyWords(viewer.getDocument());
		int length = allKeywords.size();
		ICompletionProposal[] cps = new CompletionProposal[length];
		int i = 0;
		for (String keyword : allKeywords) {
			cps[i] = new CompletionProposal(keyword + " ", offset, 0, keyword.length() + 1, getImage(keyword), keyword, null, null);
			i++;
		}
		return cps;
	}

	private Image getImage(String keyword) {
		if (isInArray(keyword, PBConstants.TYPES)) {// type
			return PBPluginImages.getImage(PBPluginImages.TYPES_IMAGE);
		} else if (isInArray(keyword, PBConstants.OPTIONS)) {// option
			return PBPluginImages.getImage(PBPluginImages.OPTION_IMAGE);
		} else if (isInArray(keyword, PBConstants.DESCRIPTOR)) {
			return PBPluginImages.getImage(PBPluginImages.DES_IMAGE);
		} else if (isInArray(keyword, PBConstants.MESSAGE_TYPE)) {
			return PBPluginImages.getImage(PBPluginImages.CLZ_IMAGE);
		}
		return PBPluginImages.getImage(PBPluginImages.OTHER_IMAGE);
	}

	private boolean isInArray(String keyword, String[] array) {
		for (String s : array) {
			if (keyword.equals(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {

		Debuger.debug("PBContentAssistProcessor.computeContextInformation()");

		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return "get content assistant error!";
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

}
