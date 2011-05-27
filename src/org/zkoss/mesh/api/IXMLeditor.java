package org.zkoss.mesh.api;


public interface IXMLeditor extends ICodeEditor{

	public boolean getHtmlMode();
	/**
	 * (ZK only:If you use xhtml mode , this one is already assume ture.)
	 * 
	 * This switches the mode to parse HTML instead of XML. This means attributes do not have to be quoted, and some elements (such as br) do not require a closing tag.
	 * @return
	 */
	public void setHtmlMode(boolean enable);
	
	public boolean getAlignCDATA();

	/**
	 * Setting this to true will force the opening tag of CDATA blocks to not be indented.
	 * @return
	 */
	public void setAlignCDATA(boolean enable);
	 

}