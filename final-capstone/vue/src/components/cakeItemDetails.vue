<template>
<div>
    <h2>Item Number {{cakeItemJSON.cakeItemID}}: {{config.cakeConfigName}}</h2>
    <h3>Style: {{style.styleName}}   Size:  {{size.sizeName}}</h3>
    <h3>Filling:  {{filling.fillingName}}   Frosting: {{frosting.frostingName}}    Flavor: {{flavor.flavorName}}</h3>
    <h3>Extras: </h3>
    <h2
    v-for="extra in cakeItemJSON.cakeItemExtras"
    v-bind:key="extra.extraID">
    {{extra.extraName}}</h2>
    <h3>Custom Message: '{{cakeItemJSON.cakeItemMessage}}'   
    Price: {{cakeItemJSON.cakeItemPrice.toLocaleString('en-US', {
                                                        style: 'currency',
                                                        currency: 'USD',
                                                                            })}}</h3>


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
            extras: '| '
        }
    },
    props: [
        'cakeItemJSON'
    ],
    created(){
    //I'm not actually sure why I have to go through the step of defining the object in data, populating it from the store, then 
    //reading from it to set the 
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