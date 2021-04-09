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
    messagePrice:{
      price_mod: 1.50
    },
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
      orderPickupDate: null,
      orderPickupTime: null,
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
    SET_ORDER_INFO(state, pickupInfo){
      state.currentActiveOrder.orderPickupDate = pickupInfo.orderPickupDate;
      state.currentActiveOrder.orderPickupTime = pickupInfo.orderPickupTime;
      state.currentActiveOrder.customerName = pickupInfo.customerName;
      state.currentActiveOrder.customerPhoneNumber = pickupInfo.customerPhoneNumber;
    },
    ADD_CAKEITEM_TO_ACTIVE_ORDER(state, cakeItem){
      state.cakeItemToOrder = cakeItem;
      state.currentActiveOrder.itemsInOrder.push(cakeItem);
      //pls note that this line is *only* for the current sprint. will refactor for multiple cakes later when we have shopping cart page.
      state.currentActiveOrder.orderPriceTotal = cakeItem.cakeItemPrice;
      //this is too tightly coupled and will be changed when we do the Order page, but it's okay for now to be able to order a single cake.
      state.currentActiveOrder.orderStatus = 1;

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
        // state.currentActiveOrder.orderPlacedDateTime = order.orderPlacedDateTime;
        state.currentActiveOrder.customerName = order.customerName;
        state.currentActiveOrder.customerPhoneNumber = order.customerPhoneNumber;

        //we can either push the active order in a POST request here or in the component, wherever we do it, we have to add code to set the current 
        //active order back to its blank state
      },
      CLEAR_ACTIVE_ORDER(state){
        state.currentActiveOrder = {
          orderStatus : null,
          orderPriceTotal: null,
          orderPickupDate: null,
          orderPickupTime: null,
          itemsInOrder: [],
          customerName : null,
          customerPhoneNumber : null
        }
      }

      }

    }
  
)
