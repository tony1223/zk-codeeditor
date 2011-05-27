package org.zkoss.codemirror;

import org.zkoss.lang.Objects;
import org.zkoss.mesh.api.ICodeEditor;
import org.zkoss.mesh.api.IPythonEditor;
import org.zkoss.mesh.api.IReStructuredTextEditor;
import org.zkoss.mesh.api.IXMLeditor;
import org.zkoss.zk.ui.HtmlBasedComponent;

public class CodeEditor extends HtmlBasedComponent implements ICodeEditor, IPythonEditor, IReStructuredTextEditor,
		IXMLeditor {

	private boolean _autosize;
	
	
	private String _text;

	private String _mode;

	private boolean _htmlMode;

	private boolean _singleLineStringErrors;

	private int _version = IPythonEditor.VERSION_2;

	private boolean _alignCDATA;

	private String _verbatim;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#getMode()
	 */
	public String getMode() {
		return _mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#setMode(java.lang.String)
	 */
	public void setMode(String mode) {
		if (!Objects.equals(_mode, mode)) {
			_mode = mode;
			smartUpdate("mode", _mode);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#getText()
	 */
	public String getText() {
		return _text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#setText(java.lang.String)
	 */
	public void setText(String text) {
		if (!Objects.equals(_text, text)) {
			_text = text;
			smartUpdate("text", _text);
		}
	}

	// super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
		super.renderProperties(renderer);

		render(renderer, "text", _text);
		render(renderer, "mode", _mode);
		
		if(_htmlMode)
			render(renderer, "htmlMode", _htmlMode);
		
		if(_singleLineStringErrors)
			render(renderer, "singleLineStringErrors", _singleLineStringErrors);
		
		if( _version != IPythonEditor.VERSION_2)
			render(renderer, "version", _version);
		
		if(_alignCDATA)
			render(renderer, "alignCDATA", _alignCDATA);
		
		if(_verbatim != null)
			render(renderer, "verbatim", _verbatim);
		
		if(_autosize)
			render(renderer, "autosize", _autosize);
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#getZclass()
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-codemirror");
	}

	public boolean getHtmlMode() {
		return _htmlMode;
	}

	/**
	 * XML mode only 
	 */
	public void setHtmlMode(boolean htmlMode) {
		if (!Objects.equals(_htmlMode, htmlMode)) {
			_htmlMode = htmlMode;
			smartUpdate("htmlMode", _htmlMode);
		}
	}

	public boolean getAlignCDATA() {
		return _alignCDATA;
	}

	/**
	 * XML mode only 
	 */
	public void setAlignCDATA(boolean alignCDATA) {
		if (!Objects.equals(_alignCDATA, alignCDATA)) {
			_alignCDATA = alignCDATA;
			smartUpdate("alignCDATA", _alignCDATA);
		}
	}

	/**
	 * ReStructuredText mode only 
	 */
	public void setVerbatim(String verbatim) {
		if (!Objects.equals(_verbatim, verbatim)) {
			_verbatim = verbatim;
			smartUpdate("verbatim", _verbatim);
		}
	}

	public String getVerbatim() {
		return _verbatim;
	}

	public int getVersion() {
		return _version;
	}

	/**
	 * python mode only 
	 */
	public void setVersion(int version) {
		if (!Objects.equals(_version, version)) {
			_version = version;
			smartUpdate("version", _version);
		}
	}

	public boolean getSingleLineStringErrors() {
		return _singleLineStringErrors;
	}

	/**
	 * python mode only 
	 */
	public void setSingleLineStringErrors(boolean singleLineStringErrors) {
		if (!Objects.equals(_singleLineStringErrors, singleLineStringErrors)) {
			_singleLineStringErrors = singleLineStringErrors;
			smartUpdate("singleLineStringErrors", _singleLineStringErrors);
		}
	}
	
	public boolean isAutosize() {
		return _autosize;
	}

	/**
	 * Means the height is auto , 
	 * the hieght will grow up when user typing instead of scroll.
	 *  
	 * If you set a height will cause this config not working .
	 */
	public void setAutosize(boolean autosize) {
		this._autosize = autosize;
	}

}
