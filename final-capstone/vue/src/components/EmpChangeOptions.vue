<template>
<div class="change-options-display">
    <p>Edit Cake Options:</p>

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
            v-bind:class="{ unavailable: !cake.isAvailable}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="cake.cakeConfigID"
              v-bind:value="cake.cakeConfigID" v-model="selectedCakeID" />
          </td>
          <td>{{cake.cakeConfigName}}</td>
          <td>{{cake.cakeConfigDescription}}</td>
          <td>{{cake.isAvailable? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(cake.cakeConfigID)">
              {{ cake.isAvailable ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>


</div>
  
</template>

<script>
import EmployeeService from "../services/EmployeeService.js";

export default {
  name: "emp-change-options",

  data() {
    return {
      selectedCakeID: {}
    }
  },

  created() {
    EmployeeService.getAllSizes().then((response) => {
      this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
    });

    EmployeeService.getAllStyles().then((response) => {
      this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
    });

    EmployeeService.getAllFlavors().then((response) => {
      this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
    });

    EmployeeService.getAllFrostings().then((response) => {
      this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    });

    EmployeeService.getAllFillings().then((response) => {
      this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
    });
  },  

}
</script>

<style>
.change-options-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>