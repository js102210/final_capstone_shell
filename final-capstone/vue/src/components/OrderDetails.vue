<!--Employee: Displays details for an Order. Displays a cakeItemDetails component for each item in the order and passes it JSON for the item
for display-->
<template>
  <div class="order-details-display">

      
        <h2>{{this.$store.state.selectedOrder.customerName}}</h2>
        <p>Ordered on {{this.$store.state.selectedOrder.orderDatePlaced}}</p>
      <p>To be delivered on {{this.$store.state.selectedOrder.orderPickupDate}}
            at {{this.$store.state.selectedOrder.orderPickupTime}}</p>
      <p>Items ordered:</p>

       <label for="status">Change order status:</label>
        <select name="status" v-model="statusToSend.statusID" 
        v-on:change="changeOrderStatus($store.state.selectedOrder.orderID, statusToSend.statusID)">
        <option
          v-for="status in $store.state.allStatusesBE"
          v-bind:key="status.statusID"
          v-bind:value="status.statusID"
        >
         Mark Order {{status.statusName}}
        </option>
        </select> 
        
 <cake-item-details
 v-for="item in $store.state.selectedOrder.itemsInOrder"
 v-bind:key="item.cakeItemID"
 v-bind:cakeItemJSON="item" />

        
  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
import cakeItemDetails from './cakeItemDetails.vue';

export default {
  components: { cakeItemDetails },
    data(){
        return{
            statusToSend : {statusID: 0},
            statusToDisplay: {}
            
        }
    },
    /**
     * gets the items in the Order and the name of the status for the Order from the store
     */
    created(){
    this.itemsInOrder = this.$store.state.selectedOrder.itemsInOrder;
    this.getCurrentStatusName();
   
    },
    methods: {
        /**
         * Sends an orderID and a statusID to the back-end. The Order with that ID is then assigned that status. No need to reload the page, as we're
         * not displaying the statusName
         * @param orderID number ID of the Order
         * @param statusID number ID of the Status to set the Order to
         * 
         */
        changeOrderStatus(orderID, statusID){
           EmployeeService.updateOrderStatus(orderID, statusID).then((response) => {
                if (response.status == 200){
                    alert('The status of the order has been changed.')
                   
                }
           },
           
           )  
    },

},

    

}
</script>

<style>

</style>