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
  /* perspective: 1000px; */
}

/* .flip-card-inner {
  transition: transform 0.6s;
  transform-style: preserve-3d;
} */

/* .cake-container:focus {
    outline: 0;
}

.cake-container:hover .flip-card-inner,
.cake-container:focus .flip-card-inner{
  transform: rotateY(180deg);
} */

/* .flip-card-front,
.flip-card-back {
    /* position: absolute; */
     /* width: 100%;
    height: 100%;
  backface-visibility: hidden;
}  */

/* .flip-card-front { */
   /* z-index: 2; */
   /* display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
}  */

.flip-card-back {
  /* transform: rotateY(180deg);
  z-index: 1; */
  display: flex;
  align-items: center;
  justify-content: center;
} 

.cake-pic {
    width: 100%;
    height: 150px;
    border: 1px solid rgba(0, 0, 0, 0.39);
    border-radius: 25px;
}

.cake-container p {
    font-size: 1.2rem;
    /* margin-top: -25px; */
    font-family: 'Lancelot', cursive;
    color: rgba(85, 0, 28, 0.753);
}


</style>