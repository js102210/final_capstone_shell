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

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    
    //used to give each cake in order a unique ID.
    cakeItemNumberForOrder: 0, 

    //this will be used to populate our order history so employees will be able to work with the orders
    //to view Pending orders, change their status, do edits, etc. Single pull to get *all* orders from the system,
    //then displaying the orders will be about *filtering* this array.
    pastOrdersArrayBE: [],

    //these are arrays to hold the various components that make up our cakes. They are populated with API pulls during 
    //created() lifecycle hooks.  The ones marked "all" are for the Employee
    //side of the app. the ones marked "available" are for the customer side of the app, since we only want 
    //customers to see available possibilities and we require login to see all possibilities.
    allCakeStylesBE:[],
    availableCakeStylesBE: [],
    allCakeSizesBE: [],
    availableCakeSizesBE: [],
    allFlavorsBE:[],
    availableFlavorsBE: [],
    allFrostingsBE: [],
    availableFrostingsBE: [],
    allFillingsBE: [],
    availableFillingsBE: [],
    allExtrasBE: [],
    availableExtrasBE: [],
    
    //holds available and all Cake Configs - available for customer side, all for employee side. Populated
    //via API calls during created() lifecycle hooks.
    allCakeConfigsBE:[],
    availableCakeConfigsBE: [ ],
    
       //placeholder for price for message - this is currently a magic number. We have an API on the back end built 
    //to eventually make it a live pull from database, but that was out of scope for our 3 sprints' MVP.
    messagePrice: 1.50,
    //status info is pulled here from the database via API call in created() lifecycle hook. For employees
    //only.
    allStatusesBE: [],

    //builder object for cakeItem before added to order object. Touched onlyvia mutations.
    cakeItemToOrder: {
      cakeItemStyleID: null,
      cakeItemSizeID: null,
      cakeItemFlavorID: null,
      cakeItemFrostingID: null,
      cakeItemFillingID: null,
      cakeItemMessage: '',
      cakeItemExtras: [],
      cakeItemPrice: null,
      cakeItemConfigID: null,
      cakeItemTempOrderID: null
    },
    //builder object for current order before sent to backend to submit order. Touched only via mutations.
    currentActiveOrder: {
      orderStatusID: null,
      orderPriceTotal: null,
      orderPickupDate: null,
      orderPickupTime: null,
      itemsInOrder: [],
      customerName: null,
      customerPhoneNumber: null
    },
    //used in OrderDetails component
    selectedOrder: {},
    //used to run order filtering in EmployeeOrderList component
    filteredOrders: []
  },
  mutations: {
    /**
     * mutation that sets the current authorization token for security purposes.
     * @param {* } state 
     * @param {*} token 
     */
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    //used in EmployeeOrderCard component to work with designated order.
    SET_SELECTED_ORDER(state, orderJSON){
      state.selectedOrder = orderJSON;
    },
    //future functionality - grab the message price from the database via API call and then setting
    //it in the store.
    SET_MESSAGE_PRICE(state, price){
      state.messagePrice = price;
    },

    //sets orders into pastOrdersArrayBE - used in EmployeeOrderList component
    SET_ALL_ORDERS_ARRAY(state, orderJSONArray){
      state.pastOrdersArrayBE = orderJSONArray;
    },
    //sets status JSONS into statusJSONArray - used in EmployeeOrderList, EmployeeOrderCard components
    SET_ALL_STATUSES_ARRAY(state, statusJSONArray){
      state.allStatusesBE = statusJSONArray;
    },

    //this group of mutations sets available / all arrays for the cake configs and components.
    SET_AVAILABLE_CAKE_CONFIG_ARRAY(state, cakeConfigJSONArray){
      state.availableCakeConfigsBE = cakeConfigJSONArray;
    },
    SET_ALL_CAKE_CONFIG_ARRAY(state, cakeConfigJSONArray){
      state.allCakeConfigsBE = cakeConfigJSONArray;
    },
    SET_ALL_SIZES_ARRAY(state, sizeJSONArray){
      state.allCakeSizesBE = sizeJSONArray;
    },
    SET_AVAILABLE_SIZES_ARRAY(state, sizeJSONArray){
      state.availableCakeSizesBE = sizeJSONArray;
    },
    SET_AVAILABLE_STYLES_ARRAY(state, styleJSONArray){
      state.availableCakeStylesBE = styleJSONArray;
    },
    SET_ALL_STYLES_ARRAY(state, styleJSONArray){
      state.allCakeStylesBE = styleJSONArray;
    },
    SET_ALL_FLAVORS_ARRAY(state, flavorJSONArray){
      state.allFlavorsBE = flavorJSONArray;
    },
    SET_AVAILABLE_FLAVORS_ARRAY(state, flavorJSONArray){
      state.availableFlavorsBE = flavorJSONArray;
    },
    SET_ALL_FROSTINGS_ARRAY(state, frostingJSONArray){
      state.allFrostingsBE = frostingJSONArray;
    },
    SET_AVAILABLE_FROSTINGS_ARRAY(state, frostingJSONArray){
      state.availableFrostingsBE = frostingJSONArray;
    },
    SET_AVAILABLE_FILLINGS_ARRAY(state, fillingJSONArray){
      state.availableFillingsBE = fillingJSONArray;
    },
    SET_ALL_FILLINGS_ARRAY(state, fillingJSONArray){
      state.allFillingsBE = fillingJSONArray;
    },
    SET_AVAILABLE_EXTRAS_ARRAY(state, extraJSONArray){
      state.availableExtrasBE = extraJSONArray;
    },
    SET_ALL_EXTRAS_ARRAY(state, extraJSONArray){
      state.allExtrasBE = extraJSONArray;
    },

    //these are used to set availability / unavailability for designated cake components
    //in the EmpChangeCake component.
    SET_CAKE_CONFIG_IS_AVAILABLE(state, id, boolean){
      const configToSet = state.allCakeConfigsBE.find(config => config.cakeConfigID == id)
      configToSet.isAvailable = boolean;
    },
    SET_SIZE_IS_AVAILABLE(state, id, boolean){
      const sizeToSet = state.allCakeSizesBE.find(size => size.sizeID == id);
      sizeToSet.isAvailable = boolean;
    },
    SET_STYLE_IS_AVAILABLE(state, id, boolean){
      const styleToSet = state.allCakeStylesBE.find(style => style.styleID == id);
      styleToSet.isAvailable = boolean;
    },
    SET_EXTRA_IS_AVAILABLE(state, id, boolean){
      const extraToSet = state.allExtrasBE.find(extra => extra.extraID == id);
      extraToSet.isAvailable = boolean;
    },
    SET_FILLING_IS_AVAILABLE(state, id, boolean){
      const fillingToSet = state.allFillingsBE.find(filling => filling.fillingID == id);
      fillingToSet.isAvailable = boolean;
    },
    SET_FROSTING_IS_AVAILABLE(state, id, boolean){
      const frostingToSet = state.allFrostingsBE.find(frosting => frosting.frostingID == id);
      frostingToSet.isAvailable = boolean;
    },
    SET_FLAVOR_IS_AVAILABLE(state, id, boolean){
      const flavorToSet = state.allFlavorsBE.find(flavor => flavor.flavorID == id);
      flavorToSet.isAvailable = boolean;
    },

    /**
     * This mutation takes a cakeJSON from the OrderCake component and makes it the
     * cakeItemToOrder. Where the cake data is staged before being put in currentActiveOrder
     * in the store
     * @param {*} state - this store's state. 
     * @param {*} cakeJSON - the supplied cakeJSON from the OrderCake component.
     */
    MAKE_CAKE_ITEM(state, cakeJSON) {
      state.cakeItemToOrder = cakeJSON;
      //creates an id for the cake item so that we can display it in the ShoppingCart properly.
      state.cakeItemNumberForOrder += 1;
      state.cakeItemToOrder.cakeItemTempOrderID = state.cakeItemNumberForOrder;
    },

    /**
     * Grabs the itemPrice calculated property from OrderCake component and puts in on cakeItemToOrder
     * @param {*} state - this store's state.
     * @param {*} price the price received from itemPrice on OrderCake component
     */
    SET_CAKE_ITEM_PRICE(state, price) {
      state.cakeItemToOrder.cakeItemPrice = price;
    },

    /**
     * takes the orderPrice calculated property from the ShoppingCart component and puts it on
     * the currentActiveOrder before currentActiveOrder is sent to the backend to place the order.
     * @param {*} state - this store's state.
     * @param {*} price - the price received from orderPRice on OrderCake component.
     */
    SET_ORDER_PRICE(state, price){
      state.currentActiveOrder.orderPriceTotal = price;
    },
    /**
     * grabs order information from the pickupInfo data object on the ShoppingCart component and puts it on
     * the currentActiveOrder store object before placing the order.
     * @param {*} state - this store's state.
     * @param {*} pickupInfo - gathered necessary order information from ShoppingCart.
     */
    SET_ORDER_INFO(state, pickupInfo) {
      //our status table is such that Pending is always set to 1. This would need to be refactored
      //if we had a business logic change that changed what our order statuses were.
      state.currentActiveOrder.orderStatusID = 1;
      state.currentActiveOrder.orderPickupDate = pickupInfo.orderPickupDate;
      state.currentActiveOrder.orderPickupTime = pickupInfo.orderPickupTime;
      state.currentActiveOrder.customerName = pickupInfo.customerName;
      state.currentActiveOrder.customerPhoneNumber = pickupInfo.customerPhoneNumber;
    },

    /**
     * updates the current cakeItemToOrder object and pushes it into the currentActiveOrder's
     * itemsInOrder array.
     * @param {*} state - this store's state.
     * @param {*} cakeItem -the cakeJSON received from the OrderCake component.
     */
    ADD_CAKE_ITEM_TO_ACTIVE_ORDER(state, cakeItem) {
      state.cakeItemToOrder = cakeItem;
      state.currentActiveOrder.itemsInOrder.push(cakeItem);

      //blanks out cakeItemToOrder so it can be reused again if customer orders multiple cakes.
      state.cakeItemToOrder =
      {
        cakeItemStyleID: null,
        cakeItemSizeID: null,
        cakeItemFlavorID: null,
        cakeItemFrostingID: null,
        cakeItemFillingID: null,
        cakeItemMessage: null,
        cakeItemPrice: null,
        cakeItemConfigID: null
      };
    },

/**
 * blanks out the currentActiveOrder object in state. after an order has been placed so that a new order
 * can be placed. Also resets cakeItemNumberForOrder back to 0.
 * @param {*} state 
 */
    CLEAR_ACTIVE_ORDER(state) {
      state.currentActiveOrder = {
        orderStatusID: null,
        orderPriceTotal: null,
        orderPickupDate: null,
        orderPickupTime: null,
        itemsInOrder: [],
        customerName: null,
        customerPhoneNumber: null
      };
      state.cakeItemNumberForOrder = 0;
    }

  }

}

)
