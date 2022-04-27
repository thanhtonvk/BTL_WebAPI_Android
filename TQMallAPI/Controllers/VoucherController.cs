using System.Collections.Generic;
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
        public IEnumerable<Voucher> GetVoucherByAccount(string account)
        {
            List<Voucher> vouchers = new List<Voucher>();
            var model =  _dbContext.Vouchers.Where(x => x.Username == account && x.Quantity>0&&x.Status==true);
            foreach (var voucher in model)
            {
                Voucher vc = new Voucher()
                {
                    ID = voucher.ID,
                    Status = voucher.Status,
                    Quantity = voucher.Quantity,
                    Sale = voucher.Sale,
                   Username = voucher.Username
                };
                vouchers.Add(vc);
            }
            return vouchers;
        }
        [HttpGet]
        [Route("api/voucher/getvouchers")]
        public IEnumerable<Voucher> GetVouchers()
        {
            List<Voucher> vouchers = new List<Voucher>();
            var model = _dbContext.Vouchers.Where(x=>x.Quantity > 0 && x.Status == true);
            foreach (var voucher in model)
            {
                Voucher vc = new Voucher()
                {
                    ID = voucher.ID,
                    Status = voucher.Status,
                    Quantity = voucher.Quantity,
                    Sale = voucher.Sale,
                    Username = voucher.Username
                };
                vouchers.Add(vc);
            }
            return vouchers;
        }

        [HttpGet]
        [Route("api/voucher/getvoucherbyid")]
        public Voucher GetVoucherByID(int id)
        {
            return _dbContext.Vouchers.Find(id);
        }
    }
}