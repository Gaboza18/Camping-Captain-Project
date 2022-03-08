<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/lib/w3.css">
<body>

	<div class="w3-content" style="max-width: 100%; position: relative">
		
		<img class="mySlides" src="images/camping-1.jpg" style="width: 100%">	
		<img class="mySlides" src="images/camping-2.jpg" style="width: 100%">
		<img class="mySlides" src="images/camping-3.jpg" style="width: 100%"> 
		<img class="mySlides" src="images/camping-4.jpg" style="width: 100%">
		<img class="mySlides" src="images/camping-5.jpg" style="width: 100%">
		<img class="mySlides" src="images/camping-6.jpg" style="width: 100%"> 
		
	</div><br><br><br>

	<script>
		var myIndex = 0;
		carousel();

		function carousel() {
			var i;
			var x = document.getElementsByClassName("mySlides");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			x[myIndex - 1].style.display = "block";
			setTimeout(carousel, 4000); // Change image every 2 seconds
		}
	</script>

</body>
</html>
