using DataAccessLayer;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TQMallWeb.Models;

namespace TQMall.Areas.Admin.Controllers
{
    public class BrandController : Controller
    {
        BrandDAL brandDAL = new BrandDAL();
        // GET: Admin/Brand
        public ActionResult Index(int? page, string keyword)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var model = brandDAL.GetBrandList().Where(x => x.Status == true);
            if (string.IsNullOrEmpty(keyword))
            {
                return View(model.ToPagedList(pageIndex, pageSize));
            }
            else
            {
                var searchProduct = model.Where(x => x.Name.ToLower().Contains(keyword.ToLower()));
                return View(searchProduct.ToPagedList(pageIndex, pageSize));
            }
        }
        public ActionResult Delete(int id)
        {
            brandDAL.DeleteBrand(id);
            return RedirectToAction("Index", "Brand");
        }
        public ActionResult Create()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Brand brand)
        {
            if (!string.IsNullOrEmpty(brand.Name) && !string.IsNullOrEmpty(brand.Image))
            {
                int result = brandDAL.AddBrand(brand);
                if (result < 1)
                {
                    ViewBag.Noti = "Thêm không thành công, kiếm tra kết nối";
                }
                return RedirectToAction("Index", "Brand");
            }
            return View(brand);
        }
        public ActionResult Update(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            var model = brandDAL.GetBrandById(id.Value);
            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Update(Brand brand)
        {
            if (!string.IsNullOrEmpty(brand.Name) && !string.IsNullOrEmpty(brand.Image))
            {
                brand.Status = true;
                int result = brandDAL.UpdateBrand(brand);
                if (result < 1)
                {
                    ViewBag.Noti = "Cập nhật không thành công, kiếm tra kết nối";
                }
                return RedirectToAction("Index", "Brand");
            }
            return View(brand);
        }
    }
}