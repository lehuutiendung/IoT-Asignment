using Dapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using MySqlConnector;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace IoT_HTTPServer.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class SensorController : ControllerBase
    {
        [HttpGet]
        public IActionResult GetInfo()
        {
            using (StreamReader sr = new StreamReader(@"C:\data.json"))
            {
                var obj = sr.ReadToEnd();
                var data = JsonConvert.DeserializeObject<List<SensorInfo>>(obj);
                var response = StatusCode(200, data);
                return response;
            }
        }

        [HttpPost]
        public IActionResult PostInfo(SensorInfo sensorInfo)
        {
            string json = JsonConvert.SerializeObject(sensorInfo);
            var filePath = @"C:\data.json";
            var jsonData = System.IO.File.ReadAllText(filePath);
            var sensorList = JsonConvert.DeserializeObject<List<SensorInfo>>(jsonData) ?? new List<SensorInfo>();
            sensorList.Add(sensorInfo);
            jsonData = JsonConvert.SerializeObject(sensorList);
            System.IO.File.WriteAllText(filePath, jsonData);
            var response = StatusCode(200);
            return response;
        }
    }
}
