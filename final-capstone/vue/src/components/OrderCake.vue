<template>
  <div class="order-display">
    <div class="cakedisplay">
      <!-- we do ternary statements here - if nothing's selected in drop-down, the cakedisplay defaults to the custom cake info -->
      <h3>
        {{
          isSomethingSelected
            ? selected.cakeItemConfigName
            : this.$store.state.availableCakeConfigsBE[0].cakeConfigName
        }}
      </h3>
      <img
        v-bind:src="[
          isSomethingSelected
            ? selected.cakeItemConfigURL
            : this.$store.state.availableCakeConfigsBE[0].cakeConfigUrl,
        ]"
      /><br />
      <p>
        {{
          isSomethingSelected
            ? selected.cakeItemConfigDescription
            : this.$store.state.availableCakeConfigsBE[0].cakeConfigDescription
        }}
      </p>
    </div>

    <form class="order-form" @submit="orderThisCake">
      <!-- <p>Your Cake:</p> -->

      <label for="cake selection">Select your cake:</label>
      <!-- the v-model sets the select to whatever the standardCakeIdForOrder is. If null, it's blank.-->
      <select
        name="cake selection"
        v-model="selected"
        v-on:change="selectCakeConfig"
      >
        <!-- sets text for options to all cake names in standardCakeConfigs. value is the cake's cake_id-->
        <option
          v-for="cakeConfig in $store.state.availableCakeConfigsBE"
          v-bind:key="cakeConfig.cakeConfigID"
          v-bind:value="{
            cakeItemConfigName: cakeConfig.cakeConfigName,
            cakeItemConfigID: cakeConfig.cakeConfigID,
            cakeItemFrostingID: cakeConfig.cakeConfigFrostingID,
            cakeItemFillingID: cakeConfig.cakeConfigFillingID,
            cakeItemFlavorID: cakeConfig.cakeConfigFlavorID,
            cakeItemConfigURL: cakeConfig.cakeConfigUrl,
            cakeItemConfigDescription: cakeConfig.cakeConfigDescription,
          }"
        >
          {{ cakeConfig.cakeConfigName }}
        </option></select
      ><br />

      <label for="cake size">Select your size:</label>
      <select name="cake size" v-model="standardCakeOrderJSON.cakeItemSizeID">
        <option
          v-for="size in $store.state.availableCakeSizesBE"
          v-bind:key="size.sizeID"
          v-bind:value="size.sizeID"
        >
          {{ size.sizeDescription }}
        </option></select
      ><br />

      <label for="standard cake style">Select your cake style:</label>
      <select
        name="standard cake style"
        v-model="standardCakeOrderJSON.cakeItemStyleID"
      >
        <option
          v-for="style in $store.state.availableCakeStylesBE"
          v-bind:key="style.styleID"
          v-bind:value="style.styleID"
        >
          {{ style.styleName }}
        </option></select
      ><br />

      <!-- Custom Cake section hidden when Custom not selected -->
      <section
        class="custom cake options"
        v-show="showCustomOptions"
      >
        <label for="custom cake flavor">Select your flavor:</label>
        <select
          name="custom cake flavor"
          v-model="standardCakeOrderJSON.cakeItemFlavorID"
        >
          <option
            v-for="flavor in $store.state.availableFlavorsBE"
            v-bind:key="flavor.flavorID"
            v-bind:value="flavor.flavorID"
          >
            {{ flavor.flavorName }}
          </option></select
        ><br />

        <label for="custom cake frosting">Select your frosting:</label>
        <select
          name="custom cake frosting"
          v-model="standardCakeOrderJSON.cakeItemFrostingID"
        >
          <option
            v-for="frosting in $store.state.availableFrostingsBE"
            v-bind:key="frosting.frostingID"
            v-bind:value="frosting.frostingID"
          >
            {{ frosting.frostingName }}
          </option></select
        ><br />

        <label for="custom cake filling">Select your filling:</label>
        <select
          name="custom cake filling"
          v-model="standardCakeOrderJSON.cakeItemFillingID"
        >
          <option
            v-for="filling in $store.state.availableFillingsBE"
            v-bind:key="filling.fillingID"
            v-bind:value="filling.fillingID"
          >
            {{ filling.fillingName }}
          </option></select
        ><br />
      </section>
      <!-- End of Custom Cake section -->

      <label for="optional message"
        >Custom Message (Optional: $1.50 extra)</label
      >
      <input
        name="message"
        type="text"
        placeholder="Optional Message"
        v-model="standardCakeOrderJSON.cakeItemMessage"
      /><br />

      <!-- <p>Order Pick-Up Information:</p> -->

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
      /><br />

      <p class="price-display">
        Your Order's Total is:
        <span id="calculated-price">$ {{ itemPrice }} </span>
      </p>

      <!-- Hide submit button if required fields are not completed -->
      <section v-if="cakeValidated">
        <input type="submit" value="Order Your Cake!" />
      </section>

      <section v-else>
        <!-- note: we need to make this so it's not too wide for mobile.-->
        <h4>
          You must select a cake style, size, and flavor in order to place your
          order!
        </h4>
      </section>
    </form>
  </div>
</template>

<script>
// import CakeOrderDisplay from './CakeOrderDisplay.vue';
import CustomerService from "../services/CustomerService.js";

