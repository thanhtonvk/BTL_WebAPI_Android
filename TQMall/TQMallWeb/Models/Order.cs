namespace TQMallWeb.Models
{
    using DataAccessLayer;
    using System.Collections.Generic;
    using System.ComponentModel;

    public  class Order
    {

        public int ID { get; set; }
        [DisplayName("Tài khoản")]

        public string Username { get; set; }
        [DisplayName("Ngày đặt")]
      
        public string Date { get; set; }
        [DisplayName("Số điện thoại")]
      
        public string PhoneNumber { get; set; }
        [DisplayName("Địa chỉ")]
      
        public string Address { get; set; }
        public int TongTien()
        {
            OrderDetailsDAL orderDetailDAL = new OrderDetailsDAL();
            ProductDetailsDAL productDetailsDAL = new ProductDetailsDAL();
            int tong = 0;
            var model = orderDetailDAL.GetOrderDetailsByIdOrder(ID);
            foreach (var item in model)
            {
                tong += productDetailsDAL.GetProductDetailsById(item.IDProductDetails).Cost.Value;
            }
            return tong;
        }
        [DisplayName("Trạng thái")]

        public short? Status { get; set; }
       
    }
}
