<%@ page contentType="text/javascript;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>


<c:include page="~./js/codemirrow/lib/codemirror.css"/>
<c:include page="~./js/codemirrow/lib/mode/clike/clike.css"/>
<c:include page="~./js/codemirrow/lib/mode/css/css.css"/>
<c:include page="~./js/codemirrow/lib/mode/diff/diff.css"/>
<c:include page="~./js/codemirrow/lib/mode/haskell/haskell.css"/>
<c:include page="~./js/codemirrow/lib/mode/javascript/javascript.css"/>
<c:include page="~./js/codemirrow/lib/mode/plsql/plsql.css"/>
<c:include page="~./js/codemirrow/lib/mode/python/python.css"/>
<c:include page="~./js/codemirrow/lib/mode/rst/rst.css"/>
<c:include page="~./js/codemirrow/lib/mode/smalltalk/smalltalk.css"/>
<c:include page="~./js/codemirrow/lib/mode/stex/stex.css"/>
<c:include page="~./js/codemirrow/lib/mode/xml/xml.css"/>

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