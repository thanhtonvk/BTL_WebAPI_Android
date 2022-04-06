using DataAccessLayer;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace TQMall.Areas.Seller.Controllers
{
    public class OrderController : Controller
    {
        // GET: Seller/Order
        OrderDAL orderDAL = new OrderDAL();
        OrderDetailsDAL orderDetailsDAL = new OrderDetailsDAL();
        public ActionResult Index(int? page, string keyword)
        {
            string username = Session["username"] as string;
            var model = orderDAL.GetOrderBySeller(username);
            model.Reverse();
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            if (string.IsNullOrEmpty(keyword))
            {
                return View(model.ToPagedList(pageIndex, pageSize));
            }
            else
            {
                var search = model.Where(x => x.Username.ToLower().Contains(keyword.ToLower()) || x.Address.ToLower().Contains(keyword.ToLower()) || x.PhoneNumber.ToLower().Contains(keyword.ToLower()));
                return View(search.ToPagedList(pageIndex, pageSize));
            }
        }
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            else
            {
                var listOrderDetails = orderDetailsDAL.GetOrderDetailsByIdOrder(id.Value);
                return View(listOrderDetails);
            }
        }
        public ActionResult Update(int? id, int status)
        {
            if (id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            else
            {
                var model = orderDAL.GetOrderByID(id.Value);
                if (model == null)
                {
                    return RedirectToAction("NotFound404", "Home", new { area = "" });
                }
                else
                {
                    model.Status = short.Parse(status + "");
                    int rs = orderDAL.Update(model);
                    if (rs > 0)
                    {
                        return RedirectToAction("Index");
                    }
                    return RedirectToAction("NotFound404", "Home", new { area = "" });
                }
            }
        }

    }
}