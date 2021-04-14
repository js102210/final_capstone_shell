<template>
  <div class="ordered-cake-card">
      <p>Cake name: {{getCakeName(orderedCake.cakeItemConfigID)}}</p>
      <p>Flavor: {{getCakeFlavor(orderedCake.cakeItemFlavorID)}}</p>
      <p>Frosting: {{getCakeFrosting(orderedCake.cakeItemFrostingID)}}</p>
      <p>Price: {{orderedCake.cakeItemPrice}} </p>
      </div>
</template>

<script>


export default {
name: "customer-ordered-cake-card",
props: ["orderedCake"],
methods: {
    getCakeName(id){
        let nameElement = this.$store.state.availableCakeConfigsBE.find(
            (element) =>
            element.cakeConfigID === id
        );
        return nameElement.cakeConfigName;
    },
    getCakeFlavor(id){
        let flavorElement = this.$store.state.availableFlavorsBE.find(
            (element) => element.flavorID === id
        );
        return flavorElement.flavorName;
    },
    getCakeFrosting(id){
        let frostingElement = this.$store.state.availableFrostingsBE.find(
            (element) => element.frostingID === id
        );
        return frostingElement.frostingName;
    },
    getCakeFilling(id){
        let fillingElement = this.$store.state.availableFillingsBE.find(
            (element) => element.fillingID ===id
        );
        return fillingElement.fillingName;
    }



},
computed:{

}

}
</script>

<style>

</style>