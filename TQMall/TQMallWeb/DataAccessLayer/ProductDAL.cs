using System;
using System.Collections.Generic;
using System.Net.Http;
using TQMallWeb.Models;

namespace DataAccessLayer
{
    public class ProductDAL
    {
        public List<Product> GetProductList(string keyword)
        {
            List<Product> products = new List<Product>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"product/getproductlist?keyword={keyword}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    products = result.Content.ReadAsAsync<List<Product>>().Result;
                }
            }

            return products;
        }
        public Product GetProductByID(int id)
        {
            Product product = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"product/getproductbyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    product = result.Content.ReadAsAsync<Product>().Result;
                }
            }
            return product;
        }
        public int AddProduct(Product product)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("product/addproduct", product);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }
        public int UpdateProduct(Product product)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync<Product>("product/updateproduct", product);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }
        public int DeleteProduct(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"product/deleteproduct?id={id}", null);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }
            return rs;
        }
        
    }
}