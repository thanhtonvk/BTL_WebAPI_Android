using DataAccessLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TQMallWeb.Models;

namespace TQMall.Areas.Seller.Controllers
{
    public class ProductDetailsController : Controller
    {
        ProductDetailsDAL productDetailsDAL = new ProductDetailsDAL();
        // GET: Seller/ProductDetails
        public ActionResult Index(int? idProduct)
        {
            if (idProduct == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            Session["idProduct"] = idProduct;
            var model = productDetailsDAL.GetProductDetailByIdProduct(idProduct.Value);
            if (model == null)
            {
                return View(new List<ProductDetail>());
            }
            return View(model);
        }

        public ActionResult Create()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(ProductDetail productDetail)
        {
            int idProduct = int.Parse(Session["idProduct"].ToString());
            productDetail.IDProduct = idProduct;
            if (ModelState.IsValid)
            {
                int rs = productDetailsDAL.AddProductDetails(productDetail);

                if (rs > 0)
                {
                    return RedirectToAction("Index", new { idProduct = idProduct });
                }
                else
                {
                    ViewBag.Noti = "Không thành công";
                    return View(productDetail);
                }

            }
            return View(productDetail);
        }
        public ActionResult Update(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            else
            {
                var model = productDetailsDAL.GetProductDetailsById(id.Value);
                return View(model);
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Update(ProductDetail productDetail)
        {
            int idProduct = int.Parse(Session["idProduct"].ToString());
            productDetail.IDProduct = idProduct;
            productDetail.Status = true;
            if (ModelState.IsValid)
            {
                int rs = productDetailsDAL.UpdateProductDetails(productDetail);

                if (rs > 0)
                {
                    return RedirectToAction("Index", new { idProduct = idProduct });
                }
                else
                {
                    ViewBag.Noti = "Không thành công";
                    return View(productDetail);
                }

            }
            return View(productDetail);
        }
        public ActionResult Delete(int? id)
        {
            if(id == null)
            {
                return RedirectToAction("NotFound404", "Home", new { area = "" });
            }
            else
            {
                int idProduct = int.Parse(Session["idProduct"].ToString());
              
                int rs = productDetailsDAL.DeleteProductDetails(id.Value);
                if (rs > 0)
                {
                    return RedirectToAction("Index", new { idProduct = idProduct });
                }
                else
                {
                    ViewBag.Noti = "Không thành công";
                    return View();
                }
            }

        }
    }
}