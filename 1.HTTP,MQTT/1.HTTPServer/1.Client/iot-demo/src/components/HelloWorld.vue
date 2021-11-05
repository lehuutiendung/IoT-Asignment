<template>
    <div>
        <button class="button" @click="handleClick()">Làm mới thông tin</button>
        <table id="table-info">
          <tr>
              <th>Ngày đo</th>
              <th>Nhiệt độ</th>
              <th>Trạng thái</th>
          </tr>
          <tr v-for="(item, index) in sensorData" :key="index">
              <td>{{ item.CreatedDate }}</td>
              <td>{{ item.Temperature }}</td>
              <td>{{ item.Status }}</td>
          </tr>
        </table>
    </div>
</template>
<script>
import axios from "axios"
export default {
  name: 'Container',
  data() {
    return {
      sensorData: [],  
      sensorInfo: {},
    }
  },
  created() {
    this.apiGetInfo();
  },
  methods: {
    // Xử lý click button
    handleClick(){
      var max = 50;
      var min = 10;
      this.sensorInfo.Temperature = Math.floor(Math.random() * (max - min)) + min;
      if(this.sensorInfo.Temperature < 20){
        this.sensorInfo.Status = "Nhiệt độ thấp";
      }else if(this.sensorInfo.Temperature >= 20 && this.sensorInfo.Temperature < 37){
        this.sensorInfo.Status = "Bình thường";
      }else{
        this.sensorInfo.Status = "Nhiệt độ cao";
      }
      this.sensorInfo.CreatedDate = new Date();
      this.sensorInfo.ModifyDate = "0001-01-01T00:00:00";
      this.sensorInfo.ID = 30;
      axios.post(`https://localhost:44349/Sensor`, this.sensorInfo)
      .then((res) => {
        console.log(res);
        this.apiGetInfo();
      })
      .catch((err) => {
        console.error(err);
      });
    },

    // Hàm thực hiện gọi API 
    apiGetInfo(){
      axios.get(`https://localhost:44349/Sensor`)
      .then(res => {
        this.sensorData = res.data;
      })
      .catch(err => {
        console.error(err); 
      })
    }
  },
}
</script>
<style scoped>
.button {
  border-radius: 4px;
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 25px 2px;
  cursor: pointer;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
}

.button:hover {
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}

#table-info {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  overflow: auto;
}

#table-info td, #table-info th {
  border: 1px solid #ddd;
  padding: 8px;
}

#table-info tr:nth-child(even){background-color: #f2f2f2;}

#table-info tr:hover {background-color: #ddd;}

#table-info th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #04AA6D;
  color: white;
}
</style>