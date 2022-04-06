using System;
using System.Data.Entity;
using System.IO;
using System.Linq;
using System.Text;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class ProductDetailsController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/productdetails/getproductdetailsbyid")]
        public ProductDetail GetProductDetailsByID(int id)
        {
            return _dbContext.ProductDetails.Find(id);
        }

        [HttpGet]
        [Route("api/productdetails/getproductdetailsbyidproduct")]
        public IQueryable<ProductDetail> GetProductDetailByIdProduct(int idProduct)
        {
            return _dbContext.ProductDetails.Where(x => x.IDProduct == idProduct&&x.Status==true);
        }

        [HttpPost]
        [Route("api/productdetails/add")]
        public int AddProductDetails([FromBody] ProductDetail productDetail)
        {
            productDetail.Status = true;
            _dbContext.ProductDetails.Add(productDetail);
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/productdetails/update")]
        public int UpdateProductDetails([FromBody] ProductDetail productDetail)
        {
            _dbContext.ProductDetails.Add(productDetail);
            _dbContext.Entry(productDetail).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/productdetails/delete")]
        public int DeleteProductDetails(int id)
        {
            var model = _dbContext.ProductDetails.Find(id);
            model.Status = false;
            _dbContext.ProductDetails.Add(model);
            _dbContext.Entry(model).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }
    }
}