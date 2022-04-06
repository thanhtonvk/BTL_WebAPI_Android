using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TQMallWeb.Models;

namespace TQMall.Areas.Seller.Controllers
{
    public class DashboardController : Controller
    {
        // GET: Seller/Home
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult Logout()
        {
            Session.RemoveAll();
            return RedirectToAction("Index", "Home", new {area = ""});
        }
      
    }
}