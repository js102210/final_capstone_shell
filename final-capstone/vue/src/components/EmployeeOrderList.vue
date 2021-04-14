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
        EmployeeService.getAllFrostings().then((response)=> {
      this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    });
    EmployeeService.getAllFillings().then((response)=>{
      this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data)
    });
    EmployeeService.getAllStyles().then((response)=>{
      this.$store.commit("SET_ALL_STYLES_ARRAY", response.data)
    });
    EmployeeService.getAllSizes().then((response)=> {
      this.$store.commit("SET_ALL_SIZES_ARRAY", response.data)
    });
    EmployeeService.getAllFlavors().then((response)=> {
      this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data)
    });
    EmployeeService.getAllConfigs().then((response)=> {
      this.$store.commit("SET_ALL_CAKE_CONFIG_ARRAY", response.data)
    });
    EmployeeService.getAllStatuses().then((response)=> {
      this.$store.commit("SET_ALL_STATUSES_ARRAY", response.data)
    })


  }
};
</script>

<style >
/* div {
border: 2px solid black;
font: 1em 'Comic Neue',sans-serif;
} */
</style>