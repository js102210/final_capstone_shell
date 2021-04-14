<template>
  <div>
      Change order status: 
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
        <h2>{{statusToDisplay.statusName}}</h2>
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
    created(){
    this.itemsInOrder = this.$store.state.selectedOrder.itemsInOrder;
    this.getCurrentStatusName();
   
    },
    methods: {
        changeOrderStatus(orderID, statusID){
           EmployeeService.updateOrderStatus(orderID, statusID).then((response) => {
                if (response.status == 200){
                    alert('')
                }
           },
           
           )  
    },
    getCurrentStatus(){
           
            this.statusToDisplay = this.$store.state.allStatusesBE.find((status)=>{
                status.statusID == this.$store.state.selectedOrder.orderStatusID
            })
           
             
     }
},

    

}
</script>

<style>

</style>