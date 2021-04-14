import axios from 'axios';

export default {
//note - all API calls in Employee Service require authorization

getAllOrders(){
return axios.get('/orders');
},
getAllConfigs(){
    return axios.get('/configs');
},
getAvailableConfigs(){
    return axios.get('/configs/available');
},

getAllFlavors(){
    return axios.get('/flavors');
},

getAllFrostings(){
    return axios.get('/frostings');
},

getAllFillings(){
    return axios.get('/fillings');
},

getAllStyles(){
    return axios.get('/styles');
},

getAllSizes(){
    return axios.get('/sizes');
},
getAllStatuses(){
    return axios.get('/statuses');
},

getAllExtras(){
    return axios.get('/extras');
},

createCakeConfig(ConfigJSON){
    return axios.post('/configs', ConfigJSON);
},

createFlavor(FlavorJSON){
    return axios.post('/flavors', FlavorJSON);
},

createFrosting(FrostingJSON){
    return axios.post('/frostings', FrostingJSON);
},

createFilling(FillingJSON){
    return axios.post('/fillings', FillingJSON);
},

createStyle(StyleJSON){
    return axios.post('/styles', StyleJSON);
},

createSize(SizeJSON){
    return axios.post('/sizes', SizeJSON);
},

createExtra(ExtraJSON){
    return axios.post('/extras', ExtraJSON);
},

updateCakeConfig(ConfigJSON){
    return axios.put(`/configs/${ConfigJSON.configID}`, ConfigJSON);
},

updateFlavor(FlavorJSON){
    return axios.put(`/flavors/${FlavorJSON.flavorID}`, FlavorJSON);
},

updateFrosting(FrostingJSON){
    return axios.put(`/frostings/${FrostingJSON.frostingID}`, FrostingJSON);
},

updateFilling(FillingJSON){
    return axios.put(`/fillings/${FillingJSON.fillingID}`, FillingJSON);
},

updateStyle(StyleJSON){
    return axios.put(`/styles/${StyleJSON.styleID}`, StyleJSON);
},

updateSize(SizeJSON){
    return axios.put(`/sizes/${SizeJSON.sizeID}`, SizeJSON);
},

updateExtra(ExtraJSON){
    return axios.put(`/extras/${ExtraJSON.extraID}`, ExtraJSON);
},

updateOrder(orderJSON){
    return axios.put(`/orders/${orderJSON.orderID}`, orderJSON);
},
updateOrderStatus(orderID, statusID){
    return axios.patch(`/orders/${orderID}/status/${statusID}`)
},
updateMessagePrice(price){
    return axios.patch(`/message/price/${price}`);
},

flipConfigStatus(id){
    return axios.patch(`/configs/flip/${id}`)
},
flipSizeStatus(id){
    return axios.patch(`/sizes/flip/${id}`)
},
flipStyleStatus(id){
    return axios.patch(`/styles/flip/${id}`)
},
flipFlavorStatus(id){
    return axios.patch(`/flavors/flip/${id}`)
},
flipFrostingStatus(id){
    return axios.patch(`/frostings/flip/${id}`)
},
flipFillingStatus(id){
    return axios.patch(`/fillings/flip/${id}`)
},
flipExtraStatus(id){
    return axios.patch(`/extras/flip/${id}`)
},
getItemsForOrder(id){
    return axios.get(`/orders/${id}/cakes`,)
}

}