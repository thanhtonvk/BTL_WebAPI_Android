using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class ImageProductController : ApiController
    {
        private DBContext _dbContext = new DBContext();
        [HttpPost]
        [Route("api/imageproduct/addimageproduct")]
        public int AddImageProduct([FromBody] ImageProduct imageProduct)
        {
            imageProduct.Status = true;
            _dbContext.ImageProducts.Add(imageProduct);
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/imageproduct/updateimageproduct")]
        public int UpdateImageProduct([FromBody] ImageProduct imageProduct)
        {
            _dbContext.ImageProducts.Add(imageProduct);
            _dbContext.Entry(imageProduct).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/imageproduct/deleteimageproduct")]
        public int DeleteImageProduct(int id)
        {
            var model = _dbContext.ImageProducts.Find(id);
          
            if (model != null)
            {
                model.Status = false;
                _dbContext.ImageProducts.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }

            return _dbContext.SaveChanges();
        }

        [HttpGet]
        [Route("api/imageproduct/getimageproduct")]
        public IQueryable<ImageProduct> GetImageProduct(int idProduct)
        {
            return _dbContext.ImageProducts.Where(x => x.IDProduct == idProduct);
        }
    }
}