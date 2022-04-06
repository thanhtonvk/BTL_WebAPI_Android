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
        public IQueryable<Account> GetAccountList()
        {
            return _dbContext.Accounts;
        }

        [HttpGet]
        [Route("api/accounts/getaccountbyusername")]
        public Account GetAccountByUsername(string username)
        {
            var model = _dbContext.Accounts.FirstOrDefault(x => x.Username.Equals(username));
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
            var model = _dbContext.Accounts.FirstOrDefault(x =>
                x.Username.ToLower().Equals(account.Username.ToLower()));
            if (model != null)
            {
                _dbContext.Accounts.Add(account);
                _dbContext.Entry(account).State = EntityState.Modified;
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