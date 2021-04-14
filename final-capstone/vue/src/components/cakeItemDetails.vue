<template>
<div>
    <h2>{{cakeItemJSON.cakeItemID}}: {{cakeConfigName}}</h2>

</div>
</template>

<script>
export default {
    data(){
        return{
            config: {},
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
     
    }

    
}
</script>

<style>

</style>