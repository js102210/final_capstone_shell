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
    standardCakeIdForOrder: null,
    cakeOrderJSON: {}, //renamed
    selectedCake: {
      
    },
     //jake: why do we have both of these? I thought custom cakes were just a config
     //dan: took out customOrderCakeObject, superfluous now that we've reconsidered
     //and make a cake JSON standard.
    availableCakeStylesBE: [
      {
        style_id: 1,
        style_name: 'pick a cake style!',
        is_available: true,
        price_mod: 0.00
      },
      {
        style_id: 2,
        style_name: "layer cake",
        is_available: true,
        price_mod: 0.00
      },
      {
        style_id: 3,
        style_name: "cupcakes",
        is_available: true,
        price_mod: 0.00
      },
      {
        style_id: 4,
        style_name: "sheet cake",
        is_available: true,
        price_mod: 0.00
      }
    ],
    availableCakeSizesBE:[
      {
        size_id: 1,
        size_name: null,
        size_description: 'pick a cake size!',
        is_available: true,
        price_mod: 0.00
      },
      {
        size_id: 2,
        size_name: "small",
        size_description: 'SMALL: a 6" double layer cake OR 12 cupcakes OR a 8"x8" sheet cake',
        is_available: true,
        price_mod: 9.99
      },
      {
        size_id: 3,
        size_name: "medium",
        size_description: 'MEDIUM: a 9" double layer cake OR 18 cupcakes OR a 9"x14" sheet cake',
        is_available: true,
        price_mod: 15.99
      },
      {
        size_id: 4,
        size_name: "large",
        size_description: 'LARGE: a 12" double layer cake OR 24 cupcakes OR a 20"x24" sheet cake',
        is_available: true,
        price_mod: 19.99
      }
    ],
    availableCakeFlavors:["vanilla", "chocolate", "carrot"],
    availableFlavorsBE:[
      {
        flavor_id: 1,
        flavor_name: 'pick a cake flavor!',
        is_available: true,
        price_mod: 0.00
      },
      {
        flavor_id: 2,
        flavor_name: "vanilla",
        is_available: true,
        price_mod: 0.00
      },
      {
        flavor_id: 3,
        flavor_name: "chocolate",
        is_available: true,
        price_mod: 0.00
      },
      {
        flavor_id: 4,
        flavor_name: "confetti",
        is_available: true,
        price_mod: 2.00
      },
      {
        flavor_id: 5,
        flavor_name: "carrot",
        is_available: true,
        price_mod: 3.00
      }

    ],
    availableFrostings: ["vanilla", "chocolate", "cream cheese"],
    availableFrostingsBE:[
      {
        frosting_id: 1,
        frosting_name: "no frosting - have a naked cake!",
        is_available: true,
        price_mod: 0.00
      },
      {
        frosting_id: 2,
        frosting_name: "vanilla",
        is_available: true,
        price_mod: 0.00
      },
      {
        frosting_id: 3,
        frosting_name: "chocolate",
        is_available: true,
        price_mod: 0.00
      },
      {
        frosting_id: 4,
        frosting_name: "cream cheese",
        is_available: true,
        price_mod: 3.00
      }
    ],
    availableFillings: ["no filling", "lemon card", "strawberries", "chocolate chips"],
    availableFillingsBE:[
      {
        filling_id: 1,
        filling_name: "no filling",
        is_available: true,
        price_mod: 0.00
      },
      {
        filling_id: 2,
        filling_name: "strawberry jam",
        is_available: true,
        price_mod: 1.00
      },
      {
        filling_id: 3,
        filling_name: "blackberry jam",
        is_available: true,
        price_mod: 1.00
      }
    ],
    availableExtras: ["edible image", "superhero toys"],
    availableExtrasBE:[
      {
        extra_id: 1,
        extra_name: 'superhero decorations',
        is_available: true,
        price_mod: 3.00
      },
      {
        extra_id: 2,
        extra_name: 'plastic bunny',
        is_available: true,
        price_mod: 1.00
      },
      {
        extra_id: 3,
        extra_name: 'birthday candles',
        is_available: true,
        price_mod: 2.00
      }
    ],
    standardCakeConfigsBE: [
      {
        cake_config_id: 1,
        cake_config_name: "Custom Cake",
        cake_config_img_url: "https://www.pngkit.com/png/detail/918-9180589_wedding-cake-icon-png-conzelmann-b-228-ckerei.png",
        cake_config_description: "Build your own cake!",
        flavor_id: 1,
        frosting_id: 1,
        filling_id: 1
      },
      {
        cake_config_id: 2,
        cake_config_name: "Curls Confetti Birthday Cake",
        cake_config_img_url: "https://preppykitchen.com/wp-content/uploads/2018/04/Funfetti-original-redone-blog-1.jpg",
        cake_config_description: "Our fun-filled confetti cake with luscious vanilla frosting",
        flavor_id: 4,
        frosting_id: 2,
        filling_id: 1
      },
      {
        cake_config_id: 3,
        cake_config_name: "Deadlift Devil's Food Cake",
        cake_config_img_url: "https://www.girlversusdough.com/wp-content/uploads/2019/10/devils-food-cake-5.jpg",
        cake_config_description: "Our award-winning deep devil's food cake with deep chocolate frosting. Go ahead, it's cheat day!",
        flavor_id: 3,
        frosting_id: 3,
        filling_id: 1
      },
      {
        cake_config_id: 4,
        cake_config_name: "Gym Bunny Carrot Cake",
        cake_config_img_url: "https://grandbaby-cakes.com/wp-content/uploads/2020/03/Carrot-Cake-10.jpg",
        cake_config_description: "Our moist carrot cake is jam-packed with healthy antoxidants and unbeatable flavor! It comes with our incredible cream cheese frosting.",
        flavor_id: 5,
        frosting_id: 4,
        filling_id: 1
      }

    ],
    placeholderCakes: [
      {
        name: "Fudge Cake",
        cake_id: 1,
        price: 13.99,
        image_url: "https://hips.hearstapps.com/del.h-cdn.co/assets/17/16/2048x1468/gallery-1492694005-108-sein-9781101967140-art-r1-1.jpg?resize=980:*",
        description: "delicious fudge mmm mmm mmm!"
      },

      {
        name: "Vanilla Cake",
        cake_id: 2,
        price: 39.99,
        image_url: "https://natashaskitchen.com/wp-content/uploads/2020/07/Vanilla-Cake-6-1024x1536.jpg",
        description: "basic yum yum"
      },
      {
        name: "Caramel Cake",
        cake_id: 3,
        price: 29.99,
        image_url: "data:image/jpeg;base64,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",
        description: "sticky and sweet."
      },
      {
        name: "Custom Cake",
        cake_id: 4,
        price: 39.99,
        image_url: "https://www.pngkit.com/png/detail/918-9180589_wedding-cake-icon-png-conzelmann-b-228-ckerei.png",
        description: "Build your own cake!"
      }

    ],
  //we can actually remove this if we have the order component send a cakeItem object, but I have it here now for convenient reference
    cakeItemToOrder : {
      cakeItemStyleId : null,
      cakeItemSizeId : null,
      cakeItemFlavorId : null,
      cakeItemFrostingID : null,
      cakeItemFillingID : null,
      cakeItemMessage : null,
      cakeItemPrice : null,
      cakeItemConfigId : null
    },
    currentActiveOrder: {
      orderStatus : null,
      orderPriceTotal: null,
      orderPlacedDateTime: null,
      itemsInOrder: [],
      customerName : null,
      customerPhoneNumber : null
    }
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
    },
    SET_SELECTED_CAKE(state, id){
      state.selectedCake.id = id;
    },
    MAKE_CAKE_ITEM(state, cakeJSON){
      state.cakeItemToOrder = cakeJSON;
    },
    SET_CAKE_ITEM_PRICE(state, price){
      state.cakeItemToOrder.cakeItemPrice = price;
    },
    ADD_CAKEITEM_TO_ACTIVE_ORDER(state, cakeItem){
      state.cakeItemToOrder = cakeItem;
      state.currentActiveOrder.itemsInOrder.push(cakeItem);
      //again, we can actually get rid of this whole thing if we decide to pass a cakeItem in, but it's here now for reference and in case we decide to
      //mutate the data store by properties instead of sending a whole object
      state.cakeItemToOrder = 
        {
          cakeItemStyleId : null,
          cakeItemSizeId : null,
          cakeItemFlavorId : null,
          cakeItemFrostingID : null,
          cakeItemFillingID : null,
          cakeItemMessage : null,
          cakeItemPrice : null,
          cakeItemConfigId : null
        };
      },
      FINALIZE_ACTIVE_ORDER(state, order){
        state.currentActiveOrder.orderStatus = order.orderStatus;
        state.currentActiveOrder.orderPriceTotal = order.orderPriceTotal;
        state.currentActiveOrder.orderPlacedDateTime = order.orderPlacedDateTime;
        state.currentActiveOrder.customerName = order.customerName;
        state.currentActiveOrder.customerPhoneNumber = order.customerPhoneNumber;

        //we can either push the active order in a POST request here or in the component, wherever we do it, we have to add code to set the current 
        //active order back to its blank state
      }

    }
  }
)
