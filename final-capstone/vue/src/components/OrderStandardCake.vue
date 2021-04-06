<template>
    <div>
        <div class="cakedisplay">
            </div>
  <form>
      <label for="standard cake selection">Select your standard cake:</label>
      <select name="standard cake" v-model="$store.state.standardCakeIdOrder">
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
        getSelectedStandardCake(){
            
        }
    }


}
</script>

<style>

</style>