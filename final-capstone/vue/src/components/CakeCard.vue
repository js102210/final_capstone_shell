<template>
  <div>
      <h3>{{cake.name}}</h3>
      <img v-bind:src=cake.image_url /> <br>
      <h2>{{cake.price}}</h2>
      <p>{{cake.description}}</p>
      <button>Order this cake!</button>

  </div>
</template>

<script>
export default {
    name: "cake-card",
    props: [
        "cake"
    ]

}
</script>

<style>

</style>