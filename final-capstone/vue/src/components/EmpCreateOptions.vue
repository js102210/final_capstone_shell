<template>
  <div class="create-options-display">
    <p>Create New Options:</p>

      <label for="cakeFlavor">Cake Flavor:</label>
      <input type="text" name="cakeFlavor" v-model="newCakeFlavor" />
      <button>Create New Flavor</button>

      <label for="cakeFrosting">Cake Frosting:</label>
      <input type="text" name="cakeFrosting" v-model="newCakeFrosting" />
      <button>Create New Frosting</button>
      
      <label for="cakeFilling">Cake Filling:</label>
      <input type="text" name="cakeFilling" v-model="newCakeFilling" />
      <button>Create New Filling</button>


</div>
</template>

<script>
export default {
  data() {
    return {
      newCakeFlavor: "",
      newCakeFrosting: "",
      newCakeFilling: "",
    }
  },
  createFlavor(){
    // call add flavor mutation?
  }

}
</script>

<style>
.create-options-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>