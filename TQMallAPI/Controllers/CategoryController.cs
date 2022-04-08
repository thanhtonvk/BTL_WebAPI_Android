using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class CategoryController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/categories/getcategories")]
        public IEnumerable<Category> GetCategoryList()
        {
            List<Category> list = new List<Category>();
            var model = _dbContext.Categories.Where(x => x.Status == true);
            foreach (var item in model)
            {
                Category category = new Category()
                {
                    ID = item.ID,
                    Name = item.Name,
                    Image = item.Image,
                };
                list.Add(category);
            }
            return list;
        }

        [HttpGet]
        [Route("api/categories/getcategorybyid")]
        public Category GetCategoryByID(int id)
        {
            var model = _dbContext.Categories.Find(id);
            Category category = new Category()
            {
                ID = model.ID,
                Name = model.Name,
                Image = model.Image,
            };
            return model;
        }

        [HttpPost]
        [Route("api/categories/postcategory")]
        public int PostCategory([FromBody] Category category)
        {

            category.Status = true;
            _dbContext.Categories.Add(category);
            return _dbContext.SaveChanges();



        }

        [HttpPut]
        [Route("api/categories/putcategory")]
        public int PutCategory([FromBody] Category category)
        {
            _dbContext.Categories.Add(category);
            _dbContext.Entry(category).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/categories/deletecategory")]
        public int DeleteCategory(int id)
        {
            var model = _dbContext.Categories.Find(id);
            if (model != null)
            {
                model.Status = false;
                _dbContext.Categories.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
                return _dbContext.SaveChanges();
            }

            return 0;
        }

        public bool checkExist(string name)
        {
            var model = _dbContext.Categories.FirstOrDefault(x => x.Name == name);
            if (model != null)
            {
                return false;
            }

            return true;
        }
    }
}