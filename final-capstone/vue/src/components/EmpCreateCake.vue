<!--Employee: Allows employees to create new types of cakes (combinations of flavor, frosting, and filling) that will populate the 
screen customers use to order. -->
<template>
  <div class="create-cake-display">
    <p>Create New Standard Cake:</p>
    <p>Required Fields (*) must be completed</p>
    <form class="create-cake" @submit="createNewCakeConfig">
      <label for="cakeName">Cake Name: *</label>
      <input type="text" name="cakeName" v-model="newCake.cakeConfigName" /><br />

      <label for="flavor">Flavor: *</label>
      <select name="flavor" v-model="newCake.cakeConfigFlavorID">
        <option
          v-for="flavor in $store.state.allFlavorsBE"
          v-bind:key="flavor.flavorID"
          v-bind:value="flavor.flavorID"
        >
          {{ flavor.flavorName }}
        </option>
      </select><br />

      <label for="frosting">Frosting:</label>
      <select name="frosting" v-model="newCake.cakeConfigFrostingID">
        <option
          v-for="frosting in $store.state.allFrostingsBE"
          v-bind:key="frosting.frostingID"
          v-bind:value="frosting.frostingID"
        >
          {{ frosting.frostingName }}
        </option>
      </select><br />

      <label for="filling">Filling:</label>
      <select name="filling" v-model="newCake.cakeConfigFillingID">
        <option
          v-for="filling in $store.state.allFillingsBE"
          v-bind:key="filling.fillingID"
          v-bind:value="filling.fillingID"
        >
          {{ filling.fillingName }}
        </option>
      </select><br />

      <label for="imageURL">Image URL: *</label>
      <input type="url" name="imageURL" v-model="newCake.cakeConfigUrl" /><br />

      <label for="description">Description: *</label>
      <input
        type="text"
        name="description"
        v-model="newCake.cakeConfigDescription"
      /><br />

      <!-- disable submit button if any field is blank -->
      <button type="submit" :disabled="!createCakeFormValidated">
        Create New Cake
      </button>
      
    </form>
  </div>
</template>

<script>
import EmployeeService from "../services/EmployeeService.js";
export default {
  name: "emp-create-cake",
  data() {
    return {
      //the model that gets filled out with the new cake config's data via
      //the magic of v-model. when the cake config is ready to send off,
      //this feeds the store which then sends to server.
      newCake: {
        cakeConfigName: "",
        cakeConfigFlavorID: 1,
        cakeConfigFrostingID: 1,
        cakeConfigFillingID: 1,
        cakeConfigUrl: "",
        cakeConfigDescription: "",
      },
    };
  },
  /**
   * gets all of the cake components and sets into store.
   */
  created() {

    EmployeeService.getAllSizes().then((response) => {
      this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
    });
    
    EmployeeService.getAllStyles().then((response) => {
      this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
    });

    EmployeeService.getAllFlavors().then((response) => {
      this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
    });

    EmployeeService.getAllFrostings().then((response) => {
      this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
    });

    EmployeeService.getAllFillings().then((response) => {
      this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
    });

    
  },
  computed: {
    /**
     * returns boolean value to indicate if the created cake config is
     * valid. Valid cake configs must have a name, a chosen flavor,
     * a URL for the picture, and a description. If this method returns false,
     * the button to create the cake config is disabled.
     */
    createCakeFormValidated() {
      if (
        this.newCake.cakeConfigName != "" &&
        this.newCake.flavorID != 1 &&
        this.newCake.cakeConfigURL != "" &&
        this.newCake.cakeConfigDescription != ""
      ) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    /**
     * method that takes the new cake config this component has built and sends it to the store,
     * then sends it from the store to the database. 
     */
    createNewCakeConfig() {
      // this.$store.commit("SET_CAKE_CONFIG_JSON", this.newCake);
      EmployeeService.createCakeConfig(this.newCake)
        .then((response) => {
          if (response.status === 201) {
            // this.$store.commit("CLEAR_CAKE_CONFIG_JSON");
            alert(this.newCake.cakeConfigName + " can now be ordered. I hope you're ready for that; please check to see that we have the necessary components available!")
            this.clearNewCake();
            //reloading to display the new Config in the list
            location.reload(); 
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "creating");
        });
    },
    /**
     * blanks out the newCake object so it can be reused.
     */
    clearNewCake() {
      this.newCake = {
        cakeConfigName: "",
        cakeConfigFlavorID: 1,
        cakeConfigFrostingID: 1,
        cakeConfigFillingID: 1,
        cakeConfigUrl: "",
        cakeConfigDescription: "",
      };
    },
    /**
     * our multipurpose error handling method.
     */
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " cake configuration. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " cake configuration. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " +
          verb +
          " cake configuration. Request could not be created.";
      }
    },
  },
};
</script>

<style>

</style>