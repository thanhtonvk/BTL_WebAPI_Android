using System;
using System.Linq;
using System.Web.Mvc;
using DataAccessLayer;
using PagedList;
using TQMallWeb.Models;

namespace TQMall.Areas.Admin.Controllers
{
    public class AccountController : Controller
    {
        private AccountDAL _accountDal = new AccountDAL();
        // GET
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult Block(string username)
        {
            _accountDal.Block(username);
            return RedirectToAction("Index");
        }
        public ActionResult SellerView(int?page, string keyword)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var model = _accountDal.GetAccountList().Where(x => x.Status == 3);
            if (string.IsNullOrEmpty(keyword))
            {
                return View(model.ToPagedList(pageIndex, pageSize));
            }
            else
            {
                var searchProduct = model.Where(x => x.FullName.ToLower().Contains(keyword.ToLower()) || x.Username.ToLower().Contains(keyword.ToLower()) || x.Address.ToLower().Contains(keyword.ToLower()));
                return View(searchProduct.ToPagedList(pageIndex, pageSize));
            }
        }
        public ActionResult UserView(int?page, string keyword)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var model = _accountDal.GetAccountList().Where(x => x.Status == 1);
            if (string.IsNullOrEmpty(keyword))
            {
                return View(model.ToPagedList(pageIndex, pageSize));
            }
            else
            {
                var searchProduct = model.Where(x => x.FullName.ToLower().Contains(keyword.ToLower()) || x.Username.ToLower().Contains(keyword.ToLower()) || x.Address.ToLower().Contains(keyword.ToLower()));
                return View(searchProduct.ToPagedList(pageIndex, pageSize));
            }
        }
        public ActionResult Logout()
        {
            Session.RemoveAll();
            return RedirectToAction("Index", "Home", new { area = "" });
        }

       
       
    }
}