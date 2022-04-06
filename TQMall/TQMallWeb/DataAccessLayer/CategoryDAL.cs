using System;
using System.Collections.Generic;
using System.Net.Http;
using TQMallWeb.Models;

namespace DataAccessLayer
{
    public class CategoryDAL
    {
         public List<Category> GetCategoryList()
        {
            List<Category> categories = new List<Category>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync("categories/getcategories");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    categories = result.Content.ReadAsAsync<List<Category>>().Result;
                }
            }

            return categories;
        }

        public Category GetCategoryById(int id)
        {
            Category category = new Category();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"categories/getcategorybyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    category = result.Content.ReadAsAsync<Category>().Result;
                }

                return category;
            }
        }

        public int AddCategory(Category category)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("categories/postcategory", category);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int UpdateCategory(Category category)
        {
          
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("categories/putcategory", category);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int DeleteCategory(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"categories/deletecategory?id={id}",null);
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