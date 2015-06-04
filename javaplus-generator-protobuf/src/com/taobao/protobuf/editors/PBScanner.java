package com.taobao.protobuf.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class PBScanner extends RuleBasedScanner {

	public PBScanner(ColorManager manager) {

		IToken keywordToken = new Token(new TextAttribute(manager.getColor(IPBColorConstants.PB_KEYWORD)));

		IToken commentToken = new Token(new TextAttribute(manager.getColor(IPBColorConstants.PB_COMMENT)));

		IToken stringToken = new Token(new TextAttribute(manager.getColor(IPBColorConstants.STRING)));

		WordRule wordRule = new WordRule(new PBWordDetector());

		for (String keyword : PBConstants.KEYWORDS) {
			wordRule.addWord(keyword, keywordToken);
		}

		setRules(new IRule[] { wordRule, new SingleLineRule("#", null, commentToken), new SingleLineRule("//", null, commentToken), new MultiLineRule("/*", "*/", commentToken), new SingleLineRule("\"", "\"", stringToken),

		new WhitespaceRule(new IWhitespaceDetector() {

			@Override
			public boolean isWhitespace(char c) {
				return Character.isWhitespace(c);
			}
		}) });
	}
}
