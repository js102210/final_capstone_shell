<template>
  <div>
      <h3>{{cake.name}}</h3>
      <img src="{{cake.image_url}}" />


  </div>
</template>

<script>
export default {
    name: "cake-card",
    props: [
        "cake"
    ]

}
</script>

<style>

</style>