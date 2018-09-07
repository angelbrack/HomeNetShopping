/** Util - Tree */

/**
 * 구현하는 쪽에서 
 * _PATH_DELETE : 노드 삭제 후 DB 삭제 URL
 * _PATH_CREATE : 노드 생성 후 DB 저장 URL 
 * 설정해야 함
 * 
 * 또는 UtilTree 생성 시 파라미터로 받을 있음
 * */
var _TREE_ID		= null;
var _PATH_DELETE	= "";
var _PATH_CREATE	= "";
var _PAHT_SORT		= "";
var _CHECK_BOX     = "";
var _CHECKED = "";
var _THEME = "classic";
function UtilTree(treeId, pathDelete, pathCreate, pathSort) {
	_TREE_ID		= treeId;
	_PATH_DELETE	= pathDelete;
	_PATH_CREATE	= pathCreate;
	_PAHT_SORT		= pathSort;
}
// Default
UtilTree.prototype.init = function() {
	$(function() {
		
		var objTree = document.getElementById(_TREE_ID);
		
		$(objTree).jstree({
			"themes" : {
				"theme": _THEME
				, "dots" : true
				, "icons": true
			}
			, "plugins": ["themes", "html_data","checkbox"]
		});
	});
};
//노드 생성 후 호출된(가상함수 비슷한 역할을 한다)
function callbackCreateItem(result)
{
	
}
//노드 생성 후 호출된(가상함수 비슷한 역할을 한다)
function callbackDragAndDrop(result)
{
	
}

function checkDelete()
{
	return true;
}

// 노드 생성 후 호출된다.
function createItem(data, rdata)
{
	var result = eval('('+rdata+')');
	$(data.rslt.obj).click(function ()
	{
		callbackCreateItem(result);
	}).click();
	alert(message1);
}
function createItemCnts(data, rdata)
{
	var result = eval('('+rdata+')');
	$(data.rslt.obj).click(function ()
	{
		callbackCreateItem(result);
	}).click();
	alert(message1);
}
function dragAndDrop(pos, tid, oid)
{
	if (_PAHT_SORT == null && _PAHT_SORT == "")
		return;
	
	$.ajax
	({
        type: 'get',
        async: false,
        url: _PAHT_SORT,
        contentType: 'charset=utf-8',
        data: {"pos":pos, "tid":tid, "oid":oid},
        datatype: 'json',
        success: function(data)
        {
        	var rtn = eval('('+data+')');
        	var result = rtn.completeYn;
        	if ("Y" == result)
        	{
        		alert('<spring:message code="COMMSG.UPDATE.SUCCESS" />');
        		callbackDragAndDrop(result);
        	} 
        	else
        	{
        		alert('<spring:message code="COMMSG.SERVER.SYNC.ERROR" />');	
        	}
		},
        error: function(data, status, err)
        {
        	alert('<spring:message code="COMMSG.SERVER.SYNC.ERROR" />');
		}
	});
}

/** Drag and Drop */

/**
 * callbackDndMove() 함수를 사용하는 곳에 생성해서 트리 노드 변경 후 액션 설정
 * callbackDndCreate() 함수를 사용하는 곳에 생성해서 트리 노드 생성 후 액션 설정
 * */
