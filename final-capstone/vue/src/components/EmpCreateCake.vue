<template>
  <div class="create-cake-display">
   <p>Create New Standard Cake: (All Fields must be completed)</p>
      <form class="create-cake" @submit.prevent="createNewCake">
        
        <label for="cakeName">Cake Name:</label>
        <input type="text" name="cakeName" v-model="newCake.name" />

        <label for="style">Style:</label>
        <select name="style" v-model="newCake.styleId">
        <option v-for="style in $store.state.availableCakeStylesBE"
          v-bind:key="style.style_id" v-bind:value="style.style_id">
          {{ style.style_name }}
        </option></select>

        <label for="size">Size:</label>
        <select name="size" v-model="newCake.sizeID">
        <option v-for="size in $store.state.availableCakeSizesBE"
          v-bind:key="size.size_id" v-bind:value="size.size_id">
          {{ size.size_description }}
        </option></select>

        <label for="flavor">Flavor:</label>
        <select name="flavor" v-model="newCake.flavorID">
        <option v-for="flavor in $store.state.availableFlavorsBE"
          v-bind:key="flavor.flavor_id" v-bind:value="flavor.flavor_id">
          {{ flavor.flavor_name}}
        </option></select>

        <label for="frosting">Frosting:</label>
        <select name="frosting" v-model="newCake.frostingID">
        <option v-for="frosting in $store.state.availableFrostingsBE"
          v-bind:key="frosting.frosting_id" v-bind:value="frosting.frosting_id">
          {{ frosting.frosting_name }}
        </option></select>

        <label for="price">Price: $</label>
        <input type="number" name="price" step=".01" v-model="newCake.price" />
        
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
        styleID: 1,
        sizeID: 1,
        flavorID: 1,
        frostingID: 1,
        fillingID: 1,
        message: "",
        price: 0.0,
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