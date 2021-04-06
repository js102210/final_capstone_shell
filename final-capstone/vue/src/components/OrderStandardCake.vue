<template>
    <div class="order-display">
        <div class="cakedisplay">
        <h1>{{selected.name}}</h1>
         <img v-bind:src=selected.image /> <br>
        <h2>{{selected.description}}</h2>
            </div>
  <form>
      <label for="standard cake selection">Select your standard cake:</label>
      <!-- the v-model sets the select to whatever the standardCakeIdForOrder is. If null, it's blank.-->
      <select name="standard cake" v-model="selected">
          <!-- sets text for options to all cake names in placeholderCakes. value is the cake's cake_id-->
          <option 
          v-for="cake in $store.state.placeholderCakes" 
          v-bind:key="cake.cake_id"
          v-bind:value="{id: cake.cake_id, name: cake.name, description: cake.description, image: cake.image_url}"
          >
              {{cake.name}}
              </option>
          </select><br>
      <label for="optional message">Put what message you want on your cake! (Optional: $1.50 extra)</label>
      <input name="message" type="text"><br>
      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local">

  </form>
  </div>
</template>

<script>
// import CakeOrderDisplay from './CakeOrderDisplay.vue';
export default {
    data() {
       return {
           selected: ''
       };
    },
//   components: { 
//     //   CakeOrderDisplay
//       },
    name: "standard order cake",
    methods: {
        
        getSelectedStandardCake(){
            let cakeID = this.$store.state.standardCakeIdOrder;
            let cakeIndex = this.$store.state.placeholderCakes.find((cake) => cake.cake_id === cakeID);
            this.$store.state.selectedCake = this.$store.state.placeholderCakes[cakeIndex];
        }
    }

}
</script>

<style>
.order-display {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
}

.cakedisplay {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    align-items: center;
    width: 150px;
    border: 2px solid black;
    border-radius: 10px;
    padding: 20px;
    margin-top: 20px;
}

.cakedisplay img {
    width: 100%;
}

</style>