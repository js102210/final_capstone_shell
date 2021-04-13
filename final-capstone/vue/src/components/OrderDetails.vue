<template>
  <div>
      <h1>{{this.$store.state.selectedOrder.customerName}}</h1>
      <h2>Ordered on {{this.$store.state.selectedOrder.orderDatePlaced}}</h2>
      <h2>To be delivered on {{this.$store.state.selectedOrder.orderPickupDate}}
            at {{this.$store.state.selectedOrder.orderPickupTime}}
      </h2>
      <h2>Items ordered:  <br></h2>
        <div v-for="item in this.$store.state.selectedOrder.itemsInOrder"
        v-bind:key="item.cakeItemID">
            <h3>{{item.cakeItemConfigID}}</h3>
        </div>
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