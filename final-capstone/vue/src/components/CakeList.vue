<template>
  <div>
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

</style>