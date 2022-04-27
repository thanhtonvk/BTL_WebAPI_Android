using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class AccountController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/accounts/getaccounts")]
        public IEnumerable<Account> GetAccountList()
        {
            var model = new List<Account>();
            foreach (var item in _dbContext.Accounts)
            {
                Account account = new Account
                {
                    Username = item.Username,
                    Password = item.Password,
                    FullName = item.FullName,
                    Avatar = item.Avatar,
                    DateOfBirth = item.DateOfBirth,
                    PhoneNumber = item.PhoneNumber,
                    Address = item.Address,
                    Status = item.Status,
                    DataUser = item.DataUser,
                    Products = null,
                    Orders = null,
                    ReviewProducts = null,
                    Vouchers = null,
                    Carts = null
                };
                model.Add(account);
            }

            return model;
        }

        [HttpGet]
        [Route("api/accounts/getaccountbyusername")]
        public Account GetAccountByUsername(string username)
        {
            var model = _dbContext.Accounts.FirstOrDefault(x => x.Username.Equals(username));
            model.Carts.Clear();
            model.Orders.Clear();
            model.Products.Clear();
            model.Vouchers.Clear();
            model.ReviewProducts.Clear();
            return model;
        }

        [HttpGet]
        [Route("api/accounts/login")]
        public int Login(string username, string password)
        {
            var model = _dbContext.Accounts.FirstOrDefault(x =>
                x.Username.Equals(username) && x.Password.Equals(password));
            if (model == null)
            {
                return 0;
            }

            return model.Status.Value;
        }

        [HttpPost]
        [Route("api/accounts/register")]
        public int Register([FromBody] Account account)
        {
            var model = _dbContext.Accounts.FirstOrDefault(x =>
                x.Username.ToLower().Equals(account.Username.ToLower()));
            if (model == null)
            {
                _dbContext.Accounts.Add(account);
                return _dbContext.SaveChanges();
            }

            return 0;
        }

        [HttpPut]
        [Route("api/accounts/update")]
        public int Update([FromBody] Account account)
        {
            var model = _dbContext.Accounts.Find(account.Username);
            if (model != null)
            {
                model.Address = account.Address;
                model.Avatar = account.Avatar;
                model.Password = account.Password;
                model.Status = account.Status;
                model.DataUser = account.DataUser;
                model.FullName = account.FullName;
                model.PhoneNumber = account.PhoneNumber;
                model.DateOfBirth = account.DateOfBirth;
                _dbContext.Accounts.Attach(model);
                _dbContext.Entry(model).State = EntityState.Modified;
                return _dbContext.SaveChanges();
            }

            return 0;
        }

        [HttpPut]
        [Route("api/accounts/block")]
        public int Block(string username)
        {
            var model = _dbContext.Accounts.FirstOrDefault(x => x.Username == username);
            model.Status = 4;
            _dbContext.Accounts.Add(model);
            _dbContext.Entry(model).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }
    }
}