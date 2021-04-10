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
import EmployeeOrderCard from './EmployeeOrderCard.vue';
export default {
  components: { EmployeeOrderCard },
};
</script>

<style>
</style>