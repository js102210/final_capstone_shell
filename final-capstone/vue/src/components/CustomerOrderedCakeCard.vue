<!--Customer: Displays an item in the customer's current order/"shopping cart". One of these will display in the customer's ordered
list/"shopping cart" view for each item they've added to the order. Gets passed JSON for the item from the parent for display. -->
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
    //JSON represents attributes of the item as keys, so String representations must be looked up from the store
    /**
     * Gets the name of a Cake Config based on the ID
     * @param id number ID of the Cake Config
     */
    getCakeName(id){
        let nameElement = this.$store.state.availableCakeConfigsBE.find(
            (element) =>
            element.cakeConfigID === id
        );
        return nameElement.cakeConfigName;
    },
    /**
     * Gets the name of a Flavor based on the ID
     * @param id number ID of the Flavor
     */
    getCakeFlavor(id){
        let flavorElement = this.$store.state.availableFlavorsBE.find(
            (element) => element.flavorID === id
        );
        return flavorElement.flavorName;
    },
    /**
     * Gets the name of a Frosting based on the ID
     * @param id number ID of the Frosting
     */
    getCakeFrosting(id){
        let frostingElement = this.$store.state.availableFrostingsBE.find(
            (element) => element.frostingID === id
        );
        return frostingElement.frostingName;
    },
    /**
     * Gets the name of a Filling based on the ID
     * @param id number ID of the Filing
     */
    getCakeFilling(id){
        let fillingElement = this.$store.state.availableFillingsBE.find(
            (element) => element.fillingID ===id
        );
        return fillingElement.fillingName;
    },
    /**
     * Gets the name of a Size based on the ID
     * @param id number ID of the Frosting
     */
    getCakeSize(id){
        let sizeElement = this.$store.state.availableCakeSizesBE.find(
            (element) => element.sizeID === id
        );
        return sizeElement.sizeDescription;
    },
    /**
     * Gets the name of a Style based on the ID
     * @param id number ID of the Style
     */
    getCakeStyle(id){
        let styleElement = this.$store.state.availableCakeStylesBE.find(
            (element) => element.styleID === id
        );
        return styleElement.styleName;
    },
   
   /**
    * Removes a single item from the active order
    * @param id number: the item's temporary itemID in the active order container
    */
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