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
  text-align: center;
}

.emp-order-cake-details {
  border: 7px dashed rgba(0, 0, 0, 0.226);
  padding: 20px;
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
  background: rgba(255, 255, 255, 0.534);
  padding: 20px;
  text-align: center;
}

.order-details-display p {
  font-family: 'Quicksand';
  font-size: 1rem;
}

/* @media screen and (max-width: 700px) {
.employee-order-display {
  margin-top: 45px;
  flex-wrap: wrap;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
} */
</style>