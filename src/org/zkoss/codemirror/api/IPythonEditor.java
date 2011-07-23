package org.zkoss.codemirror.api;


public interface IPythonEditor extends ICodeEditor{

	public static int VERSION_2 = 2;
	public static int VERSION_3 = 3;
	
	
	/**
	 * version - 2/3 - 
	 * The version of Python to recognize. Default is 2.
	 */
	public int getVersion();
	
	/**
	 * version - 2/3 - 
	 * The version of Python to recognize. Default is 2.
	 */
	public void setVersion(int version);
	
	public boolean getSingleLineStringErrors();
	
	public void setSingleLineStringErrors(boolean enable);
	 

}