<template>
  <div class="home">
    <h1>BeefCakes BakeShop</h1>
      <h2>Welcome to BeefCakes BakeShop.<br>
        You've come to the right place!</h2>
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
  color:rgb(255, 77, 136);
}

.home img {
  width: 40%;
  border: 1px solid black;
  border-radius: 10px;
  opacity: 85%;
}

.home h2 {
  font-family: 'Quicksand';
  font-weight: 400;
  margin-top: -3%;
}
</style>