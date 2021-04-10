import axios from 'axios';

export default {

    getAvailableConfigs(){
        return axios.get('/configs/available');
    },

    getAvailableFlavors(){
        return axios.get('/flavors/available');
    },

    getAvailableFrostings(){
        return axios.get('/frostings/available');
    },

    getAvailableFillings(){
        return axios.get('/fillings/available');
    },

    getAvailableStyles(){
        return axios.get('/styles/available');
    },

    getAvailableSizes(){
        return axios.get('/sizes/available');
    },

    getAvailableExtras(){
        return axios.get('/extras/available');
    },

    sendOrderJSON(orderJSON){
        return axios.post('/placeOrder', orderJSON);
    }

}