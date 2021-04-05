<template>
  <div>
      <h3>{{cake.name}}</h3>
      <img src="#" /> <br>
      <h2>{{cake.price}}</h2>
      <p>{{cake.description}}</p>

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