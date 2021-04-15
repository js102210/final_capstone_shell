<!-- Employee: This component allows employees to change the availability of Cake Configs, as well as see which ones are currently available/unavailable.
 Making a Cake Config unavailable removes it from the screen customers use to order them. -->
<template>
  <div class="change-cake-display">
    <p>Edit Standard Cakes:</p>

    <table id="standard-cakes">
      <thead>
        <tr>
          <th>Cake Name</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="cake in $store.state.allCakeConfigsBE" v-bind:key="cake.cakeConfigID"
            v-bind:class="{ unavailable: !cake.available}" >
          <td>{{cake.cakeConfigName}}</td>
          <td>{{cake.cakeConfigDescription}}</td>
          <td>{{cake.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(cake.cakeConfigID)">
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
    }
  },
  methods: {

/**
 * grabs all cake configs from the DB and sets them into the store.
 */
    prepareArray(){
  EmployeeService.getAllConfigs().then((response) => {
      this.$store.commit("SET_ALL_CAKE_CONFIG_ARRAY", response.data);
    });
    },
    /**
     * flips the aviailability of a given cake config.
     */
    flipStatus(configID) {
    EmployeeService.flipConfigStatus(configID).then((response) => {
    if (response.status == 202){
    this.$store.commit("SET_CAKE_CONFIG_IS_AVAILABLE", configID, response.data)
    }
    //the Cake Config's availability is displayed during the initial setup of the Config list, so we want to call that method each time
    //there's a change to display availability live to the user
       this.prepareArray();
      })
    }
  },
  /**
   * lifecycle hook to populate the array for all cake configs. runs when component loads.
   */
   created() {
   this.prepareArray();
  },
}
</script>

<style>

</style>