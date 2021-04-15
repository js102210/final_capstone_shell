<template>
<div class="order-page">
  <order-cake />
  </div>
</template>

<script>
import OrderCake from '../components/OrderCake.vue'
export default {
  components: { OrderCake },

}
</script>

<style>
.order-page {
  margin-top: 3%;
}

.order-display {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
}

.order-form select {
  margin-top: -10px;
}
 
.custom-cake-options select {
  margin-top: 10px;
}

.order-form {
  background: rgba(255, 255, 255, 0.349);
  -webkit-backdrop-filter: blur(20px);
  backdrop-filter: blur(10px);
  padding: 15px;
  border-radius: 70px;
  border: 7px dotted rgba(250, 44, 44, 0.329);
  font-family: 'Antic Slab', serif;
  font-size: 1rem;
  font-weight: 600;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.order-form input select,
.custom-cake-options input select {
    padding: 2px;
    border-radius: 5px;
    border: 1px solid rgba(24, 24, 24, 0.267);
    font-family: 'Antic Slab', serif;
    font-weight: 600;
  }

.cakedisplay {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  align-items: center;
  width: 260px;
  height: auto;
  border: 1px solid rgba(0, 0, 0, 0.356);
  border-radius: 40px;
  padding: 30px;
  margin-top: 20px;
  background: rgba(255, 255, 255, 0.5);
  /* -webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px); */
}
.cakedisplay img {
  width: 60%;
  height: 120px;
  border: 6px dotted rgba(245, 66, 170, 0.267);
  border-radius: 150px;
}

.cakedisplay h3 {
  margin-top: -3%;
}

.calculated-price {
  color: rgb(182, 8, 43);
  font-weight: 700;
  font-size: 1.6rem;
}

</style>