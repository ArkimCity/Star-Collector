<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>World of Words</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}

HTML CSSResult Skip Results Iframe
EDIT ON
body {
	padding: 20px;
	font-family: sans-serif;
	background: #f2f2f2;
}

img {
	width: 100%; /* need to overwrite inline dimensions */
	height: auto;
}

h2 {
	margin-bottom: .5em;
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
	grid-gap: 1em;
}

/* hover styles */
.location-listing {
	position: relative;
}

.location-image {
	line-height: 0;
	overflow: hidden;
}

.location-image img {
	filter: blur(0px);
	transition: filter 0.3s ease-in;
	transform: scale(1.1);
}

.location-title {
	font-size: 1.5em;
	font-weight: bold;
	text-decoration: none;
	z-index: 1;
	position: absolute;
	height: 100%;
	width: 100%;
	top: 0;
	left: 0;
	opacity: 0;
	transition: opacity .5s;
	background: rgba(90, 0, 10, 0.4);
	color: white;
	/* position the text in t’ middle*/
	display: flex;
	align-items: center;
	justify-content: center;
}

.location-listing:hover .location-title {
	opacity: 1;
}

.location-listing:hover .location-image img {
	filter: blur(2px);
}

/* for touch screen devices */
@media ( hover : none) {
	.location-title {
		opacity: 1;
	}
	.location-image img {
		filter: blur(2px);
	}
}
Resources1×0
.5×0
.25×Rerun
</style>

