$("#adultCheck").change(
	function() {
		   // 체크박스 값에 따라 히든 값 변경
		   console.log('변경')
		   if ( $("#adultCheck").is(":checked") ){
		       $("#YN").val('2');
				console.log('체크함');
		   }
		   else {

		       $("#YN").val('1');
				console.log('체크품');
		   }
	}
);