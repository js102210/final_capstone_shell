<template>
      <div>
        <br>
  <form>
      <label for="custom cake flavor">Select your flavor:</label>
      <select name="custom cake flavor" v-model="flavor">
        <option
          v-for="flavor in $store.state.availableCakeFlavors"
          v-bind:key="flavor"
          v-bind:value="flavor"
        >
          {{ flavor }}
        </option>
      </select><br>
         <label for="custom cake frosting">Select your frosting:</label>
      <select name="custom cake frosting" v-model="frosting">
        <option
          v-for="frosting in $store.state.availableFrostings"
          v-bind:key="frosting"
          v-bind:value="frosting"
        >
          {{ frosting }}
        </option>
      </select><br>
               <label for="custom cake filling">Select your filling:</label>
      <select name="custom cake filling" v-model="fillingID">
        <option
          v-for="filling in $store.state.availableFillings"
          v-bind:key="filling.fillingID"
          v-bind:value="{ id: filling.fillingID}"
        >
          {{ filling }}
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
      <select name="custom cake style" v-model="styleID">
        <option
          v-for="style in $store.state.availableCakeStyles"
          v-bind:key="style.styleID"
          v-bind:value="{ id: style.styleID, name: style.style_name }"
        >
          {{ style.style_name }}
        </option>
      </select>
      <br />
            <label for="optional message">Put what message you want on your cake! (Optional: $1.50 extra)</label>
      <input name="message" type="text" v-model="message"><br>
      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local" v-model="pickup_date">
  </form>
  </div>
</template>

<script>
export default {
  data(){
    return{      
        styleID: 1,
        size_id: 1,
        flavor: 1,
        frosting: 1,
        filling: 1,
        message: "",
        price: 0.0,
        pickup_date: '',
        order_date: ''
      } 

    },
    name: "custom order cake"
  }
  


</script>

<style>

</style>