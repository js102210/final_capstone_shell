<template>
<div class="order-container">
    <h2>{{statusName}}</h2>
    <h2>Order Number: {{order.orderID}}:  {{order.customerName}}</h2>
    <h3>Placed on: {{order.orderDatePlaced}} <br>
    To be picked up on: {{order.orderPickupDate}}  at: {{order.orderPickupTime}}</h3>
    <h3>Items in order: {{order.itemsInOrder.length}}</h3>
    <button v-on:click="goToSelectedOrder" >Go to items</button>
</div>
</template>

<script>
export default {
    name: "order-card",
   
    props: [
        "order"
    ],
    data(){
        return {
             statusName : ''
        }
    },
    methods: {
        goToSelectedOrder(){
        const idToNav = this.order.orderID;
        const orderBody = this.$store.state.pastOrdersArrayBE.find((order) => order.orderID == idToNav);
        this.$store.commit("SET_SELECTED_ORDER", orderBody);
        this.$router.push({name: 'order-details', params: {orderID: idToNav}} )
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