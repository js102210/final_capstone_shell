<template>
  <div class="change-cake-display">
    <p>Edit Standard Cakes:</p>

    <table id="standard-cakes">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Cake Name</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="cake in $store.state.availableCakeConfigsBE" v-bind:key="cake.cakeConfigID"
            v-bind:class="{ unavailable: !cake.isAvailable}">
          <td>
            <input type="radio" name="flip status" v-bind:id="cake.cakeConfigID"
              v-bind:value="cake.cakeConfigID" v-model="selectedCakeID" />
          </td>
          <td>{{cake.cakeConfigName}}</td>
          <td>{{cake.cakeConfigDescription}}</td>
          <td>{{cake.isAvailable? "Available": "Unavailable"}}</td>
          <td>
            <!-- v-on:click="flipStatus(cake.id)" >
              {{ cakeConfig.status === "Available" ? "Unavailable" : "Available" }} -->
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(cake.cakeConfigID)"> Flip status</button>
          </td> 
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
export default {
  name: "emp-change-cake",
  data() {
    return {
      selectedCakeID: {}
    }
  },
  methods: {
    flipStatus() {

    }
  }
}
</script>

<style>
.change-cake-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>