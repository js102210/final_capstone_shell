<template>
  <div class="employee-products-display">
      <emp-create-cake></emp-create-cake>
      <emp-change-cake></emp-change-cake>
      <emp-create-options></emp-create-options>
      <emp-change-options></emp-change-options>
  </div>
</template>

<script>
import EmpChangeCake from '../components/EmpChangeCake.vue'
import EmpChangeOptions from '../components/EmpChangeOptions.vue'
import EmpCreateCake from '../components/EmpCreateCake.vue'
import EmpCreateOptions from '../components/EmpCreateOptions.vue'
export default {
    name: 'employee-products',
  components: { 
      EmpCreateCake, 
      EmpChangeCake, 
      EmpCreateOptions, 
      EmpChangeOptions
       },
}
</script>

<style>
.employee-products-display {
  margin-top: 45px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.create-cake-display {
  width: fit-content;
  margin-top: 30px;
  border: 1px solid black;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.521);
  padding: 30px;
  font-family: 'Quicksand';
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
}

.create-cake-display input, select {
    margin: 6px;
    padding: 2px;
    border-radius: 5px;
    border: 1px solid rgb(24, 24, 24);
  }

.change-cake-display {
  width: fit-content;
  margin-top: 30px;
  border:1px solid black;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.607);
  padding: 30px;
  font-family: 'Quicksand';
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
}

.create-options-display {
  width: fit-content;
  margin-top: 30px;
  border:1px solid black;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.534);
  padding: 30px;
  font-family: 'Quicksand';
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
}

.create-options-display input, select {
    margin: 6px;
    padding: 2px;
    border-radius: 5px;
    border: 1px solid rgb(24, 24, 24);
  }

.change-options-display {
  width: fit-content;
  margin-top: 30px;
  border:1px solid black;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.607);
  padding: 30px;
  font-family: 'Quicksand';
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
}

/* .change-options-display tr {
  margin-top: 10px;
} */

.unavailable {
  color: red;
}

</style>