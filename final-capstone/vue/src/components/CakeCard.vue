<template>
  <div class="cake-container">
      <h3>{{cakeConfig.cakeConfigName}}</h3>
      <img class="cake-pic" v-bind:src=cakeConfig.cakeConfigUrl /> <br>
      <p class="cake-card-p">{{cakeConfig.cakeConfigDescription}}</p>

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
            this.$router.push('/ordercake')
        }
    }

}
</script>

<style>


</style>