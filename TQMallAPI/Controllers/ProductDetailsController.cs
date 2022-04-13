using System;
using System.Collections.Generic;
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
            var item = _dbContext.ProductDetails.Find(id);
            ProductDetail productDetail = new ProductDetail()
            {
                ID = item.ID,
                Cost = item.Cost,
                Image = item.Image,
                Name = item.Name,
                IDProduct = item.IDProduct
            };
            return productDetail;
        }

        [HttpGet]
        [Route("api/productdetails/getproductdetailsbyidproduct")]
        public IEnumerable<ProductDetail> GetProductDetailByIdProduct(int idProduct)
        {
            var model = _dbContext.ProductDetails.Where(x => x.IDProduct == idProduct && x.Status == true);
            List<ProductDetail> productDetails = new List<ProductDetail>();
            foreach (var item in model)
            {
                ProductDetail productDetail = new ProductDetail()
                {
                    ID = item.ID,
                    Cost = item.Cost,
                    Image = item.Image,
                    Name = item.Name,
                    IDProduct = item.IDProduct
                };
                productDetails.Add(productDetail);
            }

            return productDetails;
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