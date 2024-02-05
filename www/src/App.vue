<script setup>
import { ref } from "vue";
import Quote from "@/components/Quote.vue";
import product from "@/components/Product.vue"
import checkout from "@/components/Checkout.vue"
import thanks from "@/components/ThankYou.vue"

const showQuote = ref(true);
const showProduct = ref(false);
const showCheckout = ref(false);
const showThankYou = ref(false);
</script>

<template>
  <main class="container">
	<div class="quote" v-show="showQuote">
		<Quote />
	</div>

	<div class="product" v-show="showProduct">
	</div>

	<div class="checkout" v-show="showCheckout">
	</div>

	<div class="thankYou" v-show="showThankYou"></div>
  </main>
</template>

<style scoped>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
</style>
