<template>
  <div class="order-details">
<order-details/>
  </div>
</template>

<script>
import OrderDetails from '../components/OrderDetails.vue'
import EmployeeService from '../services/EmployeeService'

export default {
  components: { OrderDetails },
  created(){
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

  }

}
</script>

<style>

</style>