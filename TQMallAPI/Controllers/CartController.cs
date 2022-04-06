using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class CartController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/cart/getcarbyuser")]
        public IQueryable<Cart> GetCartByUser(string username)
        {
            return _dbContext.Carts.Where(x => x.Username.Equals(username));
        }

        [HttpPut, HttpPost]
        [Route("api/cart/addcart")]
        public int AddCart([FromBody] Cart cart)
        {
            int index = IsExist(cart.IDProductDetails);
            if (index > -1)
            {
                var model = _dbContext.Carts.ToList()[index];
                model.Quantity += 1;
                _dbContext.Carts.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }
            else
            {
                _dbContext.Carts.Add(cart);
            }

            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/cart/updatecart")]
        public int UpdateCart(int id, int quantity)
        {
            var model = _dbContext.Carts.Find(id);
            if (model != null)
            {
                model.Quantity += quantity;
                _dbContext.Carts.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }

            return _dbContext.SaveChanges();
        }

        [HttpDelete]
        [Route("api/cart/deletecart")]
        public int DeleteCart(int id)
        {
            var model = _dbContext.Carts.Find(id);
            if (model != null) _dbContext.Carts.Remove(model);
            return _dbContext.SaveChanges();
        }

        [HttpDelete]
        [Route("api/cart/deleteallcart")]
        public int DeleteAll(string username)
        {
            _dbContext.Carts.RemoveRange(_dbContext.Carts.Where(x => x.Username == username));
            return _dbContext.SaveChanges();
        }

        public int IsExist(int idProductDetails)
        {
            var model = _dbContext.Carts.FirstOrDefault(x => x.IDProductDetails == idProductDetails);
            if (model == null) return -1;
            return _dbContext.Carts.ToList().IndexOf(model);
        }
    }
}