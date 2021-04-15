<!--Employee: This component allows employees to make any given ingredient that would go in a cake (as well as sizes and styles of cakes)
 available or unavailable. Making an ingredient unavailable automatically makes any Cake Config that relies on it unavailable (the reverse is not true). -->
<template>
  <div class="change-options-display">
    <p>Edit Cake Options:</p>
    <!-- section to edit flavor availability -->
  <h2>Flavors:</h2>
    <table id="flavors">
      <thead>
        <tr>
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        
        <tr
          v-for="flavor in $store.state.allFlavorsBE"
          v-bind:key="flavor.flavorID"
          v-bind:class="{ unavailable: !flavor.available }"
        >
          <td>{{ flavor.flavorName }}</td>
          <td>{{ flavor.available ? "Available" : "Unavailable" }}</td>
          <button
            class="btnAvailableUnavailable"
            v-on:click="flipStatusFlavor(flavor.flavorID, flavor.available)"
          >
            {{ flavor.available ? "Make Unavailable" : "Make Available" }}
          </button>
        </tr>
      </tbody>
    </table>
<!-- table to edit frosting availability -->
    <h2>Frostings:</h2>
    <table id="frostings">
      <thead>
        <tr>
          
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr 
          v-for="frosting in $store.state.allFrostingsBE"
          v-bind:key="frosting.frostingID"
          v-bind:class="{ unavailable: !frosting.available }"
        >
      
          <td>{{ frosting.frostingName }}</td>
          <td>{{ frosting.available ? "Available" : "Unavailable" }}</td>
          <td>
            <button
              class="btnAvailableUnavailable"
              v-on:click="
                flipStatusFrosting(frosting.frostingID, frosting.available)
              "
            >
              {{ frosting.available ? "Make Unavailable" : "Make Available" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>
<!-- table to affect flilling availability -->
    <h2>Fillings:</h2>
    <table id="fillings">
      <thead>
        <tr>
          
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="filling in $store.state.allFillingsBE"
          v-bind:key="filling.fillingID"
          v-bind:class="{ unavailable: !filling.available }"
        >
      
          <td>{{ filling.fillingName }}</td>
          <td>{{ filling.available ? "Available" : "Unavailable" }}</td>
          <td>
            <button
              class="btnAvailableUnavailable"
              v-on:click="
                flipStatusFilling(filling.fillingID, filling.available)
              "
            >
              {{ filling.available ? "Make Unavailable" : "Make Available" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>
<!-- table to affect extra availability -->
      <h2>Extras:</h2>
    <table id="extras">
      <thead>
        <tr>
        
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="extra in $store.state.allExtrasBE"
          v-bind:key="extra.extraID"
          v-bind:class="{ unavailable: !extra.available }"
        >
        
          <td>{{ extra.extraName }}</td>
          <td>{{ extra.available ? "Available" : "Unavailable" }}</td>
          <td>
            <button
              class="btnAvailableUnavailable"
              v-on:click="flipStatusExtra(extra.extraID, extra.available)"
            >
              {{ extra.available ? "Make Unavailable" : "Make Available" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>
<!-- table to affect style availability -->
     <h2>Styles:</h2>
    <table id="styles">
      <thead>
        <tr>
         
          <th>Name</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="style in $store.state.allCakeStylesBE"
          v-bind:key="style.styleID"
          v-bind:class="{ unavailable: !style.available }"
        >
        
          <td>{{ style.styleName }}</td>
          <td>{{ style.available ? "Available" : "Unavailable" }}</td>
          <td>
            <button
              class="btnAvailableUnavailable"
              v-on:click="flipStatusStyle(style.styleID, style.available)"
            >
              {{ style.available ? "Make Unavailable" : "Make Available" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>
<!-- table to affect size availability -->
     <h2>Sizes:</h2>
    <table id="sizes">
      <thead>
        <tr>
         
          <th>Name</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="size in $store.state.allCakeSizesBE"
          v-bind:key="size.sizeID"
          v-bind:class="{ unavailable: !size.available }"
        >
        
          <td>{{ size.sizeName }}</td>
          <td>{{ size.sizeDescription }}</td>
          <td>{{ size.available ? "Available" : "Unavailable" }}</td>
          <td>
            <button
              class="btnAvailableUnavailable"
              v-on:click="flipStatusSize(size.sizeID, size.available)"
            >
              {{ size.available ? "Make Unavailable" : "Make Available" }}
            </button>
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
      //array to place configs that use a given cake component that the employee
      //is thinking about making unavailable. Used to drive the confirm message that displays
      //the name of the cake configs that will be made unavailable if the component is made unavailabe
      dependentConfigs: []
    };
  },
  methods: {
    /**
     * gets the cake configs that use the indicated flavor
     */
    getDependentConfigsFlavor(id){
      return this.$store.state.availableCakeConfigsBE.forEach(element => {
        if (element.cakeConfigFlavorID == id){
          this.dependentConfigs.push(element.cakeConfigName);
        }
      })

    },
    /**
     * gets the cake configs that use the indicated frosting
     */
    getDependentConfigsFrosting(id){
      return this.$store.state.availableCakeConfigsBE.forEach(element =>{
        if (element.cakeConfigFrostingID == id){
          this.dependentConfigs.push(element.cakeConfigName);
        }
      })
    },
    /**
     * gets the cake configs that use the indicated filling.
     */
    getDependentConfigsFilling(id){
      return this.$store.state.availableCakeConfigsBE.forEach(element => {
        if (element.cakeConfigFillingID == id){
          this.dependentConfigs.push(element.cakeConfigName);
        }
      })
    },

    /**
     * grabs all sizes from DB to populate the store.
     */
    prepareSizesArray() {
      EmployeeService.getAllSizes().then((response) => {
        this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
      });
    },
    /**
     * grabs all styles from DB to populate the store.
     */
    prepareStylesArray() {
      EmployeeService.getAllStyles().then((response) => {
        this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
      });
    },
    /**
     * grabs all flavors from DB to populate the store.
     */
    prepareFlavorsArray() {
      EmployeeService.getAllFlavors().then((response) => {
        this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
      });
    },
    /**
     * grabs all frostings from DB to populate the store.
     */
    prepareFrostingsArray() {
      EmployeeService.getAllFrostings().then((response) => {
        this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
      });
    },
    /**
     * grabs all frostings from DB to populate the store.
     */
    prepareFillingsArray() {
      EmployeeService.getAllFillings().then((response) => {
        this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
      });
    },
    /**
     * grabs all extras from DB to populate the store.
     */
    prepareExtrasArray() {
      EmployeeService.getAllExtras().then((response) => {
        this.$store.commit("SET_ALL_EXTRAS_ARRAY", response.data);
      });
    },

    //All of the flip functions need to behave in two different ways-- if the ingredient is being made unavailable, the user should be prompted
    // with a list of the Cake Configs that rely on it and will also be made unavailable. If the ingredient is being made available, the status can
    //simply be flipped. Hence, the functions are passed both the id and the current availability of the ingredient


    /**
     * flips the availability of the given Size. 
     */
    flipStatusSize(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Are you sure you want to make this size unavailable? Is that really what you want?"
            )
          ) {
            EmployeeService.flipSizeStatus(id).then((response) => {
              if (response.status == 202) {
                this.$store.commit("SET_SIZE_IS_AVAILABLE", id, response.data);
              }
              this.prepareSizesArray();
            });
          }
          break;
        case false:
          EmployeeService.flipSizeStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit("SET_SIZE_IS_AVAILABLE", id, response.data);
            }
            this.prepareSizesArray();
          });
          break;
        default:
          alert("Something went wrong");
      }
    },
    /**
     * flips the availability of the indicated style.
     */
    flipStatusStyle(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm("Are you sure you want to make this style unavailable? Is that really what you want?")
          ) {
            EmployeeService.flipStyleStatus(id).then((response) => {
              if (response.status == 202) {
                this.$store.commit("SET_STYLE_IS_AVAILABLE", id, response.data);
              }
              this.prepareStylesArray();
            });
          }
          break;
        case false:
          EmployeeService.flipStyleStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit("SET_STYLE_IS_AVAILABLE", id, response.data);
            }
            this.prepareStylesArray();
          });
          break;
        default:
          alert("Something went wrong");
      }
    },
    /**
     * flips the availability of the given flavor. If making the flavor unavailable,
     * confirms that you're willing to make the associated cake configs unavailable. 
     * Confirming makes the flavor unavailable AND associated cake configs.
     * Reloads to display new availability.
     */
    flipStatusFlavor(id, availableBoolean) {
      this.getDependentConfigsFlavor(id);
      let configString = ''
      this.dependentConfigs.forEach((configName) => configString += configName + '\n')
      this.dependentConfigs = [];
      switch (availableBoolean) {
        case true:
        
          if (
            confirm(
              "Making this flavor unavailable forsakes the following cake types, making them unavailable:\n"  + configString
              + "Can you live with that?: " 
              
            )
          ) {
            EmployeeService.flipFlavorStatus(id).then((response) => {
              if (response.status == 202) {
                this.$store.commit(
                  "SET_FLAVOR_IS_AVAILABLE",
                  id,
                  response.data
                );
              }
              this.prepareFlavorsArray();
            });
            location.reload();
          }
          break;
        case false:
          EmployeeService.flipFlavorStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit("SET_FLAVOR_IS_AVAILABLE", id, response.data);
            }
            this.prepareFlavorsArray();
          });
          break;
        default:
          alert("something went wrong");
      }
    },
    /**
     * flips the availability of the given frosting. If making the frosting unavailable,
     * confirms that you're willing to make the associated cake configs unavailable. 
     * Confirming makes the frosting unavailable AND associated cake configs.
     * Reloads to display new availability.
     */
    flipStatusFrosting(id, availableBoolean) {
      this.getDependentConfigsFrosting(id);
      let configString = ''
      this.dependentConfigs.forEach((configName) => configString += configName + '\n')
      this.dependentConfigs = [];
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Making this frosting unavailable forsakes the following cake types, making them unavailable: \n" + 
              configString + "Can you live with that?:"
            )
          ) {
            EmployeeService.flipFrostingStatus(id)
            location.reload();
          }
          break;
        case false:
          EmployeeService.flipFrostingStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit(
                "SET_FROSTING_IS_AVAILABLE",
                id,
                response.data
              );
            }
            this.prepareFrostingsArray();
          });
          break;
        default:
          alert("Something went wrong");
      }
    },
    /**
     * flips the availability of the given filling. If making the filling unavailable,
     * confirms that you're willing to make the associated cake configs unavailable. 
     * Confirming makes the filling unavailable AND associated cake configs.
     * Reloads to display new availability.
     */
    flipStatusFilling(id, availableBoolean) {
      this.getDependentConfigsFilling(id);
      let configString = ''
      this.dependentConfigs.forEach((configName)=> configString += configName + '\n')
      this.dependentConfigs = [];
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Making this filling unavailable forsakes the following cake types, making them unavailable:\n" + 
              configString + "Can you live with that?:"
            )
          ) {
            EmployeeService.flipFillingStatus(id).then((response) => {
              if (response.status == 202) {
                this.$store.commit(
                  "SET_FILLING_IS_AVAILABLE",
                  id,
                  response.data
                );
              }
              this.prepareFillingsArray();
            });
            location.reload();
          }
          break;
        case false:
          EmployeeService.flipFillingStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit("SET_FILLING_IS_AVAILABLE", id, response.data);
            }
            this.prepareFillingsArray();
          });
          break;
        default:
          alert("Something went wrong");
      }
    },
    /**
     * flips the availability on a given extra.
     */
    flipStatusExtra(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Making this extra unavailable means no one can have it. I know I cannot stop you, but I can at least make you aware."
            )
          ) {
            EmployeeService.flipExtraStatus(id).then((response) => {
              if (response.status == 202) {
                this.$store.commit("SET_EXTRA_IS_AVAILABLE", id, response.data);
              }
              this.prepareExtrasArray();
            });
          }
          break;
        case false:
          EmployeeService.flipExtraStatus(id).then((response) => {
            if (response.status == 202) {
              this.$store.commit("SET_EXTRA_IS_AVAILABLE", id, response.data);
            }
            this.prepareExtrasArray();
          });
          break;
        default:
          alert("Something went wrong");
      }
    },

/**
 * meta-method to populate all cake components in store.
 */
    prepareThePreparations() {
      this.prepareSizesArray();
      this.prepareStylesArray();
      this.prepareFlavorsArray();
      this.prepareFrostingsArray();
      this.prepareFillingsArray();
      this.prepareExtrasArray();
    },
  },

  /**
   * populates all cake component and config arrays in store.
   */
  created() {
    this.prepareThePreparations();
    EmployeeService.getAvailableConfigs().then((response) => {
     this.$store.commit("SET_AVAILABLE_CAKE_CONFIG_ARRAY", response.data);
    });
  },
};
</script>

<style>
table{
  table-layout: fixed;
}
th {
  text-align: left;
  
 
}
td{
  
  padding: 10px;
  vertical-align: text-top;
  width: 200px;
}

</style>