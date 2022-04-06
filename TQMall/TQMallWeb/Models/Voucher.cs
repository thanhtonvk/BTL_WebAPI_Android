namespace TQMallWeb.Models
{
    public class Voucher
    {
        public string ID { get; set; }


        public string Username { get; set; }

        public double? Sale { get; set; }

        public int? Quantity { get; set; }

        public bool? Status { get; set; }
    }
}