<!--Customer: Screen where the customer sees the different kinds of cakes they can buy. Populates a Cake Card for each available 
Cake Config and passes it the Config for display -->
<template>
  <div class="cakes-layout">
    <cake-card
      v-for="cakeConfig in $store.state.availableCakeConfigsBE"
      v-bind:key="cakeConfig.cakeConfigID"
      v-bind:cakeConfig="cakeConfig"
    />
  </div>

</template>

<script>

import CustomerService from '../services/CustomerService.js';
import CakeCard from "./CakeCard.vue";
export default {
  components: { CakeCard },
  methods: {
  },

/**
 * this lifecycle hook gets the available cake configs from the database, which are needed to display the cake cards.
 * Affects the store's availableCakeConfigsBE array.
 */
  created() {
    CustomerService.getAvailableConfigs().then((response) => {
      this.$store.commit("SET_AVAILABLE_CAKE_CONFIG_ARRAY", response.data);
    });
  },

}
</script>

<style>
.cakes-layout {
  display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}
.order-button {
  align-items: center;
}

.cake-container h3{
  margin-top: -25px;
  font-size: 29px;
}



@media screen and (max-width: 600px) {
  .cakes-layout {
    flex-direction: column;
    align-items: center;
  }
}
</style>