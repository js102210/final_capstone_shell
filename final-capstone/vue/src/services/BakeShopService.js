import axios from 'axios';

export default {

    getStandardConfigs(){
        return axios.get('/configs');
    }

}