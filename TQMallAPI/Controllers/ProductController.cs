using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class ProductController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/product/getproductseller")]
        public IQueryable<Product> GetProductSeller(string username)
        {
            var model = _dbContext.Products.Where(x => x.Username == username);
            model.Reverse();
            return model.Where(x => x.Status == true);
        }

        [HttpGet]
        [Route("api/product/getproductbybrand")]
        public IEnumerable<Product> GetProductByBrand(int idBrand, int page, int size)
        {
            var model = _dbContext.Brands.FirstOrDefault(x => x.ID == idBrand)?.Products;
            List<Product> products = new List<Product>();
            foreach (var item in model)
            {
                Product product = new Product()
                {
                    ID = item.ID,
                    Name = item.Name,
                    Cost = item.Cost,
                    Sale = item.Sale,
                    Image = item.Image,
                };

                products.Add(product);
            }

            int quantity = page * size;
            if (quantity > products.Count())
            {
                return products.ToList();
            }

            return products.Take(quantity).ToList();
        }

        [HttpGet]
        [Route("api/product/getproductbycategory")]
        public IEnumerable<Product> GetProductByCategory(int idCategory, int page, int size)
        {
            var model = _dbContext.Categories.FirstOrDefault(x => x.ID == idCategory)?.Products;
            List<Product> products = new List<Product>();
            foreach (var item in model)
            {
                Product product = new Product()
                {
                    ID = item.ID,
                    Name = item.Name,
                    Cost = item.Cost,
                    Sale = item.Sale,
                    Image = item.Image,
                };

                products.Add(product);
            }

            int quantity = page * size;
            if (quantity > products.Count())
            {
                return products.ToList();
            }

            return products.Take(quantity).ToList();
        }

        [HttpGet]
        [Route("api/product/getproductbyid")]
        public Product GetProductByID(int id)
        {
            var model = _dbContext.Products.Find(id);
            Product product = new Product()
            {
                ID = model.ID,
                Name = model.Name,
                Cost = model.Cost,
                Sale = model.Sale,
                Image = model.Image,
                Description = model.Description,
                Details = model.Details,
                Quantity = model.Quantity,
            };
            return product;
        }

        [HttpGet]
        [Route("api/product/getproductlist")]
        public IEnumerable<Product> GetProductList(string keyword)
        {
            var model = _dbContext.Products.Where(x => x.Status == true).ToList();
            List<Product> products = new List<Product>();
            if (string.IsNullOrEmpty(keyword))
            {
                model.Reverse();
                foreach (var  item in model)
                {
                    Product product = new Product
                    {
                        Cost = item.Cost,
                        Sale = item.Sale,
                        Details = item.Details,
                        Quantity = item.Quantity,
                        Status = item.Status,
                        Description = item.Description,
                        FlashSaleFrom = item.FlashSaleFrom,
                        FlashSaleTo = item.FlashSaleTo,
                        Image = item.Image,
                        ID = item.ID,
                        Name = item.Name,
                        Username = item.Username,
                        IDCategory = item.IDCategory,
                        IDBrand = item.IDBrand,

                    };
                    products.Add(product);
                }
                return products;
            }

            var list = model.Where(x => x.Name.ToLower().Contains(keyword.ToLower())||
                                        x.Category.Name.ToLower().Contains(keyword.ToLower())||
                                        x.Brand.Name.ToLower().Contains(keyword.ToLower())).ToList();
            list.Reverse();
           
            foreach (var  item in list)
            {
                Product product = new Product
                {
                    Cost = item.Cost,
                    Sale = item.Sale,
                    Details = item.Details,
                    Quantity = item.Quantity,
                    Status = item.Status,
                    Description = item.Description,
                    FlashSaleFrom = item.FlashSaleFrom,
                    FlashSaleTo = item.FlashSaleTo,
                    Image = item.Image,
                    ID = item.ID,
                    Name = item.Name,
                    Username = item.Username,
                    IDCategory = 0,
                    IDBrand = item.IDBrand,

                };
                products.Add(product);
            }
            return products;
        }

        [HttpGet]
        [Route("api/product/getproductmobile")]
        public IEnumerable<Product> GetProducts(string keyword, int page, int size)
        {
            var model = _dbContext.Products.Where(x => x.Status == true);
            List<Product> products = new List<Product>();
            foreach (var item in model)
            {
                Product product = new Product()
                {
                    ID = item.ID,
                    Name = item.Name,
                    Cost = item.Cost,
                    Sale = item.Sale,
                    Image = item.Image,
                    Quantity = item.Quantity
                };
                if (string.IsNullOrEmpty(keyword))
                {
                    products.Add(product);
                }
                else
                {
                    if (item.Name.ToLower().Contains(keyword.ToLower()) ||
                        item.Brand.Name.ToLower().Contains(keyword.ToLower()) ||
                        item.Category.Name.ToLower().Contains(keyword.ToLower()))
                    {
                        products.Add(product);
                    }
                }
            }

            int quantity = page * size;
            if (quantity > products.Count())
            {
                return products.ToList();
            }

            return products.Take(quantity).ToList();
        }

        [HttpPost]
        [Route("api/product/addproduct")]
        public int AddProduct([FromBody] Product product)
        {
            product.Status = true;
            _dbContext.Products.Add(product);
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/product/updateproduct")]
        public int UpdateProduct([FromBody] Product product)
        {
            _dbContext.Products.Add(product);
            _dbContext.Entry(product).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/product/deleteproduct")]
        public int DeleteProduct(int id)
        {
            var model = _dbContext.Products.Find(id);
            model.Status = false;
            _dbContext.Products.Add(model);
            _dbContext.Entry(model).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }


        [HttpGet]
        [Route("api/product/getproductflashsale")]
        public IHttpActionResult GetProductSale(int page, int size)
        {
            List<Product> products = new List<Product>();
            int now = Convert.ToInt32(DateTime.Now.Hour);
            var model = _dbContext.Products.Where(x =>
                x.FlashSaleFrom != null && x.FlashSaleFrom != null && x.Status == true);
            model = model.Where(x => x.FlashSaleFrom.Value <= now && x.FlashSaleTo.Value >= now);
            foreach (var item in model)
            {
                Product product = new Product()
                {
                    ID = item.ID,
                    Name = item.Name,
                    Cost = item.Cost,
                    Sale = item.Sale,
                    Image = item.Image,
                };
                products.Add(product);
            }

            int quantity = page * size;
            if (quantity > products.Count())
            {
                return Ok(products);
            }

            return Ok(products.Take(quantity));
        }


        [HttpPost]
        [Route("api/product/uploadimage")]
        public void UploadImage()
        {
            HttpResponseMessage result = null;
            var httpRequest = HttpContext.Current.Request;
            HttpPostedFile postedFile = null;
            if (httpRequest.Files.Count > 0)
            {
                var docfiles = new List<string>();
                foreach (string file in httpRequest.Files)
                {
                    postedFile = httpRequest.Files[file];
                    var filePath = HttpContext.Current.Server.MapPath("~/Uploads/" + postedFile.FileName);
                    postedFile.SaveAs(filePath);
                    docfiles.Add(filePath);
                }

                result = Request.CreateResponse(HttpStatusCode.Created, docfiles);
            }
            else
            {
                result = Request.CreateResponse(HttpStatusCode.BadRequest);
            }
        }
    }
}