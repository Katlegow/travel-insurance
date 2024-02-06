<script setup>
import { ref } from "vue";
import Quote from "@/components/Quote.vue";
import Product from "@/components/Product.vue"
import checkout from "@/components/Checkout.vue"
import thanks from "@/components/ThankYou.vue"

const showQuote = ref(true);
const showProduct = ref(false);
const showCheckout = ref(false);
const showThankYou = ref(false);

const productData = ref(
	{
		id: 1,
		pricedProduct: {
			productInformation: {
				productCode: "",
				productIdentifier: "",
				category: "",
				productMedia: {
					termsAndConditions: ""
				}
			},
			customerPriceBreakdowns: {
				customerPriceBreakdown: [
					{
						currency: "",
						type: "",
						exchangeRate: null,
						baseAmount: 0,
						totalAmount: 0
					},
					{
						currency: "",
						type: "",
						exchangeRate: 0,
						baseAmount: 0,
						totalAmount: 0
					}
				]
			},
			productPriceBreakdown: {
				priceDetail: [
					{
						currency: "",
						type: "",
						exchangeRate: null,
						baseAmount: 0,
						totalAmount: 0
					},
					{
						currency: "",
						type: "",
						exchangeRate: 0,
						baseAmount: 0,
						totalAmount: 0
					}
				]
			}
		}
	}
)

const BASE_URL = "http://localhost:8080/product";

//=============================== Functions ====================================

async function submitQuote(quote) {
	console.log(quote);
	const response = await fetch(
		`${BASE_URL}/price`, {
			method: 'POST',
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				requestParameters: quote
			})
		}
	);

	if (!response.ok) {
		showProduct.value = false;
		showCheckout.value = false;
		showThankYou.value = false;
		
		const message = `An error has occured: ${response.status} - ${response.statusText}`;
        throw new Error(message);
	}

	const data = await response.json();
	console.log(data);
	productData.value = data.response.responseParameters.packageSize.packages[0];

	showQuote.value = false;
	showProduct.value = true;
}

function cancel() {
	showProduct.value = false;
	showQuote.value = true;
}

</script>

<template>
  <main class="container">
	<div class="quote" v-show="showQuote">
		<Quote  @submit="submitQuote"/>
	</div>

	<div class="product" v-show="showProduct">
		<Product
			@cancel="cancel"
			:name="productData.pricedProduct.productInformation.productIdentifier"
			:category="productData.pricedProduct.productInformation.category"
			:tcs="productData.pricedProduct.productInformation.productMedia.termsAndConditions"
			:amount="productData.pricedProduct.customerPriceBreakdowns.customerPriceBreakdown[1].totalAmount"
		/>
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
