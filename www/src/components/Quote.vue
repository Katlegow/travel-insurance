<template>
  <main>
		<h1>Get a Travel Insurance Quote</h1>
		<p>
			Need an affordable Travel Insurance? Don't worry, We got You! Please fill in the form below to how much you qualify for.
		</p>
		<div>
			<form class="row g-3">
				<div class="row g-3">
					<h4>Travel Information</h4>
					<p>Booking Value: {{ bookingValue  }}</p>
					<div class="col-md-6">
						<label for="startDate" class="form-label">Start Date</label>
						<input 
							type="date" 
							:min="currentDate" 
							class="form-control" 
							id="startDate" 
							v-model="travelInfo.startDate" 
							required>
					</div>
					<div class="col-md-6">
						<label for="endDate" class="form-label">End Date</label>
						<input 
							type="date" 
							class="form-control" 
							id="endDate"
							v-model="travelInfo.endDate" 
							required>
					</div>
					<div class="col-12">
						<label for="depatureCountry" class="form-label">Depature Country</label>
						<select id="depatureCountry" class="form-select" v-model="travelInfo.departureCountry" required>
							<option disabled selected>Choose...</option>
							<option>ZA</option>
						</select>
					</div>

					<div class="col-12">
						<label for="destinationCountry" class="form-label">Destination Country</label>
						<select id="destinationCountry" class="form-select" 
								v-model="travelInfo.destinationCountry.destinationCountry" 
								required
								>
							<option disabled selected>Choose...</option>
							<option value="ZA">ZA</option>
						</select>
					</div>
				</div>
				<div class="row g-3">
					<h4>Insureds Information</h4>
					<button 
						class="btn btn-primary col-auto mb-3" @click.prevent="addInsured">Add Insured</button>
					<table class="table">
						<thead>
							<tr>
							<th scope="col">#</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Gender</th>
							<th scope="col">Residency</th>
							<th scope="col">Travel Item Value</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="insured in insureds" :key="insured.id">
								<th scope="row">{{ insured.id }}</th>
								<td><input type="date" class="form-control" v-model="insured.dateOfBirth" id="dob" required></td>
								<td>
									<select id="gender" class="form-select" v-model="insured.gender" required>
										<option disabled selected>Choose...</option>
										<option value="male">Male</option>
										<option value="male">Female</option>
									</select>
								</td>
								<td>
									<select id="residency" class="form-select" v-model="insured.residency" required>
										<option disabled selected>Choose...</option>
										<option value="ZA">ZA</option>
									</select>
								</td>
								<td><input type="text" class="form-control" v-model="insured.travelInformation.travelItemValue" id="amount"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-12">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</form>
		</div>
  </main>
</template>

<script setup>
import { computed, ref } from "vue";

const currentDate = new Date().toISOString().slice(0,10);

const bookingValue = computed( () => {
	return insureds.value.reduce(
		(total, insured) => total + parseInt(insured.travelInformation.travelItemValue),
		0
	);
})

const travelInfo = ref({
	startDate: '',
	endDate: '',
	bookingValue: bookingValue,
	departureCountry: '',
	destinationCountry: {
		destinationCountry: ''
	}
})

const insureds = ref(
	[
		{
			id: 1,
			dateOfBirth: '',
			gender: '',
			residency: '',
			travelInformation: {
				travelItemValue: 0
			}
		},

	]
);

const addInsured = () => {
	insureds.value.push(
		{
			id: insureds.value.length + 1,
			dateOfBirth: '',
			gender: '',
			residency: '',
			travelInformation: {
				travelItemValue: 0
			}
		}
	)
}

</script>

<style scoped>
	h3{
		color: black;
	}
</style>
