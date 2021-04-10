<template>
  <div class="cakes-layout">
      <cake-card v-for="cakeConfig in $store.state.standardCakeConfigsBE"
      v-bind:key="cakeConfig.cakeConfigID"
      v-bind:cakeConfig="cakeConfig" />
  </div>
   <!-- <div> --><!-- <br>
   <button class="order-button" v-on:click="moveToOrder">Order A Cake!</button> -->
  <!-- </div> -->
</template>

<script>
import CakeCard from './CakeCard.vue'
export default {
  components: { CakeCard },
  methods: {
    moveToOrder(){
      this.$router.push('/ordercake');
    }
  }

}
</script>

<style>
.cakes-layout {
  display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}
.order-button{
  align-items:center;
}

@media screen and (max-width: 600px) {

.cakes-layout{  
  flex-direction: column;
  align-items: center;
}

}
</style>