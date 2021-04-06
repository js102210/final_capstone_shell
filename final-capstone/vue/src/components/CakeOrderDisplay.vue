<template>
  <div v-bind:key="cake.cake_id">
        <h3>{{cake.name}}</h3>
      <img class="cake-pic" v-bind:src=cake.image_url /> <br>
      <h2>{{cake.price}}</h2>
      <p>{{cake.description}}</p>
  </div>
</template>

<script>
export default {
name: "cake-order-display",
props: [
    "cake"
]
}
</script>

<style>

</style>