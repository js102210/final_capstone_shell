<template>
  <div class="employee-page-display">
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
    name: 'employee-page-view',
  components: { 
      EmpCreateCake, 
      EmpChangeCake, 
      EmpCreateOptions, 
      EmpChangeOptions
       },
}
</script>

<style>
.employee-page-display {
  margin: 0px;
}

</style>