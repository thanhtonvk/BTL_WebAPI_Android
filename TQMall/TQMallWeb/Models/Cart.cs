namespace TQMallWeb.Models
{
    public partial class Cart
    {
        public int ID { get; set; }

        public string Username { get; set; }

        public int IDProductDetails { get; set; }

        public int? Quantity { get; set; }

       
    }
}