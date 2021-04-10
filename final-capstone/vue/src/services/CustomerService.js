import axios from 'axios';

export default {

    getAvailableConfigs(){
        return axios.get('/configs/available');
    },

    sendOrderJSON(orderJSON){
        return axios.post('/placeOrder', orderJSON);
    }

}