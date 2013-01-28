<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>
<meta name="keywords"
	content="fresh zone, fresh, fresh fruit, CMU, CDU, " />
<meta name="description"
	content="We sell fresh fruit directly from farmer to your hand. [supported by CDU&CMU University]" />

<!-- Include Script in folder common -->
<jsp:include page="./common/recomendScript.jsp" />

</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />
	
<div class="video_information">
		<div class="video_information_1">
			<div class="video_name">
				<span class="video_name_css">Fruits Recommended</span>
			</div>
			<div class="video_left">
				<img src="images/fruits_images/Durian.jpg" width=470px;
					height=256px; class="video_embed"></img>
			</div>
			<div class="video_right">
				<div class="video_keyword">
					<div class="keyword_number">name:</div>
					<div class="keyword_month">Durian</div>
				</div>
				<div class="video_word">The fruit Durian contains a high
					amount of sugar vitamin C and the serotonergic amino acid
					tryptophan.It is a good source of carbohydrates, proteins, and
					fats. Durian is recommended as a good source of raw fats by several
					raw food advocates, while others classify it as a high glycemic
					food, recommending minimizing consumption.</div>
			</div>
			<div class="cleaner"></div>
		</div>
	</div>
	<!-- end of video1 information strat -->
	<!-- video1 information strat -->
	<div class="video_information">
		<div class="video_information_1">
			<div class="video_left">
				<img src="images/fruits_images/Mango.jpg" width=470px; height=256px;
					class="video_embed"></img>
			</div>
			<div class="video_right">
				<div class="video_keyword">
					<div class="keyword_number">name:</div>
					<div class="keyword_month">Mango</div>
				</div>
				<div class="video_word">Mango contains a variety of
					psychochemicals and nutrients. The fruit pulp is high in prebiotic
					dietary fiber, vitamin C, diverse polyphenols and provitamin A
					carotenoids. The energy value per 100 g (3.5 oz) is 250 kJ (60
					kcal), and that of the apple mango is slightly higher (79 kcal per
					100g).</div>
			</div>
			<div class="cleaner"></div>
		</div>
	</div>
	<!-- end of video1 information strat -->
	<!-- video1 information strat -->
	<div class="video_information">
		<div class="video_information_1">
			<div class="video_left">
				<img src="images/fruits_images/Mangosteen.jpg" width=470px;
					height=256px; class="video_embed"></img>
			</div>
			<div class="video_right">
				<div class="video_keyword">
					<div class="keyword_number">name:</div>
					<div class="keyword_month">Mangosteen</div>
				</div>
				<div class="video_word">Mangosteen is native to Southeast
					Asia, generally planted for 10 years before they begin to result.
					Mangosteen has very strict environmental requirements, therefore,
					it is truly green fruit, and is also known as the "Queen of Fruit"
					.The mangosteen is rich in protein and lipid, which is good for the
					frail, the malnourished and the sick.</div>
			</div>
			<div class="cleaner"></div>
		</div>
	</div>
	<!-- end of video1 information strat -->


	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>
