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
       <label for="Extra price mod">Price Modifier: $</label>
      <input type="number" name="Extra Price mod" step="0.01" placeholder="0.00"
        v-model="newExtra.priceMod" min="0"/>
      <button type="submit" :disabled="!newExtra.extraName">Create New Extra</button>
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
       <label for="Size price mod">Price Modifier: $</label>
      <input type="number" name="Size Price mod" step="0.01" placeholder="0.00"
        v-model="newSize.priceMod" min="0"/>
      <button type="submit" :disabled="!createSizeFormValidated">Create New Size</button>
    </form><br />

</div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
export default {
  data() {
    return {
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
    createSizeFormValidated() {
       if (this.newSize.sizeName != "" && this.newSize.sizeDescription !=""){
        return true;
      } else {
        return false;
    }
    }
  },
  methods: {
  createNewFlavor(){
    EmployeeService.createFlavor(this.newFlavor).then((response) => {
      if(response.status == 200){
        alert(this.newFlavor.flavorName + ' flavor has been added! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllFlavors().then((response) => {
        this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
    })
      }
    })

  },
   createNewFrosting(){
         EmployeeService.createFrosting(this.newFrosting).then((response) => {
      if(response.status == 200){
        alert(this.newFrosting.frostingName + ' frosting has been added! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllFrostings().then((response) => {
        this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    })
      }
    })

  },
   createNewFilling(){
         EmployeeService.createFilling(this.newFilling).then((response) => {
      if(response.status == 200){
        alert(this.newFilling.fillingName + ' filling has been added! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllFillings().then((response) => {
        this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
    })
      }
    })

  },
   createNewExtra(){
         EmployeeService.createExtra(this.newExtra).then((response) => {
      if(response.status == 200){
        alert(this.newExtra.extraName + '  has been added as an Extra! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllExtras().then((response) => {
        this.$store.commit("SET_ALL_EXTRAS_ARRAY", response.data);
    })
      }
    })

  },
   createNewStyle(){
         EmployeeService.createStyle(this.newStyle).then((response) => {
      if(response.status == 200){
        alert(this.newStyle.styleName + ' style has been added! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllStyles().then((response) => {
        this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
    })
      }
    })

  },
   createNewSize(){

    EmployeeService.createSize(this.newFlavor).then((response) => {
      if(response.status == 200){
        alert(this.newSize.sizeName + ' size has been added! May it give you the strength to destroy your enemies!');
        EmployeeService.getAllSizes().then((response) => {
        this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
    })
      }
    })
  },
  }
}
</script>

<style>

</style>