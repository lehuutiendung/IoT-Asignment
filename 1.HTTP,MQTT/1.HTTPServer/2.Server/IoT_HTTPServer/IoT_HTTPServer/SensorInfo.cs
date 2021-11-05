using System;

namespace IoT_HTTPServer
{
    public class SensorInfo
    {
        /// <summary>
        /// ID
        /// </summary>
        public int ID { get; set; }

        /// <summary>
        /// Trạng thái
        /// </summary>
        public string Status { get; set; }

        /// <summary>
        /// Tên sensor
        /// </summary>
        public double Temperature { get; set; }

        /// <summary>
        /// Thông tin ngày
        /// </summary>
        public DateTime CreatedDate { get; set; }

        /// <summary>
        /// Ngày sửa
        /// </summary>
        public DateTime ModifyDate { get; set; }
    }
}
