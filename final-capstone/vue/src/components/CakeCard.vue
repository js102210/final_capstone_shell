<template>
  <div class="cake-container">
      <h3>{{cake.name}}</h3>
      <img class="cake-pic" v-bind:src=cake.image_url /> <br>
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
.cake-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    align-items: center;
    width: 150px;
    border: 2px solid black;
    border-radius: 10px;
    padding: 20px;
    margin-top: 20px;
}

.cake-pic {
    width: 100%;
}
</style>