using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class OrderDetailsController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpPost]
        [Route("api/ordertdetails/addorderdetails")]
        public int AddOrderDetails([FromBody] OrderDetail orderDetail)
        {
            _dbContext.OrderDetails.Add(orderDetail);
            UpdateQuantity(orderDetail.ProductDetail.Product.ID,orderDetail.Quantity);
            return _dbContext.SaveChanges();
        }

        [HttpGet]
        [Route("api/orderdetails/getorderdetails")]
        public IQueryable<OrderDetail> GetOrderDetails(int idOrder)
        {
            return _dbContext.OrderDetails.Where(x => x.IDOrder == idOrder);
        }

        [HttpPost]
        [Route("api/orderdetails/getorderdetailsbyid")]
        public OrderDetail GetOrderDetailsById(int id)
        {
            return _dbContext.OrderDetails.Find(id);
        }

        private void UpdateQuantity(int idProduct, int quantity)
        {
            var model = _dbContext.Products.Find(idProduct);
            if (model != null)
            {
                model.Quantity -= quantity;
                _dbContext.Products.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
                _dbContext.SaveChanges();
            }
        }
    }
}