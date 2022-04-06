using System;
using System.Collections.Generic;
using System.Net.Http;
using ModelsObject;

namespace DataAccessLayer
{
    public class OrderDetailsDAL
    {
        public List<OrderDetail> GetOrderDetailsByIdOrder(int idOrder)
        {
            List<OrderDetail> orderDetails = new List<OrderDetail>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"orderdetails/getorderdetails?idOrder={idOrder}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    orderDetails = result.Content.ReadAsAsync<List<OrderDetail>>().Result;
                }
            }

            return orderDetails;
        }

        public OrderDetail GetOrderDetailsById(int id)
        {
            OrderDetail orderDetail = new OrderDetail();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync("orderdetails/getorderdetailsbyid");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    orderDetail = result.Content.ReadAsAsync<OrderDetail>().Result;
                }
            }

            return orderDetail;
        }
    }
}