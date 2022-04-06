using DataAccessLayer;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TQMallWeb.Models;
using SelectListItem = System.Web.WebPages.Html.SelectListItem;

namespace TQMall.Areas.Seller.Controllers
{
    public class ProductController : Controller
    {
        ProductDAL productDAL = new ProductDAL();

        private BrandDAL _brandDal = new BrandDAL();

        private CategoryDAL _categoryDal = new CategoryDAL();
        // GET: Seller/Product
        public ActionResult Index(int? page, string keyword)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var user = Session["username"] as string;
            var model = productDAL.GetProductList("").Where(x=>x.Username==user);
            model.Reverse();
            if (string.IsNullOrEmpty(keyword))
            {
                return View(model.ToPagedList(pageIndex, pageSize));
            }
            else
            {
                var searchProduct = model.Where(x => x.Name.ToLower().Contains(keyword.ToLower()) || x.Details.ToLower().Contains(keyword.ToLower()) || x.Description.ToLower().Contains(keyword.ToLower()));
                return View(searchProduct.ToPagedList(pageIndex, pageSize));
            }

        }
        [HttpPost]
        public JsonResult Prefix(string prefix)
        {
            var user = Session["username"] as string;
            var model = productDAL.GetProductList("").Where(x => x.Username == user);

            var name = (from N in model
                        where N.Name.StartsWith(prefix)
                        select new { N.Name });
            return Json(name, JsonRequestBehavior.AllowGet);
        }
        public ActionResult Create()
        {

            ViewBag.Brand = new SelectList(_brandDal.GetBrandList(), "ID", "Name");
            ViewBag.Category = new SelectList(_categoryDal.GetCategoryList(), "ID", "Name");
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Product product)
        {

            ViewBag.Brand = new SelectList(_brandDal.GetBrandList(), "ID", "Name");
            ViewBag.Category = new SelectList(_categoryDal.GetCategoryList(), "ID", "Name");
            var user = Session["username"] as string;
            product.Username = user;
            product.Status = true;
            int rs = productDAL.AddProduct(product);
            if (rs > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Không thành công";
                return View(product);
            }
        }
        public ActionResult Details(int id)
        {
            var model = productDAL.GetProductByID(id);
            if (model == null)
            {
                return RedirectToAction("Index");
            }
            return View(model);
        }
        public ActionResult Update(int id)
        {
            ViewBag.Brand = new SelectList(_brandDal.GetBrandList(), "ID", "Name");
            ViewBag.Category = new SelectList(_categoryDal.GetCategoryList(), "ID", "Name");
            var model = productDAL.GetProductByID(id);
            if (model == null)
            {
                return RedirectToAction("Index");
            }
            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Update(Product product)
        {
            ViewBag.Brand = new SelectList(_brandDal.GetBrandList(), "ID", "Name");
            ViewBag.Category = new SelectList(_categoryDal.GetCategoryList(), "ID", "Name");
            int rs = productDAL.UpdateProduct(product);
            if (rs > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Không thành công";
                return View(product);
            }
        }
        public ActionResult Delete(int id)
        {
            int rs = productDAL.DeleteProduct(id);
            if (rs > 0)
            {
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.Noti = "Không thành công";
                return View();
            }
        }
    }
}