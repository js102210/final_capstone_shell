<template>
  <div>

  </div>
</template>

<script>

export default {
    data(){
        return{
            itemsInOrder : {}
            
        }
    },
    methods: {

    },
    created(){
    this.itemsInOrder = this.$store.state.selectedOrder.itemsInOrder;
    }

}
</script>

<style>

</style>