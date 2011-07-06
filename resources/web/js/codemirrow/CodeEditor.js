function delegater(member){
	return (function () {
		if(this.desktop)
			return this._instance[member]();
	});
}

var updateModetehn = function () { //this function will be called after setText() .
	if(this.desktop)
		this._instance.setOption("mode", this._prapareMode());
	
};

codemirrow.CodeEditor = zk.$extends(zk.Widget, {
    _value:'',
	_instance:null,
	_version:2,
	_lineNumbers:true,
	_readonly:0,
	_theme:'default',
	$define: {
		autosize:function () {
			if(this.desktop)
				this._getCodeMirror().css("height","auto");
		},
		value: function () { //this function will be called after setText() .
			if(this.desktop)
				this._instance.setValue(this._value); 
			
		},
		theme:function(){
			if(this.desktop)
				this._instance.setOption("theme", this._theme);
		},
		mode:updateModetehn,
		htmlMode:updateModetehn,
		singleLineStringErrors:updateModetehn,
		version:updateModetehn,
		alignCDATA:updateModetehn,
		verbatim:updateModetehn,
		lineNumbers:function () {
			if (this.desktop) 
				this._instance.setOption("lineNumbers", this._lineNumbers);
		},
		readOnly:function () {
			if (this.desktop) {
				if(this._readOnly == codemirrow.CodeEditor.TYPE_READONLY_WITH_CURSOR )
					this._instance.setOption("readOnly", "nocursor");
				else
					this._instance.setOption("readOnly", 
						this._readOnly == codemirrow.CodeEditor.TYPE_READONLY );
			}
		}
		
	},
	undo:delegater("undo"),
	redo:delegater("redo"),
	historySize:delegater("historySize"),
	
	focus_:function () {
		var wgt = this;
		if(this.desktop && this._instance)
			this._instance.focus();
		else  //Timing issue for init.
			setTimeout(function(){ 
				if(this.desktop && this._instance) 
					this._instance.focus();
			},200);
	},
	prepareInitOption_:function () {
		var wgt = this,
		  	ret = {
				mode:this._prapareMode(),
				lineNumbers:this._lineNumbers,
				onFocus:function(){
					wgt.fire("onFocus");
				},
				onBlur :function(instance){
					var val = instance.getValue();
					if (val != wgt._value) {
						wgt._value = val;
						wgt.fire("onChange", {
							value: val
						});
					}
					
					wgt.fire("onBlur");
				},
				theme:this._theme,
				/* 
				 * it's actually onChanging ..not onChange.:-(
				 */
				onChange:function(instance){
					var val = instance.getValue();
					if (val != wgt._value) {
						wgt._value = val;
						wgt.fire("onChanging", {
							value: val
						});
					}
				}
				
			};
		return ret;
	},
	_prapareMode:function(){
		var mode = {
			name:this._mode,
			/*html options*/
			htmlMode:this._htmlMode,
			alignCDATA:this._alignCDATA,
			verbatim:this._verbatim,
			singleLineStringErrors:this._singleLineStringErrors,
			version:this._version					
		};
		
		if (this._mode == "html") { //add some alises.
			mode = "text/html";
		} else if (this._mode == "xhtml") {
			mode.name = "xml";
			mode.htmlMode = true;
		} else if (this._mode == "c") {
			mode = "text/x-csrc";
		} else if (this._mode == "c++") {
			mode = "text/x-c++src";
		} else if (this._mode == "java") {
			mode = "text/x-java";
		} else if (this._mode == "js") {
			mode = "javascript";
		} else if (this._mode == "json") {
			mode.name = "javascript";
			mode.json = true;
		} else if (this._mode == "css") {
			mode = "css";
		}
		return mode;
	},
	_getCodeMirror:function () {
		return jq("> .CodeMirror",this);
	},
	bind_:function () {
		this.$supers(codemirrow.CodeEditor,'bind_', arguments);
		this._instance = CodeMirror.fromTextArea(this.$n("real"),
				this.prepareInitOption_()
			);
		
		//TODO review this.
		//The instance will trim the string , so we use the new value as value,
		//to prevent it send onChange.
		
		this._value = this._instance.getValue(); 
		var wrap  = this._getCodeMirror();
		if(this._width)
			wrap.css("width",this._width);

		if(this._autosize)
			wrap.css("height","auto");
		else if(this._height)
			wrap.css("height",this._height);
	},

	unbind_:function () {
		this._instance = null;
		this.$supers(codemirrow.CodeEditor,'unbind_', arguments);
	},

	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-codeeditor";
	}
},{
	TYPE_EDITABLE:0,
	TYPE_READONLY:1,
	TYPE_READONLY_WITH_CURSOR:2
});
