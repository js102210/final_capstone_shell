<template>
  <div id="app">
    <div id="nav">
      <div class="cust-section">
      <router-link v-bind:to="{ name: 'home' }" v-show="!$store.state.token">Home</router-link>
      <span v-show="!$store.state.token">&nbsp;|&nbsp;</span>
      <router-link v-bind:to="{ name: 'cakes' }" v-show="!$store.state.token">Show me your Cakes!</router-link>
      </div>
      <div class="emp-section">
      <router-link v-bind:to="{ name: 'login' }">Employee Login</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> | Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
/* Font imports */
@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Quicksand&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Rye&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Josefin+Slab&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Diplomata&display=swap');


html {
  background: repeating-linear-gradient(90deg, #e9feff, #e9feff 50px, #ffb8ea 50px, #ffb8ea 100px);
  min-height:100%;
  margin: 0;
  background-repeat: no-repeat;
  background-attachment: fixed;
}

h1 {
  font-family: 'Rye', cursive;
  font-weight: 500;
  margin-bottom: 1%;
  color:rgb(184, 12, 69);
  font-size: 3.3rem;
}

h3 {
  font-family: 'Sacramento';
  font-weight: 500;
  font-size: 2rem;
}

p {
  color:rgb(73, 2, 26);
  font-family: 'Josefin Slab', serif;
  font-size: 1.2rem;
  font-weight: 600;
}

#app {
  margin: 0;
}

#nav {
  position: fixed;
  top: 0;
  background-color: rgba(255, 153, 247, 0.616);
  width: 100%;
  height: 35px;
  font-family: 'Josefin Slab', serif;
  font-size: 1.5rem;
  font-weight: 900;
}

#nav a {
  text-decoration: none;
}

#nav a:hover {
  background-color: rgba(255, 152, 238, 0.39);
  border-radius: 5px;
  padding: 3px;
  transition-duration: 0.4s;
}

.cust-section {
  float: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(173, 243, 255, 0.87);
  border-radius: 5px;
  padding: 5px;
}

.emp-section {
  float: right;
  margin-top: 1px;
  margin-right: 1.5%;
  background-color: rgba(173, 243, 255, 0.493);
  border-radius: 5px;
  padding: 5px;
}

@media screen and (max-width: 600px) {
  #nav, .emp-section {
    float: none;
    display: block;
  }
  
  .cust-section {
    position: relative;
    top: 0;
    left: 0;
    transform: none;
  }

  #app {
    margin-top: 40px;
  }
}
</style>
