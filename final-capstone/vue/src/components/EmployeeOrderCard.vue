<template>
<div class="order-container">

    <h2>Order Number {{order.orderID}}:  {{order.customerName}}</h2>
    <h2>Status: {{statusName}}</h2>
    <label for="status">Change order status:</label> 
    <select name="status" v-model="statusToSend.statusID" 
      v-on:change="changeOrderStatus(order.orderID, statusToSend.statusID)">
        <option
          v-for="status in $store.state.allStatusesBE"
          v-bind:key="status.statusID"
          v-bind:value="status.statusID"
        v-bind:id="order.orderID"
        >
         Mark Order {{status.statusName}}
        </option>
    </select> <br />
    <p>Placed on: {{order.orderDatePlaced}}</p>
    <p>To be picked up on: {{order.orderPickupDate}}  at: {{order.orderPickupTime}}</p>
    <p>Total: ${{order.orderPriceTotal}}</p>
    <p>Items in order: {{order.itemsInOrder.length}}</p>
    <button v-on:click="goToSelectedOrder" >Go to items</button>
</div>  
</template>

<script>
import EmployeeService from '../services/EmployeeService';
export default {
    name: "order-card",
   
    props: [
        "order"
    ],
    data(){
        return {
             statusName : '',
             statusToSend : {statusID: 0,
                            statusName: ''}
        }
    },
    methods: {
        goToSelectedOrder(){
        const idToNav = this.order.orderID;
        const orderBody = this.$store.state.pastOrdersArrayBE.find((order) => order.orderID == idToNav);
        this.$store.commit("SET_SELECTED_ORDER", orderBody);
        this.$router.push({name: 'order-details', params: {orderID: idToNav}} )
        },
                changeOrderStatus(orderID, statusID){
           EmployeeService.updateOrderStatus(orderID, statusID).then((response) => {
                if (response.status == 200){
                    alert('')
                }
           } 
        
           )  
    }
    },
    created(){
        
        const status = this.$store.state.allStatusesBE.find((status) => status.statusID == this.order.orderStatusID);
        this.statusName = status.statusName;
    }

}
</script>

<style >
/* .order-container > * {
  font: 1em sans-serif;
  
} */


</style>