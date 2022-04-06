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
        public IQueryable<ReviewProduct> GetReview(int IDProduct)
        {
            return _dbContext.ReviewProducts.Where(x => x.IDProduct == IDProduct);
        }
    }
}