<body class="w3-light-grey">

	<!-- image grid -->
	HTML CSSResult Skip Results Iframe EDIT ON
	<div class="child-page-listing">
		<h2>Our Locations</h2>
		<div class="grid-container">
			<article id="3685" class="location-listing">
				<a class="location-title" href="#"> San Francisco </a>
				<div class="location-image">
					<a href="#"> <img width="300" height="169"
						src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/san-fransisco-768x432.jpg"
						alt="san francisco">
					</a>
				</div>
			</article>
			<article id="3688" class="location-listing">
				<a class="location-title" href="#"> London </a>
				<div class="location-image">
					<img src="https://www.google.com/logos/doodles/2020/december-holidays-days-2-30-6753651837108830.5-s.png" alt="연말 스페셜" width="92" height="33">, <img class="rg_i Q4LuWd" src="data:image/gif;base64,R0lGODlhAQABAIAAAP///////yH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" data-iid="0" data-deferred="1" jsname="Q4LuWd" width="186" height="155" alt="계수나무 - 위키백과, 우리 모두의 백과사전">
				</div>
			</article>
			<article id="3691" class="location-listing">
				<a class="location-title" href="#"> New York </a>
				<div class="location-image">
					<a href="#"> <img width="300" height="169"
						src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/new-york-768x432.jpg"
						alt="new york">
					</a>
				</div>
			</article>
			<article id="3694" class="location-listing">
				<a class="location-title" href="#"> Cape Town </a>
				<div class="location-image">
					<a href="#"> <img width="300" height="169"
						src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/cape-town-768x432.jpg"
						alt="cape town">
					</a>
				</div>
			</article>
			<article id="3697" class="location-listing">
				<a class="location-title" href="#"> Beijing </a>
				<div class="location-image">
					<a href="#"> <img width="300" height="169"
						src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/beijing-768x432.jpg"
						alt="beijing">
					</a>
				</div>
			</article>
			<article id="3700" class="location-listing">
				<a class="location-title" href="#"> Paris </a>
				<div class="location-image">
					<a href="#"> <img width="300" height="169"
						src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/paris-768x432.jpg"
						alt="paris">
					</a>
				</div>
			</article>
		</div>
		<!-- end grid container -->
	</div>




	<!-- Navigation Bar -->
	<div style="position: relative; z-index: 2;">
		<jsp:include page="NavigationBar.jsp"></jsp:include>
	</div>

	<!-- Sidebar/menu -->
	<div style="position: relative; z-index: 1;">
		<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
			style="z-index: 3; width: 300px;" id="mySidebar">
			<br>
			<div class="w3-container w3-row">
				<div class="w3-col s4">
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
						class="w3-circle w3-margin-right" style="width: 46px">
				</div>
				<div class="w3-col s8 w3-bar">
					<span>Welcome, <strong>Mike</strong></span><br> <a href="#"
						class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a> <a
						href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
					<a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
				</div>
			</div>
			<hr>
			<div class="w3-container">
				<h5>Dashboard</h5>
			</div>
			<div class="w3-bar-block">
				<a href="#"
					class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
					onclick="w3_close()" title="close menu"><i
					class="fa fa-remove fa-fw"></i>  Close Menu</a> <a href="#"
					class="w3-bar-item w3-button w3-padding w3-blue"><i
					class="fa fa-users fa-fw"></i>  Overview</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-eye fa-fw"></i>  Views</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-users fa-fw"></i>  Traffic</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-bullseye fa-fw"></i>  Geo</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-diamond fa-fw"></i>  Orders</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-bell fa-fw"></i>  News</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-bank fa-fw"></i>  General</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-history fa-fw"></i>  History</a> <a href="#"
					class="w3-bar-item w3-button w3-padding"><i
					class="fa fa-cog fa-fw"></i>  Settings</a><br>
				<br>
			</div>
		</nav>
	</div>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<!-- Header -->
		<header class="w3-container" style="padding-top: 22px">
			<h5>
				<b><i class="fa fa-dashboard"></i> My Dashboard</b>
			</h5>
		</header>

		<div class="w3-row-padding w3-margin-bottom">
			<div class="w3-quarter">
				<div class="w3-container w3-red w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-comment w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3>52</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Messages</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-blue w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-eye w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3>99</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Views</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-teal w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-share-alt w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3>23</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Shares</h4>
				</div>
			</div>
			<div class="w3-quarter">
				<div class="w3-container w3-orange w3-text-white w3-padding-16">
					<div class="w3-left">
						<i class="fa fa-users w3-xxxlarge"></i>
					</div>
					<div class="w3-right">
						<h3>50</h3>
					</div>
					<div class="w3-clear"></div>
					<h4>Users</h4>
				</div>
			</div>
		</div>

		<div class="w3-panel">
			<div class="w3-row-padding" style="margin: 0 -16px">
				<div class="w3-third">
					<h5>Regions</h5>
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
						style="width: 100%" alt="Google Regional Map">
				</div>
				<div class="w3-twothird">
					<h5>Feeds</h5>
					<table class="w3-table w3-striped w3-white">
						<tr>
							<td><i class="fa fa-user w3-text-blue w3-large"></i></td>
							<td>New record, over 90 views.</td>
							<td><i>10 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-bell w3-text-red w3-large"></i></td>
							<td>Database error.</td>
							<td><i>15 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
							<td>New record, over 40 users.</td>
							<td><i>17 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-comment w3-text-red w3-large"></i></td>
							<td>New comments.</td>
							<td><i>25 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-bookmark w3-text-blue w3-large"></i></td>
							<td>Check transactions.</td>
							<td><i>28 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-laptop w3-text-red w3-large"></i></td>
							<td>CPU overload.</td>
							<td><i>35 mins</i></td>
						</tr>
						<tr>
							<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
							<td>New shares.</td>
							<td><i>39 mins</i></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<hr>
		<div class="w3-container">
			<h5>General Stats</h5>
			<p>New Visitors</p>
			<div class="w3-grey">
				<div class="w3-container w3-center w3-padding w3-green"
					style="width: 25%">+25%</div>
			</div>

			<p>New Users</p>
			<div class="w3-grey">
				<div class="w3-container w3-center w3-padding w3-orange"
					style="width: 50%">50%</div>
			</div>

			<p>Bounce Rate</p>
			<div class="w3-grey">
				<div class="w3-container w3-center w3-padding w3-red"
					style="width: 75%">75%</div>
			</div>
		</div>
		<hr>
		<img
			src="https://ssl.pstatic.net/sstatic/search/nlogo/20201228194531.png"
			width="58" height="60" alt="NAVER">
		<div class="w3-container">
			<h5>Countries</h5>
			<table
				class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
				<tr>
					<td>United States</td>
					<td>65%</td>
				</tr>
				<tr>
					<td>UK</td>
					<td>15.7%</td>
				</tr>
				<tr>
					<td>Russia</td>
					<td>5.6%</td>
				</tr>
				<tr>
					<td>Spain</td>
					<td>2.1%</td>
				</tr>
				<tr>
					<td>India</td>
					<td>1.9%</td>
				</tr>
				<tr>
					<td>France</td>
					<td>1.5%</td>
				</tr>
			</table>
			<br>
			<button class="w3-button w3-dark-grey">
				More Countries  <i class="fa fa-arrow-right"></i>
			</button>
		</div>
		<hr>
		<div class="w3-container">
			<h5>Recent Users</h5>
			<ul class="w3-ul w3-card-4 w3-white">
				<li class="w3-padding-16"><img
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
					class="w3-left w3-circle w3-margin-right" style="width: 35px">
					<span class="w3-xlarge">Mike</span><br></li>
				<li class="w3-padding-16"><img
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
					class="w3-left w3-circle w3-margin-right" style="width: 35px">
					<span class="w3-xlarge">Jill</span><br></li>
				<li class="w3-padding-16"><img
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
					class="w3-left w3-circle w3-margin-right" style="width: 35px">
					<span class="w3-xlarge">Jane</span><br></li>
			</ul>
		</div>
		<hr>

		<div class="w3-container">
			<h5>Recent Comments</h5>
			<div class="w3-row">
				<div class="w3-col m2 text-center">
					<img class="w3-circle"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
						style="width: 96px; height: 96px">
				</div>
				<div class="w3-col m10 w3-container">
					<h4>
						John <span class="w3-opacity w3-medium">Sep 29, 2014, 9:12
							PM</span>
					</h4>
					<p>Keep up the GREAT work! I am cheering for you!! Lorem ipsum
						dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
						incididunt ut labore et dolore magna aliqua.</p>
					<br>
				</div>
			</div>

			<div class="w3-row">
				<div class="w3-col m2 text-center">
					<img class="w3-circle"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmbOoTymPbL7KE_T42za2hDFNeRxDwdD4e6A&usqp=CAU"
						style="width: 96px; height: 96px">
				</div>
				<div class="w3-col m10 w3-container">
					<h4>
						Bo <span class="w3-opacity w3-medium">Sep 28, 2014, 10:15
							PM</span>
					</h4>
					<p>Sed do eiusmod tempor incididunt ut labore et dolore magna
						aliqua.</p>
					<br>
				</div>
			</div>
		</div>
		<br>
		<div class="w3-container w3-dark-grey w3-padding-32">
			<div class="w3-row">
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-green">Demographic</h5>
					<p>Language</p>
					<p>Country</p>
					<p>City</p>
				</div>
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-red">System</h5>
					<p>Browser</p>
					<p>OS</p>
					<p>More</p>
				</div>
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-orange">Target</h5>
					<p>Users</p>
					<p>Active</p>
					<p>Geo</p>
					<p>Interests</p>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<footer class="w3-container w3-padding-16 w3-light-grey">
			<h4>FOOTER</h4>
			<p>
				Powered by <a href="https://www.w3schools.com/w3css/default.asp"
					target="_blank">w3.css</a>
			</p>
		</footer>

		<!-- End page content -->
	</div>

	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>

</body>
</html>
