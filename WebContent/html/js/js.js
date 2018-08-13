
	var txtValue = 0;
	var path="";

$(function(){
	
	$('.sel p').click(function(){
		$('.sel ul').slideDown();
	})
	$('.sel ul li').click(function(){
		var txt=$(this).html();
		txtValue = $(this).val();
		$('.sel p').html(txt);
		$('.sel ul').slideUp();
	})
	$('.task_tc .esc').click(function(){
		$('.task_tc').fadeOut();
	})
	$('.pass span').click(function(){
		if($(this).hasClass('on')){
			$(this).removeClass('on');
		}else{
			$(this).addClass('on');
		}
	})
	
	
	$('.left_box .hd li').click(function(){
	var icon=$(this).children('img').attr('src');
	$('.left_box .bd .icon img').attr('src',icon);
	})
	var num=0;
	var rot=0;
	$('.detail_nr .left .prev').click(function(){
		if(num<0){
			num=3
		}
		num--;
		var oicon=$('.left_box .hd li').eq(num).children('img').attr('src');
		$('.left_box .bd .icon img').attr('src',oicon);
	})
	$('.detail_nr .left .next').click(function(){
		if(num==3){
			num=0
		}
		num++;
		var oicon=$('.left_box .hd li').eq(num).children('img').attr('src');
		$('.left_box .bd .icon img').attr('src',oicon);
	})
})
$('.left_box .bd .zuo').click(function(){

	$('.left_box .bd .icon img').attr('style','transform: rotateY(-180deg);')
})
$('.left_box .bd .you').click(function(){

	$('.left_box .bd .icon img').attr('style','transform: rotateY(0deg);')
})