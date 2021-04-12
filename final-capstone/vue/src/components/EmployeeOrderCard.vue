<template>
<div class="order-container">
    <h2>{{statusName}}</h2>
    <h2>Order Number: {{order.orderID}}:  {{order.customerName}}</h2>
    <h3>Placed on: {{order.orderDatePlaced}}  
    To be picked up on: {{order.orderPickupDate}}  at: {{order.orderPickupTime}}</h3>
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
    created(){
        const status = this.$store.state.allStatusesBE.find((status) => status.statusID == this.order.orderStatusID);
        this.statusName = status.statusName;
    }

}
</script>

<style>
.order-container > * {
  font: 1em sans-serif;
}

</style>