using System;
using System.Collections.Generic;
using System.Net.Http;
using ModelsObject;

namespace DataAccessLayer
{
    public class ImageProductDAL
    {
        public List<ImageProduct> GetImageProductsById(int id)
        {
            List<ImageProduct> imageProducts = new List<ImageProduct>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"imageProducts/getimageproduct?idProduct={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    imageProducts = result.Content.ReadAsAsync<List<ImageProduct>>().Result;
                }

                return imageProducts;
            }
        }

        public int AddImageProduct(ImageProduct imageProduct)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("imageproduct/addimageproduct", imageProduct);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int UpdateImageProduct(ImageProduct imageProduct)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("imageproduct/updateimageproduct", imageProduct);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int DeleteImageProduct(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"imageproduct/deleteimageproduct?id={id}", null);
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