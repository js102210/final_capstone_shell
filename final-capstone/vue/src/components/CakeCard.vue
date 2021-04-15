
<!--Customer: An instance of this component will be populated for each type of cake in the store. Each card contains a button that navigates 
to the order screen and some information about that kind of cake (referred to as Cake Config). Gets passed a Cake Config from the parent.-->
<template>
  <div class="cake-container" tabIndex="0">
    <div class="flip-card-inner">  
      <div class="flip-card-front">
        <h3>{{cakeConfig.cakeConfigName}}</h3>
        <img class="cake-pic" v-bind:src=cakeConfig.cakeConfigUrl /> <br>
        <p class="cake-card-p">{{cakeConfig.cakeConfigDescription}}</p>
      </div>
      <div class="flip-card-back">
      <button id="buy-this-cake" v-on:click="moveToOrderPage()">Buy a cake!</button>
      </div>
    </div>  
  </div>
</template>

<script>
export default {
    name: "cake-card",
    props: [
        "cakeConfig"
    ],
    methods: {
      /**
       * moves the user to the page to order a cake when clicked
       */
        moveToOrderPage(){
            this.$router.push('/ordercake')
        }
    }

}
</script>

<style>


</style>