<template>
  <div class="cake-container">
      <h3>{{cake.cake_config_name}}</h3>
      <img class="cake-pic" v-bind:src=cake.cake_config_img_url /> <br>
      <p>{{cake.cake_config_description}}</p>
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
    height: 500px;
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