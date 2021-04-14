<template>
  <div>
    <div class="shopping-cart">
      <customer-ordered-cake-card
        v-for="orderedCake in $store.state.currentActiveOrder.itemsInOrder"
        v-bind:key="orderedCake.cakeItemTempOrderID"
        v-bind:orderedCake="orderedCake"
      />
    </div>
    <div class="order-price">
      <p>
        Your order's price is:
        <span class="calculated-price">$ {{ orderPrice }} </span>
      </p>
    </div>
    <form class="finalize order" @submit="placeOrder">
      <label for="customerName">Please enter your name:</label>
      <input
        name="customerName"
        type="text"
        placeholder="Your Name"
        v-model="pickupInfo.customerName"
        required
      /><br />

      <label for="customerPhoneNumber">Please enter your phone number:</label>
      <input
        name="customerPhoneNumber"
        type="tel"
        v-model="pickupInfo.customerPhoneNumber"
        required
      /><br />

      <!-- let's change this to separate date and time field inputs. see if we can limit time to bakery open hours-->
      <label for="pickup date and time"
        >When do you want to pick up your cake?</label
      >
      <input
        name="pickup date"
        type="date"
        v-model="pickupInfo.orderPickupDate"
        required
      />

      <input
        name="pickup time"
        type="time"
        v-model="pickupInfo.orderPickupTime"
        required
      />

      <input type="submit" value="Place Your Order!" />
    </form>
  </div>
</template>

<script>
import CustomerOrderedCakeCard from "./CustomerOrderedCakeCard.vue";
import CustomerService from "../services/CustomerService.js";
export default {
  data() {
    return {
      pickupInfo: {
        orderPickupDate: null,
        orderPickupTime: null,
        customerName: null,
        customerPhoneNumber: null,
      },
    };
  },
  components: { CustomerOrderedCakeCard },
  created() {
    CustomerService.getAvailableConfigs().then((response) => {
      this.$store.commit("SET_AVAILABLE_CAKE_CONFIG_ARRAY", response.data);
    });
    CustomerService.getAvailableSizes().then((response) => {
      this.$store.commit("SET_AVAILABLE_SIZES_ARRAY", response.data);
    });

    CustomerService.getAvailableStyles().then((response) => {
      this.$store.commit("SET_AVAILABLE_STYLES_ARRAY", response.data);
    });

    CustomerService.getAvailableFlavors().then((response) => {
      this.$store.commit("SET_AVAILABLE_FLAVORS_ARRAY", response.data);
    });

    CustomerService.getAvailableFrostings().then((response) => {
      this.$store.commit("SET_AVAILABLE_FROSTINGS_ARRAY", response.data);
    });

    CustomerService.getAvailableFillings().then((response) => {
      this.$store.commit("SET_AVAILABLE_FILLINGS_ARRAY", response.data);
    });

    CustomerService.getAvailableExtras().then((response) => {
      this.$store.commit("SET_AVAILABLE_EXTRAS_ARRAY", response.data);
    });
  },
  computed: {
    orderPrice() {
      let price = 0.0;
      this.$store.state.currentActiveOrder.itemsInOrder.forEach(
        (element) => (price += element.cakeItemPrice)
      );
      return price.toFixed(2);
    },
  },
  methods: {
    placeOrder() {
      if (confirm("Are you ready to place your final order? Placed orders can only be changed by calling the BeefCakes Bakeshop at 513-541-BEEF.")) {
        this.$store.commit("SET_ORDER_INFO", this.pickupInfo);
        CustomerService.sendOrderJSON(this.$store.state.currentActiveOrder)
          .then((response) => {
            if (response.status === 201) {
              
              confirm(
                "Order placed! See you on " +
                  this.pickupInfo.orderPickupDate +
                  "at " + this.pickupInfo.orderPickupTime
              );
              this.$store.commit("CLEAR_ACTIVE_ORDER");
              this.$router.push("/cakes");
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "placing");
          });
      }
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " order. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " order. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " order. Request could not be created.";
      }
    },
  },
};
</script>

<style>
.shopping-cart {
  display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}

.ordered-cake-card {
  border: 1px black;
}
</style>