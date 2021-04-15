<!--Employee: Information about a specific item of an order. To be viewed from the order details screen, 
which will populate with one of this component for each item in the order. Gets passed JSON for the item from the parent.-->
<template>
<div class="emp-order-cake-details">
    <h2>Item {{cakeItemJSON.cakeItemID}}: {{config.cakeConfigName}}</h2>
    <p>Style: {{style.styleName}}</p>
    <p>Size: {{size.sizeName}}</p>
    <p>Filling: {{filling.fillingName}}</p>
    <p>Frosting: {{frosting.frostingName}}</p>
    <p>Flavor: {{flavor.flavorName}}</p>
    <p>Extras: </p>
    <p
    v-for="extra in cakeItemJSON.cakeItemExtras"
    v-bind:key="extra.extraID">
    {{extra.extraName}}</p>
    <p>Custom Message: '{{cakeItemJSON.cakeItemMessage}}'</p>
    <p>Price: {{cakeItemJSON.cakeItemPrice.toLocaleString('en-US', { style: 'currency', currency: 'USD',}) }}</p>

</div>
</template>

<script>
export default {
    data(){
        return{
            config: {},
            style: {},
            size: {},
            filling: {},
            frosting: {},
            flavor: {},
            //extras of a cake item are displayed with string concatenation and separated by pipes
            extras: '| '
        }
    },
    props: [
        'cakeItemJSON'
    ],
    created(){
        //Item JSON passes most information as keys, so String representations of the item's attributes must be looked up in
        //the store
    this.config = this.$store.state.allCakeConfigsBE.find(
    (config)=> config.cakeConfigID == this.cakeItemJSON.cakeItemConfigID)
    

    this.style = this.$store.state.allCakeStylesBE.find(
    (style) => style.styleID == this.cakeItemJSON.cakeItemStyleID)
  

    this.size = this.$store.state.allCakeSizesBE.find(
    (size) => size.sizeID == this.cakeItemJSON.cakeItemSizeID)
   

     this.flavor = this.$store.state.allFlavorsBE.find(
     (flavor) => flavor.flavorID == this.cakeItemJSON.cakeItemFlavorID)
   

   this.filling = this.$store.state.allFillingsBE.find(
   (filling) => filling.fillingID == this.cakeItemJSON.cakeItemFillingID)

   this.frosting = this.$store.state.allFrostingsBE.find(
       (frosting)=> frosting.frostingID == this.cakeItemJSON.cakeItemFrostingID);

    



    }

   

    
}
</script>

<style>

</style>