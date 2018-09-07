$(document).ready(function() {

	//메뉴 마우스오버 이벤트
	$('#header .sub').hide();
	$('.header').find('> ul > li').on('keyup mouseenter focusin', function() {
		$('.sub').show();
		$(this).addClass('on').siblings().removeClass('on');
		$('#header').css({'height':'233', 'border-bottom':'1px solid #a68961'});
	});
	$('.header').find('> ul').on('keydown mouseleave focusout', function() {
		$('.sub').hide();
		$('.header').find('> ul > li').removeClass('on');
		$('#header').css({'height':'80', 'border-bottom':'0'});
	});

	//메인 비주얼 슬라이드 배너
	var mySwiper = new Swiper('.swiper-container', {
		keyboardControl: true,
		pagination: '.main_slides_pagination',
		paginationClickable: true,
		loop: true,
		autoplay: 4000
	});

	//메인 커뮤니티 외 클릭 이벤트
	$('.info_board').find('> div > ul > li').on('click', function() {
		$(this).addClass('on').siblings().removeClass('on');
	});

	//메인 취업행사일정 슬라이드 배너
	var mySwiper2 = new Swiper('.swiper-container2', {
		keyboardControl: true,
		slidesPerView: 'auto',
		loop: true,
		loopedSlides: 4,
		loopAdditionalSlides: 4,
		autoplay: 4000
	});
	$('.swipe_prev').on('click', function() {
		mySwiper2.swipePrev();
	});
	$('.swipe_next').on('click', function() {
		mySwiper2.swipeNext();
	});
	$('.swipe_stopPlay').on('click', function() {
		if($(this).hasClass('on')) {
			$(this).removeClass('on').addClass('off');
			mySwiper2.stopAutoplay();
		} else {
			$(this).removeClass('off').addClass('on');
			mySwiper2.startAutoplay();
		}
		return false;
	});

	//메인 관련사이트 슬라이드 배너
	var mySwiper3 = new Swiper('.swiper-container3', {
		keyboardControl: true,
		slidesPerView: 'auto',
		loop: true,
		loopedSlides: 5,
		loopAdditionalSlides: 5,
		autoplay: 4000
	});
	$('.site_prev').on('click', function() {
		mySwiper3.swipePrev();
	});
	$('.site_next').on('click', function() {
		mySwiper3.swipeNext();
	});

	//LNB

	$(".lnb li.current_depth > a").click(function(e) {
		e.preventDefault();
		var li = $(this).parent("li.current_depth");
		if(li.hasClass("selected")){
			li.removeClass("selected");
			li.find("ul").hide();
			e.false;
		}else{
			li.siblings("li.current_depth").removeClass("selected");
			li.siblings("li.current_depth").find("ul").hide();
			li.addClass("selected");
			li.find("ul").stop().slideDown(200);
		}
	});

	/*
	$(".lnb").mouseleave(function(e){
		$(".lnb li.current_depth").removeClass("selected");
		$(".lnb li.current_depth ul").hide();
	});
	*/
	
	// LNB 제어
	if($(".lnb").length >= 1){
		var lnb_offset = $(".lnb").offset().top;

		$(window).scroll(function () {
			
			if ($(this).scrollTop() > lnb_offset) {
				$(".lnb").css({
					"position":"fixed",
					"left":"0px",
					"top":"0px",
					"z-index":"10"
				});
				$("#sub_container").css("padding-top", "171px");
			} else {
				$(".lnb").css({
					"position":"static",
					"left":"0px",
					"top":"0px",
					"z-index":"0"
				});
				$("#sub_container").css("padding-top", "120px");
			}
		});
	}

	//상세 검색조건 열고 닫기
	$(".btn_search_detail").click(function(){
		if($(this).hasClass("opened")){
			$(".search_detail").stop().slideUp(100);
			$(this).removeClass("opened").text("상세 검색조건 열기");
		}else{
			$(".search_detail").stop().slideDown(100);
			$(this).addClass("opened").text("상세 검색조건 닫기");
		}
	});

	//데이트픽커
	$(".input_datepicker").datepicker({
		dateFormat:'yy.mm.dd',
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		changeMonth:true, // 월변경가능
		changeYear:true,  // 년변경가능
		showMonthAfterYear:true // 년 뒤에 월표시
	});

});