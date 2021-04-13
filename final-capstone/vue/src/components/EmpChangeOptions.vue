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
      selectedSizeID: {},
      selectedFlavorID: {},
      selectedStyleID: {},
      selectedFrostingID: {},
      selectedFillingID: {},
      selectedExtraID: {},
      dependentConfigs: []
    };
  },
  methods: {
    getDependentConfigsFlavor(id){
      return this.$store.state.availableCakeConfigsBE.forEach(element => {
        if (element.cakeConfigFlavorID == id){
          this.dependentConfigs.push(element.cakeConfigName);
        }
      })

    },
    prepareSizesArray() {
      EmployeeService.getAllSizes().then((response) => {
        this.$store.commit("SET_ALL_SIZES_ARRAY", response.data);
      });
    },
    prepareStylesArray() {
      EmployeeService.getAllStyles().then((response) => {
        this.$store.commit("SET_ALL_STYLES_ARRAY", response.data);
      });
    },
    prepareFlavorsArray() {
      EmployeeService.getAllFlavors().then((response) => {
        this.$store.commit("SET_ALL_FLAVORS_ARRAY", response.data);
      });
    },
    prepareFrostingsArray() {
      EmployeeService.getAllFrostings().then((response) => {
        this.$store.commit("SET_ALL_FROSTINGS_ARRAY", response.data);
      });
    },
    prepareFillingsArray() {
      EmployeeService.getAllFillings().then((response) => {
        this.$store.commit("SET_ALL_FILLINGS_ARRAY", response.data);
      });
    },
    prepareExtrasArray() {
      EmployeeService.getAllExtras().then((response) => {
        this.$store.commit("SET_ALL_EXTRAS_ARRAY", response.data);
      });
    },
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
    flipStatusStyle(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm("Do you truly wish to make this style unavailable? Truly?")
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
    flipStatusFlavor(id, availableBoolean) {
      this.getDependentConfigsFlavor(id);
      let configString = ''
      this.dependentConfigs.forEach((configName) => configString += configName + '\n')
      this.dependentConfigs = [];
      switch (availableBoolean) {
        case true:
        
          if (
            confirm(
              "Making this flavor unavailable forsakes the following cake types, making them unavailable.\n"  + configString
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
    flipStatusFrosting(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Making this frosting unavailable forsakes the following cake types, making them unavailable. Can you live with that?:"
            )
          ) {
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
    flipStatusFilling(id, availableBoolean) {
      switch (availableBoolean) {
        case true:
          if (
            confirm(
              "Making this filling unavailable forsakes the following cake types, making them unavailable. Can you live with that?:"
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

    prepareThePreparations() {
      this.prepareSizesArray();
      this.prepareStylesArray();
      this.prepareFlavorsArray();
      this.prepareFrostingsArray();
      this.prepareFillingsArray();
      this.prepareExtrasArray();
    },
  },

  created() {
    this.prepareThePreparations();
    EmployeeService.getAvailableConfigs().then((response) => {
     this.$store.commit("SET_AVAILABLE_CAKE_CONFIG_ARRAY", response.data);
    });
  },
};
</script>

<style>
</style>