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
    public class CategoryController : Controller
    {
        CategoryDAL categoryDAL = new CategoryDAL();
        // GET: Admin/Category
        public ActionResult Index(int? page, string keyword)
        {
            int pageSize = 10;
            int pageIndex = 1;
            pageIndex = page.HasValue ? Convert.ToInt32(page) : 1;
            var model = categoryDAL.GetCategoryList().Where(x => x.Status == true);
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
            categoryDAL.DeleteCategory(id);
            return RedirectToAction("Index", "Category");
        }
        public ActionResult Create()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Category category)
        {
            if (!string.IsNullOrEmpty(category.Name) && !string.IsNullOrEmpty(category.Image))
            {
                int result = categoryDAL.AddCategory(category);
                if (result < 1)
                {
                    ViewBag.Noti = "Thêm không thành công, kiếm tra kết nối";
                }
                return RedirectToAction("Index", "Category");
            }
            return View(category);
        }
        public ActionResult Update(int? id)
        {
            if(id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            var model = categoryDAL.GetCategoryById(id.Value);
            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Update(Category category)
        {
            if (!string.IsNullOrEmpty(category.Name) && !string.IsNullOrEmpty(category.Image))
            {
                category.Status = true;
                int result = categoryDAL.UpdateCategory(category);
                if (result < 1)
                {
                    ViewBag.Noti = "Cập nhật không thành công, kiếm tra kết nối";
                }
                return RedirectToAction("Index", "Category");
            }
            return View(category);
        }

    }
}