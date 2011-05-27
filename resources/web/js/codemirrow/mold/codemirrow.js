function (out) {

	var zcls = this.getZclass(),
		uuid = this.uuid;
		

	out.push('<div ', this.domAttrs_(), ' class="',zcls,'">',
		'<textarea id="',uuid,'-real" class="',zcls,'-content">',this._text,'</textarea>',
		'</div>');

}