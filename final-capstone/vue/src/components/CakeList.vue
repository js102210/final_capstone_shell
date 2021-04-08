<template>
  <div>
  <div class="cakes-layout">
      <cake-card v-for="cake in $store.state.standardCakeConfigsBE"
      v-bind:key="cake.cake_config_id"
      v-bind:cake="cake" />
      
  </div>
  <br>
  <button class="order-button" v-on:click="moveToOrder">Order A Cake!</button>
  </div>
</template>

<script>
import CakeCard from './CakeCard.vue'
export default {
  components: { CakeCard },
  methods: {
    moveToOrder(){
      this.$router.push('/standardorder');
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