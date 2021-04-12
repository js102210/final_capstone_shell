<template>
  <div class="home">
   <span class="banner"><h1>BeefCakes BakeShop</h1>
      <p>Welcome to BeefCakes BakeShop.<br>
        You've come to the right place!</p></span>
    <img src="..\assets\beefcakes.png" />
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
<style>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.banner {
  background: rgba(255, 255, 255, 0.521);
  /* -webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px); */
  display: flex;
  flex-direction: column;
  justify-items: center;
  align-items: center;
  line-height: 20px;
  margin-top: 19px;
  padding: 4npm run servepx;
  border-radius: 45px;
  border: 6px dotted rgba(252, 73, 103, 0.233);
  padding: 9px;
}

.home img {
  width: 40%;
  border: 11px double rgba(209, 4, 4, 0.212);
  border-radius: 250px;
  opacity: 100%;
}

.home h2 {
  font-family: 'Quicksand';
  font-weight: 400;
  margin-top: -3%;
}
</style>