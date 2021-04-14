<template>
  <div class="shopping-cart">
      <customer-ordered-cake-card 
      
      v-for="orderedCake in $store.state.currentActiveOrder.itemsInOrder"
      v-bind:key="orderedCake.cakeItemTempOrderID"
      v-bind:orderedCake="orderedCake"
      />
      </div>
</template>

<script>
import CustomerOrderedCakeCard from './CustomerOrderedCakeCard.vue'
import CustomerService from '../services/CustomerService.js'
export default {
  components: { CustomerOrderedCakeCard },
    created(){
    CustomerService.getAvailableConfigs().then((response) => {
      this.$store.commit("SET_AVAILABLE_CAKE_CONFIG_ARRAY", response.data);
    });
    CustomerService.getAvailableSizes().then((response) => {
      this.$store.commit("SET_AVAILABLE_SIZES_ARRAY", response.data);
    });

    CustomerService.getAvailableStyles().then((response) => {
      this.$store.commit("SET_AVAILABLE_STYLES_ARRAY", response.data);
    });

    CustomerService.getAvailableFlavors().then((response) => {
      this.$store.commit("SET_AVAILABLE_FLAVORS_ARRAY", response.data);
    });

    CustomerService.getAvailableFrostings().then((response) => {
      this.$store.commit("SET_AVAILABLE_FROSTINGS_ARRAY", response.data);
    });

    CustomerService.getAvailableFillings().then((response) => {
      this.$store.commit("SET_AVAILABLE_FILLINGS_ARRAY", response.data);
    });

    CustomerService.getAvailableExtras().then((response) => {
      this.$store.commit("SET_AVAILABLE_EXTRAS_ARRAY", response.data);
    });
    }

}
</script>

<style>
.shopping-cart{
      display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}


.ordered-cake-card{
    border: 1px black;
}

</style>