UtilTree.prototype.initDnd = function() {
	
	$(function() {
		
		var objTree = document.getElementById(_TREE_ID);
		
		$(objTree).jstree({
			"core" : { "initially_open" : [ "treeMainRoot" ], 'animation' : 1
				}
			, "themes" : {
				"theme": _THEME
					, "dots" : true
					, "icons": true
			}
			, "crrm" : { 
				"move" : {
					"check_move" : function (m) { 
						var p = this._get_parent(m.o);
						if (!p) {
							return false;
						}
						p = p == -1 ? this.get_container() : p;
						if(p === m.np) {
							return true;
						}
						if(p[0] && m.np[0] && p[0] === m.np[0]) { 
							return true;
						}						
						return false;
					}
				}
			}
			, "dnd" : {
				"drop_target" : false
				, "drag_target" : false
				, "drop_finish" : function (data) { 
				}
			}
			, "plugins": ["themes", "html_data", "crrm", "dnd", "contextmenu", _CHECK_BOX]
			})
			// drag*drop
			.bind('move_node.jstree', function(e, data) {
				var pos = data.rslt.p, tid = data.rslt.r[0].id.split('_').pop(), oid = data.rslt.o[0].id.split('_').pop();
				dragAndDrop(pos, tid, oid);
			//	callbackDndMove(pos, tid, oid);
			})
			.bind("create.jstree", function (e, data) {
		
				$.post(
						_PATH_CREATE, 
						{ 
							"operation" : "create_node", 
							"parentNo" : data.rslt.parent.attr("id").replace("treeItem",""), 
							"nodeNm" : data.rslt.name
						},
						
						function (rdata) {
							var r = eval('('+rdata+')');
							if(r.completeYn == "Y") {
								$(data.rslt.obj).attr("id", "treeItem" + r.id);
								createItem(data, rdata);
							//	callbackDndCreate(data, rdata);
							}
							else {
								$.jstree.rollback(data.rlbk);
								alert("create ERROR");
							}
						}
					);
				})
			.bind("remove.jstree", function (e, data) {
				if (confirm(message3)) {
					// 삭제 가능 여부 체크를 위해 화면에 구현 해야 한다.
					// 체크 해야 할 사항이 없어도 함수를 구현하고 true를 리턴 해야 한다.
					if (checkDelete(data))
					{
						
						data.rslt.obj.each(function () {
							$.ajax({
								async : false,
								type: 'POST',
								url: _PATH_DELETE,
								data : { 
									"operation" : "remove_node", 
									"nodeNo" : this.id.replace("treeItem",""),
									"dnldClsNo" : this.id.replace("treeItem","")
								}, 
								success : function (r) {
									if(!r.status) {
										alert(message2);
										callbackDeleteItem(data);
									}
								}, 
								error: function(data, textStatus, errorThrown) {
									alert(message4);
								}
							});
						});
					}
					else
					{
						$.jstree.rollback(data.rlbk);
					}
				} else {
					$.jstree.rollback(data.rlbk);
				}
			}).bind("loaded.jstree", function (e, data) { 
				if(_CHECKED != ""){
					var result= _CHECKED.split(",");
					if(result.length > 0){
						for(i=0; i < result.length; i++){
							data.inst.check_node("#treeItem" + result[i], true);
						}
					}
				}
				
			})   
			
//			.bind("click.jstree", function (event, data) {      
//				alert(event);
//			})
			.delegate("a", "click", function (event, data) { event.preventDefault(); });
		});
	};
	UtilTree.prototype.initCnts = function() {
		
		$(function() {
			
			var objTree = document.getElementById(_TREE_ID);
			
			$(objTree).jstree({
				"core" : { "initially_open" : [ "treeMainRoot" ], 'animation' : 1
					}
				, "themes" : {
					"theme": _THEME
						, "dots" : true
						, "icons": true
				}
				, "crrm" : { 
					"move" : {
						"check_move" : function (m) { 
							var p = this._get_parent(m.o);
							if (!p) {
								return false;
							}
							p = p == -1 ? this.get_container() : p;
							if(p === m.np) {
								return true;
							}
							if(p[0] && m.np[0] && p[0] === m.np[0]) { 
								return true;
							}						
							return false;
						}
					}
				}
				, "dnd" : {
					"drop_target" : false
					, "drag_target" : false
					, "drop_finish" : function (data) { 
					}
				}
				, "plugins": ["themes", "html_data", "crrm", "dnd", "contextmenu", _CHECK_BOX]
				})
				// drag*drop
				.bind('move_node.jstree', function(e, data) {
					var pos = data.rslt.p, tid = data.rslt.r[0].id.split('_').pop(), oid = data.rslt.o[0].id.split('_').pop();
					dragAndDrop(pos, tid, oid);
				//	callbackDndMove(pos, tid, oid);
				})
				.bind("create.jstree", function (e, data) {
					$.post(
							_PATH_CREATE, 
							{ 
								"operation" : "create_node", 
								"hgrkTocId" : data.rslt.parent.attr("id").replace("treeItem",""), 
								"tocNm" : data.rslt.name,
								"cntsNo" : jQuery("#cntsNo").val()
							},
							
							function (rdata) {
								var r = eval('('+rdata+')');
								if(r.completeYn == "Y") {
									createItemCnts(data, rdata);
								//	callbackDndCreate(data, rdata);
								}
								else {
									$.jstree.rollback(data.rlbk);
									alert("create ERROR");
								}
							}
						);
					})
				.bind("remove.jstree", function (e, data) {
					if (confirm(message3)) {
						// 삭제 가능 여부 체크를 위해 화면에 구현 해야 한다.
						// 체크 해야 할 사항이 없어도 함수를 구현하고 true를 리턴 해야 한다.
						if (checkDelete())
						{
							data.rslt.obj.each(function () {
								$.ajax({
									async : false,
									type: 'POST',
									url: _PATH_DELETE,
									data : { 
										"operation" : "remove_node", 
										"nodeNo" : this.id.replace("treeItem",""),
										"tocId" : this.id.replace("treeItem","")
									}, 
									success : function (r) {
										if(!r.status) {
											alert(message2);
											callbackDeleteItem(data);
										}
									}, 
									error: function(data, textStatus, errorThrown) {
										alert(message4);
									}
								});
							});
						}
						else
						{
							$.jstree.rollback(data.rlbk);
						}
					} else {
						$.jstree.rollback(data.rlbk);
					}
				}).bind("loaded.jstree", function (e, data) { 
					if(_CHECKED != ""){
						var result= _CHECKED.split(",");
						if(result.length > 0){
							for(i=0; i < result.length; i++){
								data.inst.check_node("#treeItem" + result[i], true);
							}
						}
					}
				})   
				
//				.bind("click.jstree", function (event, data) {      
//					alert(event);
//				})
				.delegate("a", "click", function (event, data) { event.preventDefault(); });
			});
		};