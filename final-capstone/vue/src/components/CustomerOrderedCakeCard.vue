<template>
  <div class="ordered-cake-card">
      <p>Cake Type: {{getCakeName(orderedCake.cakeItemConfigID)}}</p>
      <p>Flavor: {{getCakeFlavor(orderedCake.cakeItemFlavorID)}}</p>
      <p>Frosting: {{getCakeFrosting(orderedCake.cakeItemFrostingID)}}</p>
      <p>Filling: {{getCakeFilling(orderedCake.cakeItemFillingID)}} </p>
      <p>Size: {{getCakeSize(orderedCake.cakeItemSizeID)}}</p>
      <p>Style: {{getCakeStyle(orderedCake.cakeItemStyleID)}}</p>
      <div class="ordered-cake-card-message" v-show="orderedCake.cakeItemMessage.length>0">
        <p>Message: {{orderedCake.cakeItemMessage}}</p>
      </div>
      <div class="ordered-cake-card-extras" v-show="orderedCake.cakeItemExtras.length >0">
      <p>Extras:</p>
      <div v-for="extra in orderedCake.cakeItemExtras"
            v-bind:key="extra.extraID"> 
         <span class="ordered-cake-extras"><p>{{extra.extraName}}</p></span>
      </div>
      </div>
      <br>
      <p>Price: {{orderedCake.cakeItemPrice}} </p>
      <br>
      <button class="remove cake from order" v-on:click="removeCakeFromOrder(orderedCake.cakeItemTempOrderID)">Remove this cake from order</button>
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
    },
    getCakeSize(id){
        let sizeElement = this.$store.state.availableCakeSizesBE.find(
            (element) => element.sizeID === id
        );
        return sizeElement.sizeDescription;
    },
    getCakeStyle(id){
        let styleElement = this.$store.state.availableCakeStylesBE.find(
            (element) => element.styleID === id
        );
        return styleElement.styleName;
    },
    removeCakeFromOrder(id){
        if(confirm("Are you sure you want to delete this cake? If you changed your mind, you'd have to build a new cake in 'Order Your Cake'.")){
        let cakeToDeleteIndex = this.$store.state.currentActiveOrder.itemsInOrder.findIndex( 
            (element) => element.cakeItemTempOrderID === id);
            this.$store.state.currentActiveOrder.itemsInOrder.splice(cakeToDeleteIndex, 1);
        }
    }




},
computed:{

}

}
</script>

<style>

</style>