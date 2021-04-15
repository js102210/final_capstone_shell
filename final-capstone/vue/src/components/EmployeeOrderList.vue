<!--Employee: displays a list (with filter options) of each order in the database. Populates with an OrderCard for each order. Passes 
Order JSON to the OrderCards for display. -->
<template>
  <div class="orders-list">
   <table id="filterOptions">
     <th>Orders Placed After: </th>
    <th>Orders to be picked up on: </th>
    <th>Orders to be picked up before: </th> 
    <th>Orders with status: </th>
   <th>Orders placed by: </th> 
    <tbody>
     <td><input type="date" id="placedAfterFilter" v-model="filter.placedAfter"/></td>
    <td><input type="date" id="pickUpOnFilter" v-model="filter.pickupOn"/></td>
    <td><input type="date" id="pickUpBeforeFilter" v-model="filter.pickupBefore"/></td> 
    <td>
      <select name="statusFilter" id="statusFilter" v-model="filter.orderStatus">
        <option  v-for="status in $store.state.allStatusesBE"
          v-bind:key="status.statusID"
          v-bind:value="status.statusID">{{status.statusName}}</option>
        <option v-bind:value="''">All Orders</option>
      </select>
    </td>
    <td><input type="text" id="customerNameFilter" v-model="filter.customerName"/></td>
    </tbody>
    </table>
  <button v-on:click="clearFilter">Clear Filters</button>

    <employee-order-card
      v-for="order in filteredList"
      v-bind:key="order.orderID"
      v-bind:order="order"
    />
    
  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
import EmployeeOrderCard from './EmployeeOrderCard.vue';
export default {
  data() {
    return {
      //filter hosts empty strings by default but attributes are bound to input fields-- so if there's no input the filter isn't applied,
      //if there is input then it is.
      filter: {
        placedAfter: '',
        pickupOn: '',
        pickupBefore: '',
        orderStatus: '',
        customerName: ''
       
      }
    }
  },
  components: { EmployeeOrderCard },
 /**
  * populates the arrays in the store with all the needed info to display when this component is created
  */
  created(){
    this.prepareOrdersArray();
        EmployeeService.getAllFrostings().then((response)=> {
      this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    });
    EmployeeService.getAllFillings().then((response)=>{
      this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data)
    });
    EmployeeService.getAllStyles().then((response)=>{
      this.$store.commit("SET_ALL_STYLES_ARRAY", response.data)
    });
    EmployeeService.getAllSizes().then((response)=> {
      this.$store.commit("SET_ALL_SIZES_ARRAY", response.data)
    });
    EmployeeService.getAllFlavors().then((response)=> {
      this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data)
    });
    EmployeeService.getAllConfigs().then((response)=> {
      this.$store.commit("SET_ALL_CAKE_CONFIG_ARRAY", response.data)
    });
    EmployeeService.getAllStatuses().then((response)=> {
      this.$store.commit("SET_ALL_STATUSES_ARRAY", response.data)
    })


  },
  computed: {
    /**
     * the filteredList is the list of all orders in the database that have not been disqualified by input in the filter fields
     */
    filteredList(){
      let filterOrderStatus = this.filter.orderStatus;
      let filterPlacedAfter = this.filter.placedAfter;
      let filterPickupOn = this.filter.pickupOn;
      let filterPickupBefore = this.filter.pickupBefore;
      let filterCustomerName = this.filter.customerName;
      
    //filter to display only orders which are not disqualified by some input. A blank input field disqualifies nothing, and input in the fields
    //disqualifies orders based on how their attributes match to the input
      return this.$store.state.pastOrdersArrayBE.filter((order) => {
      return ( new Date(filterPlacedAfter).valueOf() < new Date(order.orderDatePlaced).valueOf() || filterPlacedAfter == '') &&
        (new Date(filterPickupOn).valueOf() == new Date(order.orderPickupDate).valueOf()  || filterPickupOn == '')       &&
        (new Date(filterPickupBefore).valueOf() > new Date(order.orderPickupDate).valueOf() || filterPickupBefore == '') &&
        (filterOrderStatus == order.orderStatus || filterOrderStatus == '') &&
        (order.customerName.toLowerCase().includes(filterCustomerName.toLowerCase()) || filterCustomerName == '')
      }) 
       
    }
  },
   methods: {
     /**
      * populates the store with all orders from the database
      */
    prepareOrdersArray(){
      EmployeeService.getAllOrders().then((response) => {
        this.$store.commit("SET_ALL_ORDERS_ARRAY", response.data);
      })
    },
   /**
    * clears all filter fields 
    */
    clearFilter(){
      this.filter = { placedAfter: '',
        pickupOn: '',
        pickupBefore: '',
        orderStatus: '',
        customerName: ''}
    }
  }
};
</script>

<style >

</style>