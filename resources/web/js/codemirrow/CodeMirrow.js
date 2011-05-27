function delegater(member){
	return (function(){
		if(this.desktop)
			return this._instance[member]();
	});
}

codemirrow.CodeMirrow = zk.$extends(zk.Widget, {
    _text:'', //default value for text attribute
	_instance:null,
	_version:2,
	$define: {
		autosize:function(){
			if(this.desktop)
				this._getCodeMirror().css("height","auto");
		},
		text: function(){ //this function will be called after setText() .
			if(this.desktop)
				this._instance.setValue(this._text); 
			
		},
		mode:function(){
			
			if(this._mode == "xhtml"){
				this.setHtmlMode(true);
			}
			
			if (this.desktop) {
				if (this._mode == "xhtml") {
					this._instance.setOption("mode", "xml");
				}else if(this._mode == "html"){
					this._instance.setOption("mode", "text/html");
				}else{
					this._instance.setOption("mode", this._mode);
				}
			}
			
		},
		htmlMode:function () {
			if (this.desktop) 
				this._instance.setOption("htmlMode", this._htmlMode);
		},
		singleLineStringErrors:function () {
			if (this.desktop) 
				this._instance.setOption("singleLineStringErrors", this._singleLineStringErrors);
		},
		version:function () {
			if (this.desktop) 
				this._instance.setOption("version", this._version);
		},
		alignCDATA:function () {
			if (this.desktop) 
				this._instance.setOption("alignCDATA", this._alignCDATA);
		},
		verbatim:function () {
			if (this.desktop) 
				this._instance.setOption("verbatim", this._verbatim);
		}
	},
	undo:delegater("undo"),
	redo:delegater("redo"),
	historySize:delegater("historySize"),
	
	doFocus_:function(){
		if(this.desktop)
			this._instance.focus();
	},
	prepareInitOption_:function(){
		var ret = {
			htmlMode:this._htmlMode,
			singleLineStringErrors:this._singleLineStringErrors,
			version:this._version,
			alignCDATA:this._alignCDATA,
			verbatim:this._verbatim,
			mode:this._mode
		};
		if(this._mode == "html"){ //add some alises.
			ret["mode"] = "text/html";
		}else if(this._mode == "xhtml"){
			ret["mode"] = "xml";
			ret["htmlMode"] = true;
		}
		return ret;
	},
	_getCodeMirror:function(){
		return jq("> .CodeMirror",this);
	},
	bind_: function () {
		this.$supers(codemirrow.CodeMirrow,'bind_', arguments);
		this._instance = CodeMirror.fromTextArea(this.$n("real"),
				this.prepareInitOption_()
			);
		var wrap  = this._getCodeMirror();
		if(this._width)
			wrap.css("width",this._width);

		if(this._autosize)
			wrap.css("height","auto");
		else if(this._height)
			wrap.css("height",this._height);
	
		
		//A example for domListen_ , REMEMBER to do domUnlisten in unbind_.
		//this.domListen_(this.$n("cave"), "onClick", "_doItemsClick");
	},

	unbind_: function () {
		this._instance = null;
		this.$supers(codemirrow.CodeMirrow,'unbind_', arguments);
	},

	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-codemirror";
	}
});
