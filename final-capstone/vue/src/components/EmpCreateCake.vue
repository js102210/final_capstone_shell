<template>
  <div class="create-cake-display">
   <p>Create New Standard Cake:</p>
      <form>
          <label for="cakeName">Cake Name:</label>
        <input type="text" name="cakeName" v-model="newCake.newCakeName" />
      <button>Create New Standard Cake</button>
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
        newCakeFlavor: "",
        newCakeFrosting: "",
        newCakeFilling: "",
        newCakePrice: 0.0,
    }
    }
  },

}
</script>

<style>

.create-cake-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>