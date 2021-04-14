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
    <td><input type="date" id="pickUpOnFilter" v-model="filter.pickUpOn"/></td>
    <td><input type="date" id="pickUpBeforeFilter" v-model="filter.pickUpBefore"/></td>
    <td>
      <select name="statusFilter" id="statusFilter" v-model="filter.orderStatus">
        <option  v-for="status in $store.state.allStatusesBE"
          v-bind:key="status.statusID"
          v-bind:value="status.statusID">{{status.statusName}}</option>
      </select>
    </td>
    <td><input type="text" id="customerNameFilter" v-model="filter.customerName"/></td>
    </tbody>
    </table>


    <employee-order-card
      v-for="order in $store.state.pastOrdersArrayBE"
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
  methods: {
    prepareOrdersArray(){
      EmployeeService.getAllOrders().then((response) => {
        this.$store.commit("SET_ALL_ORDERS_ARRAY", response.data);
      })
    }
  },
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
    filteredList(){
      let filterPlacedAfter = this.filter.placedAfter;
     /* let filterPickupOn = this.filter.pickupOn;
      let filterPickupBefore = this.filter.pickupBefore;
      let filterOrderStatus = this.filter.orderStatus;
      let filterCustomerName = this.filter.customerName; */


      return this.$store.state.pastOrdersArrayBE.filter((order) => {
        return new Date(filterPlacedAfter).valueOf() < new Date(order.orderDatePlaced).valueOf();
      })
    }
  }
};
</script>

<style >
/* div {
border: 2px solid black;
font: 1em 'Comic Neue',sans-serif;
} */
</style>