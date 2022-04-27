using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using Microsoft.Ajax.Utilities;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class OrderController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/order/getorderbuyer")]
        public IEnumerable<Order> GetOrderBuyer(string username)
        {
            var model = _dbContext.Orders.Where(x => x.Username == username);
            List<Order> orders = new List<Order>();
            foreach (var item in model)
            {
                Order order = new Order()
                {
                    ID = item.ID,
                    Address = item.Address,
                    Date = item.Date,
                    PhoneNumber = item.PhoneNumber,
                    Status = item.Status,
                    Username = item.Username
                };
                orders.Add(order);
            }

            return orders;
        }

        [HttpGet]
        [Route("api/order/getorderseller")]
        public IEnumerable<Order> GetOrderSeller(string username)
        {
            var ordersDetails = _dbContext.OrderDetails.Where(x => x.ProductDetail.Product.Account.Username == username)
                .AsEnumerable().DistinctBy(x => x.IDOrder);
            var oders = new List<Order>();
            foreach (var item in ordersDetails)
            {
                var od = _dbContext.Orders.Find(item.IDOrder);

                Order order = new Order()
                {
                    ID = od.ID,
                    Address = od.Address,
                    Date = od.Date,
                    PhoneNumber = od.PhoneNumber,
                    Status = od.Status,
                    Username = od.Username
                };
                oders.Add(order);
            }

            return oders;
        }

        [HttpGet]
        [Route("api/order/getorderbyid")]
        public Order GetOrderById(int id)
        {
            return _dbContext.Orders.Find(id);
        }

        [HttpPost]
        [Route("api/order/addorder")]
        public int AddOrder([FromBody] Order order)
        {
            _dbContext.Orders.Add(order);

            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/order/updateorder")]
        public int UpdateOrder([FromBody] Order order)
        {
            _dbContext.Orders.Add(order);
            _dbContext.Entry(order).State = EntityState.Modified;
            return _dbContext.SaveChanges();
        }
    }
}