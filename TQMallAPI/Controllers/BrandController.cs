using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class BrandController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/brands/getbrands")]
        public IQueryable<Brand> GetBrandList()
        {
            return _dbContext.Brands.Where(x => x.Status == true);
        }

        [HttpGet]
        [Route("api/brands/getbrandbyid")]
        public Brand GetBrandByID(int id)
        {
            var model = _dbContext.Brands.Find(id);
            return model;
        }

        [HttpPost]
        [Route("api/brands/postbrand")]
        public int PostBrand([FromBody] Brand brand)
        {
            brand.Status = true;
            _dbContext.Brands.Add(brand);
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/brands/putbrand")]
        public int PutBrand([FromBody] Brand brand)
        {
            _dbContext.Brands.Add(brand);
            _dbContext.Entry(brand).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/brands/deletebrand")]
        public int DeleteBrand(int id)
        {
            var model = _dbContext.Brands.Find(id);
            if (model != null)
            {
                model.Status = false;
                _dbContext.Brands.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
                return _dbContext.SaveChanges();
            }

            return 0;
        }
    }
}