<template>
  <div class="orders-list">
    <employee-order-card
      v-for="order in $store.state.pastOrdersArrayBE"
      v-bind:key="order.orderID"
      v-bind:order="order"
    />
  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
import EmployeeOrderCard from './EmployeeOrderCard.vue';
export default {
  components: { EmployeeOrderCard },
  methods: {
    prepareOrdersArray(){
      EmployeeService.getAllOrders().then((response) => {
        this.$store.commit("SET_ALL_ORDERS_ARRAY", response.data);
      })
    }
  },
  created(){
    this.prepareOrdersArray();

  }
};
</script>

<style>

</style>