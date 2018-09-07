$(function(){
	/* .wp_content 높이값이 윈도우팝업 높이에 따라 유동적으로 바뀐다 */
	$("html, body").css("overflow", "hidden");
	var wp_h = $(window).height();
	var wp_top = $(".wp_top");
	var wp_top_h = wp_top.height();
	var wp_bottom = $(".wp_bottom");
	var wp_bottom_h = wp_bottom.height();
	var wp_content = $(".wp_content");
	if(wp_content.hasClass("has_right_div")){
		$(".primary_div").css("height", wp_h - wp_top_h - 40);
		$(".right_div").css("height", wp_h - wp_top_h - 40);
	}else{
		wp_content.css("height", wp_h - wp_top_h - wp_bottom_h - 40 - 40);
	}
	$(window).resize(function() {
		var wp_h = $(window).height();
		var wp_top = $(".wp_top");
		var wp_top_h = wp_top.height();
		var wp_bottom = $(".wp_bottom");
		var wp_bottom_h = wp_bottom.height();
		var wp_content = $(".wp_content");
		if(wp_content.hasClass("has_right_div")){
			$(".primary_div").css("height", wp_h - wp_top_h - 40);
			$(".right_div").css("height", wp_h - wp_top_h - 40);
		}else{
			wp_content.css("height", wp_h - wp_top_h - wp_bottom_h - 40 - 40);
		}
	});
});