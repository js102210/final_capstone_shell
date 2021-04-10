<template>
  <div class="employee-landing-page">
      <h2>Welcome, Beefcake employee!</h2>
      <router-link v-bind:to="{ name: 'employee-products' }" >Placeholder - this should be something prettier, but it takes you to working witn Products</router-link>
      <br>
      <br>
      <router-link v-bind:to="{ name: 'employee-orders' }" >Placeholder - this should be something prettier, but it takes you to working witn Orders</router-link>
      </div>
</template>

<script>
export default {

}
</script>

<style>

</style>