export default {
  data() {
    return {
      selected: {},
      standardCakeOrderJSON: {
        cakeItemStyleID: 1,
        cakeItemSizeID: 1,
        cakeItemFlavorID: 1,
        cakeItemFrostingID: 1,
        cakeItemFillingID: 1,
        cakeItemMessage: null,
        cakeItemPrice: 0.0,
        cakeItemConfigID: null,
      },
      pickupInfo: {
        orderPickupDate: null,
        orderPickupTime: null,
        customerName: null,
        customerPhoneNumber: null,
      },
    };
  },
  //   components: {
  //     //   CakeOrderDisplay
  //       },
  name: "order-cake",
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
  },

  computed: {
    itemPrice() {
      let price = 0.0;
      if (this.standardCakeOrderJSON.cakeItemSizeID) {
        let sizeElement = this.$store.state.availableCakeSizesBE.find(
          (element) =>
            element.sizeID === this.standardCakeOrderJSON.cakeItemSizeID
        );
        price += sizeElement.priceMod;
      }

      if (this.standardCakeOrderJSON.cakeItemStyleID) {
        let styleElement = this.$store.state.availableCakeStylesBE.find(
          (element) =>
            element.styleID === this.standardCakeOrderJSON.cakeItemStyleID
        );
        price += styleElement.priceMod;
      }
      if (this.standardCakeOrderJSON.cakeItemFlavorID) {
        let flavorElement = this.$store.state.availableFlavorsBE.find(
          (element) =>
            element.flavorID === this.standardCakeOrderJSON.cakeItemFlavorID
        );
        price += flavorElement.priceMod;
      }
      if (this.standardCakeOrderJSON.cakeItemFrostingID) {
        let frostingElement = this.$store.state.availableFrostingsBE.find(
          (element) =>
            element.frostingID === this.standardCakeOrderJSON.cakeItemFrostingID
        );
        price += frostingElement.priceMod;
      }
      if (this.standardCakeOrderJSON.cakeItemFillingID) {
        let fillingElement = this.$store.state.availableFillingsBE.find(
          (element) =>
            element.fillingID === this.standardCakeOrderJSON.cakeItemFillingID
        );
        price += fillingElement.priceMod;
      }
      if (this.standardCakeOrderJSON.cakeItemMessage) {
        //need to create a table in database for the message price
        price += this.$store.state.messagePrice.priceMod;
      }

      return price.toFixed(2);
    },
    dateMinimum() {
      let today = new Date();
      let tomorrow = new Date(today.getDate());
      tomorrow.setDate(tomorrow.getDate() + 1);
      return tomorrow;
    },
    cakeValidated() {
      if (
        this.standardCakeOrderJSON.cakeItemSizeID != 1 &&
        this.standardCakeOrderJSON.cakeItemFlavorID != 1 &&
        this.standardCakeOrderJSON.cakeItemStyleID != 1
      ) {
        return true;
      } else {
        return false;
      }
    },
    isSomethingSelected() {
      if (this.selected.cakeItemConfigID > 0) {
        return true;
      } else {
        return false;
      }
    },
    showCustomOptions(){
      if(this.selected.cakeItemConfigID === 1){
        return true;
      } else {
        return false;
      }
    }
  },
  methods: {
    getSelectedStandardCake() {
      let cakeID = this.$store.state.standardCakeIdOrder;
      let cakeIndex = this.$store.state.placeholderCakes.find(
        (cake) => cake.cake_id === cakeID
      );
      this.$store.state.selectedCake = this.$store.state.placeholderCakes[
        cakeIndex
      ];
    },
    selectCakeConfig() {
      this.standardCakeOrderJSON.cakeItemConfigID = this.selected.cakeItemConfigID;
      this.standardCakeOrderJSON.cakeItemFlavorID = this.selected.cakeItemFlavorID;
      this.standardCakeOrderJSON.cakeItemFrostingID = this.selected.cakeItemFrostingID;
      this.standardCakeOrderJSON.cakeItemFillingID = this.selected.cakeItemFillingID;
    },
    orderThisCake() {
      //jake's comment: let's make sure we understand when to clear the store versus not clear the store based on errors
      this.$store.commit("MAKE_CAKE_ITEM", this.standardCakeOrderJSON);
      this.$store.commit("SET_CAKE_ITEM_PRICE", this.itemPrice);
      this.$store.commit(
        "ADD_CAKEITEM_TO_ACTIVE_ORDER",
        this.$store.state.cakeItemToOrder
      );
      this.$store.commit("SET_ORDER_INFO", this.pickupInfo);
      CustomerService.sendOrderJSON(this.$store.state.currentActiveOrder)
        .then((response) => {
          if (response.status === 201) {
            this.$store.commit("CLEAR_ACTIVE_ORDER");
            this.$router.push("/cakes");
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "creating");
        });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " cake. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg = "Error " + verb + " cake. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " cake. Request could not be created.";
      }
    },
  },
};
</script>

<style>
.order-display {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
}

.order-form {
  background: rgba(255, 255, 255, 0.349);
  -webkit-backdrop-filter: blur(20px);
  backdrop-filter: blur(10px);
  padding: 17px;
  border-radius: 10px;
  border: 1px solid rgba(0, 0, 0, 0.61);
}

.cakedisplay {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  align-items: center;
  width: 300px;
  height: auto;
  border: 1px solid rgba(0, 0, 0, 0.356);
  border-radius: 10px;
  padding: 20px;
  margin-top: 20px;
  background: rgba(255, 255, 255, 0.5);
  /* -webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px); */
}

.order-form input,
select {
  margin: 6px;
}

.cakedisplay img {
  width: 70%;
  height: 120px;
}

.cakedisplay h3 {
  margin-top: -3%;
}

#calculated-price {
  color: rgb(182, 8, 43);
  font-weight: 700;
  font-size: 1.6rem;
}
</style>