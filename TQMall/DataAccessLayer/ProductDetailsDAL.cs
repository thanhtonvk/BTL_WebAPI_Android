using System;
using System.Collections.Generic;
using System.Net.Http;
using ModelsObject;

namespace DataAccessLayer
{
    public class ProductDetailsDAL
    {
        public ProductDetail GetProductDetailsById(int id)
        {
            ProductDetail productDetail = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"productdetails/getproductdetailsbyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    productDetail = result.Content.ReadAsAsync<ProductDetail>().Result;
                }
            }

            return productDetail;
        }

        public List<ProductDetail> GetProductDetailByIdProduct(int idProduct)
        {
            List<ProductDetail> productDetails = new List<ProductDetail>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"productdetails/getproductdetailsbyidproduct?idProduct={idProduct}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    productDetails = result.Content.ReadAsAsync<List<ProductDetail>>().Result;
                }
            }

            return productDetails;
        }

        public int AddProductDetails(ProductDetail productDetail)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("productdetails/add", productDetail);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }
        public int UpdateProductDetails(ProductDetail productDetail)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("productdetails/update", productDetail);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int DeleteProductDetails(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"productdetails/delete?id={id}",null);
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