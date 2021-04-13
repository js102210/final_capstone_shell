<template>
  <div>
    <cake-list />
  </div>
</template>

<script>
import CakeList from '../components/CakeList.vue'
export default {
  components: { CakeList },

}
</script>

<style>
.cake-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    align-items: center;
    width: 160px;
    height: 500px;
    border: 1px solid rgba(0, 0, 0, 0.589);
    border-radius: 10px;
    padding: 20px;
    margin-top: 20px;
    background: rgba(255, 255, 255, 0.466);
    /* -webkit-backdrop-filter: blur(20px);
	backdrop-filter: blur(10px); */
}

.cake-pic {
    width: 100%;
    height: 30%;
     border: 1px solid rgba(0, 0, 0, 0.39);
    border-radius: 10px;
}

.cake-container p {
    font-size: 1.1rem;
    margin-top: -25px;
    font-family: 'Lancelot', cursive;
}


</style>