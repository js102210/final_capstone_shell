<template>
<div class="change-options-display">
    <p>Edit Cake Options:</p>

         <table id="flavors">
      <thead>
        <tr>
          <th>Flavors:</th>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="flavor in $store.state.allFlavorsBE" v-bind:key="flavor.flavorID"
            v-bind:class="{ unavailable: !flavor.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="flavor.flavorID"
              v-bind:value="flavor.flavorID" v-model="selectedFlavorID" />
          </td>
          <td>{{flavor.flavorName}}</td>
          <td>{{flavor.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(flavor.flavorID)">
              {{ flavor.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

      <table id="frostings">
      <thead>
        <tr>
          <th>Frostings:</th>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="frosting in $store.state.allFrostingsBE" v-bind:key="frosting.frostingID"
            v-bind:class="{ unavailable: !frosting.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="frosting.frostingID"
              v-bind:value="frosting.frostingID" v-model="selectedFrostingID" />
          </td>
          <td>{{frosting.frostingName}}</td>
          <td>{{frosting.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(frosting.frostingID)">
              {{ frosting.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

      <table id="fillings">
      <thead>
        <tr>
          <th>Fillings:</th>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="filling in $store.state.allFillingsBE" v-bind:key="filling.fillingID"
            v-bind:class="{ unavailable: !filling.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="filling.fillingID"
              v-bind:value="filling.fillingID" v-model="selectedFillingID" />
          </td>
          <td>{{filling.fillingName}}</td>
          <td>{{filling.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(filling.fillingID)">
              {{ filling.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

          <table id="extras">
      <thead>
        <tr>
          <th>Extras:</th>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="extra in $store.state.allExtrasBE" v-bind:key="extra.extraID"
            v-bind:class="{ unavailable: !extra.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="extra.extraID"
              v-bind:value="extra.extraID" v-model="selectedExtraID" />
          </td>
          <td>{{extra.extraName}}</td>
          <td>{{extra.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(extra.extraID)">
              {{ extra.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

      <table id="flavors">
      <thead>
        <tr>
          <th>Styles:</th>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="style in $store.state.allCakeStylesBE" v-bind:key="style.styleID"
            v-bind:class="{ unavailable: !style.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="style.styleID"
              v-bind:value="style.styleID" v-model="selectedStyleID" />
          </td>
          <td>{{style.styleName}}</td>
          <td>{{style.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(style.styleID)">
              {{ style.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

      <table id="sizes">
      <thead>
        <tr>
          <th>Sizes:</th>
          <th>Name</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="size in $store.state.allCakeSizesBE" v-bind:key="size.sizeID"
            v-bind:class="{ unavailable: !size.available}" >
          <td>
            <input type="radio" name="flip status" v-bind:id="size.sizeID"
              v-bind:value="size.sizeID" v-model="selectedSizeID" />
          </td>
          <td>{{size.sizeName}}</td>
          <td>{{size.sizeDescription}}</td>
          <td>{{size.available? "Available": "Unavailable"}}</td>
          <td>
            <button class="btnAvailableUnavailable" v-on:click="flipStatus(size.sizeID)">
              {{ size.available ? "Make Unavailable" : "Make Available" }} </button>
          </td> 
        </tr>
      </tbody>
    </table>

</div>
  
</template>

<script>
import EmployeeService from "../services/EmployeeService.js";

export default {
  name: "emp-change-options",

  data() {
    return {
      selectedSizeID: {},
      selectedFlavorID: {},
      selectedStyleID: {},
      selectedFrostingID: {},
      selectedFillingID: {},
      selectedExtraID: {}
    }
  },
  methods: {

    prepareSizesArray(){
    EmployeeService.getAllSizes().then((response) => {
      this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
    });
    },
    prepareStylesArray(){
      EmployeeService.getAllStyles().then((response) => {
      this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
    });
    },
    prepareFlavorsArray(){
    EmployeeService.getAllFlavors().then((response) => {
      this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
    });
    },
    prepareFrostingsArray(){
    EmployeeService.getAllFrostings().then((response) => {
      this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    });
    },
    prepareFillingsArray(){
          EmployeeService.getAllFillings().then((response) => {
      this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
    });
    },
    prepareExtrasArray(){
           EmployeeService.getAllExtras().then((response) => {
      this.$store.commit("SET_ALL_EXTRAS_ARRAY", response.data);
    });
    },
    flipStatusSize(sizeID){
      EmployeeService.flipSizeStatus(sizeID).then((response) => {
        if (response.status == 202){
          return;
        }
      })
    },

    prepareThePreparations() {
      this.prepareSizesArray();
      this.prepareStylesArray();
      this.prepareFlavorsArray();
      this.prepareFrostingsArray();
      this.prepareFillingsArray();
      this.prepareExtrasArray();
    }

  },

  created() {
  this.prepareThePreparations();
  },  

}
</script>

<style>
.change-options-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>