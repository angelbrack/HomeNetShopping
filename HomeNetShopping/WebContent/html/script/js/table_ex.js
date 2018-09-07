/*
	�������� ���̺� �ο츦 �����ϰ� �����ϴ� ���.
	interface
		Table(id, isHead) - ������ id:���̺�ID, isHead:������翩��
		addRow(beforeRow) - �������̺��� �ο츦 ���� beforeRow:row��ȣ �տ� ������ġ.
		delRow(delRow) -
		getRow(curRow) -
		getCol(curRow, curCol) - 
		getColData(curRow, curCol) -
		setRowData(curRow, values) -
		getRowCount() -
		getColCount() -
		getRowIdx() -
		
*/
function Table(id, isRmTmpl){
	this.isInit = false;
	if(id){
		this.init(id, true);
	}
}

Table.prototype.init = function (id, isRmTmpl){

	var tt = document.getElementById(id);
	if(!tt){
		alert("does not founds table id : "+id);
		return false;
	}
	
	//�����ġ ã��.
	this.rowCount = 0;
	var node = null;
	for(var i=0;i<tt.childNodes.length;i++){
		node = tt.childNodes[i];
		if("THEAD" == node.tagName){
			this.headNode = node;
		}
		
		//���ø� ��ġ ã��
		if("TBODY" == node.tagName){
			this.rowCount += 1;
			if("template" == node.getAttribute("id")){
				node.removeAttribute("id");
				this.tmplNode = node;
				if(isRmTmpl){
					tt.removeChild(node);
					this.rowCount -= 1;
				}
			}
		}
	}
	
	
	//�÷� ���� �ľ�.
	this.colCount = 0;
	if(this.tmplNode){
		for(var i=0;i<this.tmplNode.childNodes.length;i++){
			node = this.tmplNode.childNodes[i];
			if("TR" == node.tagName){
				for(var j=0;j<node.childNodes.length;j++){
					if("TD" == node.childNodes[j].tagName){
						this.colCount += 1;
					}
				}
			}
		}
	} else if(this.headNode){
		var trNode,tdNode, att;
		this.tmplNode = document.createElement("TBODY");
		for(var i=0;i<this.headNode.childNodes.length;i++){
			node = this.headNode.childNodes[i];
			if("TR" == node.tagName){
				trNode = document.createElement("TR");
				this.tmplNode.appendChild(trNode);
				for(var j=0;j<node.childNodes.length;j++){
					if("TD" == node.childNodes[j].tagName 
						|| "TH" == node.childNodes[j].tagName){
						
						tdNode = document.createElement("TD");
						
						att = node.childNodes[j].getAttribute("rowspan");
						tdNode.setAttribute("rowspan", att);
						
						att = node.childNodes[j].getAttribute("colspan");
						tdNode.setAttribute("colspan", att);
						
						tdNode.innerHTML = "";
						trNode.appendChild(tdNode);
						this.colCount += 1;
					}
				}
			}
		}
	}
	
	this.tab = tt;
	if(!this.tmplNode){
		alert("���ø� ��尡 �����ϴ�. <tbody id=\"template\">");
	} else {
		this.isInit = true;
	}
}

Table.prototype.checkInit = function(){
	if(!this.isInit){
		alert("�ʱ�ȭ�� ���� �ʾҽ��ϴ�");
		return false;
	}
	return true;
}

Table.prototype.getRow = function (rowIdx){

	if(rowIdx == 0){
		return this.headNode;
	}
	
	var currRow = 1;
	var nodeLen = this.tab.childNodes.length;
	if(this.rowCount >= rowIdx){
		for(var i=0;i<nodeLen;i++){
			node = this.tab.childNodes[i];
			if("TBODY" == node.tagName && currRow++ == rowIdx){
				return node;
			} else {
				node = null;
			}
		}
	}
	
	return node;
}

Table.prototype.getCol = function (rowIdx, colIdx){
	var row = this.getRow(rowIdx);
	if(row){
		var currCol = 1;
		if(this.colCount >= colIdx){
			for(var i=0;i<row.childNodes.length;i++){
				node = row.childNodes[i];
				if("TR" == node.tagName){
					for(var j=0;j<node.childNodes.length;j++){
						if("TD" == node.childNodes[j].tagName && currCol++ == colIdx){
							return node.childNodes[j];
						}
					}
				}
			}
		}
	}
	
	return null;
}

Table.prototype.addRow = function (rowIdx){
	if(!this.checkInit()){
		return false;
	}
	
	var clonNode = this.tmplNode.cloneNode(true);

	if(rowIdx){
		var node = this.getRow(rowIdx);
		if(!node){
			return false;
		}
		this.tab.insertBefore(clonNode, node);
	} else {
		this.tab.appendChild(clonNode);
	}
	this.rowCount += 1;
	return true;
}


Table.prototype.delRow = function (rowIdx){
	if(!this.checkInit() || this.rowCount == 0 || rowIdx == 0){
		return false;
	}
	
	var clonNode = this.tmplNode.cloneNode(true);
	var node = null;
	if(rowIdx){
		node = this.getRow(rowIdx);	
	} else {
		node = this.getRow(this.rowCount);	
	}
	
	if(node){
		this.tab.removeChild(node);
		this.rowCount -= 1;
		return true;
	} else {
		return false;
	}
}

Table.prototype.setColValue = function (rowIdx, colIdx, val){
	var col = this.getCol(rowIdx, colIdx);
	if(col){
		col.innerHTML = val;
		return false;
	}
	
	return false;
}

Table.prototype.getColValue = function (rowIdx, colIdx){
	var col = this.getCol(rowIdx, colIdx);
	if(col){
		return col.innerHTML;
	}
	
	return null;
}

Table.prototype.setColData = function (rowIdx, colIdx, obj){
	var col = this.getCol(rowIdx, colIdx);
	if(col){
		if(val == null){
			col.removeChild(null);
		} else {
			col.appendChild(val);
		}
		return true;
	}
	
	return false;
}

Table.prototype.getColData = function (rowIdx, colIdx, fldNm){
	var col = this.getCol(rowIdx, colIdx);
	if(col){
		var objArr = new Array();
		var objLen = col.childNodes.length;
		var obj = null;
		var j = 0;
		for(var i=0;i<objLen;i++){
			obj = col.childNodes[i];
			//�ʵ�� �����ϸ� �ʵ�� ��ġ �ϴ°Ÿ� ��´�.
			if(obj.type && (!fldNm || (obj.name == fldNm))){
				objArr[j++] = obj;
			}
		}
		
		if(objArr.length == 1){
			return objArr[0];
		} else {
			return objArr;
		}
	}
	
	return null;
}


Table.prototype.getRowCount = function (){
	return this.rowCount;
}

Table.prototype.getColCount = function (){
	return this.colCount;
}

Table.prototype.getRowIdx =  function (obj){
	var len = this.rowCount;
	var node = null;
	for(var i=1;i<=len;i++){
		node = this.getRow(i)
		if(node == obj){
			return i;
		}
	}
	
	return -1;
}
