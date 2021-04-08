<template>
  <div class="order-display">
    <div class="cakedisplay">
      <h3>{{ standardCakeOrderJSON.cakeItemConfigName }}</h3>
      <img v-bind:src="standardCakeOrderJSON.cakeItemConfigURL" /> <br />
      <p>{{ standardCakeOrderJSON.cakeItemConfigDescription }}</p>
    </div>

    <form class="order-form" @submit.prevent="orderThisCake">
      <p>Your Cake:</p>
      <label for="standard cake selection">Select your cake:</label>
      <!-- the v-model sets the select to whatever the standardCakeIdForOrder is. If null, it's blank.-->
      <select name="standard cake" v-model="standardCakeOrderJSON" >
        <!-- sets text for options to all cake names in standardCakeConfigs. value is the cake's cake_id-->
        <option
          v-for="config in $store.state.standardCakeConfigsBE"
          v-bind:key="config.cake_config_id"
          v-bind:value="{
            cakeItemConfigName: config.config_name,
            cakeItemConfigId: config.cake_config_id,
            cakeItemFrostingID: config.frosting_id,
            cakeItemFillingID: config.filling_id,
            cakeItemFlavorID: config.flavor_id,
            cakeItemConfigURL: config.cake_config_img_url,
            cakeItemConfigDescription: config.cake_config_description

          }"
        >
          {{ config.cake_config_name }}
        </option>
        <!-- cake_config_id: cake.cake_config_id, /these went inside value above/ 
            name: cake.cake_config_name,
            description: cake.cake_config_description,
            image: cake.cake_config_img_url,
            flavor: cake.flavor_id,
            filling: cake.filling_id,
            frosting: cake.frosting_id --></select
      ><br />
      <label for="standard cake size">Select your size:</label>
      <select name="standard cake size" v-model="standardCakeOrderJSON.cakeItemSizeId">
        <option
          v-for="size in $store.state.availableCakeSizesBE"
          v-bind:key="size.size_id"
          v-bind:value="size.size_id"
          
        >
          {{ size.size_description }}
        </option>
      </select>
      <br />
      <label for="standard cake style">Select your cake style:</label>
      <select name="standard cake style" v-model="standardCakeOrderJSON.cakeItemStyleId">
        <option
          v-for="style in $store.state.availableCakeStylesBE"
          v-bind:key="style.style_id"
          v-bind:value="style.style_id"
        >
          {{ style.style_name }}
        </option>
      </select>
      <br />
      <section class="custom cake options" v-show="standardCakeOrderJSON.cakeItemConfigId === 1"> 
        <label for="custom cake flavor">Select your flavor:</label>
        <select name="custom cake flavor" v-model="standardCakeOrderJSON.cakeItemFlavorId">
          <option
            v-for="flavor in $store.state.availableCakeFlavors"
            v-bind:key="flavor"
            v-bind:value="flavor"
          >
            {{ flavor}}
          </option></select
        ><br />
        <label for="custom cake frosting">Select your frosting:</label>
        <select name="custom cake frosting" v-model="standardCakeOrderJSON.cakeItemFrostingId">
          <option
            v-for="frosting in $store.state.availableFrostings"
            v-bind:key="frosting"
            v-bind:value="frosting"
          >
            {{ frosting }}
          </option></select
        ><br />
        <label for="custom cake filling">Select your filling:</label>
        <select name="custom cake filling" v-model="standardCakeOrderJSON.cakeItemFillingId">
          <option
            v-for="filling in $store.state.availableFillings"
            v-bind:key="filling"
            v-bind:value="filling"
          >
            {{ filling }}
          </option></select
        ><br />
   </section> 
      <label for="optional message"
        >Put what message you want on your cake! (Optional: $1.50 extra)</label
      >
      <input name="message" type="text" placeholder="Optional Message" v-model="standardCakeOrderJSON.cakeItemMessage"/><br />

      <p>Your Info:</p>

      <label for="customerName">Please enter your name:</label>
      <input name="customerName" type="text" placeholder="Your Name" /><br />
      <label for="customerPhoneNumber">Please enter your phone number:</label>
      <input name="customerPhoneNumber" type="tel" /><br />

      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local" /><br /><br />

      <button type="submit">Add Cake to Order!</button>
    </form>
  </div>
</template>

<script>
// import CakeOrderDisplay from './CakeOrderDisplay.vue';
export default {
  data() {
    return {
      selected: {
        

      },
      standardCakeOrderJSON: {
          cakeItemStyleId : null,
          cakeItemSizeId : null,
          cakeItemFlavorId : null,
          cakeItemFrostingID : null,
          cakeItemFillingID : null,
          cakeItemMessage : null,
          cakeItemPrice : null,
          cakeItemConfigId : null,
          cakeItemConfigURL : null,
          cakeItemConfigName: null,
          cakeItemConfigDescription: null
      },
    };
  },
  //   components: {
  //     //   CakeOrderDisplay
  //       },
  name: "order-standard-cake",
  methods: {
    getSelectedStandardCake() {
      let cakeID = this.$store.state.standardCakeIdOrder;
      let cakeIndex = this.$store.state.placeholderCakes.find(
        (cake) => cake.cake_id === cakeID
      );
      this.$store.state.selectedCake = this.$store.state.placeholderCakes[
        cakeIndex
      ];
    }
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

.cakedisplay {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  align-items: center;
  width: 300px;
  height: 200px;
  border: 2px solid black;
  border-radius: 10px;
  padding: 20px;
  margin-top: 20px;
}

.order-form input,
select {
  margin: 6px;
}

.cakedisplay img {
  width: 70%;
  height: 45%;
}

.cakedisplay h3 {
  margin-top: -3%;
}

.cakedisplay p {
  font-family: "Quicksand";
}
</style>