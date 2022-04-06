using System.Data.Entity;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class ReviewController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpPost]
        [Route("api/voucher/addvoucher")]
        public int AddVoucher([FromBody] Voucher voucher)
        {
            voucher.Status = true;
            _dbContext.Vouchers.Add(voucher);
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/voucher/updatevoucher")]
        public int UpdateVoucher([FromBody] Voucher voucher)
        {
            voucher.Status = true;
            _dbContext.Vouchers.Add(voucher);
            _dbContext.Entry(voucher).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/voucher/deletevoucher")]
        public int DeleteVoucher(int id)
        {
            var model = _dbContext.Vouchers.Find(id);
            model.Status = false;
            _dbContext.Vouchers.Add(model);
            _dbContext.Entry(model).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpGet]
        [Route("api/voucher/getvoucherbyaccount")]
        public IQueryable<Voucher> GetVoucherByAccount(string account)
        {
            return _dbContext.Vouchers.Where(x => x.Username == account);
        }

        [HttpGet]
        [Route("api/voucher/getvoucherbyid")]
        public Voucher GetVoucherByID(int id)
        {
            return _dbContext.Vouchers.Find(id);
        }
    }
}