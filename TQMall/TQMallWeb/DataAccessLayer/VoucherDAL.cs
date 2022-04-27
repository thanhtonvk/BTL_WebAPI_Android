using System;
using System.Collections.Generic;
using System.Net.Http;
using TQMallWeb.Models;

namespace DataAccessLayer
{
    public class VoucherDAL
    {
        public List<Voucher> GetVouchers(string account)
        {
            List<Voucher> vouchers = new List<Voucher>();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"voucher/getvoucherbyaccount?account={account}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    vouchers = result.Content.ReadAsAsync<List<Voucher>>().Result;
                }
            }

            return vouchers;
        }

        public Voucher GetVoucherById(int id)
        {
            Voucher voucher = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.GetAsync($"voucher/getvoucherbyid?id={id}");
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    voucher = result.Content.ReadAsAsync<Voucher>().Result;
                }
            }

            return voucher;
        }

        public int AddVoucher(Voucher voucher)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PostAsJsonAsync("voucher/addvoucher", voucher);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }
        public int UpdateVoucher(Voucher voucher)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsJsonAsync("voucher/updatevoucher", voucher);
                response.Wait();
                var result = response.Result;
                if (result.IsSuccessStatusCode)
                {
                    rs = result.Content.ReadAsAsync<int>().Result;
                }
            }

            return rs;
        }

        public int DeleteVoucher(int id)
        {
            int rs = -1;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Common.baseUrl);
                var response = client.PutAsync($"voucher/deletevoucher?id={id}", null);
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