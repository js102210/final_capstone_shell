<template>
    <div>
        <div class="cakedisplay">
            <!-- Need to figure out how to get the cake object's info from the standardCakeIdOrder.
            but that's for the morning!! -->
            </div>
  <form>
      <label for="standard cake selection">Select your standard cake:</label>
      <!-- the v-model sets the select to whatever the standardCakeIdForOrder is. If null, it's blank.-->
      <select name="standard cake" v-model="$store.state.standardCakeIdForOrder">
          <!-- sets text for options to all cake names in placeholderCakes. value is the cake's cake_id-->
          <option 
          v-for="cake in $store.state.placeholderCakes" 
          v-bind:key="cake.cake_id"
          v-bind:value="cake.cake_id"
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
export default {
    name: "standard order cake",
    methods: {
        /**yeah i'm trying to make this method get the cake out of the placeholdercakes array but it's not working
         * yet.
         * 
         * As ever, javascript... is weird.
         */
        getSelectedStandardCake(){
            let cakeID = this.$store.state.standardCakeIdOrder;
            let cakeIndex = this.$store.state.placeholderCakes.find((cake) => cake.cake_id === cakeID);
            this.$store.state.selectedCake = this.$store.state.placeholderCakes[cakeIndex];
        }
    }

}
</script>

<style>

</style>