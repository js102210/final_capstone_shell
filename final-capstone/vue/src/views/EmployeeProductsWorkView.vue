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
}
.change-options-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}

.change-cake-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}

.unavailable {
  color: red;
}

</style>