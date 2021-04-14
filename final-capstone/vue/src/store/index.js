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

    //these JSONS are for when we need to edit/update cake attributes - will be built
    // in appropriate employee component, then set via mutation, and the method with the API
    //call will use what's in the store, then use the CLEAR mutate to set these
    //back to empty objects.
    cakeStyleJSON: {},
    cakeSizeJSON: {},
    cakeFlavorJSON: {},
    cakeFrostingJSON: {},
    cakeFillingJSON: {},
    cakeConfigJSON: {},
    cakeExtraJSON: {},

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

    SET_SELECTED_ORDER(state, orderJSON){
      state.selectedOrder = orderJSON;
    },
    SET_MESSAGE_PRICE(state, price){
      state.messagePrice = price;
    },

    SET_CAKE_STYLE_JSON(state, styleJSON) {
      state.cakeStyleJSON = styleJSON;
    },
    SET_CAKE_SIZE_JSON(state, sizeJSON) {
      state.cakeSizeJSON = sizeJSON;
    },
    SET_CAKE_FLAVOR_JSON(state, flavorJSON) {
      state.cakeFlavorJSON = flavorJSON;
    },
    SET_CAKE_FROSTING_JSON(state, frostingJSON) {
      state.cakeFrostingJSON = frostingJSON;
    },
    SET_CAKE_FILLING_JSON(state, fillingJSON) {
      state.cakeFillingJSON = fillingJSON;
    },
    SET_CAKE_CONFIG_JSON(state, configJSON) {
      state.cakeConfigJSON = configJSON;
    },
    SET_CAKE_EXTRA_JSON(state, extraJSON) {
      state.cakeExtraJSON = extraJSON;
    },
    CLEAR_CAKE_STYLE_JSON(state) {
      state.cakeStyleJSON = {};
    },
    CLEAR_CAKE_SIZE_JSON(state) {
      state.cakeSizeJSON = {};
    },
    CLEAR_CAKE_FLAVOR_JSON(state) {
      state.cakeFlavorJSON = {};
    },
    CLEAR_CAKE_FILLING_JSON(state) {
      state.cakeFillingJSON = {};
    },
    CLEAR_CAKE_CONFIG_JSON(state) {
      state.cakeConfigJSON = {};
    },
    CLEAR_CAKE_EXTRA_JSON(state) {
      state.cakeExtraJSON = {};
    },
    SET_ALL_ORDERS_ARRAY(state, orderJSONArray){
      state.pastOrdersArrayBE = orderJSONArray;
    },
    SET_ALL_STATUSES_ARRAY(state, statusJSONArray){
      state.allStatusesBE = statusJSONArray;
    },
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
    MAKE_CAKE_ITEM(state, cakeJSON) {
      state.cakeItemToOrder = cakeJSON;
      //creates an id for the cake item so that we can display it in the ShoppingCart properly
      state.cakeItemToOrder.cakeItemTempOrderID = state.cakeItemNumberForOrder + 1;
      state.cakeItemNumberForOrder += 1;
    },
    SET_CAKE_ITEM_PRICE(state, price) {
      state.cakeItemToOrder.cakeItemPrice = price;
    },
    SET_ORDER_PRICE(state, price){
      state.currentActiveOrder.orderPriceTotal = price;
    },
    SET_ORDER_INFO(state, pickupInfo) {
      //our status table is such that Pending is always set to 1. This would need to be refactored
      //if we had a business logic change that changed what our order statuses were.
      state.currentActiveOrder.orderStatusID = 1;
      state.currentActiveOrder.orderPickupDate = pickupInfo.orderPickupDate;
      state.currentActiveOrder.orderPickupTime = pickupInfo.orderPickupTime;
      state.currentActiveOrder.customerName = pickupInfo.customerName;
      state.currentActiveOrder.customerPhoneNumber = pickupInfo.customerPhoneNumber;
    },
    ADD_CAKE_ITEM_TO_ACTIVE_ORDER(state, cakeItem) {
      state.cakeItemToOrder = cakeItem;
      state.currentActiveOrder.itemsInOrder.push(cakeItem);

      //again, we can actually get rid of this whole thing if we decide to pass a cakeItem in, but it's here now for reference and in case we decide to
      //mutate the data store by properties instead of sending a whole object
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
    FINALIZE_ACTIVE_ORDER(state, order) {
      state.currentActiveOrder.orderStatusID = order.orderStatusID;
      state.currentActiveOrder.orderPriceTotal = order.orderPriceTotal;
      // state.currentActiveOrder.orderPlacedDateTime = order.orderPlacedDateTime;
      state.currentActiveOrder.customerName = order.customerName;
      state.currentActiveOrder.customerPhoneNumber = order.customerPhoneNumber;

      //we can either push the active order in a POST request here or in the component, wherever we do it, we have to add code to set the current 
      //active order back to its blank state
    },
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
