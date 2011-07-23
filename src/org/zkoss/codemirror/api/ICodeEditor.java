package org.zkoss.codemirror.api;

import org.zkoss.zk.ui.api.HtmlBasedComponent;

/**
 * TODO not support so far , but might support in future.
 * 	tabMode ,enterMode ,firstLineNumber ,
 * 		indentWithTabs ,indentUnit ,electricChars ,
 * 		gutter ,onGutterClick ,
 */
public interface ICodeEditor extends HtmlBasedComponent {

	public static final String MODE_PYTHON = "python";

	public static final int TYPE_EDITABLE = 0;
	public static final int TYPE_READONLY = 1;
	public static final int TYPE_READONLY_WITH_CURSOR = 2;
	
	public String getMode();

	/**
	 * The mode to use. When not given, this will default to the first mode that was loaded. It may be a string, which either simply names the mode or is a MIME type associated with the mode. Alternatively, it may be an object containing configuration options for the mode, with a name property that names the mode (for example {name: "javascript", json: true}). The demo pages for each mode contain information about what configuration parameters the mode supports. You can ask CodeMirror which modes and MIME types are loaded with the CodeMirror.listModes and CodeMirror.listMIMEs functions.
	 * @param mode
	 */
	public void setMode(String mode);

	public String getValue();

	/**
	 * The starting value of the editor.
	 * @param text
	 */
	public void setValue(String text);

	public boolean isAutosize();

	/**
	 * Means the height is auto , 
	 * the hieght will grow up when user typing instead of scroll.
	 *  
	 * If you set a height will cause this config not working .
	 */
	public void setAutosize(boolean autosize);
	
	/**
	 * The tab index to assign to the editor. If not given, no tab index will be assigned.
	 * @param tabIndex
	 */
	public void setTabIndex(int tabIndex);
	
	public int getTabIndex();
	
	public boolean isShowLineNumbers();
	
	/**
	 *	Whether to show line numbers to the left of the editor.
	 * (it's the option for lineNumbers in Codemirrow)
	 * @return
	 */
	public void setShowLineNumbers(boolean linenumbers);
	
	public int isReadyOnly();
	
	/**
	 * This disables editing of the editor content by the user. (Changes through API functions will still be possible.) If you also want to disable the cursor, use "nocursor" 
	 * as a value for this option, instead of true.
	 * 
	 * @param readonly
	 */
	public void setReadyOnly(int readonlyType);
	
	/**
	 * True means readonly but have cursor , 
	 * false means editable.
	 * 
	 * If you want to set readonly without cursor,
	 * please use {@link #setReadyOnly(int)} 
	 * 
	 * @param readonlyType
	 */
	public void setReadyOnly(boolean readonlyType);


}