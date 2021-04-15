<!--Employee: display component for an order meant to populate the OrderList. Passed JSON for an Order from the parent for display. -->
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
             statusToSend : {statusID: 0}
        }
    },
    methods: {
        /**
         * Sets the selectedOrder container in the store with info for the Order then navigates the user to the page 
         * displaying details on the selectedOrder
         */
        goToSelectedOrder(){
        const idToNav = this.order.orderID;
        const orderBody = this.$store.state.pastOrdersArrayBE.find((order) => order.orderID == idToNav);
        this.$store.commit("SET_SELECTED_ORDER", orderBody);
        this.$router.push({name: 'order-details', params: {orderID: idToNav}} )
        },
        /**
         * Sends an an orderID and a statusID. The back-end then sets the Order to that Status. Reloads the page to display the updated status
         * @param orderID number ID of the Order
         * @param statusID number ID of the Status to set the Order to
         */
           changeOrderStatus(orderID, statusID){
           EmployeeService.updateOrderStatus(orderID, statusID).then((response) => {
                if (response.status == 200){
                    //menu is only populated with the names of the statuses-- the statusID is only present for the current status in the JSON --
                    //so we need to get the key from the store for a new status to send to the back-end
                    
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
        //status of the order is represented as a key in the JSON, so we need to get the String representation from the store
        const status = this.$store.state.allStatusesBE.find((status) => status.statusID == this.order.orderStatusID);
        this.statusName = status.statusName;
    }

}
</script>

<style >



</style>