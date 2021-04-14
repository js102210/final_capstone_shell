<template>
<div class="order-container">
    <h2>{{statusName}}</h2>
    <h2>Order Number: {{order.orderID}}:  {{order.customerName}}</h2>
    <h3>Placed on: {{order.orderDatePlaced}} <br>
    To be picked up on: {{order.orderPickupDate}}  at: {{order.orderPickupTime}}</h3>
    <h3>Items in order: {{order.itemsInOrder.length}}</h3> <h3>{{order.orderPriceTotal}}</h3>
    <button v-on:click="goToSelectedOrder" >Go to items</button>
          Change order status: 
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
</select> 
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
             statusToSend : {statusID: 0}
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
                   const status = this.$store.state.allStatusesBE.find((status)=> {
                        return status.statusID == this.statusToSend.statusID
                    })
            
                    alert('The order is now ' + status.statusName)
                    location.reload()
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
.order-container > * {
  font: 1em sans-serif;
  
}


</style>