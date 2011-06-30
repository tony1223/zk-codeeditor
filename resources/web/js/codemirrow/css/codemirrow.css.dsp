<%@ page contentType="text/javascript;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>


<c:include page="~./js/codemirrow/lib/codemirror.css"/>

/* default theme */
<c:include page="~./js/codemirrow/lib/default.css"/>

/* neat theme */
<c:include page="~./js/codemirrow/lib/neat.css"/>

/* Loosely based on the Midnight Textmate theme */
/* night theme */
<c:include page="~./js/codemirrow/lib/night.css"/>

/* elegant theme */
<c:include page="~./js/codemirrow/lib/elegant.css"/>



 .CodeMirror {
   border: 1px solid #eee;
 }
/* 
  2011/5/28 by TonyQ:
   This is a must have for the plug-in or 
   it won't work correctly for typing. 

   Why we need this ? Because we change the default font-family.	
*/
.CodeMirror pre,.CodeMirror p,.CodeMirror span {
	font-family:monospace;
}