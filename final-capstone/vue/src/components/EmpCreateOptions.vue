<!--Employee: allows employees to create new aspects of a cake to populate the various menus in the site. Each item has a priceMod attribute
which represents how much money that ingredient adds to the price of cakes it's in. -->
<template>
  <div class="create-options-display">
    <p>Create New Options:</p>
    <p>Required Fields (*) must be completed</p>

    <form @submit="createNewFlavor">
      <label for="Flavor">Flavor: *</label>
      <input type="text" name="Flavor" v-model="newFlavor.flavorName" />
      <label for="Flavor price mod">Price Modifier: $</label>
      <input type="number" name="Flavor Price mod" step="0.01" placeholder="0.00"
        v-model="newFlavor.priceMod" min="0"/>
      <button type="submit" :disabled="!newFlavor.flavorName">Create New Flavor</button>
    </form><br />

    <form @submit="createNewFrosting">
      <label for="Frosting">Frosting: *</label>
      <input type="text" name="Frosting" v-model="newFrosting.frostingName" />
      <label for="Frosting price mod">Price Modifier: $</label>
      <input type="number" name="Frosting Price mod" step="0.01" placeholder="0.00"
        v-model="newFrosting.priceMod" min="0"/>
      <button type="submit" :disabled="!newFrosting.frostingName">Create New Frosting</button>
    </form><br />

    <form @submit="createNewFilling">
      <label for="Filling">Filling: *</label>
      <input type="text" name="Filling" v-model="newFilling.fillingName" />
       <label for="Filling price mod">Price Modifier: $</label>
      <input type="number" name="Filling Price mod" step="0.01" placeholder="0.00"
        v-model="newFilling.priceMod" min="0"/>
      <button type="submit" :disabled="!newFilling.fillingName">Create New Filling</button>
    </form><br />

    <form @submit="createNewExtra">
      <label for="Extra">Extra: *</label>
      <input type="text" name="Extra" v-model="newExtra.extraName" />
       <label for="Extra price mod">Price Modifier: * $</label>
      <input type="number" name="Extra Price mod" step="0.01" placeholder="0.00"
        v-model="newExtra.priceMod" min="0.5"/>
      <button type="submit" :disabled="!createExtraFormValidated">Create New Extra</button>
    </form><br />

     <form @submit="createNewStyle">
      <label for="Style">Style: *</label>
      <input type="text" name="Style" v-model="newStyle.styleName" />
       <label for="Style price mod">Price Modifier: $</label>
      <input type="number" name="Style Price mod" step="0.01" placeholder="0.00"
        v-model="newStyle.priceMod" min="0"/>
      <button type="submit" :disabled="!newStyle.styleName">Create New Style</button>
    </form><br />

     <form @submit="createNewSize">
      <label for="Size">Size: *</label>
      <input type="text" name="Size" v-model="newSize.sizeName" />
       <label for="Size description">Description: *</label>
      <input type="text" name="Size description" v-model="newSize.sizeDescription" />
       <label for="Size price mod">Price Modifier: * $</label>
      <input type="number" name="Size Price mod" step="0.01" placeholder="0.00"
        v-model="newSize.priceMod" min="1"/>
      <button type="submit" :disabled="!createSizeFormValidated">Create New Size</button>
    </form><br />

</div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
export default {
  data() {
    return {
      //these are the objects that hold the info for new components
      //before they are sent to the database.
      newFlavor: {
        flavorName: "",
        isAvailable: true,
        priceMod: 0.0,
      },
      newFrosting: {
        frostingName: "",
        isAvailable: true,
        priceMod: 0.0,
      },
      newFilling: {
        fillingName: "",
        isAvailable: true,
        priceMod: 0.0,
      },
      newExtra  : {
        extraName: "",
        isAvailable: true,
        priceMod: 0.0,
      },
      newStyle: {
        styleName: "",
        isAvailable: true,
        priceMod: 0.0,
      },
      newSize: {
        sizeName: "",
        sizeDescription: "",
        isAvailable: true,
        priceMod: 0.0,
      }
    }
  },
  computed:{
    /**
     * makes sure the new size has all needed information. returns a boolean.
     * if it's false, the button to create the size won't work.
     */
    createSizeFormValidated() {
       if (this.newSize.sizeName != "" && this.newSize.sizeDescription !=""
        && this.newSize.priceMod !=0.0){
        return true;
      } else {
        return false;
    }
    },
    /**
     * makes sure the new extra has all needed information. returns a boolean.
     * if it's false, the button to create the size won't work.
     */
    createExtraFormValidated() {
       if (this.newExtra.extraName != "" && this.newExtra.priceMod !=0.0){
        return true;
      } else {
        return false;
    }
    }
  },
  methods: {
    /**
     * sends the new flavor to the database.
     */
  createNewFlavor(){
    EmployeeService.createFlavor(this.newFlavor).then((response) => {
      if(response.status == 200){
        alert(this.newFlavor.flavorName + ' flavor has been added! Thanks for "beefing up" our available options!');
      }
    })
  },
  /**
   * sends the new frosting to the database.
   */
   createNewFrosting(){
         EmployeeService.createFrosting(this.newFrosting).then((response) => {
      if(response.status == 200){
        alert(this.newFrosting.frostingName + ' frosting has been added! Thanks for "beefing up" our available options!');
      }
    })
  },
  /**
   * sends the new filling to the database.
   */
   createNewFilling(){
         EmployeeService.createFilling(this.newFilling).then((response) => {
      if(response.status == 200){
        alert(this.newFilling.fillingName + ' filling has been added! Thanks for "beefing up" our available options!');

      }
    })
  },
  /**
   * sends the new extra to the database.
   */
  createNewExtra(){
         EmployeeService.createExtra(this.newExtra).then((response) => {
      if(response.status == 200){
        alert(this.newExtra.extraName + '  has been added as an extra! Thanks for "beefing up" our available options!');
      }
    })
  },
  /**
   * sends the new style to the database.
   */
   createNewStyle(){
         EmployeeService.createStyle(this.newStyle).then((response) => {
      if(response.status == 200){
       alert(this.newStyle.styleName + ' style has been added! Thanks for "beefing up" our available options!');
      }
    })
  },
  /**
   * sends the new size to the database.
   */
   createNewSize(){
    EmployeeService.createSize(this.newSize).then((response) => {
      if(response.status == 200){
        alert(this.newSize.sizeName + ' size has been added! Thanks for "beefing up" our available options!');

      }
    })
  },
  }
}
</script>

<style>

</style>