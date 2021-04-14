<template>
<div>
    <h2>{{cakeItemJSON.cakeItemID}}: {{cakeConfigName}}</h2>
    <h3>Style: {{styleName}}   Size:  {{sizeName}}</h3>


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
            cakeConfigName: '',
            styleName: '',
            sizeName: '',
            message: '',
            fillingName: '',
            frostingName: '',
            flavorName: '',
            extras: []
        }
    },
    props: [
        'cakeItemJSON'
    ],
    created(){
    this.config = this.$store.state.allCakeConfigsBE.find(
    (config)=> config.cakeConfigID == this.cakeItemJSON.cakeItemConfigID)
    this.cakeConfigName = this.config.cakeConfigName;  

    this.style = this.$store.state.allCakeStylesBE.find(
    (style) => style.styleID == this.cakeItemJSON.cakeItemStyleID)
    this.styleName = this.style.styleName;

    this.size = this.$store.state.allCakeSizesBE.find(
    (size) => size.sizeID == this.cakeItemJSON.cakeItemSizeID)
    this.sizeName = this.size.sizeName;
    }

    

    
}
</script>

<style>

</style>