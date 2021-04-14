<template>
  <div>
      <h1>{{this.$store.state.selectedOrder.customerName}}</h1>
      <h2>Ordered on {{this.$store.state.selectedOrder.orderDatePlaced}}</h2>
      <h2>To be delivered on {{this.$store.state.selectedOrder.orderPickupDate}}
            at {{this.$store.state.selectedOrder.orderPickupTime}}
      </h2>
      <h2>Items ordered:  <br></h2>
 <cake-item-details
 v-for="item in $store.state.selectedOrder.itemsInOrder"
 v-bind:key="item.cakeItemID"
 v-bind:cakeItemJSON="item" />
       
        
  </div>
</template>

<script>
import cakeItemDetails from './cakeItemDetails.vue';
//import cakeItemDetails from '../components/cakeItemDetails.vue';
export default {
  components: { cakeItemDetails },
    data(){
        return{

            
        }
    },
    created(){
    this.itemsInOrder = this.$store.state.selectedOrder.itemsInOrder;
   
    },
   /* components: {
        cakeItemDetails
    }*/

}
</script>

<style>

</style>