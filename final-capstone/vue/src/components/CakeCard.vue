<template>
  <div class="cake-container">
      <h3>{{cakeConfig.cakeConfigName}}</h3>
      <img class="cake-pic" v-bind:src=cakeConfig.cakeConfigUrl /> <br>
      <p>{{cakeConfig.cakeConfigDescription}}</p>

      <button id="buy-this-cake" v-on:click="moveToOrderPage()">Buy a cake!</button>
  </div>
</template>

<script>
export default {
    name: "cake-card",
    props: [
        "cakeConfig"
    ],
    methods: {
        moveToOrderPage(){
            //commented out this mutation - it's just not working and I can't figureit out tonight.
            //this.$store.commit("SET_SELECTED_CONFIG", cakeConfig);
            this.$router.push('/ordercake')
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
    background: rgba(255, 255, 255, 0.349);
    /* -webkit-backdrop-filter: blur(20px);
	backdrop-filter: blur(10px); */
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