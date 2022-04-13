using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using System.Web.ModelBinding;
using System.Web.UI.WebControls;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
    public class ReviewProductController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpPost]
        [Route("api/reviewproduct/addreivew")]
        public IHttpActionResult AddReview([FromBody] ReviewProduct reviewProduct)
        {
            _dbContext.ReviewProducts.Add(reviewProduct);
            return Ok(_dbContext.SaveChanges());
        }

        [HttpGet]
        [Route("api/reviewproduct/getreview")]
        public IEnumerable<ReviewProduct> GetReview(int IDProduct)
        {
            List<ReviewProduct> reviewProducts = new List<ReviewProduct>();
            var model = _dbContext.ReviewProducts.Where(x => x.IDProduct == IDProduct);
            foreach (var item in model)
            {
                ReviewProduct reviewProduct = new ReviewProduct()
                {
                    ID = item.ID,
                    IDProduct = item.IDProduct,
                    Image = item.Image,
                    Rate = item.Rate,
                    Review = item.Review,
                    Username = item.Username
                };
                reviewProducts.Add(reviewProduct);
            }

            return reviewProducts;
        }
    }
}