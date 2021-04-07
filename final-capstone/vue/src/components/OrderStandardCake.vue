<template>
  <div class="order-display">
    <div class="cakedisplay">
      <h1>{{ selected.name }}</h1>
      <img v-bind:src="selected.image" /> <br />
      <h2>{{ selected.description }}</h2>
    </div>

    <form>
      <label for="standard cake selection">Select your standard cake:</label>
      <!-- the v-model sets the select to whatever the standardCakeIdForOrder is. If null, it's blank.-->
      <select name="standard cake" v-model="selected">
        <!-- sets text for options to all cake names in standardCakeConfigs. value is the cake's cake_id-->
        <option
          v-for="cake in $store.state.standardCakeConfigs"
          v-bind:key="cake.cake_config_id"
          v-bind:value="{
            cake_config_id: cake.cake_config_id,
            name: cake.cake_config_name,
            description: cake.cake_config_description,
            image: cake.cake_config_img_url,
            flavor: cake.flavor_id,
            filling: cake.filling_id,
            frosting: cake.frosting_id
          }"
        >
          {{ cake.cake_config_name }}
        </option></select
      ><br />
      <label for="standard cake size">Select your size:</label>
      <select name="standard cake size">
        <option
          v-for="size in $store.state.availableCakeSizes"
          v-bind:key="size.size_id"
          v-bind:value="size.size_id"
        >
          {{ size.size_description }}
        </option>
      </select>
      <br />
      <label for="standard cake style">Select your cake style:</label>
      <select name="standard cake style">
        <option
          v-for="style in $store.state.availableCakeStyles"
          v-bind:key="style.style_id"
          v-bind:value="style.style_id"
        >
          {{ style.style_name }}
        </option>
      </select>
      <br />
      <label for="optional message"
        >Put what message you want on your cake! (Optional: $1.50 extra)</label
      >
      <input name="message" type="text" /><br />
      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local" />
    </form>
  </div>
</template>

<script>
// import CakeOrderDisplay from './CakeOrderDisplay.vue';
export default {
  data() {
    return {
      selected: {},
      standardCakeOrderJSon:  {
        style_id: 1,
        size_id: 1,
        flavor_id: 1,
        frosting_id: 1,
        filling_id: 1,
        message: "",
        price: 0.0,
        cake_config_id: 1,
      } 
    };
  },
  //   components: {
  //     //   CakeOrderDisplay
  //       },
  name: "standard order cake",
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
    orderThisCake(){
         
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
  justify-content: space-evenly;
  flex-wrap: wrap;
  align-items: center;
  width: 400px;
  border: 2px solid black;
  border-radius: 10px;
  padding: 20px;
  margin-top: 20px;
}

.cakedisplay img {
  width: 100%;
}
</style>