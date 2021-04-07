<template>
  <div class="cakes-layout">
      <cake-card v-for="cake in $store.state.placeholderCakes"
      v-bind:key="cake.cake_id"
      v-bind:cake="cake" />
  </div>
</template>

<script>
import CakeCard from './CakeCard.vue'
export default {
  components: { CakeCard },

}
</script>

<style>
.cakes-layout {
  display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}

@media screen and (max-width: 600px) {

.cakes-layout{  
  flex-direction: column;
  align-items: center;
}

}
</style>