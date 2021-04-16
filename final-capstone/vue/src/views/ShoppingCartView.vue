<template>
  <div class="shopping-cart-view">
      <shopping-cart />

  </div>
</template>

<script>
import ShoppingCart from '../components/ShoppingCart.vue'

export default {
    components: {ShoppingCart},
    methods:{

    },

}

</script>

<style>
.shopping-cart {
  display: flex;
  flex-wrap: flex;
  justify-content: space-evenly;
}

.shopping-cart-banner {
  margin-top: 50px;
  text-align: center;
}

.ordered-cake-card {
  background: rgba(255, 255, 255, 0.432);
  padding: 30px;
  border-radius: 50px;
  border: 8px dotted rgba(255, 101, 229, 0.671)
}

.ordered-cake-card p {
  font-size: 1rem;
}

.where-beef {
  margin-top: 50px;
  padding: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.432);
  border: 8px dotted rgba(255, 101, 229, 0.623);
  border-radius: 75px;
}

.where-beef h1, 
.where-beef p {
  margin-top: -8px;
}

/* input.place-order {
  width: 20ems;
  height: 20ems;
} */

/* .finalize-order {
  background: rgba(255, 255, 255, 0.479);
  padding: 20px;
} */

@media screen and (max-width: 700px) {
.where-beef p {
    font-size: 1rem;
    margin-top: 5px;
  }

.shopping-cart-banner {
  flex-direction: column;
  display: flex;
  flex-wrap: wrap;
}
}
</style>