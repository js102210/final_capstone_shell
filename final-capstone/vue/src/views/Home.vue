<template>
  <div class="home">
    <h1>BeefCakes BakeShop</h1>
      <h2>Welcome to BeefCakes BakeShop.<br>
        You've come to the right place!</h2>
    <img src="https://i.pinimg.com/474x/57/81/eb/5781ebdcf6a0b23678ebf9bc1cbee0f8.jpg" />
    
  
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
  width: 30%;
  border: 2px solid black;
  border-radius: 10px;
  opacity: 65%;
}

.home h2 {
  font-family: 'Quicksand';
  font-weight: 400;
  margin-top: -3%;
}
</style>