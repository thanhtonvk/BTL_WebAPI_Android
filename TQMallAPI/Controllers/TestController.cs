using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class TestController : Controller
    {
        private DBContext db = new DBContext();

        // GET: Test
        public ActionResult Index()
        {
            var products = db.Products.Include(p => p.Account).Include(p => p.Brand).Include(p => p.Category);
            return View(products.ToList());
        }

        // GET: Test/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Product product = db.Products.Find(id);
            if (product == null)
            {
                return HttpNotFound();
            }
            return View(product);
        }

        // GET: Test/Create
        public ActionResult Create()
        {
            ViewBag.Username = new SelectList(db.Accounts, "Username", "Password");
            ViewBag.IDBrand = new SelectList(db.Brands, "ID", "Name");
            ViewBag.IDCategory = new SelectList(db.Categories, "ID", "Name");
            return View();
        }

        // POST: Test/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,Username,IDCategory,IDBrand,Cost,Sale,FlashSaleFrom,FlashSaleTo,Image,Description,Details,Quantity,Status")] Product product)
        {
            if (ModelState.IsValid)
            {
                db.Products.Add(product);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.Username = new SelectList(db.Accounts, "Username", "Password", product.Username);
            ViewBag.IDBrand = new SelectList(db.Brands, "ID", "Name", product.IDBrand);
            ViewBag.IDCategory = new SelectList(db.Categories, "ID", "Name", product.IDCategory);
            return View(product);
        }

        // GET: Test/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Product product = db.Products.Find(id);
            if (product == null)
            {
                return HttpNotFound();
            }
            ViewBag.Username = new SelectList(db.Accounts, "Username", "Password", product.Username);
            ViewBag.IDBrand = new SelectList(db.Brands, "ID", "Name", product.IDBrand);
            ViewBag.IDCategory = new SelectList(db.Categories, "ID", "Name", product.IDCategory);
            return View(product);
        }

        // POST: Test/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,Username,IDCategory,IDBrand,Cost,Sale,FlashSaleFrom,FlashSaleTo,Image,Description,Details,Quantity,Status")] Product product)
        {
            if (ModelState.IsValid)
            {
                db.Entry(product).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.Username = new SelectList(db.Accounts, "Username", "Password", product.Username);
            ViewBag.IDBrand = new SelectList(db.Brands, "ID", "Name", product.IDBrand);
            ViewBag.IDCategory = new SelectList(db.Categories, "ID", "Name", product.IDCategory);
            return View(product);
        }

        // GET: Test/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Product product = db.Products.Find(id);
            if (product == null)
            {
                return HttpNotFound();
            }
            return View(product);
        }

        // POST: Test/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Product product = db.Products.Find(id);
            db.Products.Remove(product);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
