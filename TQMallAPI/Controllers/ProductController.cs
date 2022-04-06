using System.Collections.Generic;
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
            return model.Where(x=>x.Status ==true);
        }

        [HttpGet]
        [Route("api/product/getproductbyid")]
        public Product GetProductByID(int id)
        {
            return _dbContext.Products.Find(id);
        }

        [HttpGet]
        [Route("api/product/getproductlist")]
        public IQueryable GetProductList(string keyword)
        {
            var model = _dbContext.Products.Where(x => x.Status == true);
            if (string.IsNullOrEmpty(keyword))
            {
                model.Reverse();
                return model;
            }

            model = model.Where(x => x.Name.ToLower().Contains(keyword.ToLower()) ||
                                     x.Details.ToLower().Contains(keyword.ToLower()) ||
                                     x.Description.ToLower().Contains(keyword.ToLower()) ||
                                     x.Category.Name.ToLower().Contains(keyword.ToLower()));
            model.Reverse();
            return model;
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