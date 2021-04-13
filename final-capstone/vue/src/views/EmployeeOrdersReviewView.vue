<template>
<div class="employee-order-display">

  <employee-order-list></employee-order-list>
  </div>
</template>

<script>
import EmployeeOrderList from '../components/EmployeeOrderList.vue'
import EmployeeService from '../services/EmployeeService';

export default {
  components: { EmployeeOrderList },
  created() {
     
     EmployeeService.getAllStatuses().then((response) => {
        this.$store.commit("SET_ALL_STATUSES_ARRAY", response.data);
      })
    
}

}

</script>

<style>

.employee-order-display {
  margin-top: 45px;
}
</style>