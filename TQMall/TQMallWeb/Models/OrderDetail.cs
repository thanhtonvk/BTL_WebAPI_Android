using DataAccessLayer;

namespace TQMallWeb.Models
{


    public class OrderDetail
    {
        public int ID { get; set; }

        public int IDOrder { get; set; }

        public int IDProductDetails { get; set; }

        public int Quantity { get; set; }

        public ProductDetail ProductDetail()
        {
            ProductDetailsDAL productDetailsDAL = new ProductDetailsDAL();
            return productDetailsDAL.GetProductDetailsById(IDProductDetails);
        }
        public Product Product()
        {
            ProductDAL productDAL = new ProductDAL();
            return productDAL.GetProductByID(ProductDetail().IDProduct);
        }

    
    }
}
