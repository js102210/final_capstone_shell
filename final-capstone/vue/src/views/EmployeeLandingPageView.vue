<template>
  <div class="employee-landing-page">
      <h2>Welcome, Beefcake employee!</h2>
      <router-link v-bind:to="{ name: 'employee-products' }" >Create and Edit Inventory</router-link><br />
      <router-link v-bind:to="{ name: 'employee-orders' }" >View and Edit Orders</router-link>
      </div>
</template>

<script>
export default {

}
</script>

<style>
.employee-landing-page {
  margin-top: 40px;
}

</style>