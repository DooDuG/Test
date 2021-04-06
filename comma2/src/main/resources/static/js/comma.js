

/*실행안됨

$(function(){
	$('#zoomImg').click(function(){
		$('.pop').show();
	});
});

function onDisplay(){
  $('.pop').show();n

}
function offDisplay(){
  $('.pop').hide();
  location.reload();
}
*/


/*실행됨*/



$(function(){
      $('.zoomImg').click(function(){
         var imgUrl = $(this).attr("src");
         /*var imgUrl 은 클릭한요소의 src 속성 값을 가져오고*/
         $('.pop_you').attr('src',imgUrl);
         /*클래스팝유 요소에 src속성을 추가하고 값은 imgUrl로 한다*/
         $('.pop').show();
      });
      
});

function offDisplay(){
   $('.pop').hide();
}