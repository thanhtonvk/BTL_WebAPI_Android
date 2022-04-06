using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using DataAccessLayer;
using TQMall.App_Data;
using TQMallWeb.Models;

namespace TQMall.Controllers
{
    public class HomeController : Controller
    {
        private AccountDAL _accountDal = new AccountDAL();

        public ActionResult Index()
        {
            return View();
        }
        [HttpGet]
        public ActionResult Index(string username, string password)
        {
            if (username != null || password != null)
            {
                var f_password = Utils.GetMD5(password);
                int result = _accountDal.Login(username, password);
                if (result == -1)
                {
                    ViewBag.Noti = "Lỗi server";
                }
                else if (result == 0)
                {
                    ViewBag.Noti = "Tài khoản hoặc mật khẩu không chính xác";
                }
                else
                {
                    if (result == 1)
                    {

                        ViewBag.Noti = "Đây là tài khoản người dùng, vui lòng đăng nhập bằng app";
                    }
                    else if (result == 2)
                    {
                        Session["username"] = username;
                        return RedirectToAction("Index", "Account", new { area = "Admin" });
                    }
                    else if (result == 3)
                    {
                        Session["username"] = username;
                        return RedirectToAction("Index", "Dashboard", new { area = "Seller" });
                    }
                    else
                    {
                        ViewBag.Noti = "Tài khoản đã bị khóa";
                    }
                }
            }

            return View();
        }

        public ActionResult Register()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Register(Account account)
        {
            int result = _accountDal.Register(account);
            if (result > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Đăng ký thất bại, có thể tài khoản đã tồn tại";
            }
            return View(account);
        }
        public ActionResult NotFound404()
        {
            return View();
        }

    }
}