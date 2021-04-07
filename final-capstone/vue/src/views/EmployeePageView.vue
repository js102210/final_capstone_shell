<template>
  <div>
      <emp-create-cake></emp-create-cake>
      <emp-change-cake-availabilty></emp-change-cake-availabilty>
      <emp-create-options></emp-create-options>
      <emp-change-option-availabilty></emp-change-option-availabilty>
  </div>
</template>

<script>
import EmpChangeCakeAvailabilty from '../components/EmpChangeCakeAvailabilty.vue'
import EmpChangeOptionAvailabilty from '../components/EmpChangeOptionAvailabilty.vue'
import EmpCreateCake from '../components/EmpCreateCake.vue'
import EmpCreateOptions from '../components/EmpCreateOptions.vue'
export default {
    name: employee-page-view,
  components: { 
      EmpCreateCake, 
      EmpChangeCakeAvailabilty, 
      EmpCreateOptions, 
      EmpChangeOptionAvailabilty },
}
</script>

<style>

</style>