using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;

using TQMallWeb.Models;

namespace DataAccessLayer
{
    public class AccountDAL
    {

        public IEnumerable<Account> GetAccountList()
        {
            List<Account> accounts = new List<Account>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync("accounts/getaccounts");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    accounts = result.Content.ReadAsAsync<List<Account>>().Result;
                }
            }
            return accounts;
        }
        public int Login(string username, string password)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"accounts/login?username={username}&password={password}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }
        public int Register(Account account)
        {
            int rs = -1;
            account.Status = 3;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("accounts/register", account);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int Update(Account account)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("accounts/update", account);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }



        public int Block(string username)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"accounts/block?username={username}", null);
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