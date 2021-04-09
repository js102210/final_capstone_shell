import axios from 'axios';

export default {

    getStandardConfigs(){
        return axios.get('/configs');
    },

    sendOrderJSON(orderJSON){
        return axios.post('/placeOrder', orderJSON);
    }

}