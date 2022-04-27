using System.Collections.Generic;
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
        public IEnumerable<Brand> GetBrandList()
        {
            var model = _dbContext.Brands.Where(x => x.Status == true);
            List<Brand> list = new List<Brand>();
            foreach (var item in model)
            {
                Brand brand = new Brand() {ID = item.ID, Name = item.Name, Image = item.Image, Status = item.Status};
                list.Add(brand);
            }

            return list;
        }

        [HttpGet]
        [Route("api/brands/getbrandbyid")]
        public Brand GetBrandByID(int id)
        {
            var model = _dbContext.Brands.Find(id);
            Brand brand = new Brand() {ID = model.ID, Name = model.Name, Image = model.Image,Status = model.Status};
            return brand;
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