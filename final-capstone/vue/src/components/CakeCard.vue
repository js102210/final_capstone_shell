<template>
  <div class="cake-container">
      <h3>{{cake.name}}</h3>
      <img class="cake-pic" v-bind:src=cake.image_url /> <br>
      <p>{{cake.description}}</p>

      
      <button v-on:click="moveToStandardOrderPage(cake.id)">Order this cake!</button>

  </div>
</template>

<script>
export default {
    name: "cake-card",
    props: [
        "cake"
    ],
    methods: {
        moveToStandardOrderPage(id){
            this.$store.commit("SET_SELECTED_CAKE", id);
            this.$router.push('/standardorder')
        }
    }

}
</script>

<style>
.cake-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    align-items: center;
    width: 160px;
    height: 300px;
    border: 2px solid black;
    border-radius: 10px;
    padding: 20px;
    margin-top: 20px;
    background: rgba(255, 255, 255, 0.452);
    -webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px);
}

.cake-pic {
    width: 100%;
    height: 30%;
}

.cake-container p {
    font-family: 'Quicksand';
    font-size: .9rem;
}

</style>