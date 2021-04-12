<template>
<div class="employee-order-display">

  <employee-order-list></employee-order-list>
  </div>
</template>

<script>
import EmployeeOrderList from '../components/EmployeeOrderList.vue'
export default {
  components: { EmployeeOrderList },

}
</script>

<style>

.employee-order-display {
  margin-top: 45px;
}
</style>