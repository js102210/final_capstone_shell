<!-- Customer: references the store to see items in the active order and allow customers to finalize it-- turning it into an actual Order in the database. -->
<template>
  <div class="shopping-cart-banner">
    <h3  v-show="$store.state.currentActiveOrder.itemsInOrder.length">Your Current Order:</h3>
  <div class="where-beef" v-if="$store.state.currentActiveOrder.itemsInOrder.length < 1">
    <h1>Where's the BeefCake?</h1>
    <p>Your shopping cart has no delicious BeefCakes baked goods in it!</p>
    <p>Come back when you've created the perfect cake for your next cheat day.</p>
    <router-link v-bind:to="{ name: 'order-cake' }" ><button>I want to make a cake!</button></router-link>
  </div>
    <div class="shopping-cart" v-else>
      <customer-ordered-cake-card
        v-for="orderedCake in $store.state.currentActiveOrder.itemsInOrder"
        v-bind:key="orderedCake.cakeItemTempOrderID"
        v-bind:orderedCake="orderedCake"
      />
    </div>
    <div class="order-price" v-show="$store.state.currentActiveOrder.itemsInOrder.length">
      <p>
        Your order's price is:
        <span class="calculated-price">$ {{ orderPrice }} </span>
      </p>
    </div>
    <div class="move-back-to-order" v-show="$store.state.currentActiveOrder.itemsInOrder.length">
      <router-link v-bind:to="{ name: 'order-cake' }" ><button>Add Another Cake to Cart!</button></router-link>
      </div><br />
      
      <div class="finalize-order">
    <form @submit="placeOrder"  v-show="$store.state.currentActiveOrder.itemsInOrder.length">
      <label for="customerName">Please enter your name:</label><br />
      <input
        name="customerName"
        type="text"
        placeholder="Your Name"
        v-model="pickupInfo.customerName"
        required
      /><br /><br />

      <label for="customerPhoneNumber">Please enter your phone number:</label><br />
      <input
        name="customerPhoneNumber"
        type="tel"
        v-model="pickupInfo.customerPhoneNumber"
        required
      /><br /><br/>

      <!-- let's change this to separate date and time field inputs. see if we can limit time to bakery open hours-->
      <label for="pickup date and time"
        >When do you want to pick up your cake?</label
      ><br />
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
      /><br /><br />

      <button type="submit" @click="startConf">Order Your BeefCakes!</button>
    </form></div>
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
  /**
   * lifecycle hook - populates the available cake component arrays so that we can display the names for all of 
   * each cake's components - the actual JSON in the store only has the ids for each component.
   */
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
    /**
     * totals up the price of each cake in Order and displays on screen. Used to set currentActiveOrder price
     * when placeOrder() is run.
     */
    orderPrice() {
      let price = 0.0;
      this.$store.state.currentActiveOrder.itemsInOrder.forEach(
        (element) => (price +=parseFloat(element.cakeItemPrice))
      );
      return price.toFixed(2);
    },
  },
  methods: {
    /**
     * places the order by setting the order information and price to currentActiveOrder, then submits order to the backend.
     * Requires customer confirmation that their order is finalized before proceeding.
     * If order is completed successfully, alerts the customer on pickup date and time, runs the CLEAR_ACTIVE_ORDER mutation to
     * clear the order information in the store, then pushes the user back to the home page.
     */
    placeOrder() {
      if (confirm("Are you ready to place your final order? Placed orders can only be changed by calling the BeefCakes Bakeshop at 513-555-BEEF.")) {
        this.$store.commit("SET_ORDER_INFO", this.pickupInfo);
        this.$store.commit("SET_ORDER_PRICE", this.orderPrice);
        CustomerService.sendOrderJSON(this.$store.state.currentActiveOrder)
          .then((response) => {
            if (response.status === 201) {
              
              alert(
                "Order placed! See you on " +
                  this.pickupInfo.orderPickupDate +
                  " at " + this.pickupInfo.orderPickupTime
              );
                            //unclear why, but this isn't pushing?
              this.$router.push("/");
              this.$store.commit("CLEAR_ACTIVE_ORDER");

            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "placing");
          });
      }
    },
    /**
     * our handy-dandy generic error response handler. Generates appropriate error message depending on context.
     */
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
    // startConf() {
    //     this.$confetti.start();
    //   },
    // stopConf() {
    //     this.$confetti.stop();
    //   },
};
</script>

<style>

</style>