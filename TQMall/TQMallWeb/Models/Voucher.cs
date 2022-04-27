using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace TQMallWeb.Models
{
    public class Voucher
    {
        public string ID { get; set; }


        public string Username { get; set; }
        [Required]
        [Range(0.0,1.0)]
        [DisplayName("Giảm giá")]
        public double? Sale { get; set; }
        [Required]
        [Range(0,int.MaxValue)]
        [DisplayName("Số lượng")]
        public int? Quantity { get; set; }

        public bool? Status { get; set; }
    }
}