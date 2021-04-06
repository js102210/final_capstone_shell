<template>
  <div id="app">
    <div id="nav">
      <div class="cust-section">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'cakes' }">Show me your Cakes!</router-link>
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
html {
    min-height: 100%;
}

#app {
  background: repeating-linear-gradient(45deg, #6ac1e6, #e1f2f8 50px, #ff7de3b6 100px);
  min-height:100%;
  margin: 0;
  background-repeat: no-repeat;
  background-attachment: fixed;
}

#nav {
  /* display: flex;
  justify-content: center; */
  position: relative;
  background-color: rgba(255, 0, 234, 0.856);
}

.cust-section {
  float: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.emp-section {
  float: right;
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
}
</style>
