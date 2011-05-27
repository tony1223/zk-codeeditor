package org.zkoss.mesh.api;

import org.zkoss.zk.ui.api.HtmlBasedComponent;

public interface ICodeEditor extends HtmlBasedComponent {

	public static final String MODE_PYTHON = "python";

	public String getMode();

	public void setMode(String mode);

	public String getText();

	public void setText(String text);

	public boolean isAutosize();

	public void setAutosize(boolean autosize);
}