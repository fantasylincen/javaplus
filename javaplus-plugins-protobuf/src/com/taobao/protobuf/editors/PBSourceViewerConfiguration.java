package com.taobao.protobuf.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.taobao.protobuf.editors.contentassist.PBContentAssistProcessor;

/**
 * ‘¥¥˙¬Î ”Õº≈‰÷√
 */
public class PBSourceViewerConfiguration extends SourceViewerConfiguration {
	
	private PBScanner		scanner;
	private ColorManager	colorManager;

	public PBSourceViewerConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE };
	}

	protected PBScanner getPBScanner() {
		if (scanner == null) {
			scanner = new PBScanner(colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(IPBColorConstants.DEFAULT))));
		}
		return scanner;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getPBScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant ca = new ContentAssistant();
		ca.setContentAssistProcessor(new PBContentAssistProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return ca;
	}

}