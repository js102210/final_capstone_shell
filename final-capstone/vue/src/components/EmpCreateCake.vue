<template>
  <div class="create-cake-display">
   <p>Create New Standard Cake: (All Fields must be completed)</p>
      <form class="create-cake" @submit.prevent="createNewCake">
        
        <label for="cakeName">Cake Name:</label>
        <input type="text" name="cakeName" v-model="newCake.name" />

        <label for="flavor">Flavor:</label>
        <select name="flavor" v-model="newCake.flavorID">
        <option v-for="flavor in $store.state.availableFlavorsBE"
          v-bind:key="flavor.flavorID" v-bind:value="flavor.flavorID">
          {{ flavor.flavor_name}}
        </option></select>

        <label for="frosting">Frosting:</label>
        <select name="frosting" v-model="newCake.frostingID">
        <option v-for="frosting in $store.state.availableFrostingsBE"
          v-bind:key="frosting.frostingID" v-bind:value="frosting.frostingID">
          {{ frosting.frosting_name }}
        </option></select>

         <!-- <label for="frosting">Frosting:</label>
        <select name="frosting" v-model="newCake.frostingID">
        <option v-for="frosting in $store.state.availableFrostingsBE"
          v-bind:key="frosting.frostingID" v-bind:value="frosting.frostingID">
          {{ frosting.frosting_name }}
        </option></select>   for filing  -->

        
        <label for="imageURL">Image URL:</label>
        <input type="url" name="imageURL" v-model="newCake.imageURL" />
        
        <label for="description">Description:</label>
        <input type="text" name="description" v-model="newCake.description" />

      <!-- disable submit button if any field is blank -->
      <button type="submit">Create New Cake</button>
      </form>
  </div>
</template>

<script>
export default {
  name: 'emp-create-cake',
  data(){
    return {
    newCake: {
        name: "",
        flavorID: 1,
        frostingID: 1,
        fillingID: 1,
        imageURL: "",
        description: ""

    }
    }
  },
  methods: {
    createNewCake() {
      // call create cake mutation
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