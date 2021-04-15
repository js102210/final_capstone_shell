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
     //populates status array in the store when view is initialized so we can display the string representations of the statuses
     EmployeeService.getAllStatuses().then((response) => {
        this.$store.commit("SET_ALL_STATUSES_ARRAY", response.data);
      })
    
}

}

</script>

<style>

.employee-order-display {
  margin-top: 45px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.order-container {
  margin-top: 30px;
  border: 1px solid black;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.452);
  padding: 30px;
  font-family: 'Quicksand';
  font-weight: 600;
}

.order-container p {
  font-family: 'Quicksand';
  font-weight: 600;
  font-size: 1rem;
}

.order-details-display {
  margin-top: 40px;
  font-family: 'Quicksand';
  font-weight: 600;
}

.order-details-display p {
  font-family: 'Quicksand';
  font-size: 1rem;
}

</style>