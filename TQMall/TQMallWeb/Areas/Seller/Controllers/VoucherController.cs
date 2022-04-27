using DataAccessLayer;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TQMallWeb.Models;

namespace TQMall.Areas.Seller.Controllers
{
    public class VoucherController : Controller
    {
        VoucherDAL voucherDAL = new VoucherDAL();
        // GET: Seller/Voucher
        public ActionResult Index(int?page)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var user = Session["username"] as string;
            var model = voucherDAL.GetVouchers(user);
            model.Reverse();
            return View(model.ToPagedList(pageIndex, pageSize));
          
        }

        public ActionResult Create()
        {

            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Voucher voucher)
        {

          
            var user = Session["username"] as string;
            voucher.Username = user;
            voucher.ID = new Random().Next(100000,int.MaxValue)+"";
            voucher.Status = true;
            int rs = voucherDAL.AddVoucher(voucher);
           
            if (rs > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Không thành công";
                return View(voucher);
            }
        }
        public ActionResult Details(int id)
        {
            var model =voucherDAL.GetVoucherById(id);
            if (model == null)
            {
                return RedirectToAction("Index");
            }
            return View(model);
        }
        public ActionResult Update(int id)
        {

            var model = voucherDAL.GetVoucherById(id);
            if (model == null)
            {
                return RedirectToAction("Index");
            }
            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Update(Voucher voucher)
        {
            voucher.Status = true;
            int rs = voucherDAL.UpdateVoucher(voucher);

            if (rs > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Không thành công";
                return View(voucher);
            }
        }
        // GET: Seller/Voucher/Delete/5
        public ActionResult Delete(int id)
        {
            int rs = voucherDAL.DeleteVoucher(id);
            if(rs > 0)
            {
                return RedirectToAction("Index");
            }
            return View();
        }
    }
}
