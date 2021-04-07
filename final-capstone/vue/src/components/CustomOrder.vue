<template>
      <div>
        <br>
  <form>
      <label for="custom cake flavor">Select your flavor:</label>
      <select name="custom cake flavor" v-model="flavor_id">
        <option
          v-for="flavor in $store.state.availableFlavors"
          v-bind:key="flavor.flavor_id"
          v-bind:value="{ id: flavor.flavor_id}"
        >
          {{ flavor.flavor_name }}
        </option>
      </select><br>
         <label for="custom cake frosting">Select your frosting:</label>
      <select name="custom cake frosting" v-model="frosting_id">
        <option
          v-for="frosting in $store.state.availableFrostings"
          v-bind:key="frosting.frosting_id"
          v-bind:value="{ id: frosting.frosting_id}"
        >
          {{ frosting.frosting_name }}
        </option>
      </select><br>
               <label for="custom cake filling">Select your filling:</label>
      <select name="custom cake filling" v-model="filling_id">
        <option
          v-for="filling in $store.state.availableFillings"
          v-bind:key="filling.filling_id"
          v-bind:value="{ id: filling.filling_id}"
        >
          {{ filling.filling_name }}
        </option>
      </select><br>
            <label for="custom cake size">Select your size:</label>
      <select name="custom cake size" v-model="size_id">
        <option
          v-for="size in $store.state.availableCakeSizes"
          v-bind:key="size.size_id"
          v-bind:value="{ id: size.size_id}"
        >
          {{ size.size_description }}
        </option>
      </select>
      <br />
      <label for="custom cake style">Select your cake style:</label>
      <select name="custom cake style" v-model="style_id">
        <option
          v-for="style in $store.state.availableCakeStyles"
          v-bind:key="style.style_id"
          v-bind:value="{ id: style.style_id, name: style.style_name }"
        >
          {{ style.style_name }}
        </option>
      </select>
      <br />
            <label for="optional message">Put what message you want on your cake! (Optional: $1.50 extra)</label>
      <input name="message" type="text"><br>
      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local">
  </form>
  </div>
</template>

<script>
export default {
  data(){
    return{
      
        style_id: 1,
        size_id: 1,
        flavor_id: 1,
        frosting_id: 1,
        filling_id: 1,
        message: "",
        price: 0.0,
      } 

    },
    name: "custom order cake"
  }
  


</script>

<style>

</style>