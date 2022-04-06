using System;
using System.Collections.Generic;
using System.Net.Http;
using TQMallWeb.Models;

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
        public int Update(Order order)
        {
            int rs = -1;
            using(var client = new HttpClient())
            {
                client.BaseAddress= new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("order/updateorder", order);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }
            return rs;
        }
        public Order GetOrderByID(int id)
        {
            Order order = null;
            using(var client = new HttpClient())
            {

                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"order/getorderbyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    order = result.Content.ReadAsAsync<Order>().Result;
                }
            }
            return order;
        }
    }
}