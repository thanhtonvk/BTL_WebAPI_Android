namespace TQMallWeb.Models
{

    using System.Collections.Generic;
    using System.ComponentModel;
    using System.ComponentModel.DataAnnotations;

    public class ProductDetail
    {

        public int ID { get; set; }

        public int IDProduct { get; set; }
        [DisplayName("Tên chi tiết sản phẩm")]
        [Required]

        public string Name { get; set; }

        [DisplayName("Giá bán")]
        [Required]
        [Range(1,int.MaxValue)]

        public int? Cost { get; set; }

        [DisplayName("Hình ảnh")]
        [Required]
        public string Image { get; set; }

        public bool? Status { get; set; }



    }
}
