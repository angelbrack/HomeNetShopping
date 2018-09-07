/*
	동적으로 테이블 로우를 생성하고 삭제하는 기능.
	interface
		Table(id, isHead) - 생성자 id:테이블ID, isHead:헤더존재여부
		addRow(beforeRow) - 현재테이블의 로우를 생성 beforeRow:row번호 앞에 생성위치.
		delRow(delRow) -
		getRow(curRow) -
		getCol(curRow, curCol) - 
		getColData(curRow, curCol) -
		setRowData(curRow, values) -
		getRowCount() -
		getColCount() -
		getRowIdx() -
		
*/
function Table(id, isHead){
	if(id){
		this.init(id, isHead);
	}
}

Table.prototype.init = function (id, isHead){
	var tt = document.getElementById(id);
	if(!tt){
		alert("does not founds id:"+id);
		return false;
	}
	
	//this.tab = tt.firstChild;
	var tag = null;
	var o = null;
	var isSkip = true;
	this.startIdx = 0;
	for(var i=0;i<tt.childNodes.length;i++){
		tag = tt.childNodes[i].innerHTML;
		if(tag.toLowerCase().indexOf("<tr") != -1){
			o = tt.childNodes[i];
			if(isHead && isSkip){
				isSkip = false;
			} else {
				this.startIdx = i;//헤드나 기타 데이타 시작위치 저장.
				break;
			}
		}
	}
	
	this.tab = tt;
	this.isHead = isHead;
	
	//템플릿복제하고 원노드삭제.
	//var o = this.tab.childNodes[isHead?1:0];
	this.smplTrs = o.cloneNode(true);
}

Table.prototype.addRow = function (beforeRow){
	var jobIdx = beforeRow + this.startIdx;
	var tmpNode = this.smplTrs.cloneNode(true);
	if(this.tab.childNodes.length > Number(jobIdx)){
		var node = this.tab.childNodes[jobIdx];
		this.tab.insertBefore(tmpNode, node);
	} else {
		this.tab.appendChild(tmpNode);
	}
}


Table.prototype.delRow = function (rowIdx){
	var node;
	var jobIdx = rowIdx + this.startIdx;
	
	if(this.getRowCount() == 0){
		return;
	}
	
	if(this.tab.childNodes.length > Number(jobIdx)){
		node = this.tab.childNodes[jobIdx];
	} else {
		node = this.tab.childNodes[this.tab.childNodes.length - 1];
	}
	
	this.tab.removeChild(node);
}
	
Table.prototype.getRow = function (idx){
	var jobIdx = Math.abs(idx+this.startIdx);
	var rowLen = this.tab.childNodes.length;
	if(rowLen > jobIdx){
		return this.tab.childNodes[jobIdx];
	}
	return null;
}

Table.prototype.getCol = function (rowIdx, colIdx){
	var row = this.getRow(rowIdx);
	if(row){
		var colLen = row.childNodes.length;
		if(colLen > colIdx){
			return row.childNodes[colIdx];
		}
	}
	return null;
}


Table.prototype.getColData = function (rowIdx, colIdx){
	var col = this.getCol(rowIdx, colIdx);
	if(col && col.firstChild){
		var o = col.firstChild;
		if(o){
			
			return o.type?_GetFieldValue(o, null, "no data"):o.innerHTML;
		}
	}
	return null;
}

Table.prototype.setRowData = function (rowIdx, vals){
	var colLen = this.getColCount();
	if(vals && typeof(vals) == "array"){
		for(var i=0;i<colLen;i++){
			this.setColData(rowIdx, i, vals[i]);
		}
		
		return true;
	}
	
	return false;
}


Table.prototype.setColData = function (rowIdx, colIdx, val){
	var col = this.getCol(rowIdx, colIdx);
	if(col && col.firstChild){
		var o = col.firstChild;
		if(o){
			if(o.type){
				return _SetFieldValue(o, val, "no data")
			} else {
				o.innerHTML = val;
				return true;
			}
		}
	}
	return false;
}

Table.prototype.getRowCount = function (){
	return this.tab.childNodes.length - this.startIdx;
}

Table.prototype.getColCount = function (){
	return this.tab.childNodes[0].childNodes.length;
}

Table.prototype.getRowIdx =  function (obj){
	var len = this.tab.childNodes.length;
	for(var i=0;i<len;i++){
		if(this.tab.childNodes[i] == obj){
			return i - this.startIdx;
		}
	}
	return -1;
}
