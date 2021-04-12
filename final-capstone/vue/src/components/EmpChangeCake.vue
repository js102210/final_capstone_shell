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
        <tr v-for="cake in $store.state.allCakeConfigsBE" v-bind:key="cake.cakeConfigID"
            v-bind:class="{ unavailable: !cake.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="cake.cakeConfigID"
              v-bind:value="cake.cakeConfigID" v-model="selectedCakeID" />
          </td>
          <td>{{cake.cakeConfigName}}</td>
          <td>{{cake.cakeConfigDescription}}</td>
          <td>{{cake.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(selectedCakeID)">
              {{ cake.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService.js';

export default {
  name: "emp-change-cake",
  data() {
    return {
      selectedCakeID: {}
    }
  },
  methods: {

    prepareArray(){
  EmployeeService.getAllConfigs().then((response) => {
      this.$store.commit("SET_ALL_CAKE_CONFIG_ARRAY", response.data);
    });
    },
    flipStatus(configID) {
    EmployeeService.flipConfigStatus(configID).then((response) => {
    if (response.status == 202){
    this.$store.commit("SET_CAKE_CONFIG_IS_AVAILABLE", configID, response.data)
    }
       this.prepareArray();
      })
    }
  },
   created() {
   this.prepareArray();
  },
}
</script>

<style>

</style>