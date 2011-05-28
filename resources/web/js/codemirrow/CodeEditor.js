function delegater(member){
	return (function () {
		if(this.desktop)
			return this._instance[member]();
	});
}

codemirrow.CodeEditor = zk.$extends(zk.Widget, {
    _value:'',
	_instance:null,
	_version:2,
	_lineNumbers:true,
	_readonly:0,
	$define: {
		autosize:function () {
			if(this.desktop)
				this._getCodeMirror().css("height","auto");
		},
		value: function () { //this function will be called after setText() .
			if(this.desktop)
				this._instance.setValue(this._value); 
			
		},
		mode:function () {
			
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
		},
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
				htmlMode:this._htmlMode,
				singleLineStringErrors:this._singleLineStringErrors,
				version:this._version,
				alignCDATA:this._alignCDATA,
				verbatim:this._verbatim,
				mode:this._mode,
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
				}
				//it's actually onChanging ..not onChange.:-(
				/*,
				onChange:function(instance){
				}
				*/
			};
		if(this._mode == "html"){ //add some alises.
			ret["mode"] = "text/html";
		}else if(this._mode == "xhtml"){
			ret["mode"] = "xml";
			ret["htmlMode"] = true;
		}
		return ret;
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
