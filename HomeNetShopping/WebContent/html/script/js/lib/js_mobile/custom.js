$(function(){
	//레이어 팝업 열기
	$(".open_popup").click(function(e){
		e.preventDefault();
		var popup_id = $(this).attr("href");
		$(popup_id).addClass("show");
		$("html, body").css("overflow", "hidden");
	});
	//레이어 팝업 닫기
	$(".close_popup").click(function(e){
		e.preventDefault();
		$(this).parents(".layer_popup_wrap").removeClass("show");
		$("html, body").css("overflow", "auto");
	});
});