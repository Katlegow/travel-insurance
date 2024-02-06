<template>
<div class="container fd">
	<h1 class="header">The best travel insurance cover for you!</h1>
	<div class="wrapper">
		<div class="banner">
			<img src="../assets/images/insurance.jpeg" alt="travel image banner" width="512" height="512">
		</div>
		<div class="productInfo">
			<div class="line"></div>
			<h1>{{ name }}</h1>
			<div class="price">R{{ amount }}</div>
			<div class="productDetails">
				<p>Category: {{ category }}</p>
				<p>The best travel inurance plan ever. It covers you from day one 
					till end. Please <a :href="tcs" download target="blank">click here</a> to download 
					terms and condtions.
				</p>
			</div>
			<div class="action">
				<button class="btn btnColor" @click.prevent="onPolicyIssue">Issue Policy</button>
				<button class="btn btnColor" @click.prevent="onCancel">Cancel</button>
			</div>
		</div>
	</div>
</div>
</template>

<script setup>
const emitter = defineEmits(['issuePolicy', 'cancel']);
defineProps({
  name: String,
  category: String,
  tcs: String,
  amount: Number
})

//=============================== Functions ====================================

function onPolicyIssue() {
	emitter("issuePolicy");
}

function onCancel() {
	emitter("cancel");
}
</script>

<style>
.header {
	color: rgb( 255, 87, 51 );
	margin-bottom: 60px;
}
.wrapper {
	display: flex;
	flex-direction: row;
	justify-content: center;
}
.banner {
	width: 514px;
	height: 512px;
	margin: 0 auto 0 0;
}
.productInfo {
	width: 514px;
	height: 512px;
	padding: 90px;
}
.line {
	width: 43px;
	border-color: rgb( 255, 87, 51 );
	border-width: 2px;
	border-style: solid;
	margin-bottom: 20px;
}

.price {
	color: rgb( 255, 87, 51 );
	font-size: large;
	font-weight: 700;
}

.productDetails {
	margin-top: 20px;
}
a {
	text-decoration: none;
	color: rgb( 255, 87, 51 );
}

.fd {
	flex-direction: column;
}

.action {
	margin-top: 60px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.btnColor {
	background-color: rgb( 255, 87, 51 );
	color: aliceblue;
}

.btnColor:hover {
	border-color: rgb( 255, 87, 51 );
	border-width: 2px;
	border-style: solid;
}
</style>
