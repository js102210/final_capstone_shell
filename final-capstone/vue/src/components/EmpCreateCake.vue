<template>
  <div class="create-cake-display">
   <p>Create New Standard Cake:</p>
      <form class="create-cake" @submit.prevent="createNewCake">
        
        <label for="cakeName">Cake Name: *</label>
        <input type="text" name="cakeName" v-model="newCake.cakeConfigName" />

        <label for="flavor">Flavor: *</label>
        <select name="flavor" v-model="newCake.flavorID">
        <option v-for="flavor in $store.state.availableFlavorsBE"
          v-bind:key="flavor.flavorID" v-bind:value="flavor.flavorID">
          {{ flavor.flavorName}}
        </option></select>

        <label for="frosting">Frosting:</label>
        <select name="frosting" v-model="newCake.frostingID">
        <option v-for="frosting in $store.state.availableFrostingsBE"
          v-bind:key="frosting.frostingID" v-bind:value="frosting.frostingID">
          {{ frosting.frostingName }}
        </option></select>

         <label for="filling">Filling:</label>
        <select name="filling" v-model="newCake.fillingID">
        <option v-for="filling in $store.state.availableFillingsBE"
          v-bind:key="filling.fillingID" v-bind:value="filling.fillingID">
          {{ filling.fillingName }}
        </option></select>

        
        <label for="imageURL">Image URL: *</label>
        <input type="url" name="imageURL" v-model="newCake.cakeConfigURL" />
        
        <label for="description">Description: *</label>
        <input type="text" name="description" v-model="newCake.cakeConfigDescription" />

      <!-- disable submit button if any field is blank -->
      <button type="submit" :disabled="!createCakeFormValidated" >Create New Cake</button>
      <p>Required Fields (*) must be completed</p>
      </form>
  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService.js';
export default {
  name: 'emp-create-cake',
  data(){
    return {
    newCake: {
        cakeConfigName: "",
        flavorID: 1,
        frostingID: 1,
        fillingID: 1,
        cakeConfigURL: "",
        cakeConfigDescription: ""
    }
    }
  },
  computed: {
    createCakeFormValidated() {
      if (this.newCake.cakeConfigName != "" && this.newCake.flavorID !=1 
      && this.newCake.cakeConfigURL != "" && this.newCake.cakeConfigDescription != ""){
        return true;
      } else {
        return false;
    }
  },
  methods: {
    createNewCakeConfig() {
      this.$store.commit("SET_CAKE_CONFIG_JSON", this.newCake);
      EmployeeService.createCakeConfig(this.$store.state.cakeConfigJSON)
      .then(response => {
        if(response.status ===201){
          this.$store.commit("CLEAR_CAKE_CONFIG_JSON");
          this.clearNewCake();
        }
      })
      .catch(error => {
        this.handleErrorResponse(error, "creating");
      });

    },
    clearNewCake(){
      this.newCake = {
        cakeConfigName: "",
        flavorID: 1,
        frostingID: 1,
        fillingID: 1,
        cakeConfigURL: "",
        cakeConfigDescription: ""
    };
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " cake configuration. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " cake configuration. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " cake configuration. Request could not be created.";
      }
}
  }

}
}
</script>

<style>

.create-cake-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>