using System.Collections.Generic;
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
        public IHttpActionResult AddOrderDetails(string username)
        {
            var order = _dbContext.Accounts.FirstOrDefault(x => x.Username.Equals(username))
                ?.Orders
                .OrderByDescending(x => x.ID).ToList()[0];
            var carts = _dbContext.Carts.Where(x => x.Username == username);
            foreach (var item in carts)
            {
                if (item.Quantity != null)
                {
                    if (order != null)
                    {
                        OrderDetail orderDetail = new OrderDetail()
                        {
                            IDOrder = order.ID,
                            IDProductDetails = item.IDProductDetails,
                            Quantity = item.Quantity.Value,
                            ProductDetail = item.ProductDetail
                        };
                        _dbContext.OrderDetails.Add(orderDetail);

                        UpdateQuantity(orderDetail.IDProductDetails, orderDetail.Quantity);
                        _dbContext.Carts.RemoveRange(_dbContext.Carts.Where(x => x.Username == username));
                    }
                }
            }

            return Ok(_dbContext.SaveChanges());
        }

        [HttpGet]
        [Route("api/orderdetails/getorderdetails")]
        public IEnumerable<OrderDetail> GetOrderDetails(int idOrder)
        {
            List<OrderDetail> orderDetails = new List<OrderDetail>();
            var model = _dbContext.OrderDetails.Where(x => x.IDOrder == idOrder);
            foreach (var item in model)
            {
                OrderDetail orderDetail = new OrderDetail()
                {
                    ID = item.ID,
                    Quantity = item.Quantity,
                    IDOrder = item.IDOrder,
                    IDProductDetails = item.IDProductDetails,
                };
                orderDetails.Add(orderDetail);
            }

            return orderDetails;
        }

        [Route("api/orderdetails/summary")]
        public int getSummary(int idOrder)
        {
            var model = _dbContext.OrderDetails.Where(x => x.IDOrder == idOrder);
            int sum = 0;
            foreach (var item in model)
            {
                sum += item.Quantity * item.ProductDetail.Cost.Value;
            }

            return sum;
        }

        [HttpPost]
        [Route("api/orderdetails/getorderdetailsbyid")]
        public OrderDetail GetOrderDetailsById(int id)
        {
            var model = _dbContext.OrderDetails.Find(id);
            OrderDetail orderDetail = new OrderDetail()
            {
                ID = model.ID,
                Quantity = model.Quantity,
                IDOrder = model.IDOrder,
                IDProductDetails = model.IDProductDetails,
            };
            return orderDetail;
        }

        private void UpdateQuantity(int idProductDetails, int quantity)
        {
            var model = _dbContext.ProductDetails.Find(idProductDetails)?.Product;
            if (model != null)
            {
                model.Quantity -= quantity;
                _dbContext.Products.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }
        }
    }
}