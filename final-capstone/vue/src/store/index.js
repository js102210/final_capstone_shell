import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    placeholderCakes: [
      {
        name: "fudge cake",
        cake_id: 1,
        price: 13.99,
        image_url: "https://hips.hearstapps.com/del.h-cdn.co/assets/17/16/2048x1468/gallery-1492694005-108-sein-9781101967140-art-r1-1.jpg?resize=980:*",
        description: "delicious fudge mmm mmm mmm!"
      },

      {
        name: "vanilla cake",
        cake_id: 2,
        price: 39.99,
        image_url: "https://natashaskitchen.com/wp-content/uploads/2020/07/Vanilla-Cake-6-1024x1536.jpg",
        description: "basic yum yum"
      }

    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
