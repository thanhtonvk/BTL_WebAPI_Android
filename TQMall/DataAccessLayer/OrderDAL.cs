using System;
using System.Collections.Generic;
using System.Net.Http;
using ModelsObject;

namespace DataAccessLayer
{
    public class OrderDAL
    {
        public List<Order> GetOrderBySeller(string username)
        {
            List<Order> orders = new List<Order>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"order/getorderseller?username={username}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    orders = result.Content.ReadAsAsync<List<Order>>().Result;
                }
            }

            return orders;
        }
        
    }
}