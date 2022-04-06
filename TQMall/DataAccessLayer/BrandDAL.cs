using ModelsObject;
using System;
using System.Collections.Generic;
using System.Net.Http;

namespace DataAccessLayer
{
    public class BrandDAL
    {
        public List<Brand> GetBrandList()
        {
            List<Brand> brands = new List<Brand>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync("brands/getbrands");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    brands = result.Content.ReadAsAsync<List<Brand>>().Result;
                }
            }

            return brands;
        }

        public Brand GetBrandById(int id)
        {
            Brand brand = new Brand();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"brands/getbrandbyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    brand = result.Content.ReadAsAsync<Brand>().Result;
                }

                return brand;
            }
        }

        public int AddBrand(Brand brand)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("brands/postbrand", brand);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int UpdateBrand(Brand brand)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("brands/putbrand", brand);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int DeleteBrand(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"brands/deletebrand?id={id}",null);
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