<template>
  <div class="create-cake-display">
   <p>Create New Standard Cake:</p>
      <form class="create-cake" @submit.prevent="createNewCake">
        <label for="cakeName">Cake Name:</label>
        <input type="text" name="cakeName" v-model="newCake.newCakeName" />

        <label for="standard cake style">Select Style:</label>
        <select name="standard cake style" v-model="newCake.newCakeItemStyleId">
        <option v-for="style in $store.state.availableCakeStylesBE"
          v-bind:key="style.style_id" v-bind:value="style.style_id">
          {{ style.style_name }}
        </option></select>

        <label for="standard cake size">Select Size:</label>
        <select name="standard cake size" v-model="newCake.newCakeItemSizeID">
        <option v-for="size in $store.state.availableCakeSizesBE"
          v-bind:key="size.size_id" v-bind:value="size.size_id">
          {{ size.size_description }}
        </option></select>

        <label for="custom cake flavor">Select Flavor:</label>
        <select name="custom cake flavor" v-model="newCake.newCakeItemFlavorID">
        <option v-for="flavor in $store.state.availableCakeFlavors"
          v-bind:key="flavor" v-bind:value="flavor">
          {{ flavor}}
        </option></select>

        <label for="custom cake frosting">Select your frosting:</label>
        <select name="custom cake frosting" v-model="newCake.newCakeItemFrostingID">
        <option v-for="frosting in $store.state.availableFrostings"
          v-bind:key="frosting" v-bind:value="frosting">
            {{ frosting }}
        </option></select>

        <!-- add price input -->

      <button type="submit">Create New Standard Cake</button>
      </form>
  </div>
</template>

<script>
export default {
  name: 'emp-create-cake',
  data(){
    return {
    newCake: {
        newCakeName: "",
        newCakeItemStyleID: 1,
        newCakeItemSizeID: 1,
        newCakeItemFlavorID: 1,
        newCakeItemFrostingID: 1,
        newCakeItemFillingID: 1,
        newCakeItemMessage: "",
        newCakeItemPrice: 0.0,

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