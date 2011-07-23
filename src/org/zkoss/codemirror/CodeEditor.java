package org.zkoss.codemirror;

import java.util.Map;

import org.zkoss.codemirror.api.ICodeEditor;
import org.zkoss.codemirror.api.IPythonEditor;
import org.zkoss.codemirror.api.IReStructuredTextEditor;
import org.zkoss.codemirror.api.IXMLeditor;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;

/**
 * Note : for some reason (as implementation as code mirrow) , The events we
 * didn't support very well , currently we supported only
 * onFocus,onBlur,onChange .
 * 
 * If you need more event, please give us reason and use case, will try to
 * handle it . (if possible ) ;)
 * 
 * Currently in this version we didn't support marker , if you need it , please
 * mail me a feature request. (tonylovejava[at]gmail.com)
 * 
 * @author TonyQ
 */
public class CodeEditor extends HtmlBasedComponent implements ICodeEditor, IPythonEditor, IReStructuredTextEditor,
		IXMLeditor {

	public static final String THEME_DEFAULT = "default";
	public static final String THEME_NIGHT = "night";
	public static final String THEME_NEAT = "neat";
	public static final String THEME_ELEGANT = "elegant";
	public static final String THEME_ECLIPSE = "eclipse";
	
	public static final String ON_SELECTION_REPLACED = "onSelectionReplaced";
	
	static {
		addClientEvent(CodeEditor.class, Events.ON_CHANGE, CE_IMPORTANT | CE_DUPLICATE_IGNORE);
		addClientEvent(CodeEditor.class, ON_SELECTION_REPLACED, CE_IMPORTANT | CE_DUPLICATE_IGNORE);
	}	
	
	private boolean _autosize;

	private String _value;

	private String _mode;
	
	
	private String _theme = THEME_DEFAULT;

	private boolean _htmlMode;

	private boolean _singleLineStringErrors;

	private int _version = IPythonEditor.VERSION_2;

	private boolean _alignCDATA;

	private int _readOnly = TYPE_EDITABLE;

	/**
	 * Here we follow the codemirrow naming , and use our naming only in
	 * interface level. (Default:true)
	 */
	private boolean _lineNumbers = true;

	private String _verbatim;

	private int _tabIndex = -1;

	
	public CodeEditor() {
		this(null,null);
	}
	
	public CodeEditor(String mode){
		this(mode,null);
	}
	
	public CodeEditor(String mode,String value){
		this._mode = mode;
		this._value = value;
	}
	
	// super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
		super.renderProperties(renderer);

		render(renderer, "value", _value);
		render(renderer, "mode", (_mode != null ? _mode.toLowerCase() : _mode ));

		if (_htmlMode)
			render(renderer, "htmlMode", _htmlMode);

		if (_singleLineStringErrors)
			render(renderer, "singleLineStringErrors", _singleLineStringErrors);

		if (_version != IPythonEditor.VERSION_2)
			render(renderer, "version", _version);

		if (_alignCDATA)
			render(renderer, "alignCDATA", _alignCDATA);

		if (_verbatim != null)
			render(renderer, "verbatim", _verbatim);

		if (_autosize)
			render(renderer, "autosize", _autosize);

		if (_tabIndex != -1)
			render(renderer, "tabIndex", _tabIndex);

		if (!_lineNumbers)
			render(renderer, "lineNumbers", _lineNumbers);

		if (_readOnly != TYPE_EDITABLE)
			render(renderer, "readOnly", _readOnly);
		
		if(!Objects.equals(_theme, THEME_DEFAULT))
			render(renderer, "theme", _theme);
	}

	@Override
	public void service(AuRequest request, boolean everError) {

		if (Events.ON_CHANGE.equals(request.getCommand())||
				ON_SELECTION_REPLACED.equals(request.getCommand())		
		) {
			String value = this._value;
			final Map data = request.getData();

			final Object val = data.get("value");
			String newValue = val == null ? "" : val.toString();
			this._value = newValue;
			Events.postEvent(new InputEvent(request.getCommand(), this, newValue, value));

		} else
			super.service(request, everError);
	}

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
	 * @see org.zkoss.mesh.ICodemirror#getValue()
	 */
	public String getValue() {
		return _value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.mesh.ICodemirror#setValue(java.lang.String)
	 */
	public void setValue(String text) {
		if (!Objects.equals(_value, text)) {
			_value = text;
			smartUpdate("value", _value);
		}
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

	public void setAutosize(boolean autosize) {
		this._autosize = autosize;
	}

	public void setTabIndex(int tabIndex) {
		if (!Objects.equals(_tabIndex, tabIndex)) {
			_tabIndex = tabIndex;
			smartUpdate("tabIndex", _tabIndex);
		}
	}

	public int getTabIndex() {
		return _tabIndex;
	}

	public boolean isShowLineNumbers() {
		return this._lineNumbers;
	}

	/**
	 * set if code mirror show linenumber
	 */
	public void setShowLineNumbers(boolean linenumbers) {
		if (!Objects.equals(this._lineNumbers, linenumbers)) {
			this._lineNumbers = linenumbers;
			smartUpdate("linenumbers", this._lineNumbers);
		}
	}

	
	public void setVflex(String flex) {
		throw new UnsupportedOperationException("vflex is currently not supported by CodeEditor");
	}
	
	public void setHflex(String hlex) {
		throw new UnsupportedOperationException("hflex is currently not supported by CodeEditor");
	}
	
	public int isReadyOnly() {
		return this._readOnly;
	}

	public void setReadyOnly(boolean readonly) {
		int newType = readonly ? TYPE_READONLY : TYPE_EDITABLE;
		setReadyOnly(newType);
	}

	public void setReadyOnly(int readonlyType) {
		if (!Objects.equals(this._readOnly, readonlyType)) {
			this._readOnly = readonlyType;
			smartUpdate("readonly", this._readOnly);
		}
	}

	/**
	 * Note that the replaceSelection will not change the server side value directly ,
	 * the value will update in next execution. 
	 * or you could listen the event , onSelectionReplaced(InputEvent) to handle this.
	 * @return
	 */
	public void replaceSelection(String newString){
		response(new AuInvoke(this,"replaceSelection",newString));
	}
	
	public String getTheme() {
		return _theme;
	}

	/**
	 * You could set the theme you want .
	 * Currently supported 
	public static final String THEME_DEFAULT = "default";
	public static final String THEME_NIGHT = "night";
	public static final String THEME_NEAT = "neat";
	public static final String THEME_ELEGANT = "elegant";
	public static final String THEME_ECLIPSE = "eclipse";
	 * @param theme
	 */
	public void setTheme(String theme) {
		if (!Objects.equals(this._theme, theme)) {
			this._theme = theme;
			smartUpdate("theme", this._theme);
		}
	}

}
